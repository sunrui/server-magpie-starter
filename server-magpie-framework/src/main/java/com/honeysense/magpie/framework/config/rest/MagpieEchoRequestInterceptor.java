package com.honeysense.magpie.framework.config.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
class MagpieEchoRequestInterceptor implements ClientHttpRequestInterceptor {
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] bytes, ClientHttpRequestExecution execution) throws IOException {
		String echo = traceRequest(request, bytes);

		ClientHttpResponse response = execution.execute(request, bytes);
		ClientHttpResponse responseCopy = new MagpieClientHttpResponse(response);

		echo += traceResponse(responseCopy);
		log.info(echo);

		return responseCopy;
	}

	/**
	 * 打印请求数据
	 *
	 * @param request 请求
	 * @param bytes   请求体
	 */
	private String traceRequest(HttpRequest request, byte[] bytes) {
		HttpMethod method = request.getMethod();
		URI uri = request.getURI();
		String path = uri.getPath();
		String query = uri.getQuery();
//		String body = new String(bytes, StandardCharsets.UTF_8);
		HttpHeaders headers = request.getHeaders();
		Map<String, String> headerMap = headers.toSingleValueMap();
		StringBuilder headerStringBuilder = new StringBuilder();
		for (String key : headerMap.keySet()) {
			headerStringBuilder.append("\t").append(key).append(": ").append(headerMap.get(key)).append("\n");
		}

		return String.format("\n\n%s %s%s\n%s", method, path, query != null ? "?" + query : "", headerStringBuilder.toString());
	}

	/**
	 * 打印响应结果
	 *
	 * @param response 响应结果
	 * @throws IOException io
	 */
	private String traceResponse(ClientHttpResponse response) throws IOException {
		StringBuilder inputStringBuilder = new StringBuilder();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), StandardCharsets.UTF_8));
		String line = bufferedReader.readLine();
		while (line != null) {
			inputStringBuilder.append(line);
			line = bufferedReader.readLine();
		}

		return String.format("RESPONSE %s\n", response.getStatusCode()) +
				String.format("\t%s\n", inputStringBuilder.toString());
	}
}

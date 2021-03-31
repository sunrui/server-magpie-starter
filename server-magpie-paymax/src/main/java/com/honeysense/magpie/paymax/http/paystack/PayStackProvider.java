package com.honeysense.magpie.paymax.http.paystack;

import com.honeysense.magpie.framework.utils.convert.MagpieJsonConvert;
import com.honeysense.magpie.paymax.http.paystack.initialize.PostPayStackInitializeReq;
import com.honeysense.magpie.paymax.http.paystack.initialize.PostPayStackInitializeRes;
import com.honeysense.magpie.paymax.http.paystack.verify.PostPayStackVerifyReq;
import com.honeysense.magpie.paymax.http.paystack.verify.PostPayStackVerifyRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PayStackProvider {
    @Autowired
    private RestTemplate restTemplate;

    public PayStackRes<PostPayStackInitializeRes> postInitialize(String secretKey, PostPayStackInitializeReq req) {
        String url = "https://api.paystack.co/transaction/initialize";

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 11_1_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
        headers.set("Authorization", "Bearer " + secretKey);

        try {
            ResponseEntity<PayStackRes<PostPayStackInitializeRes>> responseEntity = restTemplate.exchange(url, HttpMethod.POST,
                    new HttpEntity<>(MagpieJsonConvert.toJson(req, true), headers), new ParameterizedTypeReference<>() {
                    });
            return responseEntity.getBody();
        } catch (Exception e) {
            e.printStackTrace();

            PayStackRes<PostPayStackInitializeRes> payStackRes = new PayStackRes<>();
            payStackRes.setStatus("exception");
      return payStackRes;
        }
    }

    public PayStackRes<PostPayStackVerifyRes> postVerify(String secretKey, PostPayStackVerifyReq req) {
        String url = "https://api.paystack.co/transaction/verify/" + req.getReference();

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 11_1_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
        headers.set("Authorization", "Bearer " + secretKey);

        try {
            ResponseEntity<PayStackRes<PostPayStackVerifyRes>> responseEntity = restTemplate.exchange(url, HttpMethod.POST,
                    new HttpEntity<>(MagpieJsonConvert.toJson(req, true), headers), new ParameterizedTypeReference<>() {
                    });
            return responseEntity.getBody();
        } catch (Exception e) {
            e.printStackTrace();

            PayStackRes<PostPayStackVerifyRes> payStackRes = new PayStackRes<>();
            payStackRes.setStatus("exception");
            return payStackRes;
        }
    }
}

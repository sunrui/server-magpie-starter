package com.honeysense.magpie.framework.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.honeysense.magpie.framework.entity.MagpieException;
import com.honeysense.magpie.framework.entity.MagpieToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MagpieJwt {
    private final String issuer = "token";
    private final JWTVerifier verifier;
    private final String secret;

    public MagpieJwt() {
        secret = new MagpieResource().getResourceValue("magpie.properties", "jwt.secret");
        verifier = JWT.require(Algorithm.HMAC256(secret))
                .withIssuer(issuer)
                .build();
    }

    public String sign(MagpieToken magpieToken) {
        MagpieValidator.object(magpieToken);

        JWTCreator.Builder builder = JWT.create().withIssuer(issuer)
                .withIssuedAt(magpieToken.getCreatedAt())
                .withExpiresAt(magpieToken.getExpiredAt())
                .withJWTId(magpieToken.getUserId() + "");

        builder.withClaim("type", magpieToken.getType().name());
        builder.withClaim("ip", magpieToken.getIp());

        return builder.sign(Algorithm.HMAC256(secret));
    }

    public MagpieToken unSign(String token) {
        if (!MagpieValidator.string(token)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "token");
        }

        DecodedJWT decodedJWT;

        try {
            decodedJWT = verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new MagpieException(MagpieException.Type.PARSE_FAILED, e.getMessage());
        }

        String userId = decodedJWT.getId();
        if (!MagpieValidator.longId(Long.valueOf(userId))) {
            throw new MagpieException(MagpieException.Type.PARSE_FAILED, "userId");
        }

        MagpieToken magpieToken = new MagpieToken();
        magpieToken.setUserId(Long.valueOf(userId));

        if (!decodedJWT.getClaim("type").isNull()) {
            String type = decodedJWT.getClaim("type").asString();
            magpieToken.setType(MagpieToken.MagpieTokenType.valueOf(type));
        } else {
            throw new MagpieException(MagpieException.Type.PARSE_FAILED, "type");
        }

        if (!decodedJWT.getClaim("ip").isNull()) {
            String ip = decodedJWT.getClaim("ip").asString();
            magpieToken.setIp(ip);
        } else {
            throw new MagpieException(MagpieException.Type.PARSE_FAILED, "ip");
        }

        magpieToken.setCreatedAt(decodedJWT.getIssuedAt());
        magpieToken.setExpiredAt(decodedJWT.getExpiresAt());

        return magpieToken;
    }
}

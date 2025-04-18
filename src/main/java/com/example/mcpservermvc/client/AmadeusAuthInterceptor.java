package com.example.mcpservermvc.client;

import com.example.mcpservermvc.tool.dto.AmadeusTokenResponse;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AmadeusAuthInterceptor implements RequestInterceptor {

    private final AmadeusAuthClient auth;
    @Value("${amadeus.client-id}")
    String id;
    @Value("${amadeus.client-secret}")
    String secret;

    private AmadeusTokenResponse token;
    private Instant expiry = Instant.EPOCH;

    @Override
    public void apply(RequestTemplate tpl) {
        if (Instant.now().isAfter(expiry.minusSeconds(30))) {
            token = auth.token(new LinkedMultiValueMap<>(Map.of(
                    "grant_type", List.of("client_credentials"),
                    "client_id", List.of(id),
                    "client_secret", List.of(secret)
            )));
            expiry = Instant.now().plusSeconds(token.expiresIn());
        }
        tpl.header(HttpHeaders.AUTHORIZATION, "Bearer " + token.accessToken());
    }
}


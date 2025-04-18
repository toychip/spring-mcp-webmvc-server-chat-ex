package com.example.mcpservermvc.client;

import com.example.mcpservermvc.tool.dto.AmadeusTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "amadeusAuth", url = "https://test.api.amadeus.com")
public interface AmadeusAuthClient {

    @PostMapping(value = "/v1/security/oauth2/token",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    AmadeusTokenResponse token(MultiValueMap<String, String> body);
}

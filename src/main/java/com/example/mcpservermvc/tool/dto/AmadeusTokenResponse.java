package com.example.mcpservermvc.tool.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AmadeusTokenResponse(
        @JsonProperty("access_token") String accessToken,
        @JsonProperty("expires_in")   int    expiresIn,
        @JsonProperty("token_type")   String tokenType,
        String scope
) {}


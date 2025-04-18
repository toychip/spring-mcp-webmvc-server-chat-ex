package com.example.mcpservermvc.config;

import feign.RequestInterceptor;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.github.resilience4j.ratelimiter.RateLimiter;

@Configuration
public class RetryConfig {

    @Bean
    public RequestInterceptor rlInterceptor(RateLimiterRegistry rlr, RetryRegistry rr) {
        RateLimiter rl = rlr.rateLimiter("amadeus");
        Retry retry   = rr.retry("amadeus");

        return tpl -> Retry.decorateRunnable(retry,
                RateLimiter.decorateRunnable(rl, () -> {})).run(); // 빈 Runnable → 호출 1회
    }

    @Bean
    public ErrorDecoder tooManyRetryDecoder() {
        return (methodKey, response) -> {
            if (response.status() == 429) {
                // ① 6‑파라미터 + Long retryAfter  (null 캐스팅으로 모호성 제거)
                return new RetryableException(
                        response.status(),
                        "rate‑limit 429",
                        response.request().httpMethod(),
                        null,                 // cause
                        (Long) null,          // retryAfter → Long 캐스트
                        response.request());
            }
            return new ErrorDecoder.Default().decode(methodKey, response);
        };
    }


}

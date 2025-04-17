package com.example.mcpservermvc.config;

import com.example.mcpservermvc.tool.AuthorRepository;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToolCallbackProviderConfig {

    @Bean
    public ToolCallbackProvider dependencyExtractorProvider(AuthorRepository authorRepository) {
        return MethodToolCallbackProvider.builder().toolObjects(authorRepository).build();
    }

}

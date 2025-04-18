package com.example.mcpservermvc.config;

import com.example.mcpservermvc.tool.AuthorRepository;
import com.example.mcpservermvc.tool.FlightSearchTool;
import com.example.mcpservermvc.tool.FutureWeatherTool;
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

    @Bean
    public ToolCallbackProvider getFutureWeatherProvider(FutureWeatherTool futureWeatherTool) {
        return MethodToolCallbackProvider.builder().toolObjects(futureWeatherTool).build();
    }

    @Bean
    public ToolCallbackProvider getFlightSearchProvider(FlightSearchTool flightSearchTool) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(flightSearchTool)
                .build();
    }
}

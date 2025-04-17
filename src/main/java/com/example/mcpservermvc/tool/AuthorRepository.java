package com.example.mcpservermvc.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class AuthorRepository {
    @Tool(name = "getAuthorDetail", description = "Get author details by article title")
    public Author getAuthorByArticleTitle(String articleTitle) {
        System.out.println("articleTitle = " + articleTitle);

        return new Author("John Doe", "john.doe@example.com");
    }
}

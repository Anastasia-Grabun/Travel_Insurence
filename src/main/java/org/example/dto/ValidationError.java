package org.example.dto;

public record ValidationError (
        String errorCode,
        String description
) {}

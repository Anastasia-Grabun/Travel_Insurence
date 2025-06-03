package org.example.travel.insurance.dto;

public record ValidationError (
        String errorCode,
        String description
) {}

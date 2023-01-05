package com.aquavitta.aquavitta.dtos;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RiverDto {
    @NotBlank
    private String name;
    @NotNull
    private Long latitude;
    @NotNull
    private Long longitude;
}

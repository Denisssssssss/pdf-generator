package com.example.pdfgeneratorclient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class TokenDto implements Serializable {

    private final String token;
}

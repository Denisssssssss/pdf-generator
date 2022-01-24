package com.example.pdfgeneratorclient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PdfParamsDto {

    private final String firstName;
    private final String lastName;
    private final String type;
}

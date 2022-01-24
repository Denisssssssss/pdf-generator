package com.example.pdfgeneratorserver;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.UUID;

public class FirstTypeGenerator {

    public static void generate(String content) throws DocumentException, FileNotFoundException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(content + ".pdf"));

        document.open();
        Font font = FontFactory.getFont(FontFactory.TIMES, 30, BaseColor.BLACK);
        Chunk chunk = new Chunk(content, font);

        document.add(chunk);
        document.close();
    }
}

package com.villasalud.util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
   /* @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(p.getValueAsString(), formatter).atStartOfDay();
    }*/
   private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_DATE_TIME;
    private static final DateTimeFormatter D_M_Y = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String dateStr = p.getText().trim();

        try {
            // First try the ISO format
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateStr, ISO);
            return zonedDateTime.toLocalDateTime();
        } catch (DateTimeParseException e1) {
            try {
                // If that fails, try the "14-08-2023" format
                LocalDate localDate = LocalDate.parse(dateStr, D_M_Y);
                return localDate.atStartOfDay();
            } catch (DateTimeParseException e2) {
                throw new IOException("Failed to deserialize date", e2);
            }
        }
    }

}
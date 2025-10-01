package com.miu.lab2a;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {
    @Override
    public void write(JsonWriter out, LocalDate value) throws IOException {
        out.value(value != null ? value.toString() : null); // Store as ISO string
    }

    @Override
    public LocalDate read(JsonReader in) throws IOException {
        String str = in.nextString();
        return str != null ? LocalDate.parse(str) : null;
    }
}

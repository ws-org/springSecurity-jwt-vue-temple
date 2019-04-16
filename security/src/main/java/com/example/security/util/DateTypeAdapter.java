package com.example.security.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Date;

public class DateTypeAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {
    @Override
    public JsonElement serialize(Date src, Type arg1, JsonSerializationContext arg2) {
        return src == null ? null : new JsonPrimitive(src.getTime());
    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return json == null ? null : new Date(json.getAsLong());
    }
}

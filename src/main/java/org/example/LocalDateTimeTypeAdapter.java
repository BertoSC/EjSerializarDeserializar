package org.example;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeTypeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
    private static final DateTimeFormatter formato
                = DateTimeFormatter.ofPattern("d:MM:uuuu HH:mm:ss");
    @Override
    public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        /*JsonObject jo = new JsonObject();
        jo.addProperty("fecha", localDateTime.format(formato));
        return jo;*/
        return new JsonPrimitive(localDateTime.format(formato));  // así lo puso pepe

    }

    @Override
    public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        String fecha = jsonElement.getAsString();

        return LocalDateTime.parse(fecha, formato);
    }
}
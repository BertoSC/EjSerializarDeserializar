package org.example;

import com.google.gson.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;

public class MainPersona {
    public static void main(String[] args) throws IOException {
    Persona prueba1 = new Persona ("paco", "35");

    Gson gs = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Persona.class, new JsonSerializer<Persona>() {
                @Override
                public JsonElement serialize(Persona persona, Type type, JsonSerializationContext jsonSerializationContext) {
                    JsonObject jo = new JsonObject();
                    jo.addProperty("name", persona.getNombre());
                    jo.addProperty("age", persona.getEdad());
                    return jo;
                }


            })
            .registerTypeAdapter(Persona.class, new JsonDeserializer<Persona>() {
                @Override
                public Persona deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                    JsonObject jo = jsonElement.getAsJsonObject();
                    String n = jo.get("name").getAsString();
                    String a = jo.get("age").getAsString();
                    return new Persona (n, a);
                                   }
            })

            .create();

        System.out.println(gs.toJson(prueba1));
        try(var in = Files.newBufferedWriter(Path.of("prueba.txt"))){
            in.write(gs.toJson(prueba1));

        }
        Path p = Path.of("prueba.txt");
        try (var out= Files.newBufferedReader(Path.of("prueba.txt"))){
            String s = Files.readString(p);
            System.out.println(gs.fromJson(s, Persona.class));
        }



    }
}
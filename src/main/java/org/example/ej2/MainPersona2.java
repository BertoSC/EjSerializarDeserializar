package org.example.ej2;

import com.google.gson.*;
import org.example.Direccion;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;

public class MainPersona2 {
    public static void main(String[] args) throws IOException {
        Persona prueba2 = new Persona ("paco", "35", new Direccion("callejuela", "Santiago"));

        Gson gs = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Persona.class, new JsonSerializer<Persona>() {
                    @Override
                    public JsonElement serialize(Persona persona, Type type, JsonSerializationContext jsonSerializationContext) {
                        JsonObject jo = new JsonObject();
                        String dir = persona.getDireccion().getCalle()+","+persona.getDireccion().getCiudad();
                        jo.addProperty("name", persona.getNombre());
                        jo.addProperty("age", persona.getEdad());
                        jo.addProperty("adress", dir);
                        return jo;
                    }
                })

                .registerTypeAdapter(Persona.class, new JsonDeserializer<Persona>() {
                    @Override
                    public Persona deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                        JsonObject jo = jsonElement.getAsJsonObject();
                        String n = jo.get("name").getAsString();
                        String a = jo.get("age").getAsString();
                        String [] dir = jo.get("adress").getAsString().split(",");
                        Direccion d = new Direccion(dir[0], dir[1]);
                        return new Persona (n, a, d);
                    }
                })

                .create();

        System.out.println(gs.toJson(prueba2));
        try(var in = Files.newBufferedWriter(Path.of("prueba2.txt"))){
            in.write(gs.toJson(prueba2));

        }

        Path p = Path.of("prueba2.txt");
        try (var out= Files.newBufferedReader(Path.of("prueba2.txt"))){
            String s = Files.readString(p);
            System.out.println(gs.fromJson(s, Persona.class));
        }

    }
}
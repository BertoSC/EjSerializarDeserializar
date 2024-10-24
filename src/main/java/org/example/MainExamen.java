package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainExamen {
    public static void main(String[] args) {


    List<String> nombres = new ArrayList<>();
        nombres.add("Luz Pozo");
        nombres.add("Xela Arias");
        nombres.add("Alejandra Pizarnik");
        nombres.add("Doris Lessing");
        nombres.add("Susana March");

    LocalDateTime fechaHora = LocalDateTime.of(2023, 11, 12, 9, 45);

    //se crea el objeto Examen inicial
    Examen ad = new Examen("Acceso a Datos", fechaHora, nombres);

    LocalDateTimeTypeAdapter l = new LocalDateTimeTypeAdapter();
    Gson gs = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, l)
            .create();

        System.out.println(gs.toJson(ad));
    }
}

   /*     .registerTypeAdapter(Examen.class, new JsonSerializer<Examen>() {
    @Override
    public JsonElement serialize(Examen examen, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jo = new JsonObject();
        jo.addProperty("materia", examen.getMateria());

        JsonArray ja = new JsonArray();
        for (String s : examen.getParticipantes()) {
            JsonPrimitive jp = new JsonPrimitive(s);
            ja.add(jp);
        }
        jo.add("participantes", ja);
        return jo;
    }*(

    */



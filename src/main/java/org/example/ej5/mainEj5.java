package org.example.ej5;

import com.google.gson.*;
import org.example.Direccion;
import org.example.ej3.Persona;


import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class mainEj5 {
    public static void main(String[] args) {
        Persona5 p5 = new Persona5("paco", "35", new Direccion("callejuela", "Santiago"));

        List<Persona5> nombres = new ArrayList<>();

        List<Persona5> p = new ArrayList<>();
        nombres.add(new Persona5("Luz Pozo", "45", new Direccion("callejuela", "Santiago")));
        nombres.add(new Persona5("Rosalía", "5", new Direccion("avenida", "Porto")));
        nombres.add(new Persona5("A tope", "25", new Direccion("oxight", "Xandar")));
        nombres.add(new Persona5("Tú si que sabes", "65", new Direccion("Viva", "Mexico")));
        nombres.add(new Persona5("Dalo todo", "8", new Direccion("A ver de esta", "Si va")));

        String[] hobbies = {"Nadar", "viciar", "fumar", "comer", "mandanga", "el delicioso"};
        p5.setLista(nombres);
        p5.setHobbies(hobbies);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Persona5.class, new JsonSerializer<Persona5>() {
                    @Override
                    public JsonElement serialize(Persona5 persona, Type type, JsonSerializationContext jsonSerializationContext) {
                        JsonObject jo = new JsonObject();
                        String dir = persona.getDireccion().getCalle() + "," + persona.getDireccion().getCiudad();
                        jo.addProperty("name", persona.getNombre());
                        jo.addProperty("age", persona.getEdad());
                        jo.addProperty("adress", dir);
                        JsonArray jA = new JsonArray();
                        for (Persona5 p : persona.getLista()) {
                            JsonObject temp = new JsonObject();
                            temp.addProperty("name", p.getNombre());
                            temp.addProperty("age", p.getEdad());
                            String dirA = p.getDireccion().getCalle() + "," + p.getDireccion().getCiudad();
                            temp.addProperty("adress", dirA);
                            jA.add(temp);
                        }
                        jo.add("friends", jA);
                        JsonArray jB = new JsonArray();
                        StringBuilder sb = new StringBuilder();
                        String[] temp = persona.getHobbies();
                        for (String s : temp) {
                            sb.append(s).append("-");
                        }
                        int ind = sb.lastIndexOf("-");
                        sb.deleteCharAt(ind);
                        jo.addProperty("hobbies", sb.toString());
                        return jo;


                    }
                })
                .registerTypeAdapter(Persona5.class, new JsonDeserializer<Persona5>() {
                    @Override
                    public Persona5 deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                        JsonObject jo = jsonElement.getAsJsonObject();
                        String n = jo.get("name").getAsString();
                        String a = jo.get("age").getAsString();
                        String [] dir = jo.get("adress").getAsString().split(",");
                        Direccion d = new Direccion(dir[0], dir[1]);
                        List<Persona5> amigos = new ArrayList<>();
                        JsonArray ar = jo.get("friends").getAsJsonArray();
                        for (JsonElement joA:ar){
                            JsonObject joSub= joA.getAsJsonObject();
                            String nom = joSub.get("name").getAsString();
                            String ed= joSub.get("age").getAsString();
                            String []di= joSub.get("adress").getAsString().split(",");
                            Direccion de = new Direccion(di[0], di[1]);
                            Persona5 temp = new Persona5(nom, ed, de);
                            amigos.add(temp);
                        }
                        String [] temp = jo.get("hobbies").getAsString().split("-");
                        Persona5 fin = new Persona5(n, a, d);
                        fin.setLista(amigos);
                        fin.setHobbies(temp);
                        return fin;
                    }
                }).create();


        System.out.println(gson.toJson(p5));
        try(var in = Files.newBufferedWriter(Path.of("prueba5.txt"))){
            in.write(gson.toJson(p5));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Path pat = Path.of("prueba5.txt");
        try (var out= Files.newBufferedReader(Path.of("prueba5.txt"))){
            String s = Files.readString(pat);
            Persona5 prueba= gson.fromJson(s, Persona5.class);
            for (String stri: prueba.getHobbies()){
                System.out.println(stri);
            }

                    } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

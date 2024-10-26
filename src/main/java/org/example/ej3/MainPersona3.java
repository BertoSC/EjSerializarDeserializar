package org.example.ej3;
import com.google.gson.*;
import org.example.Direccion;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MainPersona3 {
    public static void main(String[] args) throws IOException {
        List<Persona> nombres = new ArrayList<>();

        List <Persona> p =new ArrayList<>();
        nombres.add(new Persona("Luz Pozo", "35", new Direccion("callejuela", "Santiago")));
        nombres.add(new Persona("Rosalía", "35", new Direccion("callejuela", "Santiago")));
        nombres.add(new Persona("A tope", "35", new Direccion("callejuela", "Santiago")));
        nombres.add(new Persona("Tú si que sabes", "35", new Direccion("callejuela", "Santiago")));
        nombres.add(new Persona("Dalo todo", "35", new Direccion("callejuela", "Santiago")));
        Persona prueba3= new Persona("paco", "35", new Direccion("callejuela", "Santiago"));

        prueba3.setLista(nombres);

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
                        JsonArray jA = new JsonArray();
                        for (Persona p: persona.getLista()){
                            JsonObject temp = new JsonObject();
                            temp.addProperty("name", p.getNombre());
                            temp.addProperty("age", p.getEdad());
                            String dirA = p.getDireccion().getCalle()+","+p.getDireccion().getCiudad();
                            temp.addProperty("adress", dirA);

                            jA.add(temp);
                        }
                        jo.add("friends", jA);

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
                        List<Persona> amigos = new ArrayList<Persona>();
                        JsonArray ar = jo.get("friends").getAsJsonArray();
                        for (JsonElement joA:ar){
                            JsonObject joSub= joA.getAsJsonObject();
                            String nom = joSub.get("name").getAsString();
                            String ed= joSub.get("age").getAsString();
                            String []di= joSub.get("adress").getAsString().split(",");
                            Direccion de = new Direccion(di[0], di[1]);
                            Persona temp = new Persona(nom, ed, de);
                            amigos.add(temp);
                        }

                        Persona fin = new Persona(n, a, d);
                        fin.setLista(amigos);
                        return fin;
                    }
                })

                .create();

        System.out.println(gs.toJson(3));
        try(var in = Files.newBufferedWriter(Path.of("prueba3.txt"))){
            in.write(gs.toJson(prueba3));

        }

        Path pat = Path.of("prueba3.txt");
        try (var out= Files.newBufferedReader(Path.of("prueba3.txt"))){
            String s = Files.readString(pat);
            System.out.println(gs.fromJson(s, Persona.class));
        }

    }
}

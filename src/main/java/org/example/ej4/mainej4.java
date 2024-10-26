package org.example.ej4;

import com.google.gson.*;
import org.example.ej3.Persona;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;

public class mainej4 {
    public static void main(String[] args) {

        Producto p1 = new Producto("champú", 106.576);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Producto.class, new JsonSerializer<Producto>() {
                    @Override
                    public JsonElement serialize(Producto producto, Type type, JsonSerializationContext jsonSerializationContext) {
                        JsonObject jo = new JsonObject();
                        jo.addProperty("name", producto.getNombre());
                        String p = producto.getPrecio().toString();
                        //String formato = String.format("%.2f", producto.getPrecio());
                        String cadenaF = p.substring(0, p.indexOf(".")+1)+p.substring(p.indexOf(".")+1, p.indexOf(".")+3);
                        jo.addProperty("precio", cadenaF);
                        //jo.addProperty("precio", formato);
                        return jo;

                    }
                })
                .registerTypeAdapter(Producto.class, new JsonDeserializer<Producto>() {
                    @Override
                    public Producto deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                        JsonObject jo = jsonElement.getAsJsonObject();
                        String nombre = jo.get("name").getAsString();
                        Double precio = jo.get("precio").getAsDouble();
                        Producto p = new Producto(nombre, precio);
                        return p;

                    }
                })
                .create();

        System.out.println(gson.toJson(p1));
        try(var in = Files.newBufferedWriter(Path.of("prueba4.txt"))){
            in.write(gson.toJson(p1));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Path pat = Path.of("prueba4.txt");
        String linea;
        StringBuilder text = new StringBuilder();
        try (var out = Files.newBufferedReader(pat)){
            while ((linea = out.readLine())!=null){

            text.append(linea);

            }
            String js = text.toString();

            Producto p2 = gson.fromJson(js, Producto.class);
            System.out.println(p2);



        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
}

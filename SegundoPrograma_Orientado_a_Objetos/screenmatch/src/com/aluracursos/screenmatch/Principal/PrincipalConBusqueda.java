package com.aluracursos.screenmatch.Principal;

import java.io.FileWriter;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.aluracursos.screenmatch.exceptions.ErrorEnConversionDeDuracionException;
import com.aluracursos.screenmatch.modelos.Titulo;
import com.aluracursos.screenmatch.modelos.TituloOmdb;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.FieldNamingPolicy;

public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Este programa permite buscar una película en la API de OMDB
        // Se solicita al usuario que ingrese el nombre de la película que desea buscar
        Scanner lectura = new Scanner(System.in);
        List<Titulo> titulos = new ArrayList<>();
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();

        while (true){
            // Se solicita al usuario que ingrese el nombre de la película
            // Si el usuario ingresa "salir", el programa termina
            System.out.println("Ingrese el nombre de la película que desea buscar: ");
            var busqueda = lectura.nextLine();

            if (busqueda.equalsIgnoreCase("salir")){
                break;
            }
            // Se construye la URL de la API de OMDB con el nombre de la película y una clave de API
            String direccion = "https://www.omdbapi.com/?t=" + busqueda.replace(" ", "+") + "&apikey=66674b15";

            // Se crea un cliente HTTP y se envía una solicitud GET a la URL construida
            try{
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                // Se crea una variable para almacenar la respuesta de la API en formato JSON
                String json = response.body();
                // Se imprime la respuesta de la API en la consola
                System.out.println(json);


                gson.fromJson(json, Titulo.class);
                TituloOmdb miTituloOmdb = gson.fromJson(json, TituloOmdb.class);
                System.out.println(miTituloOmdb);


                Titulo miTitulo = new Titulo(miTituloOmdb);
                System.out.println("Titulo ya convertido: " + miTitulo);


                titulos.add(miTitulo);

            }catch (NumberFormatException e){
                System.out.println("Ocurrió un error: ");
                System.out.println(e.getMessage());
            }catch (IllegalArgumentException e){
                System.out.println("Error en la URI, verifique la dirección: ");
            }catch (ErrorEnConversionDeDuracionException e){
                System.out.println(e.getMessage());
            }

        }
        System.out.println(titulos);

        FileWriter escritura = new FileWriter("titulos.json");
        escritura.write(gson.toJson(titulos));
        escritura.close();
        System.out.println("Finalizó la ejecución del programa.");
    }
}

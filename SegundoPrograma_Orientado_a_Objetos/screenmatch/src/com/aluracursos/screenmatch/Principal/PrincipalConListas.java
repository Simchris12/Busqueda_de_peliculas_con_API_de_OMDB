package com.aluracursos.screenmatch.Principal;

import com.aluracursos.screenmatch.modelos.Pelicula;
import com.aluracursos.screenmatch.modelos.Serie;
import com.aluracursos.screenmatch.modelos.Titulo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PrincipalConListas {
    public static void main(String[] args){

        Pelicula miPelicula = new Pelicula("Transformers", 2007);
        miPelicula.evalua(9);
        Pelicula otraPelicula = new Pelicula("Matrix", 1999);
        miPelicula.evalua(6);
        var peliculaDeChris = new Pelicula("Forest Gump", 1994);
        miPelicula.evalua(10);
        Serie casaDragon = new Serie("La casa del dragón", 2022);


        ArrayList<Titulo> lista = new ArrayList<>();
        lista.add(peliculaDeChris);
        lista.add(miPelicula);
        lista.add(otraPelicula);
        lista.add(casaDragon);


            ArrayList<String> listaDeLosArtistas = new ArrayList<>();
            listaDeLosArtistas.add("Penélope Cruz");
            listaDeLosArtistas.add("Antonio Banderas");
            listaDeLosArtistas.add("Ricardo Darín");
            System.out.println("Lista de artistas: " + listaDeLosArtistas);

            Collections.sort(listaDeLosArtistas);
            System.out.println("Lista de artistas ordenada: " + listaDeLosArtistas);

            Collections.sort(lista);
            System.out.println("Lista de títulos ordenada: " + lista);

            lista.sort(Comparator.comparing(Titulo::getFechaDeLanzamiento));
            System.out.println("Lista ordenada por fecha: " + lista);
    }
}

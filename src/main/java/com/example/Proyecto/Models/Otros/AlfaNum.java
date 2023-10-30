package com.example.Proyecto.Models.Otros;

import java.util.Random;

public class AlfaNum {
    
     private static final String CARACTERES = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int LONGITUD = 5;
    private static Random random = new Random();

    public static String generarAlfanumerico() {
        StringBuilder sb = new StringBuilder(LONGITUD);
        for (int i = 0; i < LONGITUD; i++) {
            int indice = random.nextInt(CARACTERES.length());
            char caracter = CARACTERES.charAt(indice);
            sb.append(caracter);
        }
        return sb.toString();
    }

}

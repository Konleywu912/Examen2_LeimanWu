/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examen2_leimanwu.baseDeDatos;

/**
 *
 * @author leymanwu
 */
import java.io.*;
import java.net.*;

public class ClienteMain {

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 5000); 
                ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream()); 
                ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream())) {

            System.out.println("Conectado al servidor");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

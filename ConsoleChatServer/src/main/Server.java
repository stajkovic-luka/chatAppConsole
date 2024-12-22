
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lukas
 */
public class Server {
    public static void main(String[] args) {
        
        Socket socket = null;
        ServerSocket serverSocket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        
        while(true){
        try{
           
           System.out.println("Server pokrenut...(Korisnik 2)");
           serverSocket = new ServerSocket(9005);
           socket = serverSocket.accept();
            System.out.println("Konekcija uspostavljena...");
            System.out.println("-----");
           inputStreamReader = new InputStreamReader(socket.getInputStream());
           outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
           
           bufferedReader = new BufferedReader(inputStreamReader);
           bufferedWriter = new BufferedWriter(outputStreamWriter);
           
           
            while (true) {                
                String msgFromClient = bufferedReader.readLine();
                System.out.println("Client: " + msgFromClient);
                
                bufferedWriter.write("Message received");
                bufferedWriter.newLine();
                bufferedWriter.flush();
                
                if (msgFromClient.equalsIgnoreCase("KRAJ")) {
                    break;
                }
                
            }
            
            socket.close();
            bufferedReader.close();
            bufferedWriter.close();
            inputStreamReader.close();
            outputStreamWriter.close();
            
            
           
           
            
        }catch(IOException ex){
            ex.printStackTrace();
        }
        }
        
    }
 
   
}

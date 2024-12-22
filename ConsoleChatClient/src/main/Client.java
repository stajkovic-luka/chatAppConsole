/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lukas
 */
public class Client {
    public static void main(String[] args){
        
        Socket soc = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        
        try {
            System.out.println("Klijent pokrenut... (Korisnik 1)");
            System.out.println("-----");
            soc = new Socket("localhost", 9005);
            
            inputStreamReader = new InputStreamReader(soc.getInputStream());
            outputStreamWriter = new OutputStreamWriter(soc.getOutputStream());
            
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            
            Scanner scanner = new Scanner(System.in);
            
            while(true){
                String msgForSending = scanner.nextLine();
                bufferedWriter.write(msgForSending);
                bufferedWriter.newLine(); // \n karakter na kraj poruke
                bufferedWriter.flush();
                
                String receivedMsg = bufferedReader.readLine();
                System.out.println("Server: "+receivedMsg);
                
                //Izlazak iz programa unosom KRAJ
                if (msgForSending.equalsIgnoreCase("KRAJ")) {
                    break;
                }
                
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally{
            try{
                if (soc != null) {
                    soc.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
            } catch(IOException ex){
                ex.printStackTrace();
            }
        }
        
    }
}

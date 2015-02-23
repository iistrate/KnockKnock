/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knockknockclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Ioan
 */
public class KnockKnockClient {
    private int mPORT = 4999;
        
    public void start() {
        try {
            Socket s = new Socket("127.0.0.1", mPORT);
            
            //read and write to/from server
            InputStream instream = s.getInputStream();
            OutputStream outstream = s.getOutputStream();
            Scanner in = new Scanner(instream);
            PrintWriter out = new PrintWriter(outstream);   
            
            
            String response = in.nextLine();
            System.out.println("Receiving: " + response); 
            
            String command = "Who there \n";
            System.out.print("Sending: " + command);
            out.print(command);
            out.flush();
     
            response = in.nextLine();
            System.out.println("Receiving: " + response);    
            
            command = response + " who \n";
            System.out.print("Sending: " + command);
            out.print(command);
            out.flush();     
            
            response = in.nextLine();
            System.out.println("Receiving: " + response);    
            
            command = "QUIT \n";
            System.out.print("Sending: " + command);
            out.print(command);
            out.flush();
            
            s.close();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        KnockKnockClient client = new KnockKnockClient();
        client.start();
    }
}

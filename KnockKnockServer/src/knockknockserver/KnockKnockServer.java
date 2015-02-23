/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knockknockserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Ioan
 */
public class KnockKnockServer {
    private int mPORT = 4999;
    private Joke mJoke = new Joke();
    private boolean running = true;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        KnockKnockServer KnockKnock = new KnockKnockServer();
        KnockKnock.start();
    }

    private void start() {
        try {
            ServerSocket mServerSocket = new ServerSocket(4999);
            System.out.println("Server started.");
            while(running) {                
                //listening for clients
                Socket sock = mServerSocket.accept();
                System.out.println("Client connected.");

                Scanner in = new Scanner(sock.getInputStream());
                PrintWriter out = new PrintWriter(sock.getOutputStream());
                
                //choose joke
                mJoke.chooseJoke();
                //has a joke, initial display joke
                String joke = mJoke.getJoke();
                out.println(joke);
                out.flush();
                
                //while we have stuff to read
                while(in.hasNext()) {
                    String clientResponse = in.next();
                    if (clientResponse.contains("Who")) {
                        mJoke.advanceJoke();
                        joke = mJoke.getJoke();
                        out.println(joke);
                        out.flush();
                    }
                    if (clientResponse.contains(mJoke.getKeyword())) {
                        mJoke.advanceJoke();
                        joke = mJoke.getJoke();
                        out.println(joke);
                        out.flush();
                    }
                    if (clientResponse.equals("QUIT")) {
                        mJoke.resetJoke();
                        break;
                    }
                }
                
                in.close();
                out.close();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

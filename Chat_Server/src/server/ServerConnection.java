/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author H
 */
public class ServerConnection implements Runnable{
    ArrayList clientStreams;
    ArrayList<String> clientAccounts;
    public void ServerConnection(ArrayList clientStreams, ArrayList<String> clientAccounts){
    this.clientStreams = clientStreams;
    this.clientAccounts = clientAccounts;
    }
    @Override
    public void run() {
            clientStreams = new ArrayList();
            clientAccounts = new ArrayList();  

            try 
            {
                ServerSocket serverSock = new ServerSocket(2222);

                while (true) 
                {
                    Socket clientSock = serverSock.accept();
                    PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
                    clientStreams.add(writer);

                    Thread listener = new Thread(new ClientHandler(clientSock, writer));
                    listener.start();
    //				txtAreaChat.append("Got a connection. \n");
                    
                }
            }
            catch (Exception ex)
            {
//                txtAreaChat.append("Error making a connection. \n");
            }
        }
    }
    


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author H
 */
public class ServerConnection implements Runnable{
    ArrayList clientStreams;
    ArrayList<String> clientAccounts;
    public void ServerConnection(){
        
    }
    @Override
    public void run() {
            
            clientStreams = new ArrayList();
            clientAccounts = new ArrayList();  
            int temp = 1;
            try 
            {
                ServerSocket serverSock = new ServerSocket(2222);

                while (true) 
                {
                    Socket clientSock = serverSock.accept();
                    System.out.println("got a connection");
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
    public ArrayList<String> GetListClient(){
        return this.clientAccounts;
    }

public class ClientHandler implements Runnable{
    public BufferedReader reader;
    public Socket socket;
    public PrintWriter clientPrint;
    public ClientHandler(Socket clientSocket, PrintWriter user){
        clientPrint = user;
            try 
            {
                socket = clientSocket;
                System.out.println("Got clinent handler");
                InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(isReader);
            }
            catch (Exception ex) 
            {
//                txtAreaChat.append("Unexpected error... \n");
                System.out.println("Unexpedted errror");
            }

    }
    
    public ClientHandler(){
    }

    @Override
    public void run() {
        String message;
        String connect = "Connect";
        String disconnect = "disconnect";
        String chat = "chat" ;
        String[] data;

        try 
        {
            while ((message = reader.readLine()) != null) 
            {
//                    ta_chat.append("Received: " + message + "\n");
                data = message.split(":");
                System.out.println("receive");
                System.out.println(message);
                for (String token:data) 
                {
//                        ta_chat.append(token + "\n");
                }

                if (data[2].equals(connect)) 
                {
                    System.out.println("conected");
                    
//                        tellEveryone((data[0] + ":" + data[1] + ":" + chat));
                        AddClient(data[0]);
                } 
                else if (data[2].equals(disconnect)) 
                {
//                        tellEveryone((data[0] + ":has disconnected." + ":" + chat));
//                        userRemove(data[0]);
                } 
                else if (data[2].equalsIgnoreCase("chat"))
                {
                      SendAllClient(data[1]);
                } 
                else 
                {
//                        ta_chat.append("No Conditions were met. \n");
                }
            } 
         } 
         catch (Exception ex) 
         {
             System.out.println("Lost connection");
            ex.printStackTrace();

         } 
    } 
    
//    public void 
    public void AddClient (String username) 
    {
        clientAccounts.add(username);
//        tellEveryone(done);
    }
    public void SendAllClient(String message){
        System.out.println(clientStreams.size());
        System.out.println("send:" + message);
        Iterator iter = clientStreams.iterator();

        while (iter.hasNext()) 
        {
            try 
            {
                PrintWriter writer = (PrintWriter) iter.next();
		writer.println(message);
                System.out.println("Sending: "+ message);
                writer.flush();
            } 
            catch (Exception ex) 
            {
                System.out.println("Something went wrong");
            }
        }
    }
    
  
}  
}

    


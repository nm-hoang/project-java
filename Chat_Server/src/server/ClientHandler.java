/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author H
 */
public class ClientHandler implements Runnable{
    public BufferedReader reader;
    public Socket socket;
    public PrintWriter clientPrint;
    public ClientHandler(Socket clientSocket, PrintWriter user){
        clientPrint = user;
            try 
            {
                socket = clientSocket;
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
        String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat" ;
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
//                        tellEveryone((data[0] + ":" + data[1] + ":" + chat));
//                        userAdd(data[0]);
                    } 
                    else if (data[2].equals(disconnect)) 
                    {
//                        tellEveryone((data[0] + ":has disconnected." + ":" + chat));
//                        userRemove(data[0]);
                    } 
                    else if (data[2].equals(chat)) 
                    {
//                        tellEveryone(message);
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
    }
    
    

package me.zwoosks.serverGetter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Main {
	
	
	public static void main (String[] args) {
		
		try {
            @SuppressWarnings("resource")
			Socket socket = new Socket();
            socket.connect(new InetSocketAddress("142.44.157.248", 25632), 1 * 1000);
           
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
           
            out.write(0xFE);
           
            StringBuilder str = new StringBuilder();
           
            int b;
            while ((b = in.read()) != -1) {
                    if (b != 0 && b > 16 && b != 255 && b != 23 && b != 24) {
                            str.append((char) b);
                    }
            }
           
            String[] data = str.toString().split("§");
            int onlinePlayers = Integer.valueOf(data[1]);
            int maxPlayers = Integer.valueOf(data[2]);
            String motd = String.valueOf(data[0]);
           
            
            //Send online players to the channel
            System.out.println(" Jugadors connectats: " + onlinePlayers + "/" + maxPlayers);
            System.out.println("MOTD: " + motd);
            
            
		} catch (Exception evt) {
            evt.printStackTrace();
		}
	}
}

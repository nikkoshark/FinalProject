package Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;

public class Server extends Thread{

	private static LocalDate date = LocalDate.now();
	
	public static void main(String[] args) {
       while (true) {
    	   try {
           	
           	   ServerSocket serverSocket = new ServerSocket(7000);
           	
               System.out.println("listening to port: 7000");
               Socket clientSocket = serverSocket.accept();
               System.out.println(clientSocket + " connected.");
               
               DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
               DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
               
               date = LocalDate.now();
               
               System.out.println(date.getDayOfMonth());
               
               int bytes = 0;
               File file = new File("clinic_data_backup_" + date.getDayOfMonth() + "_" + date.getMonthValue() + "_"+ date.getYear() + ".dat");
               FileOutputStream fileOutputStream = new FileOutputStream(file);
               
               long size = dataInputStream.readLong();     
               byte[] buffer = new byte[4*1024];
               while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1) {
                   fileOutputStream.write(buffer, 0, bytes);
                   size -= bytes;
               }
               fileOutputStream.close();

               dataInputStream.close();
               dataOutputStream.close();
               clientSocket.close();
               
           } catch (Exception e){
               //e.printStackTrace();
           }
       }
    }
}
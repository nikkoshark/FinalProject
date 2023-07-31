package Socket;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import logic.Clinic;

public class Server extends Thread{

	public void run(){
		while (true) {			
			try {
				ServerSocket serverSocket = new ServerSocket(8000);
				System.out.println("connecting to server socket..");
				
				Socket socket = serverSocket.accept();
				System.out.println("connected to: " + socket.getInetAddress());
				
				InputStream inputStream = socket.getInputStream();
				ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
				
			
				File file = new File("clinic_data_respaldo.dat");
		        FileOutputStream outputStream;
		        ObjectOutputStream objectOutputStream;
		        
		        try {
					outputStream = new FileOutputStream(file);
					objectOutputStream = new ObjectOutputStream(outputStream);
					objectOutputStream.writeObject(objectInputStream);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "El programa no ha cerrado adecuadamente.", "Error en guardado", JOptionPane.ERROR_MESSAGE);
				}
		        
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, "El programa no ha cerrado adecuadamente.", "Error en guardado", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
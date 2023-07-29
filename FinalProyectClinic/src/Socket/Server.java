package Socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{

	public void run(){
		try {
			ServerSocket serverSocket = new ServerSocket(8000);
			System.out.println("connecting to server socket..");
			
			Socket socket = serverSocket.accept();
			System.out.println("connected to: " + socket.getInetAddress());
			
			InputStream inputStream = socket.getInputStream();
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
			
//			try {
//				Testing test = (Testing) objectInputStream.readObject();
//				System.out.println("info: " + test.getName());
//				System.out.println("edad: " + test.getAge());
//				
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			serverSocket.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
		
		
	}

}

package login;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Clinic;

public class Login extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				File file = new File("file.dat");
				
				FileInputStream inputStream;
				FileOutputStream outputStream;
				
				ObjectInputStream objectInputStream;
				ObjectOutputStream objectOutputStream;
				
				try {
					inputStream = new FileInputStream(file);
					objectInputStream = new ObjectInputStream(inputStream);
					Clinic tmp = (Clinic) objectInputStream.readObject();
					Clinic.setClinic(tmp);
					inputStream.close();
					objectInputStream.close();
				} catch (Exception e) {
					try {
						outputStream = new FileOutputStream(file);
						objectOutputStream = new ObjectOutputStream(outputStream);
						User user = new User("Admin", "admin", "admin123");
						Clinic.getInstance().insertUser(user);
						objectOutputStream.writeObject(Clinic.getInstance());
						outputStream.close();
						objectOutputStream.close();
						
					} catch (Exception e2) {
						System.out.println("File wasn't found or couldn't write");
						e2.printStackTrace();
					}
				}
				
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}

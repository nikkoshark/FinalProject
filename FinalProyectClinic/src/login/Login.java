package login;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Visual.Main;
import javafx.scene.input.KeyCode;
import logic.Clinic;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;






public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField pswfPass;
	private JButton btnLogin;

	/**
	 * Launch the application.
	 */
	
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				File file = new File("clinic_data.dat");
				
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
						User user = new User("Admin", "a", "a");
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
		setBounds(100, 100, 483, 334);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		setTitle("Benessere");
		Image logo = new ImageIcon(getClass().getClassLoader().getResource("benessere- short.png")).getImage();
		setIconImage(logo);
		
		JLabel lblNewLabel = new JLabel("\u00A1BIENVENIDO!");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(167, 11, 130, 41);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("USUARIO:");
		lblNewLabel_1.setFont(new Font("Calibri", Font.ITALIC, 14));
		lblNewLabel_1.setBounds(167, 77, 77, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblContrasea = new JLabel("CONTRASE\u00D1A:");
		lblContrasea.setFont(new Font("Calibri", Font.ITALIC, 14));
		lblContrasea.setBounds(167, 145, 91, 14);
		contentPane.add(lblContrasea);
		
		txtUser = new JTextField();
		txtUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
		txtUser.setBounds(167, 90, 130, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		//action perform to make enter press login button
		pswfPass = new JPasswordField();
		pswfPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
		pswfPass.setBounds(167, 160, 130, 20);
		contentPane.add(pswfPass);
		
		btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		btnLogin.setBounds(192, 220, 89, 23);
		contentPane.add(btnLogin);
	
	}
	
	public void login() {
		if(Clinic.getInstance().validUser(txtUser.getText(), String.valueOf(pswfPass.getPassword()))) {
			
			//save users in the file...
			Main principal = new Main(Clinic.getLoginUser());
			principal.setVisible(true);
			dispose();
			
		}
		else {
			JOptionPane.showMessageDialog(null, "Usario o contraseña no valido, \npor favor verificar los campos.", "Fallo en iniciar sesion", JOptionPane.ERROR_MESSAGE);
		}
	}
}

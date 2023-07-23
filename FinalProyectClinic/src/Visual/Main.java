package Visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.glass.events.WindowEvent;

import logic.Clinic;
import login.Login;
import login.User;

import java.awt.SystemColor;
import java.awt.Window;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JLabel;
import java.awt.Font;

public class Main extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private MSecretaryPanel secretaryPanel;
	private MMedicPanel medicPanel;
	private MAdminPanel adminPanel;
	private JButton btnNewButton;
	private User user = null;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Main(User loginUser) {
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	File file = new File("clinic_data.dat");
		        FileOutputStream outputStream;
		        ObjectOutputStream objectOutputStream;
		        
		        try {
					outputStream = new FileOutputStream(file);
					objectOutputStream = new ObjectOutputStream(outputStream);
					objectOutputStream.writeObject(Clinic.getInstance());
				} catch (Exception e) {
					// TODO: handle exception
				}   
		    }
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height-50);
		setResizable(false);
		setLocationRelativeTo(null);
		
		user = loginUser;
		
		secretaryPanel = new MSecretaryPanel();
		medicPanel = new MMedicPanel();
		adminPanel = new MAdminPanel();
		
		if(user.getType().equals("Secretary")) {
			menuclicked(secretaryPanel);
		}else if(user.getType().equals("Medic")) {
			menuclicked(medicPanel);
		}else {
			menuclicked(adminPanel);
		}
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		panel.add(secretaryPanel);
		panel.add(medicPanel);
		panel.add(adminPanel);
		
		btnNewButton = new JButton("LOG OUT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(1257, 0, 93, 23);
		panel.add(btnNewButton);
	}
	

	private void menuclicked(JPanel panel) {
		secretaryPanel.setVisible(false);
		medicPanel.setVisible(false);
		adminPanel.setVisible(false);
		panel.setVisible(true);
		
	}
}

package Visual;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Clinic;
import login.Login;
import login.User;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Main extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	//private MSecretaryPanel secretaryPanel;
	private MMedicPanel medicPanel;
	private MAdminPanel adminPanel;
	private JButton btnNewButton;
	private User user = null;

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
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "El programa no ha cerrado adecuadamente.", "Error en guardado", JOptionPane.ERROR_MESSAGE);
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
		
		//secretaryPanel = new MSecretaryPanel();
		medicPanel = new MMedicPanel();
		adminPanel = new MAdminPanel();
		
		if(user.getType().equals("Secretaria")) {
			//menuclicked(secretaryPanel);
		}else if(user.getType().equals("Medico")) {
			menuclicked(medicPanel);
		}else {
			menuclicked(adminPanel);
		}
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1360, 689);
		panel.setBackground(SystemColor.activeCaption);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//panel.add(secretaryPanel);
		panel.add(medicPanel);
		panel.add(adminPanel);
		
		btnNewButton = new JButton("LOG OUT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
				
				File file = new File("clinic_data.dat");
		        FileOutputStream outputStream;
		        ObjectOutputStream objectOutputStream;
		        
		        try {
					outputStream = new FileOutputStream(file);
					objectOutputStream = new ObjectOutputStream(outputStream);
					objectOutputStream.writeObject(Clinic.getInstance());
				} catch (Exception e1) {
					// TODO: handle exception
				} 
			}
		});
		btnNewButton.setBounds(1257, 0, 93, 23);
		panel.add(btnNewButton);
	}
	

	private void menuclicked(JPanel panel) {
		//secretaryPanel.setVisible(false);
		medicPanel.setVisible(false);
		adminPanel.setVisible(false);
		panel.setVisible(true);
		
	}
}

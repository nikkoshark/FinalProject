package Visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Clinic;
import login.Login;
import login.User;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javax.swing.JLabel;

public class Main extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private MSecretaryPanel secretaryPanel;
	private MMedicPanel medicPanel;
	private MAdminPanel adminPanel;
	private User user = null;
	private JLabel lblWelcome;

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
		
		setTitle("Benessere");
		Image logo = new ImageIcon(getClass().getClassLoader().getResource("benessere- short.png")).getImage();
		setIconImage(logo);
		
		user = loginUser;
		
		secretaryPanel = new MSecretaryPanel();
		medicPanel = new MMedicPanel();
		adminPanel = new MAdminPanel();
		
		if(user.getType().equals("Secretaria")) {
			menuclicked(secretaryPanel);
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
		
		panel.add(secretaryPanel);
		panel.add(medicPanel);
		panel.add(adminPanel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(1200, 0, 160, 21);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		lblWelcome = new JLabel("¡Bienvenido, "+user.getName()+"!");
		lblWelcome.setBounds(10, 3, 121, 14);
		panel_1.add(lblWelcome);
		
		panel_1.addMouseListener(new PanelButtonMouseAdapter(panel_1));
	}
	

	private class PanelButtonMouseAdapter extends MouseAdapter{
			
		JPanel panel;
		public PanelButtonMouseAdapter(JPanel panel) {
			this.panel = panel;
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setBackground(new Color(119, 136, 153));
		}
		@Override
		public void mouseExited(MouseEvent e) {
			panel.setBackground(new Color(240, 240, 240));
		}
		@Override
		public void mousePressed(MouseEvent e) {
			panel.setBackground(new Color(192, 192, 192));
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
	}
	
	
	private void menuclicked(JPanel panel) {
		secretaryPanel.setVisible(false);
		medicPanel.setVisible(false);
		adminPanel.setVisible(false);
		panel.setVisible(true);
		
	}
}

package VisualTest;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import login.Login;
import login.User;

import java.awt.SystemColor;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class Main extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private MSecretaryPanel secretaryPanel;
	private MMedicPanel medicPanel;
	private MAdminPanel adminPanel;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main(null);
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
	public Main(User loginUser) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height-50);
		setResizable(false);
		setLocationRelativeTo(null);
		
		secretaryPanel = new MSecretaryPanel();
		medicPanel = new MMedicPanel();
		adminPanel = new MAdminPanel();
		
		if(loginUser.getType().equals("Secretary")) {
			menuclicked(secretaryPanel);
		}else if(loginUser.getType().equals("Medic")) {
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

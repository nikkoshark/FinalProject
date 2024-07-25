package Socket;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class VisualSockets extends JFrame {
	
	private static Server server = null;
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualSockets frame = new VisualSockets();
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
	public VisualSockets() {
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	dispose();
		    	System.exit(1);
		    }
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Activar Servidores");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(136, 36, 140, 22);
		panel.add(lblNewLabel);
		
		JButton btnActivar = new JButton("Activar");
		btnActivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame parentComponent = new JFrame();
			    JFileChooser fileChooser= new JFileChooser();
			    fileChooser.setBounds(140, 36, 140, 22);
			    
			    File fileSaved = null;
			    
			    int returnVal = fileChooser.showOpenDialog(parentComponent);
			    if ( returnVal == JFileChooser.APPROVE_OPTION) {
			        File fileToSave = fileChooser.getSelectedFile();
			        fileSaved = fileChooser.getSelectedFile();
			        try{
			        	System.out.println(fileToSave);
			            JOptionPane.showMessageDialog(null, "Archivo salvado");
			        }
			        catch(Exception e2){
			            JOptionPane.showMessageDialog(null, e2);
			        }
			    }
			    
				server = new Server(fileSaved.toString());
			}
		});
		btnActivar.setBounds(232, 112, 97, 25);
		panel.add(btnActivar);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				server.destroy();
			}
		});
		btnApagar.setBounds(123, 112, 97, 25);
		panel.add(btnApagar);
	}
}
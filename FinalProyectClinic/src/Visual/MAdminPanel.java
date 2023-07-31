package Visual;


import javax.swing.JPanel;

import AdminJDialogs.CreateDiseaseVaccine;
import AdminJDialogs.CreateMedic;
import AdminJDialogs.CreateUser;
import AdminPanels.DefaultDashboard;
import AdminPanels.ListDisease;
import AdminPanels.ListMedic;
import AdminPanels.ListUsers;
import AdminPanels.ListVaccine;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

public class MAdminPanel extends JPanel {

	private ListDisease listDisease;
	private ListVaccine listVaccine;
	private ListUsers listUsers;
	private ListMedic listMedic;
	private DefaultDashboard defDash;
	/**
	 * Create the panel.
	 */
	public MAdminPanel() {
		setOpaque(false);
		setSize(1340, 648);
		setLayout(null);

		listDisease = new ListDisease();
		listVaccine = new ListVaccine();
		listUsers = new ListUsers();
		listMedic = new ListMedic();
		defDash = new DefaultDashboard();
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1259, 21);
		add(menuBar);
		
		JMenu mnS = new JMenu("Usuarios");
		menuBar.add(mnS);
		
		JMenuItem mntmSList = new JMenuItem("Listar");
		mntmSList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuclicked(listUsers);
			}
		});
		mnS.add(mntmSList);
		
		JMenuItem mntmSCreate = new JMenuItem("Registrar");
		mntmSCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateUser createUser = new CreateUser(null);
				createUser.setVisible(true);
				
			}
		});
		mnS.add(mntmSCreate);
		
		JMenu mnM = new JMenu("Medics");
		menuBar.add(mnM);
		
		JMenuItem mntmMList = new JMenuItem("List");
		mntmMList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuclicked(listMedic);
			}
		});
		mnM.add(mntmMList);
		
		JMenuItem mntmMCreate = new JMenuItem("Create");
		mntmMCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateMedic createMedic = new CreateMedic(null);
				createMedic.setVisible(true);
			}
		});
		mnM.add(mntmMCreate);
		
		JMenu mnV = new JMenu("Vaccines");
		menuBar.add(mnV);
		
		JMenuItem mntmVList = new JMenuItem("List");
		mntmVList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuclicked(listVaccine);
			}
		});
		mnV.add(mntmVList);
		
		JMenuItem mntmVCreate = new JMenuItem("Create");
		mntmVCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateDiseaseVaccine createVaccine = new CreateDiseaseVaccine("vaccine", null, null);
				createVaccine.setVisible(true);
			}
		});
		mnV.add(mntmVCreate);
		
		JMenu mnD = new JMenu("Diseases");
		menuBar.add(mnD);
		
		JMenuItem mntmDList = new JMenuItem("List");
		mntmDList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ListDiseaseVaccine.changeType("disease");
				menuclicked(listDisease);
			}
		});
		mnD.add(mntmDList);
		
		JMenuItem mntmDCreate = new JMenuItem("Create");
		mntmDCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateDiseaseVaccine createDisease = new CreateDiseaseVaccine("disease", null, null);
				createDisease.setVisible(true);
			}
		});
		mnD.add(mntmDCreate);
		
		JMenu nmR = new JMenu("Respaldo");
		nmR.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "¿Desea hacer un respaldo de todo el sistema?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);
				if(option == JOptionPane.OK_OPTION) {
					try {
						  File file = new File("clinic_data.dat");
						  FileInputStream fileInputStream = new FileInputStream(file);
						  
						  Socket socket = new Socket("localhost", 7000);
						  
						  DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
						  					  
					      int bytes = 0;
					      dataOutputStream.writeLong(file.length());  
					      byte[] buffer = new byte[4*1024];
					      while ((bytes = fileInputStream.read(buffer))!= -1){
					    	  dataOutputStream.write(buffer,0,bytes);
					    	  dataOutputStream.flush();
					      }
					      fileInputStream.close();
					      
					  } catch (Exception e2) {
						  System.out.println(e2);
					  }
					JOptionPane.showMessageDialog(null, "¡Se ha creado un respaldo del sistema exitosamente!", "Respaldo Exitoso", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		menuBar.add(nmR);
		
		JPanel InfoP = new JPanel();
		InfoP.setBounds(10, 67, 1320, 551);
		InfoP.setOpaque(false);
		add(InfoP);
		setVisible(false);
		InfoP.setLayout(null);
		
		JButton btnNewButton = new JButton("VOLVER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuclicked(defDash);
			}
		});
		btnNewButton.setBounds(1241, 33, 89, 23);
		add(btnNewButton);
		
		InfoP.add(defDash);
		InfoP.add(listDisease);
		InfoP.add(listVaccine);
		InfoP.add(listUsers);
		InfoP.add(listMedic);
		
		menuclicked(defDash); //default
	}
	

	private void menuclicked(JPanel panel) {
		listDisease.setVisible(false);
		listVaccine.setVisible(false);
		listUsers.setVisible(false);
		listMedic.setVisible(false);
		defDash.setVisible(false);
		
		panel.setVisible(true);
		
	}
}

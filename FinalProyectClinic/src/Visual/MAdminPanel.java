package Visual;


import javax.swing.JPanel;

import AdminJDialogs.CreateDiseaseVaccine;
import AdminJDialogs.CreateMedic;
import AdminJDialogs.CreateSecretary;
import AdminPanels.DefaultDashboard;
import AdminPanels.ListDisease;
import AdminPanels.ListMedic;
import AdminPanels.ListSecretary;
import AdminPanels.ListVaccine;

import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class MAdminPanel extends JPanel {

	private ListDisease listDisease;
	private ListSecretary listSecretary;
	private ListVaccine listVaccine;
	private ListMedic listMedic;
	private DefaultDashboard defDash;
	/**
	 * Create the panel.
	 */
	public MAdminPanel() {
		setBackground(SystemColor.activeCaption);

		setSize(1340, 648);
		setLayout(null);

		listDisease = new ListDisease();
		listSecretary = new ListSecretary();
		listMedic = new ListMedic();
		listVaccine = new ListVaccine();
		defDash = new DefaultDashboard();
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1340, 21);
		add(menuBar);
		
		JMenu mnS = new JMenu("Secretaries");
		menuBar.add(mnS);
		
		JMenuItem mntmSList = new JMenuItem("List");
		mntmSList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuclicked(listSecretary);
			}
		});
		mnS.add(mntmSList);
		
		JMenuItem mntmSCreate = new JMenuItem("Create");
		mntmSCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateSecretary createSecretary = new CreateSecretary();
				createSecretary.setVisible(true);
				
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
				CreateMedic createMedic = new CreateMedic();
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
				CreateDiseaseVaccine createVaccine = new CreateDiseaseVaccine("Vaccine");
				createVaccine.setVisible(true);
			}
		});
		mnV.add(mntmVCreate);
		
		JMenu mnD = new JMenu("Diseases");
		menuBar.add(mnD);
		
		JMenuItem mntmDList = new JMenuItem("List");
		mntmDList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuclicked(listDisease);
			}
		});
		mnD.add(mntmDList);
		
		JMenuItem mntmDCreate = new JMenuItem("Create");
		mntmDCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateDiseaseVaccine createDiseaseVaccine = new CreateDiseaseVaccine("Disease");
				createDiseaseVaccine.setVisible(true);
			}
		});
		mnD.add(mntmDCreate);
		
		JPanel InfoP = new JPanel();
		InfoP.setBounds(10, 67, 1320, 551);
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
		
		
		JLabel lblNewLabel = new JLabel("EXAMPLE OF ADMIN (HUGE DASHBOARD)");
		lblNewLabel.setBounds(239, 17, 426, 47);
		add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		InfoP.add(defDash);
		InfoP.add(listDisease);
		InfoP.add(listVaccine);
		InfoP.add(listSecretary);
		InfoP.add(listMedic);
		
		menuclicked(defDash); //default
	}
	

	private void menuclicked(JPanel panel) {
		listDisease.setVisible(false);
		listVaccine.setVisible(false);
		listSecretary.setVisible(false);
		listMedic.setVisible(false);
		defDash.setVisible(false);
		
		panel.setVisible(true);
		
	}
}
package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;

import com.toedter.calendar.JDateChooser;

import Dashboards.AppointmentInfo;
import logic.Appoinment;
import logic.Clinic;
import logic.Medic;
import logic.Person;

public class CreateAppointment extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNamePatient;
	private JTextField txtCode;
	private JDateChooser dateChooser;
	private JComboBox cbStatus;
	private JFormattedTextField ftxtPhone;
	private JFormattedTextField ftxtSSN;
	private JTextArea txtaDescription;
	private JComboBox cbDoctor;
	private Appoinment appoinment = null;

	private Medic medic = null;
	private Date date = null;

	/**
	 * Create the dialog.
	 */
	public CreateAppointment(Appoinment app) {
		appoinment = app;
		setTitle("Modificar");
		if(appoinment == null) {
			setTitle("Registrar");
		}
		
		setBounds(100, 100, 605, 401);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("CREATE APPOINTMENT");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel.setBounds(116, 45, 236, 34);
			contentPanel.add(lblNewLabel);

			JLabel lblNewLabel_1 = new JLabel("C\u00F3digo:");
			lblNewLabel_1.setBounds(116, 90, 46, 14);
			contentPanel.add(lblNewLabel_1);
	
			txtCode = new JTextField();
			txtCode.setEditable(false);
			txtCode.setText(getCodeAppoinment((Clinic.getInstance().getCodeAppoinment())));
			txtCode.setColumns(10);
			txtCode.setBounds(116, 115, 120, 20);
			contentPanel.add(txtCode);

			JLabel lblIdPaciente = new JLabel("C\u00E9dula Paciente:");
			lblIdPaciente.setBounds(116, 146, 110, 14);
			contentPanel.add(lblIdPaciente);
	
			ftxtSSN = new JFormattedTextField();
			ftxtSSN.setBounds(116, 169, 120, 20);
			contentPanel.add(ftxtSSN);
			
			MaskFormatter maskssn;
			try {
				maskssn = new MaskFormatter("###-#######-#");
				maskssn.install(ftxtSSN);					
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			JLabel lblNombrePaciente = new JLabel("Nombre Paciente:");
			lblNombrePaciente.setBounds(247, 146, 110, 14);
			contentPanel.add(lblNombrePaciente);
	
			txtNamePatient = new JTextField();
			txtNamePatient.setColumns(10);
			txtNamePatient.setBounds(247, 169, 120, 20);
			contentPanel.add(txtNamePatient);
			
			JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
			lblTelfono.setBounds(378, 146, 86, 14);
			contentPanel.add(lblTelfono);
				
			ftxtPhone = new JFormattedTextField();
			ftxtPhone.setBounds(376, 169, 120, 20);
			contentPanel.add(ftxtPhone);
	
			MaskFormatter maskphone;
			try {
				maskphone = new MaskFormatter("###-###-####");
				maskphone.install(ftxtPhone);					
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		

			JLabel lblCdigoDoctor = new JLabel("Doctor:");
			lblCdigoDoctor.setBounds(247, 90, 110, 14);
			contentPanel.add(lblCdigoDoctor);
			
			cbDoctor = new JComboBox();
			cbDoctor.setModel(new DefaultComboBoxModel(new String[] {"<Seleccionar>"}));
			cbDoctor.setBounds(246, 115, 121, 20);
			contentPanel.add(cbDoctor);
		
			JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
			lblDescripcin.setBounds(117, 200, 84, 14);
			contentPanel.add(lblDescripcin);
	
			txtaDescription = new JTextArea();
			txtaDescription.setLineWrap(true);
			txtaDescription.setBounds(116, 220, 382, 73);
			contentPanel.add(txtaDescription);
			
			JLabel lblFechaDeCita = new JLabel("Fecha de Cita:");
			lblFechaDeCita.setBounds(438, 11, 94, 14);
			contentPanel.add(lblFechaDeCita);
	
			dateChooser = new JDateChooser();
			dateChooser.setBounds(438, 36, 94, 20);
			contentPanel.add(dateChooser);

			date = new Date();
			dateChooser.setDate(date);
			LocalDateTime local = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
			
			JLabel lblStatus = new JLabel("Status:");
			lblStatus.setBounds(378, 90, 46, 14);
			contentPanel.add(lblStatus);
			
			cbStatus = new JComboBox();
			cbStatus.setModel(new DefaultComboBoxModel(new String[] {"En Espera", "No Presente"}));
			cbStatus.setBounds(376, 115, 120, 20);
			contentPanel.add(cbStatus);
		}
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.inactiveCaption);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSave = new JButton("MODIFICAR");
				if(appoinment == null) {
					btnSave.setText("SALVAR");
				}
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (cbDoctor.getSelectedIndex() != 0 && !ftxtSSN.getText().contains("   -       - ") && !txtNamePatient.getText().isEmpty()) {
							
								int i = 1;
								for (Person per : Clinic.getInstance().getMyPersons()) {
									if (per instanceof Medic) {
										Medic med = (Medic) per;
										if (cbDoctor.getSelectedIndex() == i) {
											medic = med;
										}
										i++;
									}
								}
							
							if(appoinment == null) {
									
								Appoinment insApp = new Appoinment(txtCode.getText(), txtNamePatient.getText(), ftxtSSN.getText(), ftxtPhone.getText(), txtaDescription.getText(), LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()), medic, String.valueOf(cbStatus.getSelectedItem()));
								Clinic.getInstance().insertAppoinment(insApp);
								JOptionPane.showMessageDialog(null, "Cita apuntada.", "Registrar Cita", JOptionPane.INFORMATION_MESSAGE);
								clean();
								MSecretaryPanel.loadAppointments();
								
							} else {
								
								appoinment.setCode(txtCode.getText());
								appoinment.setName(txtNamePatient.getText());
								appoinment.setSsn(ftxtSSN.getText());
								appoinment.setPhoneNumber(ftxtPhone.getText());
								appoinment.setMedic(medic);
								appoinment.setDescription(txtaDescription.getText());
								appoinment.setDate(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
								appoinment.setStatus(String.valueOf(cbStatus.getSelectedItem()));
								
								Clinic.getInstance().modifiedAppoinment(appoinment);
								dispose();
							}
							MSecretaryPanel.loadAppointments();
							MMedicPanel.loadAppointments();
							AppointmentInfo.refreshChart();
						}
						else {
							JOptionPane.showMessageDialog(null, "¡Parámetro(s) sin completar!\nPor favor completar los campos.", "Información Vacía", JOptionPane.ERROR_MESSAGE);
						}
					}

				});
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			}
			{
				JButton cancelButton = new JButton("CANCEL");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		loadDoctors();
		loadApp();
	}
	

	private void clean() {
		txtCode.setText(getCodeAppoinment(Clinic.getInstance().getCodeAppoinment()));
		txtNamePatient.setText("");
		txtaDescription.setText("");
		ftxtPhone.setText("");
		ftxtSSN.setText("");
		cbStatus.setSelectedIndex(0);
		cbDoctor.setSelectedIndex(0);
		dateChooser.setDate(new Date());
	}
	
	private void loadApp() {
		if(appoinment != null) {
			txtCode.setText(appoinment.getCode());
			txtNamePatient.setText(appoinment.getName());
			ftxtSSN.setText(appoinment.getSsn());
			ftxtPhone.setText(appoinment.getPhoneNumber());
			txtaDescription.setText(appoinment.getDescription());
			ZonedDateTime zone = appoinment.getDate().atZone(ZoneId.systemDefault());
			dateChooser.setDate(Date.from(zone.toInstant()));
			
			int i = 1;
			for (Person per : Clinic.getInstance().getMyPersons()) {
				if (per instanceof Medic) {
					Medic med = (Medic) per;
					
					if (appoinment.getMedic().getCode().equalsIgnoreCase(med.getCode())) {
						cbDoctor.setSelectedIndex(i);
					}
					i++;
				}
			}

			cbStatus.setSelectedItem(appoinment.getStatus());
		}
	}
	
	private void loadDoctors() {
		cbDoctor.removeAllItems();
		for(Person aux : Clinic.getInstance().getMyPersons()) {
			if(aux instanceof Medic) {
				String name = new String(aux.getName());
				cbDoctor.addItem(name);
			}
		}
		cbDoctor.insertItemAt(new String("<Seleccionar>"), 0);
		cbDoctor.setSelectedIndex(0);

	}
	
	private static String getCodeAppoinment(int codeApp) {
		int total = codeApp / 10;
		String code = null;
		
		code = "A-0000" + codeApp;
		
		if (total >= 1 && total < 10) {
			code = "A-000" + codeApp;
		}
		else if (total >= 10 && total < 100) {
			code = "A-00" + codeApp;
		}
		else if (total >= 100 && total < 1000) {
			code = "A-0" + codeApp;
		}
		else if (total >= 1000) {
			code = "A-" + codeApp;
		}
		
		return code;
	}
}

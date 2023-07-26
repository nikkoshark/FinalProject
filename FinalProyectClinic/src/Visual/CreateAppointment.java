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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.toedter.calendar.JDateChooser;

import Dashboards.AppointmentInfo;
import logic.Appoinment;
import logic.Clinic;
import logic.Medic;
import logic.Person;

public class CreateAppointment extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtCode;
	private JDateChooser dateChooser;
	private JComboBox cbStatus;
	private JFormattedTextField ftxtPhone;
	private JFormattedTextField ftxtSSN;
	private JTextArea txtaDescription;
	private JComboBox cbDoctor;
	private Appoinment appoinment = null;

	private Medic medic;
	private Date date;

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

			JLabel lblNewLabel_1 = new JLabel("C\u00F3digo");
			lblNewLabel_1.setBounds(116, 90, 46, 14);
			contentPanel.add(lblNewLabel_1);
	
			txtCode = new JTextField();
			txtCode.setEditable(false);
			txtCode.setColumns(10);
			txtCode.setBounds(116, 115, 120, 20);
			contentPanel.add(txtCode);

			JLabel lblIdPaciente = new JLabel("C\u00E9dula Paciente");
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
			
			JLabel lblNombrePaciente = new JLabel("Nombre Paciente");
			lblNombrePaciente.setBounds(247, 146, 110, 14);
			contentPanel.add(lblNombrePaciente);
	
			txtName = new JTextField();
			txtName.setColumns(10);
			txtName.setBounds(247, 169, 120, 20);
			contentPanel.add(txtName);
			
			JLabel lblTelfono = new JLabel("Tel\u00E9fono");
			lblTelfono.setBounds(378, 146, 86, 14);
			contentPanel.add(lblTelfono);
				
			ftxtPhone = new JFormattedTextField();
			ftxtPhone.setBounds(376, 171, 120, 20);
			contentPanel.add(ftxtPhone);
	
			MaskFormatter maskphone;
			try {
				maskphone = new MaskFormatter("###-###-####");
				maskphone.install(ftxtPhone);					
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		

			JLabel lblCdigoDoctor = new JLabel("Doctor");
			lblCdigoDoctor.setBounds(247, 90, 110, 14);
			contentPanel.add(lblCdigoDoctor);
			
			cbDoctor = new JComboBox();
			cbDoctor.setModel(new DefaultComboBoxModel(new String[] {"<Seleccionar>"}));
			cbDoctor.setBounds(246, 115, 121, 20);
			contentPanel.add(cbDoctor);
		
			JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
			lblDescripcin.setBounds(117, 200, 84, 14);
			contentPanel.add(lblDescripcin);
	
			txtaDescription = new JTextArea();
			txtaDescription.setLineWrap(true);
			txtaDescription.setBounds(116, 220, 382, 73);
			contentPanel.add(txtaDescription);
			
			JLabel lblFechaDeCita = new JLabel("Fecha de Cita");
			lblFechaDeCita.setBounds(461, 11, 94, 14);
			contentPanel.add(lblFechaDeCita);
	
			dateChooser = new JDateChooser();
			dateChooser.setBounds(438, 36, 94, 20);
			contentPanel.add(dateChooser);

			//LocalDateTime date = LocalDateTime.now();
			//Date date = new Date();
			//dateChooser.setda(date);

			date = new Date();
			dateChooser.setDate(date);
			LocalDateTime local = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
			
			JLabel lblStatus = new JLabel("Status");
			lblStatus.setBounds(378, 90, 46, 14);
			contentPanel.add(lblStatus);
			
			cbStatus = new JComboBox();
			cbStatus.setModel(new DefaultComboBoxModel(new String[] {"En Espera", "No Presente", "Vista"}));
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
						if(appoinment == null) {
							//Date newdDate = new Date(dateChooser);
							Appoinment insApp = new Appoinment(txtCode.getText(), txtName.getText(), ftxtSSN.getText(), ftxtPhone.getText(), txtaDescription.getText(), LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()), /*Clinic.getInstance().searchPerson(txtDoctor.getText())*/ medic, String.valueOf(cbStatus.getSelectedItem()));
							Clinic.getInstance().insertAppoinment(insApp);
							JOptionPane.showMessageDialog(null, "Cita apuntada.", "Registrar Cita", JOptionPane.INFORMATION_MESSAGE);
							clean();
							MSecretaryPanel.loadAppointments();
						} else {
							appoinment.setCode(txtCode.getText());
							appoinment.setName(txtName.getText());
							appoinment.setSsn(ftxtSSN.getText());
							appoinment.setPhoneNumber(ftxtPhone.getText());
							appoinment.setMedic(medic);
							appoinment.setDescription(txtaDescription.getText());
							appoinment.setDate(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
							appoinment.setStatus(String.valueOf(cbStatus.getSelectedItem()));
							
							Clinic.getInstance().modifiedAppoinment(appoinment);
							dispose();
							MSecretaryPanel.loadAppointments();
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
		loadApp();
		loadDoctors();
	}
	

	private void clean() {
		txtCode.setText("");
		txtName.setText("");
		txtaDescription.setText("");
		ftxtPhone.setText("");
		ftxtSSN.setText("");
		cbStatus.setSelectedIndex(0);
		cbDoctor.setSelectedIndex(0);
		dateChooser.setDate(null);
	}
	
	private void loadApp() {
		if(appoinment != null) {
			txtCode.setText(appoinment.getCode());
			txtName.getText();
			ftxtSSN.getText();
			ftxtPhone.getText();
			//appoinment.setMedic(medic);
			txtaDescription.getText();
			//dateChooser.setDate(appoinment.getDate());
			//appoinment.setDate(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
			if(appoinment.getStatus().equalsIgnoreCase("En Espera")) {
				cbStatus.setSelectedIndex(0);
			}else if (appoinment.getStatus().equalsIgnoreCase("No Presente")) {
				cbStatus.setSelectedIndex(1);
			}else {
				cbStatus.setSelectedIndex(2);
			}
				
			appoinment.setStatus(String.valueOf(cbStatus.getSelectedItem()));
			
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
}

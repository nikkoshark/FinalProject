package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import CheckupPanels.CheckupsRecord;
import CheckupPanels.DiseasePanel;
import CheckupPanels.InfoPanel;
import CheckupPanels.MedicalRecordPanel;
import logic.Clinic;
import logic.Person;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;

public class CreateCheckup extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtLastName;
	private InfoPanel infoPanel;
	private DiseasePanel diseasePanel;
	private MedicalRecordPanel medicalRecordPanel;
	private CheckupsRecord checkupsRecord;
	private JTextField txtAge;
	private JTextField txtCode;
	private JTextField txtEmail;
	private JDateChooser dateChooser;
	private JTextArea txtaAddress;
	private JFormattedTextField ftxtPhone;
	private JFormattedTextField ftxtSSN;
	private JComboBox cbSex;
	private JComboBox cbBloodType;
	private JButton btnEdit;
	private Person patient = null;
	private int patientVer = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateCheckup dialog = new CreateCheckup(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateCheckup(Person per) { //sends a "PERSON" from appointment, if NEW, autofills from clinic, ELSE, txt's enabled
		patient = Clinic.getInstance().searchPerson(per.getSsn());
		
		setBounds(100, 100, 915, 546);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		if (patient != null) { //if found, show everything
			ftxtSSN.setText(patient.getSsn());
			txtName.setText(patient.getName());
			txtLastName.setText(patient.getLastName());
			//txtEmail.setText(patient);
			//dateChooser.setText(true);
			txtaAddress.setText(patient.getAddress());
			ftxtPhone.setText(patient.getPhoneNumber());
			changeEditable(false, 0, "Editar Paciente");
		} else { //just send whatever was before
			txtName.setText(per.getName());
			txtLastName.setText(per.getLastName());
			ftxtPhone.setText(per.getPhoneNumber());
		}
		
		infoPanel = new InfoPanel();
		diseasePanel = new DiseasePanel();
		medicalRecordPanel = new MedicalRecordPanel();
		checkupsRecord = new CheckupsRecord();
		
		contentPanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("REGISTRAR CONSULTA");
		lblTitle.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 28));
		lblTitle.setBounds(10, 11, 290, 33);
		contentPanel.add(lblTitle);
		
		{
			JPanel patientP = new JPanel();
			patientP.setBounds(10, 50, 265, 423);
			contentPanel.add(patientP);
			patientP.setLayout(null);
			{
				JLabel lblCode = new JLabel("C\u00F3digo");
				lblCode.setBounds(10, 24, 46, 14);
				patientP.add(lblCode);

				txtCode = new JTextField();
				txtCode.setEditable(false);
				txtCode.setColumns(10);
				txtCode.setBounds(10, 43, 120, 20);
				patientP.add(txtCode);
				
				JLabel lblCdula = new JLabel("C\u00E9dula");
				lblCdula.setBounds(140, 24, 46, 14);
				patientP.add(lblCdula);
				
				ftxtSSN = new JFormattedTextField();
				ftxtSSN.setEditable(false);
				ftxtSSN.setBounds(140, 43, 120, 20);
				patientP.add(ftxtSSN);

				MaskFormatter maskssn;
				try {
					maskssn = new MaskFormatter("###-#######-#");
					maskssn.install(ftxtSSN);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				
				JLabel lblName = new JLabel("Nombre");
				lblName.setBounds(10, 69, 46, 14);
				patientP.add(lblName);
				
				txtName = new JTextField();
				txtName.setEditable(false);
				txtName.setBounds(10, 88, 120, 20);
				patientP.add(txtName);
				txtName.setColumns(10);
				
				
				JLabel lblLastName = new JLabel("Apellido");
				lblLastName.setBounds(140, 69, 46, 14);
				patientP.add(lblLastName);
				
				txtLastName = new JTextField();
				txtLastName.setEditable(false);
				txtLastName.setColumns(10);
				txtLastName.setBounds(140, 88, 120, 20);
				patientP.add(txtLastName);
				
				
				JLabel lblGmail = new JLabel("Correo Electr\u00F3nico");
				lblGmail.setBounds(140, 119, 120, 14);
				patientP.add(lblGmail);
			
				txtEmail = new JTextField();
				txtEmail.setEditable(false);
				txtEmail.setColumns(10);
				txtEmail.setBounds(140, 138, 120, 20);
				patientP.add(txtEmail);
					
				
				JLabel label = new JLabel("Tel\u00E9fono");
				label.setBounds(10, 119, 120, 14);
				patientP.add(label);
				
				ftxtPhone = new JFormattedTextField();
				ftxtPhone.setEditable(false);
				ftxtPhone.setBounds(10, 138, 120, 20);
				patientP.add(ftxtPhone);
				
				MaskFormatter maskphone;
				try {
					maskphone = new MaskFormatter("###-###-####");
					maskphone.install(ftxtPhone);					
				} catch (ParseException e) {
					e.printStackTrace();
				}
			
				JLabel lblAge = new JLabel("Edad");
				lblAge.setBounds(140, 169, 46, 14);
				patientP.add(lblAge);
				
				txtAge = new JTextField();
				txtAge.setEditable(false);
				txtAge.setBounds(140, 188, 120, 20);
				patientP.add(txtAge);
			
				JLabel lblBirthdate = new JLabel("Fecha de Nacimiento");
				lblBirthdate.setBounds(10, 169, 120, 14);
				patientP.add(lblBirthdate);
				
				dateChooser = new JDateChooser();
				dateChooser.setBounds(10, 188, 120, 20);
				patientP.add(dateChooser);
				dateChooser.setEnabled(false);
			
				JLabel lblAddress = new JLabel("Direcci\u00F3n");
				lblAddress.setBounds(11, 265, 249, 14);
				patientP.add(lblAddress);
				
				txtaAddress = new JTextArea();
				txtaAddress.setLineWrap(true);
				txtaAddress.setEditable(false);
				txtaAddress.setBounds(10, 285, 250, 93);
				patientP.add(txtaAddress);
			
				JLabel lblSex = new JLabel("Sexo");
				lblSex.setBounds(10, 219, 120, 14);
				patientP.add(lblSex);
				
				cbSex = new JComboBox();
				cbSex.setModel(new DefaultComboBoxModel(new String[] {"<Seleccionar>", "Femenino", "Masculino"}));
				cbSex.setBounds(10, 234, 120, 20);
				patientP.add(cbSex);
				
				JLabel lblBloodType = new JLabel("Tipo de Sangre");
				lblBloodType.setBounds(140, 219, 120, 14);
				patientP.add(lblBloodType);
				
				cbBloodType = new JComboBox();
				cbBloodType.setModel(new DefaultComboBoxModel(new String[] {"<No Verificado>", "A+", "A-", "AB+", "AB-", "B+", "B-", "O+", "O-"}));
				cbBloodType.setBounds(140, 234, 120, 20);
				patientP.add(cbBloodType);
			}
			btnEdit = new JButton("Editar Paciente");
			btnEdit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(patientVer == 0) {
						changeEditable(true, 1, "Establecer Paciente");
					} else {
						changeEditable(false, 0, "Editar Paciente");
					}
				}
			});
			btnEdit.setBounds(10, 389, 250, 23);
			patientP.add(btnEdit);	
		}
		
		
		
		JPanel infoP = new JPanel();
		infoP.setBackground(SystemColor.inactiveCaption);
		infoP.setBounds(285, 70, 614, 403);
		contentPanel.add(infoP);
		infoP.setLayout(null);
			
			
		infoP.add(infoPanel);
		infoP.add(diseasePanel);
		infoP.add(medicalRecordPanel);
		infoP.add(checkupsRecord);
			
		menuclicked(infoPanel); //default
			
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.inactiveCaption);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int option = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea salir? Perderá todo su proceso.", "Verificación", JOptionPane.OK_CANCEL_OPTION);
						if(option == JOptionPane.OK_OPTION) {
							dispose();
						}
					}
				});
				buttonPane.add(cancelButton);
			}
		}

		{
			JButton btninf = new JButton("inf");
			btninf.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					menuclicked(infoPanel);
				}
			});
			btninf.setBounds(285, 50, 89, 23);
			contentPanel.add(btninf);
		}
		{
			JButton btnhis = new JButton("historial");
			btnhis.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					menuclicked(medicalRecordPanel);
				}
			});
			btnhis.setBounds(384, 50, 89, 23);
			contentPanel.add(btnhis);
		}
	}
	
	private void changeEditable(boolean bool, int ver, String text) {
		ftxtSSN.setEditable(bool);
		txtName.setEditable(bool);
		txtLastName.setEditable(bool);
		txtEmail.setEditable(bool);
		dateChooser.setEnabled(bool);
		txtaAddress.setEditable(bool);
		ftxtPhone.setEditable(bool);
		patientVer = ver;
		btnEdit.setText(text);
	}

	private void menuclicked(JPanel panel) {
		infoPanel.setVisible(false);
		diseasePanel.setVisible(false);
		medicalRecordPanel.setVisible(false);
		checkupsRecord.setVisible(false);
		
		panel.setVisible(true);
		
	}
}

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;

import com.toedter.calendar.JDateChooser;

import Dashboards.AppointmentInfo;
import logic.Clinic;
import logic.SqlConnection;

import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;

public class CreateAppointment extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNamePatient;
	private JTextField txtCode;
	private JDateChooser dateChooser;
	private JSpinner spnTime;
	private JComboBox cbStatus;
	private JFormattedTextField ftxtPhone;
	private JFormattedTextField ftxtSSN;
	private JTextArea txtaDescription;
	private JComboBox cbDoctor;
	private String appointment = null;
	//private String dateString;
	//private String timeString;
	//private LocalDateTime gettheDateTime;
	
	private Connection con = SqlConnection.getConnection();
	private PreparedStatement ps;
	private ResultSet rs;

	private int medicPOS = (-1);
	private Date date = null;

	/**
	 * Create the dialog.
	 */
	public CreateAppointment(String app) {
		appointment = app;
		setTitle("Modificar");
		if(appointment == null) {
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
			dateChooser.setBounds(422, 36, 110, 20);
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
		
		spnTime = new JSpinner();
		spnTime.setModel(new SpinnerListModel(new String[] {"08:00 AM", "08:30 AM", "09:00 AM", "09:30 AM", "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM", "12:00 PM", "12:30 PM", "01:00 PM", "01:30 PM", "02:00 PM", "02:30 PM", "03:00 PM", "03:30 PM", "04:00 PM", "04:30 PM", "05:00 PM", "05:30 PM", "06:00 PM"}));
		spnTime.setBounds(422, 56, 110, 20);
		contentPanel.add(spnTime);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.inactiveCaption);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSave = new JButton("MODIFICAR");
				if(appointment == null) {
					btnSave.setText("SALVAR");
				}
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (cbDoctor.getSelectedIndex() != 0 && !ftxtSSN.getText().contains("   -       - ") && !txtNamePatient.getText().isEmpty()) {

								try {
									ps = con.prepareStatement("SELECT p.name FROM medic m "
											+ "JOIN person p ON p.id = m.id_person");
									
									rs = ps.executeQuery();
									int i = 0;
									while (rs.next()) {
										if(cbDoctor.getSelectedIndex() == i) {
											medicPOS = i;
										}
										i++;
									}
									
								} catch (Exception e2) {
									JOptionPane.showMessageDialog(null, "error en modificar createappointment. "+e2.toString());
								}
							
							if(appointment == null) {
								
								try {
									ps = con.prepareStatement("INSERT INTO appointment(id, name_patient, "
											+ "ssn_patient, phone_patient, description, status, id_medic) VALUES(?,?,?,?,?,?,?)");
									ps.setString(1, txtCode.getText());
									ps.setString(2, txtNamePatient.getText());
									ps.setString(3, ftxtSSN.getText());
									ps.setString(4, ftxtPhone.getText());
									ps.setString(5, txtaDescription.getText());
									ps.setString(6, (String)cbStatus.getSelectedItem());
									ps.setInt(7, cbDoctor.getSelectedIndex());

									ps.executeUpdate();

									JOptionPane.showMessageDialog(null, "¡Se ha guardado!");
									
									
									
								} catch (Exception e2) {
									JOptionPane.showMessageDialog(null, "error dentro de insert createappointment. " + e2.toString());
									e2.printStackTrace();
								}
								clean();
								MSecretaryPanel.loadSQLApp();
								
							} else { 
								

								try {
									ps = con.prepareStatement("UPDATE appointment SET name_patient=?, ssn_patient=?, phone_patient=?,"
											+ "description=?, status=?, id_medic=? WHERE id=?");
									ps.setString(1, txtNamePatient.getText());
									ps.setString(2, ftxtSSN.getText());
									ps.setString(3, ftxtPhone.getText());
									ps.setString(4, txtaDescription.getText());
									ps.setString(5, (String)cbStatus.getSelectedItem());
									ps.setInt(6, cbDoctor.getSelectedIndex());
									ps.setString(7, appointment);

									ps.executeUpdate();

									JOptionPane.showMessageDialog(null, "¡Se ha modificado!");
									
									
									
								} catch (Exception e2) {
									JOptionPane.showMessageDialog(null, "error dentro de update createappointment. " + e2.toString());
									e2.printStackTrace();
								}
								dispose();
								MSecretaryPanel.loadSQLApp();
								MMedicPanel.loadSQLApp();
								AppointmentInfo.refreshChart();
							}
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
		loadSQLDoctors();
		loadSQLApp();
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
	

	private void loadSQLApp() {
		if (appointment != null) {
			try {
				
				ps = con.prepareStatement("SELECT id, name_patient, ssn_patient, phone_patient, "
						+ "description, status, /*date_a, time_a,*/ id_medic FROM appointment "
						+ "WHERE id = ?");
				ps.setString(1, appointment); //ESTE ES EL ID, PRIMERA POSICION
				rs = ps.executeQuery();
				
				while (rs.next()) {
					txtCode.setText(rs.getString("id"));
					txtNamePatient.setText(rs.getString("name_patient"));
					ftxtSSN.setText(rs.getString("ssn_patient"));
					ftxtPhone.setText(rs.getString("phone_patient"));
					txtaDescription.setText(rs.getString("description"));
					cbStatus.setSelectedItem(rs.getString("status"));
					cbDoctor.setSelectedIndex(rs.getInt("id_medic"));
					

				}
				JOptionPane.showMessageDialog(null, "ALM SE ESTA VIENDO");
				
				
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "error dentro del loadsqlapp create app. " +e.toString());
				e.printStackTrace();
			}
		}
	}
	

	private void loadSQLDoctors() {
		cbDoctor.removeAllItems();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT person.name FROM medic "
					+ "JOIN person ON person.id = medic.id_person");
			
			
			rs = ps.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				cbDoctor.addItem(name);
			}

			cbDoctor.insertItemAt(new String("<Seleccionar>"), 0);
			cbDoctor.setSelectedIndex(0);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "error dentro del loadsqldoctor create appointment. " +e.toString());
			e.printStackTrace();
		}
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

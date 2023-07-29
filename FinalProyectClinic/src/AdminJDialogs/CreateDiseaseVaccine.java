package AdminJDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import AdminPanels.ListDisease;
import AdminPanels.ListVaccine;
import logic.Clinic;
import logic.Disease;
import logic.Vaccine;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class CreateDiseaseVaccine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCode = new JTextField();
	private JTextField txtName;
	private JLabel lblTitle;
	private JTextArea txtDesc;
	private Disease disease = null;
	private Vaccine vaccine = null;
	private String type = null;
	private JCheckBox chckbxIsWatched = new JCheckBox("En Vigilancia");


	/**
	 * Create the dialog.
	 */
	public CreateDiseaseVaccine(String typeToRegister, Disease dis, Vaccine vac) {
		
		disease = dis;
		vaccine = vac;
		type = typeToRegister;
		
		setTitle("Modificar");
		if(disease == null && vaccine == null) {
			setTitle("Registrar");
		}
		
		if(type.equalsIgnoreCase("disease")) {
			lblTitle = new JLabel("REGISTRO ENFERMEDAD");
			chckbxIsWatched.setVisible(true);
			txtCode.setText(getCodeDisease(Clinic.getInstance().codeDisease));
		}else {
			lblTitle = new JLabel("REGISTRO VACUNA");
			chckbxIsWatched.setVisible(false);
			txtCode.setText(getCodeVaccine(Clinic.getInstance().codeVaccine));
		}
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		{
			
			lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblTitle.setBounds(200, 20, 230, 25);
			contentPanel.add(lblTitle);
		}
		{
			JLabel lblCode = new JLabel("C\u00F3digo");
			lblCode.setBounds(32, 9, 46, 14);
			contentPanel.add(lblCode);

			
			txtCode.setEditable(false);
			txtCode.setColumns(10);
			txtCode.setBounds(32, 32, 120, 20);
			contentPanel.add(txtCode);

			JLabel lblAddress = new JLabel("Descripci\u00F3n");
			lblAddress.setBounds(32, 113, 120, 14);
			contentPanel.add(lblAddress);

			txtDesc = new JTextArea();
			txtDesc.setLineWrap(true);
			txtDesc.setBounds(32, 136, 376, 73);
			contentPanel.add(txtDesc);

			JLabel lblName = new JLabel("Nombre");
			lblName.setBounds(32, 61, 46, 14);
			contentPanel.add(lblName);
			
			txtName = new JTextField();
			txtName.setColumns(10);
			txtName.setBounds(32, 84, 185, 20);
			contentPanel.add(txtName);
			
			chckbxIsWatched.setBackground(SystemColor.activeCaption);
			chckbxIsWatched.setBounds(240, 82, 113, 25);
			contentPanel.add(chckbxIsWatched);

			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.inactiveCaption);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCreate = new JButton("MODIFICAR");
				if(disease == null && vaccine == null) {
					btnCreate.setText("REGISTRAR");
				}
				btnCreate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if (!txtCode.getText().isEmpty() && !txtName.getText().isEmpty() && !txtDesc.getText().isEmpty()) {
							if(vaccine == null && disease == null) {
								if(type.equalsIgnoreCase("disease")) {
									Disease insDisease = new Disease(txtCode.getText(), txtName.getText(), txtDesc.getText(), chckbxIsWatched.isSelected());
									Clinic.getInstance().insertDisease(insDisease);
									JOptionPane.showMessageDialog(null, "¡Registro de Enfermedad Satisfactoria!", "Registrar", JOptionPane.INFORMATION_MESSAGE);
									clear();
								} else {
									Vaccine insVaccine = new Vaccine(txtCode.getText(), txtName.getText(), txtDesc.getText());
									Clinic.getInstance().insertVaccine(insVaccine);
									JOptionPane.showMessageDialog(null, "¡Registro de Vacuna Satisfactoria!", "Registrar", JOptionPane.INFORMATION_MESSAGE);
									clear();
								}
							} else  {
								if (type.equalsIgnoreCase("disease")){
									disease.setCode(txtCode.getText());
									disease.setName(txtName.getText());
									disease.setDescription(txtDesc.getText());
									disease.setWatched(chckbxIsWatched.isSelected());
									Clinic.getInstance().modifiedDisease(disease);
									dispose();
								} else {
									vaccine.setCode(txtCode.getText());
									vaccine.setName(txtName.getText());
									vaccine.setDescription(txtDesc.getText());
									Clinic.getInstance().modifiedVaccine(vaccine);
									dispose();
								}
							}
							ListDisease.loadDisease();
							ListVaccine.loadVaccine();
								
						} else {
							JOptionPane.showMessageDialog(null, "¡Parámetro(s) sin completar!\nPor favor completar los campos.", "Información Vacía", JOptionPane.ERROR_MESSAGE);
						}
						
						
					}

				});
				buttonPane.add(btnCreate);
				getRootPane().setDefaultButton(btnCreate);
			}
			{
				JButton btnCancel = new JButton("CANCEL");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(btnCancel);
			}
		}
		loadVD();
	}

	private void clear() {
		txtCode.setText("");
		txtName.setText("");
		txtDesc.setText("");	
		
		if (type.equalsIgnoreCase("disease")) {
			chckbxIsWatched.setSelected(false);
		}
	}
	
	private void loadVD() {
		if (disease != null) {
			txtCode.setText(disease.getCode());
			txtName.setText(disease.getName());
			txtDesc.setText(disease.getDescription());
			chckbxIsWatched.setSelected(disease.isWatched());
		}
		else if (vaccine != null) {
			txtCode.setText(vaccine.getCode());
			txtName.setText(vaccine.getName());
			txtDesc.setText(vaccine.getDescription());
		}
	}
	
	private static String getCodeVaccine(int codeVaccine) {
		int total = codeVaccine / 10;
		String code = null;
		
		code = "V-0000" + codeVaccine;
		
		if (total >= 1 && total < 10) {
			code = "V-000" + codeVaccine;
		}
		else if (total >= 10 && total < 100) {
			code = "V-00" + codeVaccine;
		}
		else if (total >= 100 && total < 1000) {
			code = "V-0" + codeVaccine;
		}
		else if (total >= 1000) {
			code = "V-" + codeVaccine;
		}
		
		return code;
	}

	
	private static String getCodeDisease(int codeDisease) {
		int total = codeDisease / 10;
		String code = null;
		
		code = "D-0000" + codeDisease;
		
		if (total >= 1 && total < 10) {
			code = "D-000" + codeDisease;
		}
		else if (total >= 10 && total < 100) {
			code = "D-00" + codeDisease;
		}
		else if (total >= 100 && total < 1000) {
			code = "D-0" + codeDisease;
		}
		else if (total >= 1000) {
			code = "D-" + codeDisease;
		}
		
		return code;
	}
}

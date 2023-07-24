package AdminJDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import AdminPanels.ListDiseaseVaccine;
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

public class CreateDiseaseVaccine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCode;
	private JTextField txtName;
	private JLabel lblTitle;
	private JTextArea txtADesc;
	private Disease disease = null;
	private Vaccine vaccine = null;
	private int VD;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateDiseaseVaccine dialog = new CreateDiseaseVaccine(0, null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateDiseaseVaccine(int type, Disease dis, Vaccine vac) {
		
		disease = dis;
		vaccine = vac;
		VD = type;
		
		setTitle("Modificar");
		if(disease == null || vaccine == null) {
			setTitle("Registrar");
		}
		
		if(VD == 0) {
			lblTitle = new JLabel("NUEVA ENFERMEDAD");
		}else {
			lblTitle = new JLabel("NUEVA VACUNA");
		}
		
		setLocationRelativeTo(null);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			
			lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblTitle.setBounds(114, 11, 202, 25);
			contentPanel.add(lblTitle);
		}
		{
			JLabel lblCode = new JLabel("C\u00F3digo");
			lblCode.setBounds(32, 59, 46, 14);
			contentPanel.add(lblCode);

			txtCode = new JTextField();
			txtCode.setColumns(10);
			txtCode.setBounds(32, 84, 185, 20);
			contentPanel.add(txtCode);

			JLabel lblAddress = new JLabel("Descripci\u00F3n");
			lblAddress.setBounds(32, 115, 120, 14);
			contentPanel.add(lblAddress);

			txtADesc = new JTextArea();
			txtADesc.setLineWrap(true);
			txtADesc.setBounds(32, 140, 376, 73);
			contentPanel.add(txtADesc);

			JLabel lblName = new JLabel("Nombre");
			lblName.setBounds(223, 59, 46, 14);
			contentPanel.add(lblName);
			
			txtName = new JTextField();
			txtName.setColumns(10);
			txtName.setBounds(223, 84, 185, 20);
			contentPanel.add(txtName);

			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.inactiveCaption);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCreate = new JButton("REGISTRAR");
				if(disease == null || vaccine == null) {
					btnCreate.setText("SALVAR");
				}
				btnCreate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(VD == 0 && disease == null) {
							Disease insDisease = new Disease(txtCode.getText(), txtName.getText(), txtADesc.getText(), true);
							Clinic.getInstance().insertDisease(insDisease);
							//
							JOptionPane.showMessageDialog(null, "¡Registro de Enfermedad Satisfactoria!", "Registrar", JOptionPane.INFORMATION_MESSAGE);
							clear();
							ListDiseaseVaccine.loadDisease();
						} else if(VD == 1 && vaccine == null) {
							Vaccine insVaccine = new Vaccine(txtCode.getText(), txtName.getText(), txtADesc.getText());
							Clinic.getInstance().insertVaccine(insVaccine);
							//
							JOptionPane.showMessageDialog(null, "¡Registro de Vacuna Satisfactoria!", "Registrar", JOptionPane.INFORMATION_MESSAGE);
							clear();
							ListDiseaseVaccine.loadVaccine();
							
						} else if (VD == 0){
							disease.setCode(txtCode.getText());
							disease.setName(txtName.getText());
							disease.setDescription(txtADesc.getText());
							disease.setWatched(true);
							
							Clinic.getInstance().modifiedDisease(disease);
							dispose();
							ListDiseaseVaccine.loadDisease();							
						} else {
							vaccine.setCode(txtCode.getText());
							vaccine.setName(txtName.getText());
							vaccine.setDescription(txtADesc.getText());
							Clinic.getInstance().modifiedVaccine(vaccine);
							dispose();
							ListDiseaseVaccine.loadVaccine();
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
	}

	private void clear() {
		txtCode.setText("");
		txtName.setText("");
		txtADesc.setText("");	
	}
	
}

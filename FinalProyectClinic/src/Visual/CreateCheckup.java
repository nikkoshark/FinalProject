package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CheckupPanels.CheckupsRecord;
import CheckupPanels.DiseasePanel;
import CheckupPanels.InfoPanel;
import CheckupPanels.MedicalRecordPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class CreateCheckup extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private InfoPanel infoPanel;
	private DiseasePanel diseasePanel;
	private MedicalRecordPanel medicalRecordPanel;
	private CheckupsRecord checkupsRecord;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateCheckup dialog = new CreateCheckup();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateCheckup() {
		setBounds(100, 100, 691, 501);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		

		infoPanel = new InfoPanel();
		diseasePanel = new DiseasePanel();
		medicalRecordPanel = new MedicalRecordPanel();
		checkupsRecord = new CheckupsRecord();
		
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("CREATE CHECKUP");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel.setBounds(151, 11, 223, 33);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblBetterAsPanel = new JLabel("BETTER AS PANEL?");
			lblBetterAsPanel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblBetterAsPanel.setBounds(358, 11, 223, 33);
			contentPanel.add(lblBetterAsPanel);
		}
		{
			JPanel panel = new JPanel();
			panel.setBounds(10, 50, 265, 378);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				textField = new JTextField();
				textField.setEditable(false);
				textField.setBounds(10, 88, 120, 20);
				panel.add(textField);
				textField.setColumns(10);
			}
			{
				textField_1 = new JTextField();
				textField_1.setEditable(false);
				textField_1.setColumns(10);
				textField_1.setBounds(140, 88, 120, 20);
				panel.add(textField_1);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Nombre");
				lblNewLabel_1.setBounds(10, 69, 46, 14);
				panel.add(lblNewLabel_1);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Apellido");
				lblNewLabel_2.setBounds(140, 69, 46, 14);
				panel.add(lblNewLabel_2);
			}
			{
				JLabel lblCdula = new JLabel("C\u00E9dula");
				lblCdula.setBounds(10, 22, 46, 14);
				panel.add(lblCdula);
			}
			{
				textField_2 = new JTextField();
				textField_2.setEditable(false);
				textField_2.setColumns(10);
				textField_2.setBounds(10, 41, 120, 20);
				panel.add(textField_2);
			}
			{
				JButton btnNewButton = new JButton("Crear Nuevo Paciente");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CreatePatient createPat = new CreatePatient();
						createPat.setModal(true);
						createPat.setVisible(true);
					}
				});
				btnNewButton.setBounds(10, 344, 250, 23);
				panel.add(btnNewButton);
			}
			{
				JButton btnNewButton_1 = new JButton("Buscar Paciente");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						SearchPatient searchPat = new SearchPatient();
						searchPat.setModal(true);
						searchPat.setVisible(true);
					}
				});
				btnNewButton_1.setBounds(10, 314, 250, 23);
				panel.add(btnNewButton_1);
			}
		}
		{
			JPanel infoP = new JPanel();
			infoP.setBackground(SystemColor.inactiveCaption);
			infoP.setBounds(285, 70, 390, 358);
			contentPanel.add(infoP);
			infoP.setLayout(null);
			
			
			infoP.add(infoPanel);
			infoP.add(diseasePanel);
			infoP.add(medicalRecordPanel);
			infoP.add(checkupsRecord);
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.inactiveCaption);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		menuclicked(infoPanel); //default
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
			JButton btnenf = new JButton("enf");
			btnenf.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					menuclicked(diseasePanel);
				}
			});
			btnenf.setBounds(383, 50, 89, 23);
			contentPanel.add(btnenf);
		}
		{
			JButton btnhis = new JButton("historial");
			btnhis.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					menuclicked(medicalRecordPanel);
				}
			});
			btnhis.setBounds(493, 50, 89, 23);
			contentPanel.add(btnhis);
		}
	}
	


	private void menuclicked(JPanel panel) {
		infoPanel.setVisible(false);
		diseasePanel.setVisible(false);
		medicalRecordPanel.setVisible(false);
		checkupsRecord.setVisible(false);
		
		panel.setVisible(true);
		
	}
	
}

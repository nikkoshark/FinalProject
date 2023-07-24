package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CreatePatient extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreatePatient dialog = new CreatePatient();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreatePatient() {
		setBounds(100, 100, 504, 390);
		//setResizable(false);
		//setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("NUEVO PACIENTE");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel.setBounds(160, 11, 174, 25);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("C\u00F3digo");
			lblNewLabel_1.setBounds(34, 47, 46, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblCdula = new JLabel("C\u00E9dula");
			lblCdula.setBounds(160, 47, 46, 14);
			contentPanel.add(lblCdula);
		}
		{
			JLabel lblSexo = new JLabel("Sexo");
			lblSexo.setBounds(308, 47, 46, 14);
			contentPanel.add(lblSexo);
		}
		{
			JLabel lblNombres = new JLabel("Nombre[s]");
			lblNombres.setBounds(33, 100, 85, 14);
			contentPanel.add(lblNombres);
		}
		{
			JLabel lblApellidos = new JLabel("Apellido[s]");
			lblApellidos.setBounds(255, 100, 126, 14);
			contentPanel.add(lblApellidos);
		}
		{
			JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento");
			lblFechaDeNacimiento.setBounds(33, 154, 112, 14);
			contentPanel.add(lblFechaDeNacimiento);
		}
		{
			JLabel lblEdad = new JLabel("Edad");
			lblEdad.setBounds(260, 154, 46, 14);
			contentPanel.add(lblEdad);
		}
		{
			JLabel lblTelfono = new JLabel("Tel\u00E9fono");
			lblTelfono.setBounds(341, 208, 111, 14);
			contentPanel.add(lblTelfono);
		}
		{
			JLabel lblTelfonoAlternativo = new JLabel("Tel\u00E9fono Alternativo");
			lblTelfonoAlternativo.setBounds(341, 258, 137, 14);
			contentPanel.add(lblTelfonoAlternativo);
		}
		{
			JLabel lblDireccin = new JLabel("Direcci\u00F3n");
			lblDireccin.setBounds(34, 208, 84, 14);
			contentPanel.add(lblDireccin);
		}
		{
			JTextArea textArea = new JTextArea();
			textArea.setLineWrap(true);
			textArea.setEditable(false);
			textArea.setBounds(33, 228, 298, 73);
			contentPanel.add(textArea);
		}
		{
			textField = new JTextField();
			textField.setEditable(false);
			textField.setColumns(10);
			textField.setBounds(34, 72, 111, 20);
			contentPanel.add(textField);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(160, 71, 138, 20);
			contentPanel.add(textField_1);
		}
		{
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			textField_3.setBounds(33, 125, 199, 20);
			contentPanel.add(textField_3);
		}
		{
			textField_4 = new JTextField();
			textField_4.setColumns(10);
			textField_4.setBounds(255, 123, 199, 20);
			contentPanel.add(textField_4);
		}
		{
			textField_5 = new JTextField();
			textField_5.setColumns(10);
			textField_5.setBounds(33, 178, 199, 20);
			contentPanel.add(textField_5);
		}
		{
			textField_6 = new JTextField();
			textField_6.setEditable(false);
			textField_6.setColumns(10);
			textField_6.setBounds(260, 178, 71, 20);
			contentPanel.add(textField_6);
		}
		{
			textField_7 = new JTextField();
			textField_7.setColumns(10);
			textField_7.setBounds(341, 227, 111, 20);
			contentPanel.add(textField_7);
		}
		{
			textField_8 = new JTextField();
			textField_8.setColumns(10);
			textField_8.setBounds(341, 281, 111, 20);
			contentPanel.add(textField_8);
		}
		{
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"<No Verificado>", "A+", "A-", "AB+", "AB-", "B+", "B-", "O+", "O-"}));
			comboBox.setBounds(341, 177, 111, 20);
			contentPanel.add(comboBox);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Tipo de Sangre");
			lblNewLabel_2.setBounds(341, 154, 111, 14);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Seleccionar>", "Femenino", "Masculino"}));
			comboBox.setBounds(308, 72, 144, 20);
			contentPanel.add(comboBox);
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}

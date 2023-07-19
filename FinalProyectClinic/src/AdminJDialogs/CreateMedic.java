package AdminJDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateMedic extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_4;
	private JTextField textField_10;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateMedic dialog = new CreateMedic();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateMedic() {
		setBounds(100, 100, 519, 399);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNuevoMdico = new JLabel("NUEVO M\u00C9DICO");
			lblNuevoMdico.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNuevoMdico.setBounds(174, 16, 174, 25);
			contentPanel.add(lblNuevoMdico);
		}
		{
			JLabel lblHorarioEntrada = new JLabel("Horario Entrada");
			lblHorarioEntrada.setBounds(346, 213, 120, 14);
			contentPanel.add(lblHorarioEntrada);
		}
		{
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			textField_2.setBounds(346, 238, 110, 20);
			contentPanel.add(textField_2);
		}
		{
			JLabel lblHorarioSalida = new JLabel("Horario Salida");
			lblHorarioSalida.setBounds(346, 261, 120, 14);
			contentPanel.add(lblHorarioSalida);
		}
		{
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			textField_3.setBounds(346, 286, 110, 20);
			contentPanel.add(textField_3);
		}
		{
			JLabel label = new JLabel("C\u00F3digo");
			label.setBounds(39, 52, 46, 14);
			contentPanel.add(label);
		}
		{
			textField = new JTextField();
			textField.setEditable(false);
			textField.setColumns(10);
			textField.setBounds(39, 77, 128, 20);
			contentPanel.add(textField);
		}
		{
			JLabel label = new JLabel("C\u00E9dula");
			label.setBounds(177, 52, 46, 14);
			contentPanel.add(label);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(177, 76, 142, 20);
			contentPanel.add(textField_1);
		}
		{
			JLabel label = new JLabel("Sexo");
			label.setBounds(329, 52, 46, 14);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Apellido[s]");
			label.setBounds(247, 107, 126, 14);
			contentPanel.add(label);
		}
		{
			textField_5 = new JTextField();
			textField_5.setColumns(10);
			textField_5.setBounds(247, 128, 212, 20);
			contentPanel.add(textField_5);
		}
		{
			textField_6 = new JTextField();
			textField_6.setColumns(10);
			textField_6.setBounds(38, 130, 199, 20);
			contentPanel.add(textField_6);
		}
		{
			JLabel label = new JLabel("Nombre[s]");
			label.setBounds(38, 105, 85, 14);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Fecha de Nacimiento");
			label.setBounds(38, 159, 112, 14);
			contentPanel.add(label);
		}
		{
			textField_7 = new JTextField();
			textField_7.setColumns(10);
			textField_7.setBounds(38, 183, 128, 20);
			contentPanel.add(textField_7);
		}
		{
			JLabel lblEspecialidad = new JLabel("Especialidad");
			lblEspecialidad.setBounds(177, 161, 110, 14);
			contentPanel.add(lblEspecialidad);
		}
		{
			textField_8 = new JTextField();
			textField_8.setColumns(10);
			textField_8.setBounds(177, 183, 159, 20);
			contentPanel.add(textField_8);
		}
		{
			JLabel label = new JLabel("Direcci\u00F3n");
			label.setBounds(39, 213, 84, 14);
			contentPanel.add(label);
		}
		{
			JTextArea textArea = new JTextArea();
			textArea.setLineWrap(true);
			textArea.setEditable(false);
			textArea.setBounds(38, 233, 182, 73);
			contentPanel.add(textArea);
		}
		{
			JLabel label = new JLabel("Tel\u00E9fono");
			label.setBounds(346, 159, 46, 14);
			contentPanel.add(label);
		}
		{
			textField_9 = new JTextField();
			textField_9.setColumns(10);
			textField_9.setBounds(346, 184, 111, 20);
			contentPanel.add(textField_9);
		}
		{
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"<SELECCIONAR>", "Femenino", "Masculino"}));
			comboBox.setBounds(329, 77, 127, 20);
			contentPanel.add(comboBox);
		}
		{
			JLabel lblUsuario = new JLabel("Usuario");
			lblUsuario.setBounds(228, 213, 120, 14);
			contentPanel.add(lblUsuario);
		}
		{
			textField_4 = new JTextField();
			textField_4.setColumns(10);
			textField_4.setBounds(228, 238, 110, 20);
			contentPanel.add(textField_4);
		}
		{
			JLabel lblContrasea = new JLabel("Contrase\u00F1a");
			lblContrasea.setBounds(228, 261, 120, 14);
			contentPanel.add(lblContrasea);
		}
		{
			textField_10 = new JTextField();
			textField_10.setColumns(10);
			textField_10.setBounds(228, 286, 110, 20);
			contentPanel.add(textField_10);
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
	}

}

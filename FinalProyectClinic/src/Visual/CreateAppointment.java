package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CreateAppointment extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateAppointment dialog = new CreateAppointment();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateAppointment() {
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
			lblNewLabel.setBounds(188, 42, 236, 34);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("C\u00F3digo");
			lblNewLabel_1.setBounds(116, 90, 46, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblIdPaciente = new JLabel("ID Paciente");
			lblIdPaciente.setBounds(116, 146, 72, 14);
			contentPanel.add(lblIdPaciente);
		}
		{
			JLabel lblNombrePaciente = new JLabel("Nombre Paciente");
			lblNombrePaciente.setBounds(247, 146, 110, 14);
			contentPanel.add(lblNombrePaciente);
		}
		{
			JLabel lblTelfono = new JLabel("Tel\u00E9fono");
			lblTelfono.setBounds(378, 146, 86, 14);
			contentPanel.add(lblTelfono);
		}
		{
			JLabel lblCdigoDoctor = new JLabel("C\u00F3digo Doctor");
			lblCdigoDoctor.setBounds(247, 90, 110, 14);
			contentPanel.add(lblCdigoDoctor);
		}
		{
			JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
			lblDescripcin.setBounds(117, 200, 84, 14);
			contentPanel.add(lblDescripcin);
		}
		{
			JLabel lblFechaDeCita = new JLabel("Fecha de Cita");
			lblFechaDeCita.setBounds(461, 11, 94, 14);
			contentPanel.add(lblFechaDeCita);
		}
		{
			JLabel lblStatus = new JLabel("Status");
			lblStatus.setBounds(378, 90, 46, 14);
			contentPanel.add(lblStatus);
		}
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBounds(116, 220, 348, 73);
		contentPanel.add(textArea);
		
		textField = new JTextField();
		textField.setBounds(115, 169, 86, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(247, 169, 86, 20);
		contentPanel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(378, 169, 86, 20);
		contentPanel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(461, 34, 94, 20);
		contentPanel.add(textField_3);
		{
			textField_4 = new JTextField();
			textField_4.setColumns(10);
			textField_4.setBounds(247, 115, 86, 20);
			contentPanel.add(textField_4);
		}
		{
			textField_5 = new JTextField();
			textField_5.setColumns(10);
			textField_5.setBounds(378, 115, 86, 20);
			contentPanel.add(textField_5);
		}
		{
			textField_6 = new JTextField();
			textField_6.setEditable(false);
			textField_6.setColumns(10);
			textField_6.setBounds(116, 115, 86, 20);
			contentPanel.add(textField_6);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.inactiveCaption);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("SAVE");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("CANCEL");
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

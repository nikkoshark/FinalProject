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

public class CreateDiseaseVaccine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblTitle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateDiseaseVaccine dialog = new CreateDiseaseVaccine(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateDiseaseVaccine(String type) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			if(type == "Disease") {
				lblTitle = new JLabel("NUEVA ENFERMEDAD");
			}else {
				lblTitle = new JLabel("NUEVA VACUNA");
			}
			
			lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblTitle.setBounds(114, 11, 202, 25);
			contentPanel.add(lblTitle);
		}
		{
			JLabel label = new JLabel("C\u00F3digo");
			label.setBounds(32, 59, 46, 14);
			contentPanel.add(label);
		}
		{
			textField = new JTextField();
			textField.setColumns(10);
			textField.setBounds(32, 84, 185, 20);
			contentPanel.add(textField);
		}
		{
			JLabel label = new JLabel("Descripci\u00F3n");
			label.setBounds(32, 115, 120, 14);
			contentPanel.add(label);
		}
		{
			JTextArea textArea = new JTextArea();
			textArea.setLineWrap(true);
			textArea.setEditable(false);
			textArea.setBounds(32, 140, 376, 73);
			contentPanel.add(textArea);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(223, 84, 185, 20);
			contentPanel.add(textField_1);
		}
		{
			JLabel label = new JLabel("Nombre");
			label.setBounds(223, 59, 46, 14);
			contentPanel.add(label);
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

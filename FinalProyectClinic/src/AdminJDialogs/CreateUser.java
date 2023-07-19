package AdminJDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Clinic;
import login.User;
import sun.misc.Cleaner;

import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUser;
	private JPasswordField txtPsw;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateUser dialog = new CreateUser();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateUser() {
		setBounds(100, 100, 518, 389);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTitle = new JLabel("NUEVO USUARIO");
			lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblTitle.setBounds(159, 44, 188, 25);
			contentPanel.add(lblTitle);
		}
		{
			JLabel lblHorarioEntrada = new JLabel("Usuario");
			lblHorarioEntrada.setBounds(122, 114, 120, 14);
			contentPanel.add(lblHorarioEntrada);
		}
		{
			txtUser = new JTextField();
			txtUser.setColumns(10);
			txtUser.setBounds(122, 139, 110, 20);
			contentPanel.add(txtUser);
		}
		{
			JLabel lblHorarioSalida = new JLabel("Contrase\u00F1a");
			lblHorarioSalida.setBounds(122, 173, 120, 14);
			contentPanel.add(lblHorarioSalida);
		}
		{
			JLabel lblTipo = new JLabel("Tipo");
			lblTipo.setBounds(262, 114, 46, 14);
			contentPanel.add(lblTipo);
		}
		{
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"<SELECCIONAR>", "Secretary", "Medic", "Admin"}));
			comboBox.setBounds(262, 139, 127, 20);
			contentPanel.add(comboBox);
		}
		
		txtPsw = new JPasswordField();
		txtPsw.setBounds(122, 198, 110, 20);
		contentPanel.add(txtPsw);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.inactiveCaption);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("SAVE");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						User insUser = new User(String.valueOf(comboBox.getSelectedItem()), txtUser.getText(), String.valueOf(txtPsw.getPassword()));
						Clinic.getInstance().insertUser(insUser);
						clean();
					}

				});
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
	
	private void clean() {
		txtUser.setText("");
		txtPsw.setText("");
		comboBox.setSelectedIndex(0);
						
	}
}

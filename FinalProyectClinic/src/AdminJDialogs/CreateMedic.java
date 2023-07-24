package AdminJDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Clinic;
import logic.Medic;

import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import com.toedter.calendar.JDateChooser;
import javax.swing.SpinnerListModel;

public class CreateMedic extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCode;
	private JTextField txtSSN;
	private JTextField txtLastName;
	private JTextField txtName;
	private JTextField txtSpeciality;
	private JTextField txtPhone;
	private JTextField txtUser;
	private JTextField txtPsw;
	private JTextArea txtAAddress;
	private JComboBox cbSex;

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
		setLocationRelativeTo(null);
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
			
			JLabel lblHorarioEntrada = new JLabel("Horario Entrada");
			lblHorarioEntrada.setBounds(346, 213, 120, 14);
			contentPanel.add(lblHorarioEntrada);

			JLabel lblHorarioSalida = new JLabel("Horario Salida");
			lblHorarioSalida.setBounds(346, 261, 120, 14);
			contentPanel.add(lblHorarioSalida);

			JLabel lblCode = new JLabel("C\u00F3digo");
			lblCode.setBounds(39, 52, 46, 14);
			contentPanel.add(lblCode);

			txtCode = new JTextField();
			txtCode.setEditable(false);
			txtCode.setColumns(10);
			txtCode.setBounds(39, 77, 128, 20);
			contentPanel.add(txtCode);
		}
		{
			JLabel lblCedula = new JLabel("C\u00E9dula");
			lblCedula.setBounds(177, 52, 46, 14);
			contentPanel.add(lblCedula);
		}
		{
			txtSSN = new JTextField();
			txtSSN.setColumns(10);
			txtSSN.setBounds(177, 76, 142, 20);
			contentPanel.add(txtSSN);
		}
		{
			JLabel lblSexo = new JLabel("Sexo");
			lblSexo.setBounds(329, 52, 46, 14);
			contentPanel.add(lblSexo);
		}
		{
			JLabel lblApellido = new JLabel("Apellido[s]");
			lblApellido.setBounds(247, 107, 126, 14);
			contentPanel.add(lblApellido);
		}
		{
			txtLastName = new JTextField();
			txtLastName.setColumns(10);
			txtLastName.setBounds(247, 128, 212, 20);
			contentPanel.add(txtLastName);
		}
		{
			txtName = new JTextField();
			txtName.setColumns(10);
			txtName.setBounds(38, 130, 199, 20);
			contentPanel.add(txtName);
		}
		{
			JLabel lblNombre = new JLabel("Nombre[s]");
			lblNombre.setBounds(38, 105, 85, 14);
			contentPanel.add(lblNombre);
		}
		{
			JLabel lblDate = new JLabel("Fecha de Nacimiento");
			lblDate.setBounds(38, 159, 112, 14);
			contentPanel.add(lblDate);
		}
		{
			JLabel lblEspecialidad = new JLabel("Especialidad");
			lblEspecialidad.setBounds(177, 161, 110, 14);
			contentPanel.add(lblEspecialidad);
		}
		{
			txtSpeciality = new JTextField();
			txtSpeciality.setColumns(10);
			txtSpeciality.setBounds(177, 183, 159, 20);
			contentPanel.add(txtSpeciality);
		}
		{
			JLabel lblDir = new JLabel("Direcci\u00F3n");
			lblDir.setBounds(39, 213, 84, 14);
			contentPanel.add(lblDir);
		}
		{
			txtAAddress = new JTextArea();
			txtAAddress.setLineWrap(true);
			txtAAddress.setEditable(false);
			txtAAddress.setBounds(38, 233, 182, 73);
			contentPanel.add(txtAAddress);
		}
		{
			JLabel lblTel = new JLabel("Tel\u00E9fono");
			lblTel.setBounds(346, 159, 46, 14);
			contentPanel.add(lblTel);
		}
		{
			txtPhone = new JTextField();
			txtPhone.setColumns(10);
			txtPhone.setBounds(346, 184, 111, 20);
			contentPanel.add(txtPhone);
		}
		{
			cbSex = new JComboBox();
			cbSex.setModel(new DefaultComboBoxModel(new String[] {"<SELECCIONAR>", "F", "M"}));
			cbSex.setBounds(329, 77, 127, 20);
			contentPanel.add(cbSex);
		}
		{
			JLabel lblUsuario = new JLabel("Usuario");
			lblUsuario.setBounds(228, 213, 120, 14);
			contentPanel.add(lblUsuario);
		}
		{
			txtUser = new JTextField();
			txtUser.setColumns(10);
			txtUser.setBounds(228, 238, 110, 20);
			contentPanel.add(txtUser);
		}
		{
			JLabel lblContrasea = new JLabel("Contrase\u00F1a");
			lblContrasea.setBounds(228, 261, 120, 14);
			contentPanel.add(lblContrasea);
		}
		{
			txtPsw = new JTextField();
			txtPsw.setColumns(10);
			txtPsw.setBounds(228, 286, 110, 20);
			contentPanel.add(txtPsw);
		}
		
		JSpinner spnEntry = new JSpinner();
		spnEntry.setModel(new SpinnerListModel(new String[] {"0800", "0830", "0900", "0930", "1000", "1030", "1100", "1130", "1200", "1230", "1300", "1330", "1400", "1430", "1500", "1530", "1600", "1630", "1700", "1730", "1800"}));
		spnEntry.setBounds(346, 238, 110, 20);
		contentPanel.add(spnEntry);
		
		JSpinner spnExit = new JSpinner();
		spnExit.setBounds(346, 286, 110, 20);
		contentPanel.add(spnExit);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(36, 183, 131, 20);
		contentPanel.add(dateChooser);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.inactiveCaption);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAdd = new JButton("INSERTAR");
				btnAdd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//Medic medic = new Medic(txtCode.getText(), txtSSN.getText(), txtName.getText(), txtLastName.getText(), txtPhone.getText(), txtAAddress.getText(), new Date()/*txtDate.getText()*/, String.valueOf(cbSex.getSelectedItem()), txtSpeciality.getText(), true);
						//Clinic.getInstance().insertPerson(medic);
					}
				});
				btnAdd.setActionCommand("OK");
				buttonPane.add(btnAdd);
				getRootPane().setDefaultButton(btnAdd);
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

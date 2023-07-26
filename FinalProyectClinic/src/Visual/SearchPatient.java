package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logic.Clinic;
import logic.Patient;
import logic.Person;

import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SearchPatient extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private JTextField searchBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SearchPatient dialog = new SearchPatient();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SearchPatient() {
		setBounds(100, 100, 581, 381);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblBuscarPaciente = new JLabel("BUSCAR PACIENTE");
			lblBuscarPaciente.setBounds(191, 11, 212, 25);
			lblBuscarPaciente.setFont(new Font("Tahoma", Font.PLAIN, 20));
			contentPanel.add(lblBuscarPaciente);
		}
		{
			JPanel panel = new JPanel();
			panel.setBounds(82, 51, 409, 166);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable();
					scrollPane.setViewportView(table);
					model = new DefaultTableModel();
					String[] headers = {"Código", "Nombre", "Apellido", "Sexo"}; //HEADERS FOR THE LIST
					model.setColumnIdentifiers(headers);
					table.setModel(model);
				}
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBounds(82, 214, 409, 65);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JRadioButton rdbtnNewRadioButton = new JRadioButton("C\u00E9dula");
			rdbtnNewRadioButton.setBounds(47, 7, 109, 23);
			panel.add(rdbtnNewRadioButton);
			
			JRadioButton rdbtnNombre = new JRadioButton("Nombre");
			rdbtnNombre.setBounds(170, 7, 109, 23);
			panel.add(rdbtnNombre);
			
			JRadioButton rdbtnApellido = new JRadioButton("Apellido");
			rdbtnApellido.setBounds(294, 7, 109, 23);
			panel.add(rdbtnApellido);
			{
				searchBar = new JTextField();
				searchBar.setBounds(137, 34, 166, 20);
				panel.add(searchBar);
				searchBar.setColumns(10);
			}
			
			JButton btnSearch = new JButton("BUSCAR");
			btnSearch.setBounds(313, 33, 89, 23);
			panel.add(btnSearch);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.inactiveCaption);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnEdit = new JButton("Ver Expediente");
				buttonPane.add(btnEdit);
				getRootPane().setDefaultButton(btnEdit);
			}
			{
				JButton btnClose = new JButton("Cancelar");
				buttonPane.add(btnClose);
			}
		}
		loadPatient();
	}
	
	public static void loadPatient() {
		model.setRowCount(0);
		row = new Object[table.getColumnCount()];
		
		for (Person patient : Clinic.getInstance().getMyPersons()) {
			if(patient instanceof Patient) {
				row[0] = patient.getSsn();
				row[1] = patient.getName();
				row[2] = patient.getLastName();
				row[3] = patient.getSex();
				model.addRow(row);
			}
		}
	}
	
	
	
}

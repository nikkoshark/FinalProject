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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SearchPatient extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private static JTextField txtSearchBar;
	private static JRadioButton rdbtnCedula;
	private static JRadioButton rdbtnNombre;
	private static JRadioButton rdbtnCodigo;

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
				scrollPane.setLocation(98, 0);
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable(){
						public boolean editCellAt(int row, int column, java.util.EventObject e) {
							return false;
						}
					};
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if (e.getClickCount() == 2) {
								System.out.println("dos click");
								int index = table.getSelectedRow();
								if(index>=0) {
									String ssn = (String) table.getModel().getValueAt(index, 1); 
									System.out.println(ssn);
									Person patient = Clinic.getInstance().searchPerson(ssn);
									System.out.println(patient.getName() + patient.getSsn());
									
									CreateCheckup createCheck = new CreateCheckup(null, patient);
									createCheck.setModal(true);
									createCheck.setVisible(true);
								}
							}
						}
					});
					scrollPane.setViewportView(table);
					model = new DefaultTableModel();
					String[] headers = {"Código", "Cedula", "Nombre", "Apellido"}; //HEADERS FOR THE LIST
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
			
			rdbtnCedula = new JRadioButton("C\u00E9dula");
			rdbtnCedula.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					changeRadioButton(rdbtnCedula);
				}
			});
			rdbtnCedula.setSelected(true);
			rdbtnCedula.setBounds(47, 7, 109, 23);
			panel.add(rdbtnCedula);
			
			rdbtnNombre = new JRadioButton("Nombre");
			rdbtnNombre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					changeRadioButton(rdbtnNombre);
				}
			});
			rdbtnNombre.setBounds(170, 7, 109, 23);
			panel.add(rdbtnNombre);
			
			rdbtnCodigo = new JRadioButton("Codigo");
			rdbtnCodigo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					changeRadioButton(rdbtnCodigo);
				}
			});
			rdbtnCodigo.setBounds(294, 7, 109, 23);
			panel.add(rdbtnCodigo);
			{
				txtSearchBar = new JTextField();
				txtSearchBar.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						loadPatient();
					}
				});
				txtSearchBar.setBounds(121, 34, 166, 20);
				panel.add(txtSearchBar);
				txtSearchBar.setColumns(10);
			}
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
				btnClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
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
				if (rdbtnCedula.isSelected()) {
					if (patient.getSsn().contains(txtSearchBar.getText())) {
						row[0] = patient.getCode();
						row[1] = patient.getSsn();
						row[2] = patient.getName();
						row[3] = patient.getLastName();
						model.addRow(row);
					}
				}else if (rdbtnCodigo.isSelected()) {
					if (patient.getCode().contains(txtSearchBar.getText())) {
						row[0] = patient.getCode();
						row[1] = patient.getSsn();
						row[2] = patient.getName();
						row[3] = patient.getLastName();
						model.addRow(row);
					}
				}else if (rdbtnNombre.isSelected()) {
					if (patient.getName().contains(txtSearchBar.getText())) {
						row[0] = patient.getCode();
						row[1] = patient.getSsn();
						row[2] = patient.getName();
						row[3] = patient.getLastName();
						model.addRow(row);
					}
				}
			}
		}
	}
	
	public void changeRadioButton(JRadioButton btn) {
		rdbtnCedula.setSelected(false);
		rdbtnCodigo.setSelected(false);
		rdbtnNombre.setSelected(false);
		
		btn.setSelected(true);
		
	}
	
}

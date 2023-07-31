package AdminPanels;

import javax.swing.JPanel;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import AdminJDialogs.CreateDiseaseVaccine;
import Dashboards.VaccinesInfo;
import logic.Clinic;
import logic.Vaccine;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ScrollPaneConstants;

public class ListVaccine extends JPanel {
	private static JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private Vaccine selVaccine = null;
	private JButton btnDelete;
	private JButton btnEdit;
	private VaccinesInfo vacin;

	/**
	 * Create the panel.
	 */
	public ListVaccine() {
		setBackground(SystemColor.activeCaption);
		setSize(1320, 551);
		setLayout(null);
		setVisible(false);
		
		//vacin = new VaccinesInfo(selVaccine);
		
		JLabel lblTitle = new JLabel("LISTAR VACUNAS");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setBounds(10, 11, 285, 42);
		add(lblTitle);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 64, 612, 415);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				
				System.out.println(index);
				
				if(index>=0) {
					System.out.println("paso");
					btnEdit.setEnabled(true);
					btnDelete.setEnabled(true);
					selVaccine = Clinic.getInstance().getMyVaccines().get(index);
					//VaccinesInfo.loadVacc();
				}
			}
		});
		model = new DefaultTableModel();
		String[] headers = {"Código", "Nombre", "Descripción"}; //HEADERS FOR THE LIST
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		btnDelete = new JButton("ELIMINAR");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la vacuna: " + selVaccine.getName() + "?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					Clinic.getInstance().removeVaccine(selVaccine);
					btnEdit.setEnabled(false);
					btnDelete.setEnabled(false);
					loadVaccine();
				}
				
			}
		});
		btnDelete.setBounds(436, 490, 89, 23);
		add(btnDelete);
		
		btnEdit = new JButton("EDITAR");
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateDiseaseVaccine regVaccine = new CreateDiseaseVaccine("vaccine", null, selVaccine);
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
				regVaccine.setModal(true);
				regVaccine.setVisible(true);
				
			}
		});
		btnEdit.setBounds(543, 490, 89, 23);
		add(btnEdit);
		
		JPanel dash = new JPanel();
		dash.setLayout(null);
		dash.setBackground(SystemColor.activeCaption);
		dash.setBounds(743, 80, 431, 415);
		add(dash);
		
		//dash.add(vacin);
		
		loadVaccine();
	}
	
	public static void loadVaccine() {
		model.setRowCount(0);
		row = new Object[table.getColumnCount()];

		for(Vaccine vaccine : Clinic.getInstance().getMyVaccines()) {
			row[0] = vaccine.getCode();
			row[1] = vaccine.getName();
			row[2] = vaccine.getDescription();
			model.addRow(row);
			
		}
	}
}

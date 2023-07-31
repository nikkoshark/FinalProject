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

import com.sun.org.apache.xml.internal.utils.Trie;

import AdminJDialogs.CreateDiseaseVaccine;
import Dashboards.DiseaseInfo;
import Dashboards.VaccinesInfo;
import logic.Clinic;
import logic.Disease;
import logic.Vaccine;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ScrollPaneConstants;
import Dashboards.UserInfo;

public class ListDisease extends JPanel {
	private static JTable table;
	private static DefaultTableModel model;
	private static Object[] row; 
	private JButton btnDelete;
	private JButton btnEdit;
	private Disease selDisease;
	private DiseaseInfo dinfo;

	/**
	 * Create the panel.
	 */
	public ListDisease() {
		setBackground(SystemColor.activeCaption);
		setSize(1320, 551);
		setLayout(null);
		setVisible(false);
		
		dinfo = new DiseaseInfo();
		
		JLabel lblTitle = new JLabel("LISTAR ENFERMEDADES");
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
				
				if(index>=0) {
					btnEdit.setEnabled(true);
					btnDelete.setEnabled(true);
					selDisease = Clinic.getInstance().getMyDiseases().get(index);
				}
			}
		});
		model = new DefaultTableModel();
		String[] headers = {"Código", "Nombre", "Descripción", "Estatus"};
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		btnDelete = new JButton("ELIMINAR");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(selDisease != null) {
					int option = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la enfermedad: " + selDisease.getName() + "?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						Clinic.getInstance().removeDisease(selDisease);
						btnEdit.setEnabled(false);
						btnDelete.setEnabled(false);
						loadDisease();
					}
				} 				
			}
		});
		btnDelete.setBounds(436, 490, 89, 23);
		add(btnDelete);
		
		btnEdit = new JButton("EDITAR");
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateDiseaseVaccine regDisease = new CreateDiseaseVaccine("disease", selDisease, null);
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
				regDisease.setModal(true);
				regDisease.setVisible(true);
			}
		});
		btnEdit.setBounds(543, 490, 89, 23);
		add(btnEdit);
		
		JPanel dash = new JPanel();
		dash.setLayout(null);
		dash.setBackground(SystemColor.activeCaption);
		dash.setBounds(743, 80, 431, 415);
		add(dash);
		
		dash.add(dinfo);

		loadDisease();
	}
	
	public static void loadDisease() {
		model.setRowCount(0);
		row = new Object[table.getColumnCount()];

		for(Disease disease : Clinic.getInstance().getMyDiseases()) {
			row[0] = disease.getCode();
			row[1] = disease.getName();
			row[2] = disease.getDescription();
			row[3] = " Neutro";
			if (disease.isWatched()) {					
				row[3] = " Vigilado";
			}
			model.addRow(row);
			
		}
	}
}

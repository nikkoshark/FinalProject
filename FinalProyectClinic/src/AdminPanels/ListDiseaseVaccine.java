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

import libraries.org.jfree.data.general.ValueDataset;

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

public class ListDiseaseVaccine extends JPanel {
	private static JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private JLabel lblTitle;
	private JButton btnDelete;
	private JButton btnEdit;
	private Disease selDisease;
	private Vaccine selVaccine;
	private DiseaseInfo dinfo;
	private VaccinesInfo vacin;
	private int VD;

	/**
	 * Create the panel.
	 */
	public ListDiseaseVaccine(int type) {
		VD = type;
		setBackground(SystemColor.activeCaption);
		setSize(1320, 551);
		setLayout(null);
		setVisible(false);
		
		dinfo = new DiseaseInfo();
		vacin = new VaccinesInfo();
		
		if(VD == 0) {
			lblTitle = new JLabel("LISTAR ENFERMEDADES");
		}else {
			lblTitle = new JLabel("LISTAR VACUNAS");
		}
		
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
					if(VD == 0) {
						selDisease = Clinic.getInstance().getMyDiseases().get(index);
					} else {
						selVaccine = Clinic.getInstance().getMyVaccines().get(index);
					}
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
				
				if(selDisease != null) {
					int option = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la enfermedad: " + selDisease.getName() + "?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						Clinic.getInstance().removeDisease(selDisease);
						btnEdit.setEnabled(false);
						btnDelete.setEnabled(false);
						loadDisease();
					}
				} else {
					int option = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la vacuna: " + selVaccine.getName() + "?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						Clinic.getInstance().removeVaccine(selVaccine);
						btnEdit.setEnabled(false);
						btnDelete.setEnabled(false);
						loadVaccine();
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
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
				if(VD == 0) {
					CreateDiseaseVaccine regDisease = new CreateDiseaseVaccine(0, selDisease, null);
					regDisease.setModal(true);
					regDisease.setVisible(true);
				} else {
					CreateDiseaseVaccine regVaccine = new CreateDiseaseVaccine(1, null, selVaccine);
					regVaccine.setModal(true);
					regVaccine.setVisible(true);
				}
			}
		});
		btnEdit.setBounds(543, 490, 89, 23);
		add(btnEdit);
		
		JPanel dash = new JPanel();
		dash.setLayout(null);
		dash.setBackground(SystemColor.activeCaption);
		dash.setBounds(743, 80, 431, 415);
		add(dash);
		//
		dash.add(dinfo);
		dash.add(vacin);
		
		menuclicked(VD);

		if(VD == 0) {
			loadDisease();
		}else {
			loadVaccine();
		}
	}
	
	private void menuclicked(int type) {
		if(type==1) {
			dinfo.setEnabled(false);
			vacin.setEnabled(true);
		} else {
			dinfo.setEnabled(true);
			vacin.setEnabled(false);
		}
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

	public static void loadDisease() {
		model.setRowCount(0);
		row = new Object[table.getColumnCount()];

		for(Disease disease : Clinic.getInstance().getMyDiseases()) {
			row[0] = disease.getCode();
			row[1] = disease.getName();
			row[2] = disease.getDescription();
			model.addRow(row);
			
		}
	}
}

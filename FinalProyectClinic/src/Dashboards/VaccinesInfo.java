package Dashboards;

import java.awt.Dimension;
import java.sql.Connection;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import logic.Clinic;
import logic.SqlConnection;
import logic.Vaccine;

public class VaccinesInfo extends JPanel {

	private static DefaultPieDataset dataset;
	private static JFreeChart chart;
	private static int cantVaccinated = 0;

	public VaccinesInfo() {
		setOpaque(false);
		setSize(350,350);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 0, 350, 350);
		add(panel);

		dataset = new DefaultPieDataset();

		dataset.setValue("Vacunas", Clinic.getInstance().getMyVaccines().size());

		chart = ChartFactory.createPieChart("Vacunas en el sistema", dataset);
		ChartPanel chartPanel = new ChartPanel(chart); 
		chartPanel.setBounds(0, 0, 350, 350);

		panel.setLayout(null);
		chartPanel.setPreferredSize(new Dimension(350, 350));

		panel.add(chartPanel);
	}
	

	public static void refreshSQLChart(int selVaccine) {
		try {
			Connection con = SqlConnection.getConnection();
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	
	}
	
	/*
	public static void refreshChart(int selVaccine) {
		
		dataset.clear();
			
		cantVaccinated = Clinic.getInstance().totalPatientsWithVaccine(selVaccine.getCode());
		String titlet = selVaccine.getName();
		
		dataset.setValue("Vacunados", cantVaccinated);
		dataset.setValue("No vacunados", Clinic.getInstance().getMyPersons().size() - cantVaccinated); 

		chart.setTitle("Vacunados de: "+titlet);
		
		
	}*/
	
	
}
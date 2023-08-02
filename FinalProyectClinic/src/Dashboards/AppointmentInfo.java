package Dashboards;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import logic.Clinic;

public class AppointmentInfo extends JPanel {

	private static DefaultPieDataset dataset;
	/*
	 * 
	 * */
	public AppointmentInfo() {
		setSize(350,350);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 350, 350);
		add(panel);

		dataset = new DefaultPieDataset();
		
		dataset.setValue("No Presente", Clinic.getInstance().totalPatientsStatus("No Presente"));
		dataset.setValue("En Espera", Clinic.getInstance().totalPatientsStatus("En Espera"));
		dataset.setValue("Vista", Clinic.getInstance().totalPatientsStatus("Visto")); 

		JFreeChart chart = ChartFactory.createPieChart("STATUS PACIENTES" , dataset);
		
		ChartPanel chartPanel = new ChartPanel(chart); 
		chartPanel.setBounds(0, 0, 350, 350);

		panel.setLayout(null);
		chartPanel.setPreferredSize(new Dimension(350, 350));

		panel.add(chartPanel);
	}
	
	public static void refreshChart() {
		dataset.clear();
		
		dataset.setValue("No Presente", Clinic.getInstance().totalPatientsStatus("No Presente"));
		dataset.setValue("En Espera", Clinic.getInstance().totalPatientsStatus("En Espera"));
		dataset.setValue("Visto", Clinic.getInstance().totalPatientsStatus("Visto")); 
	}


}
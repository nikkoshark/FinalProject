package Dashboards;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import logic.Clinic;
import logic.Disease;

public class DiseaseInfo extends JPanel {

	private static DefaultPieDataset dataset;
	private static JFreeChart chart;
	private static int cantSick = 0;
	
	public DiseaseInfo() {
		setSize(350,350);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 350, 350);
		add(panel);

		dataset = new DefaultPieDataset();

		dataset.setValue("Enfermedades", Clinic.getInstance().getMyDiseases().size());
		
		chart = ChartFactory.createPieChart("Enfermedades en el sistema" , dataset);
		ChartPanel chartPanel = new ChartPanel(chart); 
		chartPanel.setOpaque(false);
		chartPanel.setBounds(0, 0, 350, 350);

		panel.setLayout(null);
		chartPanel.setPreferredSize(new Dimension(350, 350));

		panel.add(chartPanel);
	}
	
	public static void refreshChart(Disease selDisease) {
		dataset.clear();
		
		cantSick = Clinic.getInstance().totalPatientsWithDisease(selDisease.getCode());
		String titlet = selDisease.getName();
		
		dataset.setValue("Enfermos", cantSick);
		dataset.setValue("Sanos", Clinic.getInstance().getMyPersons().size() - cantSick); 

		chart.setTitle("Enfermos de: "+titlet);
	}
}
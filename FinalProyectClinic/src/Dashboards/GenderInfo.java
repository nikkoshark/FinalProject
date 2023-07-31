package Dashboards;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import logic.Clinic;

public class GenderInfo extends JPanel {
	
	private static DefaultPieDataset dataset;

	public GenderInfo() {
		setSize(350,350);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 350, 350);
		add(panel);
		
		dataset = new DefaultPieDataset();

		dataset.setValue("Femenino", Clinic.getInstance().totalPacienteGenero('F'));
		dataset.setValue("Masculino", Clinic.getInstance().totalPacienteGenero('M'));
		
		JFreeChart chart = ChartFactory.createPieChart("GÉNERO" , dataset);
		
		ChartPanel chartPanel = new ChartPanel(chart); 
		chartPanel.setBounds(0, 0, 350, 350);
		panel.setLayout(null);
		chartPanel.setPreferredSize(new Dimension(350, 350));
		panel.add(chartPanel);
		
	}
	
	public static void refreshChart() {
		dataset.setValue("Femenino", Clinic.getInstance().totalPacienteGenero('F'));
		dataset.setValue("Masculino", Clinic.getInstance().totalPacienteGenero('M'));
	}


}

package Dashboards;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import logic.Clinic;

public class UserInfo extends JPanel {

	private static DefaultPieDataset dataset;

	public UserInfo() {
		setSize(350,350);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 0, 350, 350);
		add(panel);

		dataset = new DefaultPieDataset();
		
		refreshUser();
		
		JFreeChart chart = ChartFactory.createPieChart("USER PIE CHART" , dataset);
		
		ChartPanel chartPanel = new ChartPanel(chart); 
		chartPanel.setBounds(0, 0, 350, 350);
		panel.setLayout(null);
		chartPanel.setPreferredSize(new Dimension(350, 350));
		panel.add(chartPanel);
	}
	
	public static void refreshUser() {
		dataset.clear();
		
		dataset.setValue("Secretaria", Clinic.getInstance().totalUsers("Secretaria"));
		dataset.setValue("Medico", Clinic.getInstance().totalUsers("Medico"));
		dataset.setValue("Admin", Clinic.getInstance().totalUsers("Admin"));
		
	}

}

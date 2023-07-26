package Dashboards;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import logic.Clinic;
import login.User;
import java.awt.Color;

public class UserInfo extends JPanel {

	/**
	 * Create the panel.
	 */
	public UserInfo() {
		setSize(350,350);
		setLayout(null);
		//setVisible(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 350, 350);
		add(panel);

		DefaultPieDataset dataset = new DefaultPieDataset();
		
		dataset.setValue("Secretaria", Clinic.getInstance().totalUsers("Secretaria"));
		dataset.setValue("Medico", Clinic.getInstance().totalUsers("Medico"));
		dataset.setValue("Admin", Clinic.getInstance().totalUsers("Admin"));
		JFreeChart chart = ChartFactory.createPieChart("USER PIE CHART" , dataset);
		
		ChartPanel chartPanel = new ChartPanel(chart); 
		chartPanel.setBounds(25, 25, 300, 300);
		Dimension dimension = new Dimension(300, 300);
		panel.setLayout(null);
		chartPanel.setPreferredSize(dimension);
		panel.add(chartPanel);
	}


}

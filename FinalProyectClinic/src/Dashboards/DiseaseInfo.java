
package Dashboards;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import logic.Clinic;
import logic.Disease;
import login.User;

public class DiseaseInfo extends JPanel {

	/**
	 * Create the panel.
	 */
	public DiseaseInfo() {
		setSize(350,350);
		setLayout(null);
		//setVisible(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 350, 350);
		add(panel);

		DefaultPieDataset dataset = new DefaultPieDataset();
		
		dataset.setValue("To be determined", 1);
		
		JFreeChart chart = ChartFactory.createPieChart("DISEASE PIE CHART" , dataset);
		
		ChartPanel chartPanel = new ChartPanel(chart); 
		chartPanel.setBounds(25, 23, 300, 300);

		Dimension dimension = new Dimension(300, 300);
		panel.setLayout(null);
		chartPanel.setPreferredSize(dimension);

		panel.add(chartPanel);
	}
	


}

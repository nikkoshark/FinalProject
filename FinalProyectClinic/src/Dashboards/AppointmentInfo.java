package Dashboards;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import com.sun.java.swing.plaf.windows.WindowsBorders.DashedBorder;

import logic.Clinic;
import logic.Disease;

public class AppointmentInfo extends JPanel {

	/*
	 * 
	 * */
	public AppointmentInfo() {
		setSize(350,350);
		setLayout(null);
		//setVisible(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 350, 350);
		add(panel);

		DefaultPieDataset dataset = new DefaultPieDataset();
		
		dataset.setValue("No Presente", Clinic.getInstance().totalPatientsStatus("No Presente"));
		dataset.setValue("En Espera", Clinic.getInstance().totalPatientsStatus("En Espera"));
		dataset.setValue("Vista", Clinic.getInstance().totalPatientsStatus("Vista")); 

		JFreeChart chart = ChartFactory.createPieChart("STATUS PIE CHART" , dataset);
		
		ChartPanel chartPanel = new ChartPanel(chart); 
		chartPanel.setBounds(25, 23, 300, 300);

		panel.setLayout(null);
		chartPanel.setPreferredSize(new Dimension(300, 300));

		panel.add(chartPanel);
	}


}

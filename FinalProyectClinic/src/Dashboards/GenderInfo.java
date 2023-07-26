package Dashboards;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import logic.Appoinment;
import logic.Clinic;
import logic.Disease;

public class GenderInfo extends JPanel {

	/*
	  		PERSONAS EN BASE A GÉNERO POR DÍA O POR MES O GENERALMENTE
	  		ACTUALMENTE FUNCIONA GENERAL x PERSONA QUE EXISTA EN EL SISTEMA
	 */ 
	public GenderInfo() {
		setSize(350,350);
		setLayout(null);
		//setVisible(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 350, 350);
		add(panel);
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Femenino", Clinic.getInstance().totalPacienteGenero('F'));
		dataset.setValue("Masculino", Clinic.getInstance().totalPacienteGenero('M'));

		JFreeChart chart = ChartFactory.createPieChart("GÉNERO PIE CHART" , dataset);
		
		ChartPanel chartPanel = new ChartPanel(chart); 
		chartPanel.setBounds(25, 23, 300, 300);

		panel.setLayout(null);
		chartPanel.setPreferredSize(new Dimension(300, 300));

		panel.add(chartPanel);
	}


}

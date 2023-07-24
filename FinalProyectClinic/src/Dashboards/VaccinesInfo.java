package Dashboards;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.omg.IOP.ENCODING_CDR_ENCAPS;

import logic.Clinic;
import logic.Vaccine;
import login.User;

public class VaccinesInfo extends JPanel {

	/*
	  PERSONAS VACUNADAS EN BASE A LA VACUNA ELEGIDA 
	  PORCENTAJE DE LOS VACUNADOS VS NO VACUNADOS
	 */
	public VaccinesInfo() {
		setSize(400,400);
		setLayout(null);
		//setVisible(false);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 350, 350);
		add(panel);

		DefaultPieDataset dataset = new DefaultPieDataset();
		
		dataset.setValue("Secretaria", typeEquals("Secretaria"));
		dataset.setValue("Medico", typeEquals("Medico"));
		dataset.setValue("Admin", typeEquals("Admin"));

		JFreeChart chart = ChartFactory.createPieChart("DISEASE PIE CHART" , dataset);
		
		ChartPanel chartPanel = new ChartPanel(chart); 
		chartPanel.setBounds(25, 23, 300, 300);

		Dimension dimension = new Dimension(300, 300);
		panel.setLayout(null);
		chartPanel.setPreferredSize(dimension);

		panel.add(chartPanel);
	}
	
	//LOOK AT EVERY PERSON, CHECK IF INSIDE THE PERSON HAS BEEN VACCINATED BY IT
	private int typeEquals(String name) {
		int cant = 0;
		for(Vaccine vaccine: Clinic.getInstance().getMyVaccines()) {
			if(vaccine.getName().equalsIgnoreCase(name)){
				cant++;
			}
		}		
		return cant;
	}


}
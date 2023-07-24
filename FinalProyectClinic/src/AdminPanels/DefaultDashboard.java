package AdminPanels;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Dashboards.DiseaseInfo;
import Dashboards.UserInfo;
import Dashboards.VaccinesInfo;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DefaultDashboard extends JPanel {
	
	private UserInfo usin;
	private VaccinesInfo vacin;
	private DiseaseInfo dinfo;

	/**
	 * Create the panel.
	 */
	public DefaultDashboard() {
		setSize(1320, 551);
		setLayout(null);
		setVisible(false);

		usin = new UserInfo();
		vacin = new VaccinesInfo();
		dinfo = new DiseaseInfo();
		
		JLabel lblNewLabel = new JLabel("DEFAULT DASHBOARD");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(296, 11, 285, 42);
		add(lblNewLabel);
		
		JPanel dash1 = new JPanel();
		dash1.setBounds(43, 91, 400, 400);
		add(dash1);
		dash1.setLayout(null);
		
		JPanel dash2 = new JPanel();
		dash2.setBounds(482, 91, 400, 400);
		add(dash2);
		dash2.setLayout(null);
		
		JPanel dash3 = new JPanel();
		dash3.setBounds(910, 91, 400, 400);
		add(dash3);
		dash3.setLayout(null);

		
		dash1.add(usin);
		dash2.add(vacin);
		dash3.add(dinfo);
		
		//menuclicked(usin);

	}
}

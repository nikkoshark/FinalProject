package AdminPanels;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DefaultDashboard extends JPanel {

	/**
	 * Create the panel.
	 */
	public DefaultDashboard() {
		setSize(1320, 551);
		setLayout(null);
		setVisible(false);
		
		JLabel lblNewLabel = new JLabel("DEFAULT DASHBOARD");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(269, 173, 285, 42);
		add(lblNewLabel);

	}

}

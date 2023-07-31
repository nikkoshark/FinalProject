package CheckupPanels;

import javax.swing.JPanel;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JLabel;

public class DiseasePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public DiseasePanel() {
		setBackground(SystemColor.inactiveCaption);
		setSize(390,358);
		setLayout(null);
		setVisible(false);
		
		JLabel lblNewLabel = new JLabel("DISEASE PANEL");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(113, 162, 176, 25);
		add(lblNewLabel);
	}

}

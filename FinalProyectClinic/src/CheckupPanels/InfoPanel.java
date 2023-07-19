package CheckupPanels;

import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;

public class InfoPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public InfoPanel() {
		setBackground(SystemColor.inactiveCaption);
		setSize(390,358);
		setLayout(null);
		setVisible(false);
		
		JLabel lblNewLabel = new JLabel("INFO PANEL");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(113, 162, 117, 14);
		add(lblNewLabel);
	}

}

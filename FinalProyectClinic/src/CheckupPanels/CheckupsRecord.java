package CheckupPanels;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CheckupsRecord extends JPanel {

	/**
	 * Create the panel.
	 */
	public CheckupsRecord() {
		setBackground(SystemColor.inactiveCaption);
		setSize(390,358);
		setLayout(null);
		setVisible(false);
		
		JLabel lblNewLabel = new JLabel("CHECKUPS RECORD PANEL");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(49, 162, 256, 25);
		add(lblNewLabel);

	}

}

package CheckupPanels;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MedicalRecordPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public MedicalRecordPanel() {
		setBackground(SystemColor.inactiveCaption);
		setSize(390,358);
		setLayout(null);
		setVisible(false);
		
		JLabel lblNewLabel = new JLabel("MEDICAL RECORD PANEL");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(64, 161, 241, 14);
		add(lblNewLabel);

	}

}

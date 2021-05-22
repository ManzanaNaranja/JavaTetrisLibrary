package ui.panels;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BottomPanel extends JPanel {
	public JButton btn_start, btn_pause, btn_reset;
	public BottomPanel() {
		setBounds(10, 554, 562, 48);
		setLayout(null);
		
		btn_start = new JButton("Start");
		btn_start.setBounds(0, 0, 84, 48);
		btn_start.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		add(btn_start);
		
		btn_pause = new JButton("Pause");
		btn_pause.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		btn_pause.setBounds(94, 0, 84, 48);
		add(btn_pause);
		
		btn_reset = new JButton("Reset");
		btn_reset.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		btn_reset.setBounds(188, 0, 84, 48);
		add(btn_reset);
	}
	
	
}

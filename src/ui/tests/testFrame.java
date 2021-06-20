package ui.tests;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JSlider;

public class testFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testFrame frame = new testFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public testFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 649);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnl_left = new JPanel();
		pnl_left.setBackground(Color.PINK);
		pnl_left.setBounds(10, 10, 271, 534);
		contentPane.add(pnl_left);
		
		JPanel pnl_right = new JPanel();
		pnl_right.setBounds(286, 10, 286, 534);
		contentPane.add(pnl_right);
		pnl_right.setLayout(null);
		
		JLabel lbl_score = new JLabel("Score: ");
		lbl_score.setFont(new Font("Sitka Subheading", Font.PLAIN, 22));
		lbl_score.setBounds(10, 10, 72, 28);
		pnl_right.add(lbl_score);
		
		JLabel lbl_lines_cleared = new JLabel("Lines Cleared: ");
		lbl_lines_cleared.setFont(new Font("Sitka Subheading", Font.PLAIN, 22));
		lbl_lines_cleared.setBounds(10, 40, 146, 28);
		pnl_right.add(lbl_lines_cleared);
		
		JLabel lbl_evaulation = new JLabel("Evaluation: ");
		lbl_evaulation.setFont(new Font("Sitka Subheading", Font.PLAIN, 22));
		lbl_evaulation.setBounds(10, 71, 118, 28);
		pnl_right.add(lbl_evaulation);
		
		JLabel lbl_toggle_ai = new JLabel("Toggle AI:");
		lbl_toggle_ai.setFont(new Font("Sitka Subheading", Font.PLAIN, 22));
		lbl_toggle_ai.setBounds(10, 101, 105, 28);
		pnl_right.add(lbl_toggle_ai);
		
		JCheckBox checkbox_toggle_ai = new JCheckBox("");
		checkbox_toggle_ai.setBounds(112, 101, 31, 21);
		pnl_right.add(checkbox_toggle_ai);
		checkbox_toggle_ai.setHorizontalAlignment(SwingConstants.LEFT);
		checkbox_toggle_ai.setFont(new Font("Sitka Subheading", Font.PLAIN, 20));
		
		JLabel lbl_speed = new JLabel("Speed: ");
		lbl_speed.setBounds(10, 128, 118, 28);
		pnl_right.add(lbl_speed);
		lbl_speed.setFont(new Font("Sitka Subheading", Font.PLAIN, 22));
		
		JSlider slider = new JSlider();
		slider.setBounds(85, 128, 191, 22);
		pnl_right.add(slider);
		
		JPanel pnl_bottom = new JPanel();
		pnl_bottom.setBounds(10, 554, 562, 48);
		contentPane.add(pnl_bottom);
		pnl_bottom.setLayout(null);
		
		JButton btn_start = new JButton("Start");
		btn_start.setBounds(0, 0, 84, 48);
		btn_start.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		pnl_bottom.add(btn_start);
		
		JButton btn_start_1 = new JButton("Pause");
		btn_start_1.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		btn_start_1.setBounds(94, 0, 84, 48);
		pnl_bottom.add(btn_start_1);
		
		JButton btn_start_2 = new JButton("Reset");
		btn_start_2.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		btn_start_2.setBounds(188, 0, 84, 48);
		pnl_bottom.add(btn_start_2);
	}
}

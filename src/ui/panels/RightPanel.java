package ui.panels;

import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

public class RightPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbl_score, lbl_lines_cleared, lbl_evaluation;
	public RightPanel() {
		setBounds(286, 10, 286, 534);
		setLayout(null);
		
		lbl_score = new JLabel("Score: 0");
		lbl_score.setFont(new Font("Sitka Subheading", Font.PLAIN, 22));
		lbl_score.setBounds(10, 10, 200, 28);
		add(lbl_score);
		
		lbl_lines_cleared = new JLabel("Lines Cleared: 0");
		lbl_lines_cleared.setFont(new Font("Sitka Subheading", Font.PLAIN, 22));
		lbl_lines_cleared.setBounds(10, 40, 200, 28);
		add(lbl_lines_cleared);
		
		lbl_evaluation = new JLabel("Evaluation: 0");
		lbl_evaluation.setFont(new Font("Sitka Subheading", Font.PLAIN, 22));
		lbl_evaluation.setBounds(10, 71, 200, 28);
		add(lbl_evaluation);
		
		JLabel lbl_toggle_ai = new JLabel("Toggle AI:");
		lbl_toggle_ai.setFont(new Font("Sitka Subheading", Font.PLAIN, 22));
		lbl_toggle_ai.setBounds(10, 101, 105, 28);
		add(lbl_toggle_ai);
		
		JCheckBox checkbox_toggle_ai = new JCheckBox("");
		checkbox_toggle_ai.setBounds(112, 101, 31, 21);
		add(checkbox_toggle_ai);
		checkbox_toggle_ai.setHorizontalAlignment(SwingConstants.LEFT);
		checkbox_toggle_ai.setFont(new Font("Sitka Subheading", Font.PLAIN, 20));
		
		JLabel lbl_speed = new JLabel("Speed: ");
		lbl_speed.setBounds(10, 128, 118, 28);
		add(lbl_speed);
		lbl_speed.setFont(new Font("Sitka Subheading", Font.PLAIN, 22));
		
		JSlider slider = new JSlider();
		slider.setBounds(85, 128, 191, 22);
		add(slider);
	}
	
	public void setScore(int score) {
		String[] lbl = lbl_score.getText().split(":");
		this.lbl_score.setText(lbl[0] + ": " + score);
	}
	
	public void setLinesCleared(int lines) {
		String[] lbl = lbl_lines_cleared.getText().split(":");
		this.lbl_lines_cleared.setText(lbl[0] + ": " + lines);
	}
	
	public void setEval(double eval) {
		String[] lbl = lbl_evaluation.getText().split(":");
		this.lbl_evaluation.setText(lbl[0] + ": " + eval);
	}
}

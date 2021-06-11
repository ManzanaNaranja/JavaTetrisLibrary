package ui;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.panels.BottomPanel;
import ui.panels.LeftPanel;
import ui.panels.RightPanel;

public class Window {
	public JPanel contentPane;
	public RightPanel rightPanel;
	public LeftPanel leftPanel;
	public BottomPanel bottomPanel;
	
	public Window(int width, int height) {
		JFrame frame = new JFrame();
		rightPanel = new RightPanel();
		leftPanel = new LeftPanel();
		bottomPanel = new BottomPanel();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setPreferredSize(new Dimension(width, height));
		contentPane = new JPanel();
		contentPane.setFocusable(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(rightPanel);
		contentPane.add(leftPanel);
		contentPane.add(bottomPanel);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	
	}
}

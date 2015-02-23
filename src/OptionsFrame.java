import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;


public class OptionsFrame {
	JFrame frame;
	public final Dimension frameSize = new Dimension(200,200);
	private boolean randomFours = false;
	private int scale;

	public OptionsFrame() {
		randomFours = Game.gameBoard.getRandomFours();
		scale = Game.gameFrame.getScale();
		frame = new JFrame();

		frame.setSize(frameSize);
		
		//frame.pack();
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("Options");
		frame.setLocationRelativeTo(null);
		//frame.setLayout(new GridLayout(1,1));
		
		JPanel panel = new JPanel(new GridBagLayout());
		
		JCheckBox randomFoursBox = new JCheckBox("Random Fours");
		randomFoursBox.addActionListener(new R4());
		randomFoursBox.setSelected(randomFours);
		
		JRadioButton size1 = new JRadioButton("x1");
		size1.addActionListener(new P1P());
		JRadioButton size2 = new JRadioButton("x2");
		size2.addActionListener(new P1E());
		JRadioButton size3 = new JRadioButton("x3");
		size3.addActionListener(new P1M());
		JRadioButton size4 = new JRadioButton("x4");
		size4.addActionListener(new P1H());
		
		ButtonGroup group1 = new ButtonGroup();
		group1.add(size1);
		group1.add(size2);
		group1.add(size3);
		group1.add(size4);
		
		switch(Game.gameFrame.getScale()) {
			case 1: size1.setSelected(true); break;
			case 2: size2.setSelected(true); break;
			case 3: size3.setSelected(true); break;
			case 4: size4.setSelected(true); break;
		}
		
		Label ssLabel = new Label("Screen Size");
		ssLabel.setFont(new Font("Sans_Serif", Font.BOLD, 12));
		
		JButton button1 = new JButton("OK");
		button1.setPreferredSize(new Dimension(55,20));
		button1.addActionListener(new OKAction());
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setPreferredSize(new Dimension(55,20));
		cancelButton.addActionListener(new CancelAction());

		GridBagConstraints c= new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(randomFoursBox,c);
		
//		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.gridwidth = 3;
		c.ipady  = 5;
		panel.add(new JSeparator(), c);
		
//		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0;
		c.gridwidth = 3;
		c.ipady = 0;
		panel.add(ssLabel, c);
		
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.gridx = 1;
//		c.gridy = 2;
//		c.weightx = 0;
//		panel.add(new Label(" "), c);
		
//		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 0;
		c.gridwidth = 1;
		panel.add(size1, c);
		
//		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 0;
		panel.add(size2, c);
		
//		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.weightx = 0;
		panel.add(size3, c);
		
//		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		c.weightx = 0;
		panel.add(size4, c);
		
//		panel.add(new Label(" "));
//		panel.add(new Label(" "));
		
//		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		c.weightx = 0;
		c.gridwidth = 3;
		panel.add(new JSeparator(), c);		
		
//		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 6;
		c.weightx = 0;
		c.gridwidth = 2;
		panel.add(button1, c);
		
//		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 7;
		c.weightx = 0;
		c.gridwidth = 2;
		panel.add(cancelButton, c);
		
		frame.add(panel);
	}
/*	
	public OptionsFrame() {
		randomFours = Game.gameBoard.getRandomFours();
		scale = Game.gameFrame.getScale();
		frame = new JFrame();

		frame.setSize(frameSize);
		
		//frame.pack();
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("Options");
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridBagLayout());
		
		JPanel panel1 = new JPanel(new GridLayout(0,1));
		JCheckBox randomFoursBox = new JCheckBox("Random Fours");
		randomFoursBox.addActionListener(new R4());
		randomFoursBox.setSelected(randomFours);

		panel1.add(randomFoursBox);
		
		JPanel panel2 = new JPanel(new GridLayout(0,2));
		
		JRadioButton size1 = new JRadioButton("x1");
		size1.addActionListener(new P1P());
		JRadioButton size2 = new JRadioButton("x2");
		size2.addActionListener(new P1E());
		JRadioButton size3 = new JRadioButton("x3");
		size3.addActionListener(new P1M());
		JRadioButton size4 = new JRadioButton("x4");
		size4.addActionListener(new P1H());
		
		ButtonGroup group1 = new ButtonGroup();
		group1.add(size1);
		group1.add(size2);
		group1.add(size3);
		group1.add(size4);
		
		switch(Game.gameFrame.getScale()) {
			case 1: size1.setSelected(true); break;
			case 2: size2.setSelected(true); break;
			case 3: size3.setSelected(true); break;
			case 4: size4.setSelected(true); break;
		}
		
		Label ssLabel = new Label("Screen Size");
		ssLabel.setFont(new Font("Sans_Serif", Font.BOLD, 12));
		
		panel2.add(ssLabel);
		panel2.add(new Label(" "));
		panel2.add(size1);
		panel2.add(size2);
		panel2.add(size3);
		panel2.add(size4);
		
		JPanel panel3 = new JPanel(new GridLayout(0,2));
		
		panel3.add(new Label(" "));
		panel3.add(new Label(" "));
		
		JButton button1 = new JButton("OK");
		button1.setPreferredSize(new Dimension(55,20));
		button1.addActionListener(new OKAction());
		panel3.add(button1);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setPreferredSize(new Dimension(55,20));
		cancelButton.addActionListener(new CancelAction());
		panel3.add(cancelButton);


		GridBagConstraints c= new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.gridx = 0;
		c.gridy = 0;
		
		frame.add(panel1,c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		frame.add(new JSeparator(), c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0;
		frame.add(panel2, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 0;
		frame.add(new JSeparator(), c);		
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.weightx = 0;
		frame.add(panel3, c);
	}
*/	
	class OKAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Game.gameBoard.setRandomFours(randomFours);
			Game.gameFrame.setScale(scale);
			frame.dispose();
			Game.paused = false;
		}
	}
	class CancelAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			frame.dispose();
			Game.paused = false;
		}
	}
	class R4 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			randomFours = !randomFours;
		}
	}
	class P1P implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			scale = 1;
		}
	}
	class P1E implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			scale = 2;
		}
	}
	class P1M implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			scale = 3;
		}
	}
	class P1H implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			scale = 4;
		}
	}

}
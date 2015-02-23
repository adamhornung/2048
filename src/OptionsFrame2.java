import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;

public class OptionsFrame2 extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	public final Dimension frameSize = new Dimension(200,200);
	private boolean randomFours = false;
	private int scale;
	
	public OptionsFrame2() {
		super("Options");
		
		randomFours = Game.gameBoard.getRandomFours();
		scale = Game.gameFrame.getScale();
		
		setSize(200,175);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLayout(new BorderLayout());
		setVisible(true);
		setLocationRelativeTo(null);
		
		JPanel randomFoursPanel = new JPanel();
		
		JCheckBox randomFoursBox = new JCheckBox("Random Fours");
		randomFoursBox.addActionListener(this);
		randomFoursBox.setSelected(randomFours);
		
		randomFoursPanel.add(randomFoursBox);
		
		add(randomFoursPanel, BorderLayout.NORTH);
		
		JPanel radioPanel = new JPanel();
		
		JPanel radioPanelBig = new JPanel();
		radioPanelBig.setLayout(new BorderLayout());
		
		JRadioButton size1 = new JRadioButton("x1");
		size1.addActionListener(this);
		JRadioButton size2 = new JRadioButton("x2");
		size2.addActionListener(this);
		JRadioButton size3 = new JRadioButton("x3");
		size3.addActionListener(this);
		JRadioButton size4 = new JRadioButton("x4");
		size4.addActionListener(this);
		
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
		
		radioPanel.add(size1);
		radioPanel.add(size2);
		radioPanel.add(size3);
		radioPanel.add(size4);

			radioPanelBig.add(new JSeparator(), BorderLayout.NORTH);
			radioPanelBig.add(radioPanel, BorderLayout.CENTER);
			radioPanelBig.add(new JSeparator(), BorderLayout.SOUTH);
		
		add(radioPanelBig, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		//buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		
		JButton button1 = new JButton("OK");
		//button1.setPreferredSize(new Dimension(55,20));
		button1.addActionListener(this);
		
		JButton cancelButton = new JButton("Cancel");
		//cancelButton.setPreferredSize(new Dimension(55,20));
		cancelButton.addActionListener(this);
		
			buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(button1);
			buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));
		buttonPanel.add(cancelButton);
		
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
	}

	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("OK")) {
			Game.gameBoard.setRandomFours(randomFours);
			Game.gameFrame.setScale(scale);
			Game.paused = false;
			dispose();
		}
		else if(actionCommand.equals("Cancel")) {
			Game.paused = false;
			dispose();
		}
		else if(actionCommand.equals("Random Fours"))
			randomFours = !randomFours;
		else if(actionCommand.equals("x1"))
			scale = 1;
		else if(actionCommand.equals("x2"))
			scale = 2;
		else if(actionCommand.equals("x3"))
			scale = 3;
		else if(actionCommand.equals("x4"))
			scale = 4;
		else
			System.out.print(actionCommand);
	}
}
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;


public class MenuBar {
	public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;
        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");  // not sure what this does
        menuBar.add(menu);

        //a group of JMenuItems
        menuItem = new JMenuItem("New Game", KeyEvent.VK_N);
        //menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        menuItem.addActionListener(new NewGameAction());
        menu.add(menuItem);

        menuItem = new JMenuItem("Pause", KeyEvent.VK_P);
        menuItem.addActionListener(new PauseAction());
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Options", KeyEvent.VK_O);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
        menuItem.addActionListener(new OptionsAction());
        menu.add(menuItem);
        
//		ImageIcon icon = createImageIcon("images/middle.gif");
//        menuItem = new JMenuItem("Exit", icon);
        menuItem = new JMenuItem("Exit");
        menuItem.setMnemonic(KeyEvent.VK_X);
        menuItem.addActionListener(new ExitAction());
        menu.add(menuItem);

        menuBar.add(makeOptionsMenu());
        
        return menuBar;
    }
	
	private JMenu makeOptionsMenu() {
		JMenu menu = new JMenu("Options");
		
		JCheckBoxMenuItem cbMenuItem = new JCheckBoxMenuItem("RandomFours");
		cbMenuItem.addActionListener(new r4Action());
		cbMenuItem.setSelected(Game.gameBoard.getRandomFours());
		
		menu.add(cbMenuItem);
		
		menu.addSeparator();
		
		JMenu subMenu = new JMenu("Screen Size");
		ButtonGroup sizeGroup = new ButtonGroup();
		JRadioButtonMenuItem size1 = new JRadioButtonMenuItem("x1");
		JRadioButtonMenuItem size2 = new JRadioButtonMenuItem("x2");
		JRadioButtonMenuItem size3 = new JRadioButtonMenuItem("x3");
		JRadioButtonMenuItem size4 = new JRadioButtonMenuItem("x4");
		
		sizeGroup.add(size1);
		sizeGroup.add(size2);
		sizeGroup.add(size3);
		sizeGroup.add(size4);
		
		subMenu.add(size1);
		subMenu.add(size2);
		subMenu.add(size3);
		subMenu.add(size4);
		
		menu.add(subMenu);
		
		return menu;
	}
	
	class ExitAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	class NewGameAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Game.reset();
		}
	}
	class PauseAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Game.paused = !Game.paused;
		}
	}
	class OptionsAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Game.paused = true;
			new OptionsFrame2();
		}
	}
	class r4Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Game.gameBoard.setRandomFours(!Game.gameBoard.getRandomFours());
		}
	}
}
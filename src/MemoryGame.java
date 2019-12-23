 import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MemoryGame {
	public static int openCards = 0;
	private static int numCards = Main.layout.length;
	/**
	 * Checks if there are two open cards
	 * @return true and deletes buttons, 
	 * else flip back down
	 */
	public static void checkOpen() {
		if(openCards == 2) {
			openCards = 0;
			if(checkMatch(Main.layout)) {
				Main.panel.revalidate();
				Main.panel.repaint();
			}
			
			else {
				JOptionPane.showMessageDialog(null, "Cards didn't match. Switch Player");
				flipBack(Main.layout);
				Main.switchPlayer();
				Main.panel.revalidate();
				Main.panel.repaint();
			}
			if(numCards == 0) {
				int score1 = Main.player1.getScore();
				int score2 = Main.player2.getScore();
				if(score1 > score2) {
					JOptionPane.showMessageDialog(null, Main.player1.name + " won!");
				}
				else if(score2 > score1) {
					JOptionPane.showMessageDialog(null, Main.player2.name + " won!");
				}
				else {
					JOptionPane.showMessageDialog(null, "You both tied");
				}
				reset();
			}
		}
	}
	/**
	 * Determines if 2 cards match
	 * @param buttons Buttons set up
	 * @return true if match
	 */
	public static boolean checkMatch(Button buttons[]) {
		Button button1 = null;
		Button button2 = null;
		boolean checked = false;
		
		for(int i = 0; i < buttons.length; i++) {
			if(!buttons[i].icon.equals("cards/back1.gif") && !checked && buttons[i].getIcon() != null) {
				button1 = buttons[i];
				checked = true;
			}
			else if(!buttons[i].icon.equals("cards/back1.gif") && buttons[i].getIcon() != null) {
				button2 = buttons[i];
			}
		}
		
		if(button1.icon.equals(button2.icon)) {
			Main.currPlayer.addPoint();
			Main.keepScore();
			numCards -= 2;
			if(numCards != 0) {
				JOptionPane.showMessageDialog(null, "Matched! Go again");
			}
			button1.setIcon(null);
			button1.setEnabled(false);
			button1.setOpaque(false);
			button1.setContentAreaFilled(false);
			button1.setBorderPainted(false);
			button2.setIcon(null);
			button2.setEnabled(false);
			button2.setOpaque(false);
			button2.setContentAreaFilled(false);
			button2.setBorderPainted(false);
			return true;
		}
		return false;
	}
	
	
	/**
	 * Flip back down
	 * @param buttons
	 */
	public static void flipBack(Button buttons[]) {
		for(int i = 0; i < buttons.length; i++) {
			if(!buttons[i].icon.equals("cards/back1.gif") && buttons[i].getIcon() != null) {
				buttons[i].icon = "cards/back1.gif";
				buttons[i].setIcon(new ImageIcon(buttons[i].icon));
			}
		}
	}
	public static void reset() {
		JButton button = new JButton("reset");
		Main.panel.removeAll();
		Main.panel.add(button);
		Main.panel.revalidate();
		Main.panel.repaint();
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Main.shuffle(Main.layout);
				for(int i = 0; i < Main.layout.length; i++) {
					Main.layout[i].icon = "cards/back1.gif";
					Main.layout[i].setIcon(new ImageIcon(Main.layout[i].icon));
					Main.layout[i].setEnabled(true);
					//Main.layout[i].setOpaque(true);
					Main.layout[i].setContentAreaFilled(true);
					Main.layout[i].setBorderPainted(true);
					Main.panel.add(Main.layout[i]);
				}
				Main.panel.remove(button);
				Main.player1.setScore(0);
				Main.player2.setScore(0);
				Main.keepScore();
				numCards = Main.layout.length;
				
				Main.panel.revalidate();
				Main.panel.repaint();	
			}
		});
	}
	
}

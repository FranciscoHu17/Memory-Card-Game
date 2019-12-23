//Author: Francisco Hu
//Date Created: May 6, 2018
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Main {

	private static JFrame frame;
	public static JPanel panel = new JPanel();
	public static JPanel scorePanel = new JPanel();
	public static JLabel scoreBoard = new JLabel();
	public static Button[] layout;
	public static Player player1, player2, currPlayer;
	
    public static void main(String[] args) {
        String[] ranks = {"ace",
        		"2","3"
        		,"4","5","6","7","8","9","10","jack", "queen","king"
        		};
        String[] suits = {"diamonds","clubs","hearts","spades"};
        String[] newRanks = new String[ranks.length*2];
        int index = 0;
        
        for(int i = 0; i < ranks.length; i++) {
        	newRanks[index] = ranks[i];
        	newRanks[index + 1] = ranks[i];
        	index += 2;
        }
        
        String name1 = JOptionPane.showInputDialog("Player 1 Name: ");
        String name2 = JOptionPane.showInputDialog("Player 2 Name: ");
        
        player1 = new Player(name1);
        player2 = new Player(name2);
        currPlayer = player1;
        
        scorePanel.add(scoreBoard);
        frame = new JFrame("Memory Lane");
		frame.setVisible(true);
		frame.setSize(800, 800);
		
		//JLabel img
		scorePanel.setBackground(Color.WHITE);
        panel.setBackground(Color.GRAY);
        
        frame.add(scorePanel,BorderLayout.SOUTH);
		frame.add(panel);
		
		keepScore();
		frame.setTitle(currPlayer.name + "'s Turn");
		layout = buttonSetup(newRanks, suits);
		
		
	
    }
    
    /**
     * Sets up the button layout on the panel
     * @param ranks String array of ranks
     * @param suits String array of suits
     * @return Button layout
     */
    public static Button[] buttonSetup(String[] ranks, String[] suits) {
    	Button[] buttons = new Button[ranks.length*suits.length];
    	int index = 0;
    	
    	for(int r = 0; r < ranks.length; r++) {
    		for(int s = 0; s < suits.length; s++) {
    			buttons[index] = new Button(ranks[r], suits[s]);
    			index++;
    		}
    	}
    	shuffle(buttons);
    	for(int i = 0; i < buttons.length;i++) {
    		panel.add(buttons[i]);
    	}
    	return buttons;
    	
    }
    public static void shuffle(Button buttons[]) {
 	   for(int i = 0; i < 10; i++){
 	   	   for(int j = 0; j < buttons.length; j++){
 	   	       int pos = (int)(Math.random() * (buttons.length));
 	   	       Button temp = buttons[pos];
 	   	       buttons[pos] = buttons[j];
 	   	       buttons[j] = temp;
 	   	   }
 	   }
 	}
    public static void switchPlayer() {
    	if(currPlayer == player1) {
    		currPlayer = player2;
    	}
    	else {
    		currPlayer = player1;
    	}
    	frame.setTitle(currPlayer.name + "'s Turn");
    }
    public static void keepScore() {
    	scorePanel.remove(scoreBoard);
		scoreBoard = new JLabel(player1.name + "'s Score: " + player1.getScore() + "       " 
									+ player2.name + "'s Score: " + player2.getScore());
		scorePanel.add(scoreBoard);
    }
    

    
}

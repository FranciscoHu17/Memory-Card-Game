import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Button extends JButton implements ActionListener {
	private String pathName = "";  //where the image is stored at
	public String icon = "cards/back1.gif";  //button display
	/**
	 * Button is set to an icon
	 * @param rank Goes to convert(String var)
	 * @param suit Goes to convert(String var)
	 */
	public Button(String rank, String suit) {
		convert(rank + suit);
		setIcon(new ImageIcon(icon));
		setPreferredSize(new Dimension(85,110));
		this.addActionListener(this);
	}
	
	/**
	 * Converts the information to corresponding pathName
	 * @param info Used to determine the pathName
	 */
	public void convert(String info) {
		pathName = "cards/" + info + ".gif";
	}
	
	/**
	 * When button is clicked, reveals the card
	 * Checks if there 2 cards
	 */
	public void actionPerformed(ActionEvent e) {
		icon = pathName;
		setIcon(new ImageIcon(pathName));
		MemoryGame.openCards++;
		MemoryGame.checkOpen();
		
	}

}

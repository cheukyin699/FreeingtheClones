package freeclones.main;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class InstructionPanel extends JPanel {
	private static final long serialVersionUID = 7713594669799307665L;
	private static final String InstructionsText =
			"Freeing the Clones (also called 'Pebbling the Chessboard') "
			+ "is a game where there are checker pieces called clones/pebbles. "
			+ "Clones are denoted by the circles in the cells(squares)."
			+ "The goal of the game is to get all the clones out of the 'prison' on the lower left hand corner "
			+ "(Denoted by the red squares) and into the outside world.\n\n"
			+ "You can only move clones by 'cloning' - having a clone clone itself onto the upper and right side "
			+ "of the original clone, and then 'kill' itself, leaving two new clones in its upper and right positions."
			+ "Cloning can be achieved by clicking on a clone, and NOT AN EMPTY CELL.\n\n"
			+ "You cannot clone if there are no clones on the square or if either of the two new clones' spawn positions "
			+ "are obstructed, in which case the cloning would be unsuccessful.\n\n"
			+ "If you are stuck, feel free to click on the 'Show answer' button down below.\n\n"
			+ "Freeing the Clones " + Frame.VERSION_NO + " implementation in Java by Ng Cheuk Yin\n"
			+ "For extra help, please contact me by email <cheukyin699@yahoo.com> or in person.";
	private GridBagLayout gridbaglayout;
	private JTextPane instructions;
	private JScrollPane iscrollpane;
	
	public InstructionPanel() {
		gridbaglayout = new GridBagLayout();
		this.setLayout(gridbaglayout);
		GridBagConstraints c = new GridBagConstraints();
		
		instructions = new JTextPane();
		iscrollpane = new JScrollPane(instructions);
		
		instructions.setText(InstructionsText);
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		this.add(iscrollpane, c);
		
		this.setVisible(true);
		this.setBackground(Color.black);
	}
}

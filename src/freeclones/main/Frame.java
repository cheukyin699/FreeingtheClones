package freeclones.main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Frame extends JFrame implements ActionListener {
	public static final String VERSION_NO = "v1.2.3";
	public static final String HELP =
			"Freeing the Clones " + VERSION_NO + " by Ng Cheuk Yin\n\n"
			+ "Parameters:\n"
			+ "-h\t\tGet help\n"
			+ "-v\t\tGet version number\n"
			+ "-a\t\tSet the size of the board in this format: width,height - with no spaces\n"
			+ "-t\t\tSet the size of an individual tile in this format: width,height - with no spaces\n";
	private static final long serialVersionUID = 5794531640395831013L;
	private static final String Title = "Freeing the Clones";
	
	public static final Dimension Size = new Dimension(700,500);
	
	private GridBagLayout gridlayout;
	private Board board;
	private JButton reset;
	private JButton undo;
	private JButton ans;
	private InstructionPanel ipanel;
	
	public Frame() {
		GridBagConstraints gbc = new GridBagConstraints();
		gridlayout = new GridBagLayout();
		this.setLayout(gridlayout);
		
		board = new Board();
		reset = new JButton("Reset Board");
		undo = new JButton("Undo Move");
		ans = new JButton("Show Answer");
		ipanel = new InstructionPanel();
		
		reset.addActionListener(this);
		undo.addActionListener(this);
		ans.addActionListener(this);
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 10;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		this.add(board, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridheight = 3;
		gbc.gridwidth = 1;
		this.add(ipanel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weighty = 0.4;
		this.add(reset, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(undo, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.weighty = 0.3;
		this.add(ans, gbc);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle(Title);
		this.setSize(Size);
		//this.pack();
	}

	public static void main(String[] args) {
		boolean gui = true;
		for(int i=0; i<args.length; i++) {
			try {
				if(args[i].equals("-a")) {
					// Sets the size of the board
					i++;
					int w, h;
					w = Integer.parseInt(args[i].split(",")[0]);
					h = Integer.parseInt(args[i].split(",")[1]);
					Board.Size = new Dimension(w, h);
				}
				else if(args[i].equals("-t")) {
					// Sets the size of a single tile
					i++;
					int w, h;
					w = Integer.parseInt(args[i].split(",")[0]);
					h = Integer.parseInt(args[i].split(",")[1]);
					Board.Tile_Size = new Dimension(w, h);
				}
				else if(args[i].equals("-h")) {
					// Display help string, then return
					System.out.println(HELP);
					gui = false;
				}
				else if(args[i].equals("-v")) {
					// Display version number, then return
					System.out.println(VERSION_NO);
					gui = false;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		Frame f = new Frame();
		
		if(gui)
			f.setVisible(true);
		else {
			f.dispose();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == reset) {
			board.resetBoard();
		}
		else if(e.getSource() == undo) {
			board.undoClone();
		}
		else if(e.getSource() == ans) {
			System.out.println("http://www.youtube.com/watch?v=lFQGSGsXbXE");
		}
	}

}

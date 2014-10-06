package freeclones.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Board extends JPanel implements MouseListener {
	public static Dimension Size = new Dimension(8, 8);
	public static Dimension Tile_Size = new Dimension(25,25);
	private static final long serialVersionUID = 7313105091412821828L;
	private boolean tiles[][];
	private String errMsg;
	private List<MoveFormat> moves;
	
	public void undoClone() {
		// Removes most recent history, then resets and does everything again
		try {
			if(moves.size() == 0)
				return;
			moves.remove(moves.size() - 1);
			
			List<MoveFormat> templist = new ArrayList<MoveFormat>(moves);
			moves.clear();
			
			this.resetTiles();
			for(int i=0; i<templist.size(); i++) {
				this.cloneSelf(templist.get(i).x, templist.get(i).y);
			}
			
			this.repaint();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void resetTiles() {
		for(int y=0; y<Size.height; y++) {
			for(int x=0; x<Size.width; x++) {
				tiles[x][y] = false;
			}
		}
		
		// Set specific tiles to specific values
		try {
			tiles[0][Size.height - 1] = true;
			tiles[0][Size.height - 2] = true;
			tiles[1][Size.height - 1] = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		// Clear all the moves
		moves.clear();
	}
	
	public Board() {
		tiles = new boolean[Size.width][Size.height];
		moves = new ArrayList<MoveFormat>();
		errMsg = "";
		
		// Sets the preferred size to a good one
		this.setPreferredSize(new Dimension(Size.width * Tile_Size.width, Size.height * Tile_Size.height));
		this.addMouseListener(this);
		this.setVisible(true);
		this.resetBoard();
	}
	
	public void resetBoard() {
		// Reset all the tiles
		this.resetTiles();
		
		// Repaint the whole area
		// to update it
		this.repaint();
	}
	
	public void cloneSelf(int x, int y) {
		// Check for clone space:
		// If the click was valid (not negative or out of range)
		// If the cloned spots were valid(not out of range)
		// If the cloned spots were empty
		if(x >= 0 && x < Size.width && y >= 0 && y < Size.height &&
				x + 1 < Size.width && y - 1 < Size.width &&
				!tiles[x + 1][y] && !tiles[x][y - 1]) {
			tiles[x][y] = false;
			tiles[x + 1][y] = true;
			tiles[x][y - 1] = true;
			
			// Add to history
			moves.add(new MoveFormat(x,y));
			
			// Reset error message
			errMsg = "";
		}
		else {
			// Set error message
			errMsg = "Error: Cannot clone tile";
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Translates the x and y coordinates into
		// 2d array coordinates by rounding
		int cx, cy;
		cx = e.getX() / Tile_Size.width;
		cy = e.getY() / Tile_Size.height;
		
		// Calls the clone function if there is a clone on the clicked tile
		if(tiles[cx][cy]) {
			this.cloneSelf(cx, cy);
			this.repaint();
		}
		// Prints out an error message if there are any errors
		if(errMsg != "") {
			System.out.println(errMsg);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void paint(Graphics g) {
		// Calls super
		super.paint(g);
		
		Graphics2D gd = (Graphics2D)g;
		
		// Own paint methods - paint all tiles
		for(int y=0; y<Size.height; y++) {
			for(int x=0; x<Size.width; x++) {
				// Draws a tile for every tile in 2d array
				// If the squares are part of the prison, color them red instead of black
				gd.setColor(Color.BLACK);
				gd.drawRect(x * Tile_Size.width, y * Tile_Size.height, Tile_Size.width, Tile_Size.height);
				if((x == 0 && y == Size.height - 1) || (x == 0 && y == Size.height - 2) || (x == 1 && y == Size.height - 1)) {
					gd.setColor(Color.RED);
					gd.fillRect(x * Tile_Size.width, y * Tile_Size.height, Tile_Size.width, Tile_Size.height);
				}
				
				// If element is true, draw a circle in it
				if(tiles[x][y]) {
					gd.setColor(Color.BLACK);
					gd.drawOval(x * Tile_Size.width, y * Tile_Size.height, Tile_Size.width, Tile_Size.height);
					gd.setColor(Color.WHITE);
					gd.fillOval(x * Tile_Size.width, y * Tile_Size.height, Tile_Size.width, Tile_Size.height);
				}
			}
		}
	}

}

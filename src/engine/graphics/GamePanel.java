package engine.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener, MouseMotionListener, MouseListener {

	public static int S_WIDTH = 528;
	public static int S_HEIGHT = 528;

	public final static int TILE_SIZE = S_WIDTH/24;
	public byte[][] tiles = new byte[50][50];
	BufferedImage img = new BufferedImage(tiles.length * TILE_SIZE, tiles[0].length * TILE_SIZE, BufferedImage.TYPE_INT_RGB);
	
	public int cameraCenterX = tiles.length/2 * TILE_SIZE;
	public int cameraCenterY = tiles[0].length/2 * TILE_SIZE;

	public Timer t;
	
	boolean interlaceEven = true;

	public GamePanel() {
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		setPreferredSize(new Dimension(S_WIDTH, S_HEIGHT));
		
//		t = new Timer(500, this);
//		t.start();
		
		setFocusable(true);
		requestFocus();
		
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				if (j < 10) {
					tiles[i][j] = 0;
				} else if (j < 11) {
					tiles[i][j] = 3;
				} else if (j < 12) {
					if(Math.random() > 0.5) {
						tiles[i][j] = 3;
					} else {
						tiles[i][j] = 4;
					}
				} else {
					if(Math.random() > 0.9) {
						tiles[i][j] = 2;
					} else {
						tiles[i][j] = 4;
					}
				}
			}
		}
		
		for(int i = 0; i < img.getWidth(); i++) {
			for(int j = 0; j < img.getHeight(); j++) {
				img.setRGB(i, j, toRGB(tiles[i/TILE_SIZE][j/TILE_SIZE]));
			}
		}
		
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, S_WIDTH, S_HEIGHT, cameraCenterX - S_WIDTH/2, cameraCenterY - S_HEIGHT/2, cameraCenterX + S_WIDTH/2, cameraCenterY + S_HEIGHT/2, Color.black, this);
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {}
	@Override
	public void mouseMoved(MouseEvent arg0) {}
	@Override
	public void actionPerformed(ActionEvent arg0) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			cameraCenterY--;
			break;
		case KeyEvent.VK_A:
			cameraCenterX--;
			break;
		case KeyEvent.VK_S:
			cameraCenterY++;
			break;
		case KeyEvent.VK_D:
			cameraCenterX++;
			break;
		case KeyEvent.VK_E:
			break;
		case KeyEvent.VK_Q:
			break;
		case KeyEvent.VK_SPACE:
			break;
		case KeyEvent.VK_ENTER:
			break;
		}
		
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
	
	private int[] colors = {Color.cyan.getRGB(), Color.black.getRGB(), Color.gray.getRGB(), Color.green.getRGB(), (new Color(135,72,42).getRGB())};
	private int toRGB(byte b) {
		//return b >> 24 + b >> 16 + b >> 8;
		return colors[b];
	}
}

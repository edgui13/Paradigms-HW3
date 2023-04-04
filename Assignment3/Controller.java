
//Name: Edgar Alcocer
//Date: Feb 28 2023
//Assignment Description: Build a map editor for a four-room video game.
//This is the controller which takes care of all the key functionality
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener {

	View view;
	Model model;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;

	Controller() {
	}

	public void actionPerformed(ActionEvent e) {
	}

	// Set destination for the image to go w/ the mouse
	public void mousePressed(MouseEvent e) {
		int mousex = e.getX();
		int mousey = e.getY();
		int x = mousex - mousex % 50 + view.scrollPosX;
		int y = mousey - mousey % 50 + view.scrollPosY;

		// check if a tile already exists at thte clicked position
		boolean tileExists = false;
		for (Tile tile : model.tiles) {
			if (tile.removeTile(x, y)) {
				model.tiles.remove(tile);
				tileExists = true;
				break;
			}
		}
		// If no tile exists, add a new tile at the clicked position
		if (!tileExists) {
			model.addTile(x, y);
		}
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	void setView(View v) {
		view = v;
	}

	Controller(Model m) {
		model = m;
	}

	// Read the key that is pressed to move the image or close the window
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
				keyLeft = true;
				if (view.scrollPosX > 0) {
					view.scrollPosX -= 700;
				}
				break;
			case KeyEvent.VK_D:
				keyRight = true;
				if (view.scrollPosX < view.MAX_SCROLL_X) {
					view.scrollPosX += 700;
				}
				break;
			case KeyEvent.VK_W:
				keyUp = true;
				if (view.scrollPosY > 0) {
					view.scrollPosY -= 500;
				}
				break;
			case KeyEvent.VK_X:
				keyDown = true;
				if (view.scrollPosY < view.MAX_SCROLL_Y) {
					view.scrollPosY += 500;
				}
				break;
			case KeyEvent.VK_Q:
				System.exit(0); // will quit your program's execution if pressing q/Q.
				break;
			case KeyEvent.VK_ESCAPE:
				System.exit(0); // will quit your program's execution if pressing esc.
				break;
		}
	}

	// Move the image after reading the key
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
				keyLeft = false;
				break;
			case KeyEvent.VK_D:
				keyRight = false;
				break;
			case KeyEvent.VK_W:
				keyUp = false;
				break;
			case KeyEvent.VK_X:
				keyDown = false;
				break;
			case KeyEvent.VK_S:
				Json saveFile = model.marshal();
				saveFile.save("map.json");
				// System.out.println("Map is Saved!");
				break;
			case KeyEvent.VK_L:
				Json loadFile = Json.load("map.json");
				model.unmarshal(loadFile);
				// System.out.println("Map is loaded");
				break;
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	void update() {
	}
}

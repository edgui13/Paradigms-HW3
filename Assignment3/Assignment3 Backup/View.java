
//Name: Edgar Alcocer
//Date: Feb 28 2023
//Assignment Description: Build a map editor for a four-room video game.
//This is the view which is the frame that is shown to the user it moves with in combianation with the contols.
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
// import java.io.IOException;
import java.io.File;
// import javax.swing.JButton;
import java.awt.Color;

class View extends JPanel {
	BufferedImage tile_image;
	Model model;
	int scrollPosX;
	int scrollPosY;
	int MAX_SCROLL_X = 700;
	int MAX_SCROLL_Y = 500;

	// View Constructor
	View(Controller c, Model m) {
		c.setView(this);
		this.model = m;
		try {
			this.tile_image = ImageIO.read(new File("tile.jpg"));// loads the image into memory it doesnt draw it
		} catch (Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}

	// draw the Tiles
	public void paintComponent(Graphics g) { // this will draw the image
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		for (int i = 0; i < model.tiles.size(); i++) {
			Tile t = model.tiles.get(i);
			g.drawImage(tile_image, t.x - scrollPosX, t.y - scrollPosY, t.width, t.height, null);
		}
	}

}

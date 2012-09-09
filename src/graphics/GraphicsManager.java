/*
 * Created on Oct 13, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package graphics;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

/**
 * @author Ivan Stuart
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class GraphicsManager {

	private Map<String, Image> map = new HashMap<String, Image>();

	private GraphicsConfiguration gc;

	/**
	 *  
	 */
	public GraphicsManager(GraphicsConfiguration gConfig) {
		super();
		gc = gConfig;

	}

	public Image getFlippedImage(Image image) {
		return getScaledImage(image, 1, -1);
	}

	public Image getImage(String name) {

		return map.get(name);
	}

	public Image getMirrorImage(Image image) {
		return getScaledImage(image, -1, 1);
	}

	private Image getScaledImage(Image image, float x, float y) {

		// set up the transform
		AffineTransform transform = new AffineTransform();
		transform.scale(x, y);
		transform.translate((x - 1) * image.getWidth(null) / 2,
				(y - 1) * image.getHeight(null) / 2);

		// create a transparent (not translucent) image
		Image newImage = gc.createCompatibleImage(image.getWidth(null),
				image.getHeight(null), Transparency.BITMASK);

		// draw the transformed image
		Graphics2D g = (Graphics2D) newImage.getGraphics();
		g.drawImage(image, transform, null);
		g.dispose();

		return newImage;
	}

	/**
	 * Gets an image from the images/ directory.
	 */
	public Image loadImage(String name) {

		String filename = "images\\" + name;
		System.out.println("Name= " + filename);
		Image icon = new ImageIcon(filename).getImage();
		System.out.println("Image= " + icon);
		return icon;
	}

	public void loadImages() {
		System.out.println("Loading graphics into memory!");

		String prefix = "images/";

		URL aURL = GraphicsManager.class.getResource(prefix + "background.png");
		Image icon = new ImageIcon(aURL).getImage();
		map.put("background", icon);

		// Wizard Images
		aURL = GraphicsManager.class.getResource("images/wiz_left_01.png");
		icon = new ImageIcon(aURL).getImage();
		map.put("wizard1", icon);

		aURL = GraphicsManager.class.getResource("reverse/wiz_right_01.png");
		icon = new ImageIcon(aURL).getImage();
		map.put("wizard2", icon);

		// Wizard Images death
		aURL = GraphicsManager.class.getResource("images/W1_death.png");
		icon = new ImageIcon(aURL).getImage();
		map.put("death1", icon);

		aURL = GraphicsManager.class.getResource("reverse/W2_death.png");
		icon = new ImageIcon(aURL).getImage();
		map.put("death2", icon);

		loadSpells();

	}

	private void loadSpellImages(String type) {

		for (int level = 1; level < 6; level++) {
			String identifier = String.valueOf(level) + type;
			String imageName = "images/L" + identifier + ".png";
			String reverseName = "reverse/L" + identifier + ".png";
			URL spellURL = GraphicsManager.class.getResource(imageName);
			Image icon = new ImageIcon(spellURL).getImage();
			map.put(identifier, icon);

			spellURL = GraphicsManager.class.getResource(reverseName);
			icon = new ImageIcon(spellURL).getImage();
			map.put(identifier + "r", icon);
		}
	}

	private void loadSpells() {
		loadSpellImages("a");
		loadSpellImages("d");
		loadSpellImages("t");
		loadSpellImages("o");
	}
}
package graphics;

import java.awt.Graphics2D;
import java.awt.Image;

public class Sprite {

	protected Image image;

	// position (pixels)
	protected float x;
	protected float y;

	// velocity (pixels per millisecond)
	protected float dx;
	protected float dy;

	// dimensions of sprite
	protected int height;
	protected int width;

	public Sprite() {

	}

	/**
	 * Creates a new Sprite object with the specified Animation.
	 */
	public Sprite(Image myImage) {
		image = myImage;
		this.setSize();

		System.out.println("Sprite is (" + height + "," + width + ")");

	}

	/**
	 * @param g
	 */
	public void draw(Graphics2D g) {
		g.drawImage(image, this.getX(), this.getY(), null);
	}

	/**
	 * @return Returns the height.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return Returns the width.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Gets this Sprite's current x position.
	 */
	public int getX() {
		return Math.round(x);
	}

	/**
	 * Gets this Sprite's current y position.
	 */
	public int getY() {
		return Math.round(y);
	}

	public boolean isCollision(Sprite sp) {
		int spX = sp.getX();
		int spY = sp.getY();

		// check if the two sprites' boundaries intersect
		return (x + width > spX && y + height > spY && x < spX + sp.getWidth() && y < spY
				+ sp.getHeight());

	}

	public void mirrorVertically() {

	}

	protected void setSize() {
		if (image != null) {
			height = image.getHeight(null);
			width = image.getWidth(null);
		}
	}

	/**
	 * Sets this Sprite's current x position.
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * Sets this Sprite's current y position.
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * Updates this Sprite's Animation and its position based on the velocity.
	 */
	public void update(long elapsedTime) {
		x += dx * elapsedTime;
		y += dy * elapsedTime;
		// if animation update when need to
	}

}

/*
 * Created on Oct 10, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package graphics;

import java.awt.Image;

/**
 * @author Ivan Stuart
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class BackgroundSprite extends Sprite {

	/**
	 * @param image
	 */
	public BackgroundSprite(Image aImage) {
		super(aImage);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isCollision(Sprite sp) {
		return false;
	}

}

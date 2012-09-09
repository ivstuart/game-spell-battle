/*
 * Created on Nov 4, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package controller;

import graphics.WizardInterface;

/**
 * @author Ivan Stuart
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class Move extends Pressable {

	private int direction;

	/**
	 * 
	 */
	public Move(WizardInterface aWizard, int aDirection) {
		super(aWizard);
		direction = aDirection;

		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.Pressable#pressed()
	 */
	@Override
	public void pressed() {
		// TODO Auto-generated method stub
		super.getWizard().move(direction);
	}

}

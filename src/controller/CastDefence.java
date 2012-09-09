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
public class CastDefence extends Pressable {

	/**
	 * 
	 */
	public CastDefence(WizardInterface aWizard) {
		super(aWizard);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.Pressable#pressed()
	 */
	@Override
	public void pressed() {
		// TODO Auto-generated method stub
		super.getWizard().castDefence();
	}

}

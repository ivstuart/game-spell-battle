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
public class CastAttack extends Pressable {

	private String type;

	/**
	 * 
	 */
	public CastAttack(WizardInterface aWizard, String aType) {
		super(aWizard);
		type = aType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.Pressable#pressed()
	 */
	@Override
	public void pressed() {
		// TODO Auto-generated method stub
		super.getWizard().castAttack(type);
	}

}

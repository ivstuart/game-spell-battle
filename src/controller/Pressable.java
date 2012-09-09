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
public abstract class Pressable {

	private WizardInterface wizard;

	public Pressable(WizardInterface aWizard) {
		wizard = aWizard;
	}

	public WizardInterface getWizard() {
		return wizard;
	}

	public abstract void pressed();

}

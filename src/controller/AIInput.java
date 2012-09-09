/*
 * Created on Nov 4, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package controller;

import graphics.StatusPanel;
import graphics.Wizard;

/**
 * @author Ivan Stuart
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class AIInput {

	private Wizard myWizard;
	private StatusPanel stats;

	/**
	 * 
	 */
	public AIInput(Wizard aWizard) {
		super();
		myWizard = aWizard;
		stats = myWizard.getMyStats();
	}

	public void update() {
		if (stats.isChanneling()) {
			if (stats.getPower() / 80 >= 5) {
				myWizard.castAttack("a");
			}
			if (stats.getMana() < 1) {
				myWizard.castAttack("a");
			}
		} else {
			if (stats.getMana() > 390) {
				stats.channelMana();
			}
		}
	}
}

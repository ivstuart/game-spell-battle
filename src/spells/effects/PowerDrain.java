/*
 * Created on Nov 4, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package spells.effects;

import graphics.GamePanel;
import graphics.StatusPanel;
import graphics.WizardInterface;

/**
 * @author Ivan Stuart
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class PowerDrain implements SpellEffect {

	private int amount;

	/**
	 * 
	 */
	public PowerDrain(int amt) {
		super();
		amount = amt;
		// TODO Auto-generated constructor stub
	}

	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see spells.spellEffect#effect(graphics.WizardInterface)
	 */
	@Override
	public void effect(WizardInterface target) {
		// TODO Auto-generated method stub
		StatusPanel sp = target.getMyStats();
		int power = sp.getPower();
		int drain = Math.min(power, amount);
		sp.addPower(-drain);
		// Small hack
		if (drain < 0) {
			drain = -drain;
		}
		GamePanel.getOpponent(target).getMyStats().addPower(drain);
	}

}

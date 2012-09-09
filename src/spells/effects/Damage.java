/*
 * Created on Nov 4, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package spells.effects;

import graphics.WizardInterface;

/**
 * @author Ivan Stuart
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class Damage implements SpellEffect {

	private int amount;

	/**
	 * 
	 */
	public Damage(int amt) {
		super();
		amount = amt;
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see spells.spellEffect#effect(graphics.WizardInterface)
	 */
	@Override
	public void effect(WizardInterface target) {
		// TODO Auto-generated method stub
		target.getMyStats().burn(amount);
	}

}

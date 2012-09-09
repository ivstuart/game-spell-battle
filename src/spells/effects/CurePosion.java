/*
 * Created on Nov 4, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package spells.effects;

import graphics.GamePanel;
import graphics.WizardInterface;

/**
 * @author Ivan Stuart
 * 
 */
public class CurePosion implements SpellEffect {

	/**
	 * 
	 */
	public CurePosion(int amt) {
		super();

	}

	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see spells.spellEffect#effect(graphics.WizardInterface)
	 */
	@Override
	public void effect(WizardInterface target) {
		GamePanel.getOpponent(target).getMyStats().posion(0, 0);
	}

}

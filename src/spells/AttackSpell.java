/*
 * Created on Oct 10, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package spells;

import game.ScreenManager;
import graphics.Sprite;
import graphics.WizardInterface;
import spells.effects.EffectFactory;
import spells.effects.SpellEffect;

/**
 * @author Ivan Stuart
 * 
 *         TODO The constructors can be refactored into less code
 * 
 *         Possible refactor of common code from this and Defence Spell class
 */
public class AttackSpell extends Sprite implements SpellInterface {

	public static final int MIN_CAST_MANA = 80;
	public static final float SPELL_SPEED = 0.2F;

	private Sprite myWizard;
	private int level;
	private int power;
	private int direction;

	private SpellEffect effect;

	private String spellType;

	public AttackSpell(String type, int direct, int aLevel, Sprite wizard) {
		spellType = type;
		direction = direct;
		level = aLevel;
		power = MIN_CAST_MANA * aLevel;
		myWizard = wizard;
		String spellString = String.valueOf(level) + spellType;
		effect = EffectFactory.getEffect(spellString);
		if (direction == -1) {
			spellString = spellString + "r";
		}
		this.image = ScreenManager.gManager.getImage(spellString);
		this.setSize();
		this.y = 400 - (this.height / 2);
		this.dx = SPELL_SPEED * direction;

	}

	/**
	 * @param left
	 * @param level
	 */
	public AttackSpell(String type, Sprite wizard, int direct, int pwr) {
		spellType = type;
		myWizard = wizard;
		power = pwr;
		direction = direct;
		level = pwr / MIN_CAST_MANA;

		String spellString = String.valueOf(level) + spellType;
		effect = EffectFactory.getEffect(spellString);
		if (direction == -1) {
			spellString = spellString + "r";
		}
		this.image = ScreenManager.gManager.getImage(spellString);
		if (image == null) {
			System.out.println("Spell String = " + spellString);
		}
		this.setSize();
		this.x = myWizard.getX()
				+ ((myWizard.getWidth() + this.width - 40) * direction);
		this.y = myWizard.getY() + 100 - (this.height / 2);
		this.dx = SPELL_SPEED * direction;

	}

	public AttackSpell changeLevel(int levelChange) {
		if (level + levelChange < 1) {
			return null;
		}
		AttackSpell spell = new AttackSpell(spellType, myWizard, direction,
				power + (levelChange * 30));
		spell.setX(x);
		spell.setY(y);
		return spell;
	}

	@Override
	public void effect(WizardInterface target) {
		effect.effect(target);
	}

	/**
	 * @return
	 */
	public int getDirection() {
		// TODO Auto-generated method stub
		return direction;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see spells.SpellInterface#getLevel()
	 */
	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return level;
	}

	/**
	 * @return Returns the spellType.
	 */
	public String getSpellType() {
		return spellType;
	}

	/**
	 * @return
	 */
	public Sprite getWizard() {
		// TODO Auto-generated method stub
		return myWizard;
	}

	@Override
	public boolean isCollision(Sprite sp) {
		// This next line does not work.
		if (sp == myWizard) {
			return false;
		}
		return super.isCollision(sp);
	}

}

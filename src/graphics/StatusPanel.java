/*
 * Created on 25-Jun-2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * @author default
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class StatusPanel extends JPanel implements Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8519370190897122806L;
	public static final float scaleFactor = 0.5F;
	public static final int height = 40;
	public static final int width = 500;

	private float posionFactor;
	private int posion;

	private float health;
	private float power;
	private float mana;

	private float hpRegen;
	private float manaRegen;

	private int maxHp;
	private int maxPow;
	private int maxMana;

	private boolean channeling = false;

	public StatusPanel() {
		setPreferredSize(new Dimension(width, height));
	}

	/**
	 * @param i
	 */
	public void addMana(int i) {
		// TODO Auto-generated method stub
		mana += i;
	}

	/**
	 * @param i
	 */
	public void addPower(int i) {
		power += i;
		if (power < 0) {
			power = 0;
		}
	}

	public void burn(int damage) {
		health -= damage;
	}

	/**
	 * 
	 */
	public int cast() {
		// TODO Auto-generated method stub
		int tempPower = (int) power;
		channeling = false;
		power = 0;
		return tempPower;
	}

	/**
	 * 
	 */
	public void channelMana() {
		// TODO Auto-generated method stub
		channeling = !channeling;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/**
	 * @return
	 */
	public int getHealth() {
		return (int) health;
	}

	/**
	 * @return
	 */
	public int getMana() {
		return (int) mana;
	}

	/**
	 * @return
	 */
	public int getPower() {
		return (int) power;
	}

	public void heal(int healing) {
		health += healing;
		if (healing > maxHp) {
			health = maxHp;
		}
	}

	/**
	 * @param stats1
	 */
	public void init(int[] stats1) {

		/**
		 * Useful as a reference this.addRow("Health",healthSpinner);
		 * this.addRow("Mana",manaSpinner);
		 * this.addRow("Magical Attack",mAttackSpinner);
		 * this.addRow("Magical Defence",mDefenceSpinner);
		 * this.addRow("Health Regeneration",healthRegenSpinner);
		 * this.addRow("Mana Regeneration",manaRegenSpinner);
		 * 
		 */

		maxHp = (300 * stats1[0]) / 100;
		maxPow = 480;
		maxMana = (1500 * stats1[1]) / 100;

		health = maxHp;
		power = 0;
		mana = maxMana;

		hpRegen = (float) (stats1[4] / 150.0);
		manaRegen = (float) (stats1[5] / 30.0);

		channeling = false;
		this.repaint();
	}

	public boolean isChanneling() {
		return channeling;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graphics.Drawable#draw(java.awt.Graphics2D)
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// TODO Auto-generated method stub
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width - 2, height - 2);

		if (health > 0) {
			g.setColor(Color.GREEN);
			g.fill3DRect(2, 2, 2 + (int) health, 10, true);
		}

		if (power > 0) {
			g.setColor(Color.RED);
			g.fill3DRect(2, 14, 2 + (int) power, 10, true);
		}

		if (mana > 0) {
			g.setColor(Color.BLUE);
			g.fill3DRect(2, 26, 2 + (int) (mana / 4), 10, true);
		}
	}

	/**
	 * @param i
	 * @param posion
	 */
	public void posion(int i, float factor) {
		posion += i;
		posionFactor = factor;
	}

	/**
	 * @param i
	 */
	public void setHealth(int i) {
		health = i;
	}

	/**
	 * @param i
	 */
	public void setMana(int i) {
		// TODO Auto-generated method stub
		mana = i;
	}

	public void update(long elapsedTime) {
		if (channeling) {
			if (mana > 0) {
				power += elapsedTime * scaleFactor;
				mana -= elapsedTime * scaleFactor;
			} else {
				mana = 0;
				channeling = false;
			}
		} else {
			if (power > 0) {
				power -= elapsedTime * scaleFactor;
			}
			if (mana < maxMana) {
				mana += elapsedTime * scaleFactor / 3 + manaRegen;
			}
			health += hpRegen;
		}
		if (power > maxPow) {
			health = 0;
			power = 0;
			mana = 0;
		}
		if (posion > 0) {
			posion--;
			health -= posionFactor;
		}
		if (health > maxHp) {
			health = maxHp;
		}
		if (mana > maxMana) {
			mana = maxMana;
		}
		this.repaint();
	}

}

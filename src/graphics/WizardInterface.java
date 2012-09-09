/*
 * Created on Oct 11, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package graphics;

/**
 * @author Ivan Stuart
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public interface WizardInterface {

	/**
	 * @param type
	 */
	public void castAttack(String type);

	/**
	 * 
	 */
	public void castDefence();

	public int getDirection();

	public StatusPanel getMyStats();

	/**
	 * @param string
	 */
	public String getWinMessage();

	public boolean isDead();

	public void keyAction(char keyChar);

	/**
	 * 
	 */
	public void manaBuildUp();

	/**
	 * @param direction
	 */
	public void move(int direction);

	/**
	 * @param i
	 * @param j
	 */
	public void setBounds(int i, int j);

	public void setDead(boolean death);

	/**
	 * @param string
	 */
	public void setWinMessage(String string);

	public void update(long elapsedTime);
}

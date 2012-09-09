/*
 * Created on Oct 8, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package graphics;

import game.GameManager;
import game.ScreenManager;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import sound.AudioCache;
import spells.AttackSpell;
import spells.DefenceSpell;
import spells.SpellInterface;
import controller.AIInput;
import controller.UserInput;
import controller.UserInputFactory;
import controller.WizardController;

/**
 * @author Ivan Stuart
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class GamePanel extends JPanel {

	private class TimerAction implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			message.update();
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7070886990353076394L;

	public static final int SPEED = 1000;
	private static List<Sprite> sprites;
	private static Wizard wizard1;

	private static Wizard wizard2;

	private static int counter;

	public static WizardInterface getOpponent(WizardInterface me) {
		if (me == wizard1) {
			return wizard2;
		} else {
			return wizard1;
		}

	}

	public static List<Sprite> getSprites() {
		return sprites;
	}

	/**
	 * 
	 */
	public static void restart() {
		// TODO Auto-generated method stub
		restart = true;
	}

	private Timer myTimer; // TODO for messages etc...

	private static Sprite dead;

	private BackgroundSprite background;

	private MessageSprite message;

	private ScreenManager sm;

	private static boolean restart = false;

	private AIInput ai = null;

	/**
	 * 
	 */
	public GamePanel(ScreenManager screenManager) {
		super();

		sm = screenManager;

		setPreferredSize(new Dimension(1000, 600));

		Image bg = sm.getGManager().getImage("background");

		background = new BackgroundSprite(bg);
		message = new MessageSprite();
		message.x = 440;
		message.y = 200;

		wizard1 = new Wizard(sm.getStatus1(), sm.getGManager().getImage(
				"wizard1"), 0, 1);
		wizard2 = new Wizard(sm.getStatus2(), sm.getGManager().getImage(
				"wizard2"), 900, -1);

		wizard2.setWinMessage("Player One is the WINNER!");
		wizard1.setWinMessage("Player Two is the WINNER!");

		wizard1.setBounds(0, 300);
		wizard2.setBounds(600, 900);

		UserInput user1 = null;
		UserInput user2 = null;

		try {
			// This is the new user control code
			user1 = UserInputFactory.create(wizard1, "aszxcvb");
			user2 = UserInputFactory.create(wizard2, "oplkjhg");
		} catch (Exception e) {
			e.printStackTrace();
		}
		GameManager.setPaused(true);
		addKeyListener(new WizardController(user1));
		addKeyListener(new WizardController(user2));

	}

	public void add(Sprite aSprite) {

		sprites.add(aSprite);
	}

	/**
	 * 
	 */
	private void checkWizardStatus(WizardInterface aWizard, String imageString) {
		// TODO Auto-generated method stub
		if (aWizard.getMyStats().getHealth() < 1) {
			Sprite death = new Sprite(sm.getGManager().getImage(imageString));
			death.setX(((Sprite) aWizard).getX() - 60);
			death.setY(((Sprite) aWizard).getY() - 64);
			sprites.add(death);
			dead = death;
			sprites.remove(aWizard);
			aWizard.setDead(true);
			message.setMessage(aWizard.getWinMessage());
			message.x = 300;
			GameManager.setPaused(true);

			if (Math.random() > .7) {
				int number = 1 + (int) (Math.random() * 7);
				AudioCache.play(String.valueOf(number));
			} else {
				AudioCache.play("dead");
			}

		}
	}

	/**
	 * @param sprite
	 * @param anotherSprite
	 */
	private void collision(Sprite sprite, Sprite anotherSprite) {
		// TODO Auto-generated method stub
		if (sprite instanceof DefenceSpell
				&& anotherSprite instanceof DefenceSpell) {
			sprites.remove(sprite);
			sprites.remove(anotherSprite);
			AudioCache.play("absorb");
			return;
		}

		if (sprite instanceof DefenceSpell
				&& anotherSprite instanceof AttackSpell) {
			this.defenceCollision((DefenceSpell) sprite,
					(AttackSpell) anotherSprite);
		}
		if (anotherSprite instanceof DefenceSpell
				&& sprite instanceof AttackSpell) {
			this.defenceCollision((DefenceSpell) anotherSprite,
					(AttackSpell) sprite);

		}

	}

	/**
	 * 
	 */
	private void collisionDetection() {
		// TODO Auto-generated method stub

		outsideLoop:

		for (int index = 0; index < sprites.size(); index++) {

			Sprite aSprite = sprites.get(index);
			for (int innerIndex = index + 1; innerIndex < sprites.size(); innerIndex++) {
				Sprite anotherSprite = sprites.get(innerIndex);

				if (aSprite.isCollision(anotherSprite)) {
					// System.out.println("A collision occured between "+index+" and "+innerIndex);
					if (aSprite instanceof WizardInterface
							&& anotherSprite instanceof SpellInterface) {

						SpellInterface aSpell = (SpellInterface) anotherSprite;

						aSpell.effect((WizardInterface) aSprite);
						AudioCache.play("zap2");
						sprites.remove(anotherSprite);
						continue outsideLoop;
					}

					this.collision(aSprite, anotherSprite);
				}
			}
		}
	}

	/**
	 * @param spell
	 * @param spell2
	 */
	private void defenceCollision(DefenceSpell spell, AttackSpell spell2) {
		// Reflect first
		int reflectSpellLevel = Math.min(spell2.getLevel(), spell.getReflect());
		AudioCache.play("absorb");

		if (reflectSpellLevel > 0) {
			AttackSpell newSpell = new AttackSpell(spell2.getSpellType(),
					spell.getDirection(), reflectSpellLevel, spell.getWizard());
			newSpell.setX(spell.getX());// + spell2.width / 2 -
										// newSpell.width/2);
			sprites.add(newSpell);
			AudioCache.play("zap5");
		}

		// Absorb after reflect
		int newSpellLevel = spell2.getLevel() - spell.getAbsorb();

		if (newSpellLevel > 0) {
			AttackSpell newSpell = new AttackSpell(spell2.getSpellType(),
					spell2.getDirection(), newSpellLevel, spell2.getWizard());
			newSpell.setX(spell2.getX());// + spell2.width / 2 -
											// newSpell.width/2);
			sprites.add(newSpell);
			AudioCache.play("zap6");
		}

		// Remove orginal colliding spells, both of them
		sprites.remove(spell2);
		sprites.remove(spell);
	}

	public void init() {
		this.repaint();

		GameManager.setPaused(true);

		sprites = new LinkedList<Sprite>();

		dead = null;
		counter = 0;
		wizard1.setDead(false);
		wizard2.setDead(false);

		sprites.add(background);
		sprites.add(message);
		message.x = 440;
		message.reset();

		myTimer = new Timer(SPEED, new TimerAction());
		myTimer.start();

		// Required for restarting
		wizard1.x = 0;
		wizard1.dx = 0;
		wizard2.x = 900;
		wizard2.dx = 0;

		sprites.add(wizard1);
		sprites.add(wizard2);
		restart = false;

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int index = 0; index < sprites.size(); index++) {

			Sprite aSprite = sprites.get(index);

			if (aSprite.x > this.getWidth() || aSprite.x < -65) {
				sprites.remove(index);
				index--;
				// System.out.println("Removed sprite");
				continue;
			}

			this.collisionDetection();

			if (wizard1.isDead() == false) {
				this.checkWizardStatus(wizard1, "death1");
			}

			if (wizard2.isDead() == false) {
				this.checkWizardStatus(wizard2, "death2");
			}

			if (dead != null) {
				counter++;
				if (counter > 450) {
					sprites.remove(dead);
					// end game
					counter = 0;
					if (wizard1.isDead() || wizard2.isDead()) {
						restart = true;
					}
					dead = null;
				}
			}

			aSprite.draw((Graphics2D) g);

			if (restart) {
				sm.startGame();
			}

			if (ai != null) {
				ai.update();
			}

		}

	}

	/**
	 * 
	 */
	public void setAIon() {
		// TODO Auto-generated method stub
		ai = new AIInput(wizard2);
	}
}

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
public class UserInputFactory {

	public static int NUMBER_OF_KEYS = 7;

	public static UserInput create(WizardInterface aWizard, String keys)
			throws Exception {
		if (keys.length() != NUMBER_OF_KEYS) {
			throw new Exception("Incorrect number of user input keys defined!");
		}
		UserInput user1 = new UserInput();
		user1.add(keys.substring(0, 1), new Move(aWizard, -1));
		user1.add(keys.substring(1, 2), new Move(aWizard, 1));

		user1.add(keys.substring(2, 3), new ManaBuildUp(aWizard));
		user1.add(keys.substring(3, 4), new CastDefence(aWizard));
		user1.add(keys.substring(4, 5), new CastAttack(aWizard, "a"));
		user1.add(keys.substring(5, 6), new CastAttack(aWizard, "t"));
		user1.add(keys.substring(6, 7), new CastAttack(aWizard, "o"));

		// TODO factor this out elsewhere?

		user1.add("n", new MusicOff(null));
		user1.add("m", new MusicOn(null));

		user1.add("0", new PauseGame(null));
		user1.add("1", new UnPauseGame(null));

		return user1;
	}

}

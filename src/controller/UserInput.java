/*
 * Created on Nov 4, 2004
 *
 */
package controller;

import java.util.HashMap;

/**
 * @author Ivan Stuart
 * 
 */
public class UserInput {

	private HashMap<String, Pressable> keys;

	public UserInput() {
		super();
		keys = new HashMap<String, Pressable>();
	}

	/**
	 * 
	 * @param keyString
	 * @param action
	 */
	public void add(String keyString, Pressable action) {
		keys.put(keyString, action);
	}

	/**
	 * 
	 * @param keyString
	 */
	public void keyAction(String keyString) {

		Pressable key = keys.get(keyString);

		if (key != null) {
			key.pressed();
		}
	}
}

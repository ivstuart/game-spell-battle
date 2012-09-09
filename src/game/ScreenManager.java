/*
 * Created on Oct 4, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package game;

import graphics.GamePanel;
import graphics.GraphicsManager;
import graphics.StatusPanel;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import sound.AudioCache;
import wizDesigner.DesignerPanel;

/**
 * @author Ivan Stuart
 * 
 *         Player 1 Info Player 2 Info Main Graphics Window TODO To change the
 *         template for this generated type comment go to Window - Preferences -
 *         Java - Code Style - Code Templates
 */
public class ScreenManager {

	private JFrame frame;
	private Container pane;

	private DesignerPanel designPanel;
	private JPanel gamePanel;

	public static GraphicsManager gManager;

	private StatusPanel status1;
	private StatusPanel status2;

	public ScreenManager() {

		frame = new JFrame("War Of The Warlocks v1.0");

		pane = frame.getContentPane();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gManager = new GraphicsManager(frame.getGraphicsConfiguration());

		gManager.loadImages();

		this.initialise();

		frame.pack();

		frame.setVisible(true);

	}

	/**
	 * @return Returns the frame.
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * @return Returns the gManager.
	 */
	public GraphicsManager getGManager() {
		return gManager;
	}

	public Container getPane() {
		return pane;
	}

	/**
	 * @return Returns the status1.
	 */
	public StatusPanel getStatus1() {
		return status1;
	}

	/**
	 * @return Returns the status2.
	 */
	public StatusPanel getStatus2() {
		return status2;
	}

	/**
	 * 
	 */
	private void initialise() {
		// TODO Auto-generated method stub
		status1 = new StatusPanel();
		status2 = new StatusPanel();

		JPanel playerStatus = new JPanel();

		playerStatus.add(status1);
		playerStatus.add(status2);

		pane.add(playerStatus, BorderLayout.NORTH);

		designPanel = new DesignerPanel();

		gamePanel = new GamePanel(this);

		pane.add(designPanel, BorderLayout.SOUTH);

	}

	public void repaintGame() {
		gamePanel.repaint();
	}

	public void startDesigner() {
		pane.remove(gamePanel);
		AudioCache.stop("music1");
		GameManager.setPaused(true);
		pane.add(designPanel, BorderLayout.SOUTH);
		frame.pack();
		designPanel.requestFocus();
	}

	public void startGame() {
		// IvanSound.loop("music1");
		status1.init(designPanel.getStats1());
		status2.init(designPanel.getStats2());
		if (designPanel.isAIon()) {
			((GamePanel) gamePanel).setAIon();
		}
		pane.remove(designPanel);
		pane.add(gamePanel, BorderLayout.SOUTH);
		frame.pack();
		gamePanel.requestFocus();
		((GamePanel) gamePanel).init();

	}

	public void update(long elapsedTime) {
		status1.update(elapsedTime);
		status2.update(elapsedTime);
	}
}

/*
 * Created on Oct 17, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package wizDesigner;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Ivan Stuart
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class WizardDesigner extends JPanel {

	protected class ButtonListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String name = arg0.getActionCommand();
			if (name.equals(healthButton.getActionCommand())) {
				addValue(healthText);
			} else if (name.equals(manaButton.getActionCommand())) {
				addValue(manaText);
			} else if (name.equals(regenButton.getActionCommand())) {
				addValue(regenText);
			} else if (name.equals(attackButton.getActionCommand())) {
				addValue(attackText);
			} else if (name.equals(defenceButton.getActionCommand())) {
				addValue(defenceText);
			}

		}

		/**
		 * @param healthButton
		 */
		private void addValue(TextField field) {
			// TODO Auto-generated method stub
			String number = field.getText();
			int value = Integer.parseInt(number);
			int pool = Integer.parseInt(poolText.getText());
			if (pool < 1) {
				return;
			}
			value++;
			pool--;
			field.setText(String.valueOf(value));
			poolText.setText(String.valueOf(pool));
		}

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4625208638057331030L;
	private JLabel pool = new JLabel("Pool");
	private JLabel health = new JLabel("Health");
	private JLabel mana = new JLabel("Mana");
	private JLabel attack = new JLabel("M.Attack");
	private JLabel defence = new JLabel("M.Defence");

	private JLabel regen = new JLabel("Regeneration");
	private TextField poolText = new TextField("25");

	private JLabel poolButton = new JLabel("");
	private TextField healthText = new TextField("100");

	private JButton healthButton = new JButton("Add Hp");
	private TextField manaText = new TextField("100");

	private JButton manaButton = new JButton("Add Ma");
	private TextField attackText = new TextField("100");

	private JButton attackButton = new JButton("Add Att");
	private TextField defenceText = new TextField("100");

	private JButton defenceButton = new JButton("Add Def");
	private TextField regenText = new TextField("100");

	private JButton regenButton = new JButton("Add Reg");

	/**
	 * 
	 */
	public WizardDesigner() {
		super();
		setPreferredSize(new Dimension(600, 120));
		setLayout(new GridLayout(6, 3));

		this.add(pool);
		this.add(poolText);
		poolText.setEditable(false);
		poolText.setFocusable(false);

		this.add(poolButton);

		this.add(health);
		this.add(healthText);
		healthText.setEditable(false);
		healthText.setFocusable(false);
		this.add(healthButton);

		this.add(mana);
		this.add(manaText);
		manaText.setEditable(false);
		manaText.setFocusable(false);
		this.add(manaButton);

		this.add(attack);
		this.add(attackText);
		attackText.setEditable(false);
		attackText.setFocusable(false);
		this.add(attackButton);

		this.add(defence);
		this.add(defenceText);
		defenceText.setEditable(false);
		defenceText.setFocusable(false);
		this.add(defenceButton);

		this.add(regen);
		this.add(regenText);
		regenText.setEditable(false);
		regenText.setFocusable(false);
		this.add(regenButton);

		ButtonListener but = new ButtonListener();

		healthButton.addActionListener(but);
		manaButton.addActionListener(but);
		attackButton.addActionListener(but);
		defenceButton.addActionListener(but);
		regenButton.addActionListener(but);

		// TODO Auto-generated constructor stub
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

}

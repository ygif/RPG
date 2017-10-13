package rpgSource;

import java.text.DecimalFormat;

import javax.swing.*;

public class BattleSim {
	static Player player1 = new Player(45, 15, 10, 12);
	static Enemy[] enemy1 = new Enemy[3];
	static double damage = 0;
	static int turn = 1;
	static boolean playersTurn;
	static int x;
	static DecimalFormat numberPrinter = new DecimalFormat("###");
	static RPGGUI gui;
	static Thread thread;
	
	public static void main(String[] args) {
		init();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gui.appendToConsole("Start battle!\n");
		String winner = battle();
		gui.appendToConsole("%n" + winner + " wins.\n");
		JOptionPane.showMessageDialog(gui, winner + " wins.", "Winner", JOptionPane.INFORMATION_MESSAGE);
		gui.dispose();
	}
	
	static String battle() {
		while(player1.currentHealth > 0 || enemy1[x].currentHealth > 0 ){
			if(playersTurn == true){
				gui.appendToConsole("It's the players turn.\n");
				gui.appendToConsole("Choose an action:\n");
				gui.appendToConsole("moves(1), flee(2), use an item(3), or super attack(4).\n");
				String temp = selectAction();
				if(temp.equalsIgnoreCase("fled")){
					return "Nobody";
				}
				playersTurn = false;
			}
			if(enemy1[x].currentHealth <= 0){
				player1.experiencePoints += 6;
				player1.increaseLevel();
				return "The player";
			}
			gui.appendToConsole("\n");
			if(playersTurn == false) {
				if (x == 1) {
					((Dragon) enemy1[x]).land();
					gui.appendToConsole("It's the " + enemy1[x].name + "'s turn.\n");
					playersTurn = true;
					damage = ((Dragon) enemy1[x]).randomActionSelctor();
				} else {
					gui.appendToConsole("It's the " + enemy1[x].name + "'s turn.\n");
					damage = enemy1[x].useAMoveRandom((int) (Math.random() * 100));
					playersTurn = true;
				}
				if(damage > 0){
					player1.reduceHealth(damage);
					damage = 0;
				}
				gui.updatePlayerHealth(numberPrinter.format(player1.currentHealth));
				gui.appendToConsole("The player has " + numberPrinter.format(player1.currentHealth) + " health.\n");
			}
			if (player1.currentHealth <= 0){
				return "The enemy";
			}
			gui.updateEnemyHealth(numberPrinter.format(enemy1[x].currentHealth));
			turn++;
		}
		return "nobody";
	}
	
	static String selectAction() {
		int action = getSelector();
		RPGGUI.resetSelector();
		switch (action) {
		case 1:
			gui.appendToConsole("Chose a move.\n");
			gui.appendToConsole("Attack(1), sword slash(2), or magic beam(3).\n");
			int move = getSelector();
			RPGGUI.resetSelector();
			damage = player1.useAMove(move);
			if (damage > 0) {
				enemy1[x].reduceHealth(damage);
				gui.updateEnemyHealth(numberPrinter.format(enemy1[x].currentHealth));
				gui.appendToConsole("The " + enemy1[x].name + " has " + numberPrinter.format(enemy1[x].currentHealth) + " health.\n");
			}
			damage = 0;
			return "nothing";
		case 2:
			gui.appendToConsole("The player is trying to flee.\n");
			if (player1.flee() == true) {
				gui.appendToConsole("The player successfully flees the battle.\n");
				return "fled";
			} else {
				gui.appendToConsole("The player failed to flee.\n");
				return "Failed to flee";
			}
		case 3:
			gui.appendToConsole("Choose an item:\n");
			gui.appendToConsole("Health potion(1) or damage potion(2).\n");
			int selector = getSelector();
			RPGGUI.resetSelector();
			damage = player1.useAnItem(selector);
			if (damage > 0) {
				gui.appendToConsole(numberPrinter.format(damage) + " damage\n");
				enemy1[x].reduceHealthRecoil(damage);
				gui.updateEnemyHealth(numberPrinter.format(enemy1[x].currentHealth));
				gui.appendToConsole("The " + enemy1[x].name + " has " + numberPrinter.format(enemy1[x].currentHealth) + " health.\n");
			}
			gui.updatePlayerHealth(numberPrinter.format(player1.currentHealth));
			return "Used an item.";
		case 4:
			damage = player1.useSpecialAttack();
			enemy1[x].reduceHealth(damage);
			gui.updateEnemyHealth(numberPrinter.format(enemy1[x].currentHealth));
			gui.appendToConsole("The enemy has " + numberPrinter.format(enemy1[x].currentHealth) + " health.\n");
			damage = 0;
			return "nothing";
		default:
			gui.appendToConsole("Invalid input.\n");
			gui.appendToConsole("You can only type in:\n");
			gui.appendToConsole("moves(1), flee(2), use an item(3), or super attack(4).\n");
			return selectAction();
		}
	}
	
	static int getSelector(){
		int temp = 0;
		while(temp == 0){
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			temp = RPGGUI.selector;
		}	
		return temp;		
	}
	
	static void setWhoGoesFirst() {
		if (player1.speed >= enemy1[x].speed) {
			playersTurn = true;
		} else {
			playersTurn = false;
		}
	}
	
	static String getMaxPlayerHealth(){
		return numberPrinter.format(player1.maxHealth);
	}
	
	static String getMaxEnemyHealth(){
		return numberPrinter.format(enemy1[x].maxHealth);
	}
	
	static String getEnemyName(){
		return enemy1[x].getName();
	}

	static void init(){
		enemy1[0] = new Ogre(2, 30, 8, 8, 10, "ogre");
		enemy1[1] = new Dragon(2, 30, 7, 9, 10, "dragon");
		enemy1[2] = new MegaOgre(2, 40, 10, 9, 8, "mega ogre");
		Object[] options = {enemy1[0].name, enemy1[1].name, enemy1[2].name};
		x = JOptionPane.showOptionDialog(null, "Select your enemy.", "Enemy Selector", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
		if(x == -1){
			System.exit(0);
		}
		gui = RPGGUI.getInstance();
		gui.startGUI();
		setWhoGoesFirst();
	}
}

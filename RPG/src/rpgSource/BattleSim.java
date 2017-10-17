package rpgSource;

import java.text.DecimalFormat;

import javax.swing.*;

import rpgSource.entity.Dragon;
import rpgSource.entity.Enemy;
import rpgSource.entity.MegaOgre;
import rpgSource.entity.Ogre;
import rpgSource.entity.Player;

public class BattleSim {
	static Player player1 = new Player(45, 15, 10, 12, 10);
	static Enemy[] enemy1 = new Enemy[3];
	static double damage = 0;
	public static int turn = 1;
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
	
	public static String battle() {
		while(player1.getCurrentHealth() > 0 || enemy1[x].getCurrentHealth() > 0 ){
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
			if(enemy1[x].getCurrentHealth() <= 0){
				player1.setExperiencePoints(player1.getExperiencePoints() + 6);
				player1.increaseLevel();
				return "The player";
			}
			gui.appendToConsole("\n");
			if(playersTurn == false) {
				if (x == 1) {
					((Dragon) enemy1[x]).land();
					gui.appendToConsole("It's the " + enemy1[x].getName() + "'s turn.\n");
					playersTurn = true;
					damage = ((Dragon) enemy1[x]).randomActionSelctor();
				} else {
					gui.appendToConsole("It's the " + enemy1[x].getName() + "'s turn.\n");
					damage = enemy1[x].useAMoveRandom((int) (Math.random() * 100));
					playersTurn = true;
				}
				if(damage > 0){
					player1.reduceHealth(damage);
					damage = 0;
				}
				gui.updatePlayerHealth(numberPrinter.format(player1.getCurrentHealth()));
				gui.appendToConsole("The player has " + numberPrinter.format(player1.getCurrentHealth()) + " health.\n");
			}
			if (player1.getCurrentHealth() <= 0){
				return "The enemy";
			}
			gui.updateEnemyHealth(numberPrinter.format(enemy1[x].getCurrentHealth()));
			turn++;
		}
		return "nobody";
	}
	
	public static String selectAction() {
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
				gui.updateEnemyHealth(numberPrinter.format(enemy1[x].getCurrentHealth()));
				gui.appendToConsole("The " + enemy1[x].getName() + " has " + numberPrinter.format(enemy1[x].getCurrentHealth()) + " health.\n");
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
				gui.updateEnemyHealth(numberPrinter.format(enemy1[x].getCurrentHealth()));
				gui.appendToConsole("The " + enemy1[x].getName() + " has " + numberPrinter.format(enemy1[x].getCurrentHealth()) + " health.\n");
			}
			gui.updatePlayerHealth(numberPrinter.format(player1.getCurrentHealth()));
			return "Used an item.";
		case 4:
			damage = player1.useSpecialAttack();
			enemy1[x].reduceHealth(damage);
			gui.updateEnemyHealth(numberPrinter.format(enemy1[x].getCurrentHealth()));
			gui.appendToConsole("The enemy has " + numberPrinter.format(enemy1[x].getCurrentHealth()) + " health.\n");
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
		if (player1.getSpeed() >= enemy1[x].getSpeed()) {
			playersTurn = true;
		} else {
			playersTurn = false;
		}
	}
	
	static String getMaxPlayerHealth(){
		return numberPrinter.format(player1.getMaxHealth());
	}
	
	static String getMaxEnemyHealth(){
		return numberPrinter.format(enemy1[x].getMaxHealth());
	}
	
	static String getEnemyName(){
		return enemy1[x].getName();
	}

	static void init(){
		enemy1[0] = new Ogre(2, 30, 8, 8, 10, "ogre");
		enemy1[1] = new Dragon(2, 30, 7, 9, 10, "dragon");
		enemy1[2] = new MegaOgre(2, 40, 10, 9, 8, "mega ogre");
		Object[] options = {enemy1[0].getName(), enemy1[1].getName(), enemy1[2].getName()};
		x = JOptionPane.showOptionDialog(null, "Select your enemy.", "Enemy Selector", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
		if(x == -1){
			System.exit(0);
		}
		gui = RPGGUI.getInstance();
		gui.startGUI();
		setWhoGoesFirst();
	}
}

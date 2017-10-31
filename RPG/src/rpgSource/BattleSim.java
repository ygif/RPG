package rpgSource;

import java.text.DecimalFormat;

import javax.swing.*;

import rpgSource.entity.Dragon;
import rpgSource.entity.Enemy;
import rpgSource.entity.MegaOgre;
import rpgSource.entity.Ogre;
import rpgSource.entity.Player;

public class BattleSim {
	public static Player player1 = new Player(45, 15, 10, 12, 10);
	public static Enemy[] enemy1 = new Enemy[3];
	static double damage = 0;
	public static int turn = 1;
	static boolean playersTurn;
	public static int x;
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
		player1.createMoves();
		gui.appendToConsole("Start battle!\n");
		String winner = battle();
		gui.appendToConsole(winner + " wins.\n");
		JOptionPane.showMessageDialog(gui, winner + " wins.", "Winner", JOptionPane.INFORMATION_MESSAGE);
		gui.dispose();
	}
	
	public static String battle() {
		while(player1.getCurrentHealth() > 0 || enemy1[x].getCurrentHealth() > 0 ){
			if(playersTurn == true){
				gui.updatePlayerMp(numberPrinter.format(player1.getMp()));
				gui.appendToConsole("It's the players turn.\n");
				gui.appendToConsole("Choose an action:\n");
				gui.appendToConsole("moves(1), flee(2), use an item(3), or super attack(4).\n");
				int temp = (int) player1.selectAction(getSelector());
				if(temp == -1){
					return "Nobody";
				}
				playersTurn = false;
				RPGGUI.resetSelector();
			}
			player1.p.apply();
			if(enemy1[x].getCurrentHealth() <= 0){
				player1.setExperiencePoints(player1.getExperiencePoints() + 6);
				player1.increaseLevel();
				return "The player";
			}
			updateStats();
			gui.appendToConsole("\n");
			if(playersTurn == false) {
				if (x == 1) {
					((Dragon) enemy1[x]).land();
					gui.appendToConsole("It's the " + enemy1[x].getName() + "'s turn.\n");
					playersTurn = true;
					((Dragon) enemy1[x]).selectAction((int) Math.random() * 100);
				} else {
					gui.appendToConsole("It's the " + enemy1[x].getName() + "'s turn.\n");
					enemy1[x].useAMove((int) (Math.random() * 100));
					playersTurn = true;
				}
				enemy1[x].p.apply();
			}
			if (player1.getCurrentHealth() <= 0){
				return "The enemy";
			}
			updateStats();
			turn++;
		}
		return "nobody";
	}
	
	static void updateStats() {
		gui.updatePlayerHealth(numberPrinter.format(player1.getCurrentHealth()));
		gui.updatePlayerMp(numberPrinter.format(player1.getMp()));
		gui.updateEnemyHealth(numberPrinter.format(enemy1[x].getCurrentHealth()));
	}
	
	public static int getSelector(){
		int temp = -1;
		while(temp == -1){
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
		enemy1[0] = new Ogre("ogre", 2, 30, 8, 8, 10);
		enemy1[1] = new Dragon("dragon", 2, 30, 7, 9, 10);
		enemy1[2] = new MegaOgre("mega ogre", 2, 40, 10, 9, 8);
		Object[] options = {enemy1[0].getName(), enemy1[1].getName(), enemy1[2].getName()};
		x = JOptionPane.showOptionDialog(null, "Select your enemy.", "Enemy Selector", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
		if(x == -1){
			System.exit(0);
		}
		gui = RPGGUI.getInstance();
		gui.startGUI();
		setWhoGoesFirst();
	}

	public static String getMaxPlayerMP() {
		return numberPrinter.format(player1.getMp());
	}
}

package rpgSource;

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
	static RPGGUI gui;
	
	public static void main(String[] args) {
		init();
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		player1.createMoves();
		gui.setActionMenu(player1);
		gui.appendToConsole("Start battle!\n\n");
		String winner = battle();
		gui.appendToConsole(winner + " wins.\n");
		JOptionPane.showMessageDialog(gui, winner + " wins.", "Winner", JOptionPane.INFORMATION_MESSAGE);
		gui.dispose();
	}
	
	public static String battle() {
		while(player1.getCurrentHealth() > 0 || enemy1[x].getCurrentHealth() > 0 ){
			if(playersTurn == true){
				gui.updatePlayerMp(player1.getMp());
				gui.appendToConsole("It's the players turn.\n");
				RPGGUI.waitForProceed();
				int temp = (int) player1.selectAction(RPGGUI.getSel(), RPGGUI.getSel2());
				if(temp == -1){
					return "Nobody";
				}
				playersTurn = false;
			}
			player1.p.apply();
			updateStats();
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
					((Dragon) enemy1[x]).selectAction((int) (Math.random() * 100), ((int) Math.random() * 100));
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
		gui.updatePlayerHealth(player1.getCurrentHealth());
		gui.updatePlayerMp(player1.getMp());
		gui.updateEnemyHealth(enemy1[x].getCurrentHealth());
	}
	
	public static int getSelector(){
		int temp = -1;
		while(temp == -1){
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			temp = RPGGUI.sel;
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
	
	static double getMaxPlayerHealth(){
		return player1.getMaxHealth();
	}
	
	static double getMaxEnemyHealth(){
		return enemy1[x].getMaxHealth();
	}
	
	static String getEnemyName(){
		return enemy1[x].getName();
	}

	static void init(){
		enemy1[0] = new Ogre("ogre", 2, 40, 8, 8, 10);
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

	public static int getMaxPlayerMP() {
		return player1.getMp();
	}
}

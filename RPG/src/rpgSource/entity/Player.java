package rpgSource.entity;

import rpgSource.BattleSim;
import rpgSource.ItemPacket;
import rpgSource.Items;
import rpgSource.MovePacket;
import rpgSource.RPGGUI;
import rpgSource.moves.MagicMove;
import rpgSource.moves.Move;
import rpgSource.moves.PreConNumMove;
import rpgSource.moves.PlayerNormAtk;

/**
 * This class controls everything that the user wants to do.
 * 
 * @author FlightGuy
 *
 */

public class Player extends Entities implements PlayerActions{

	public Player(int health, int attack, int defense, int speed, int mp) {
		super("player", health, attack, defense, speed, mp);
		items[0] = new Items(2, 15, "Health Potion", "The player drinks a health potion");
		items[1] = new Items(2, 15, "Damage Potion", "The player throws a damage potion at the enemy.");
	}
	
	public void createMoves() {
		atk = new PlayerNormAtk(10, "regular attack", "The player attacks the enemy.", this);
		swd = new PlayerNormAtk(14, "sword", "The player slashes at the enemy with a sword.", this);
		beam = new MagicMove(18, "magic beam", "The player emits a beam of concentrated magic at the enemy.", this, 5);
		spAtk = new PreConNumMove(30, "super move", "The player uses the special move.", 
				"The player is not completely charged up yet.\n", this);
	}
	
	Items[] items = new Items[2];
	int potions = 2;
	int damagePotions = 1;
	int level = 1;
	private int experiencePoints = 0;
	public int charge = 0;
	
	Move atk;
	Move swd;
	MagicMove beam;
	Move spAtk;
	
	/**
	 * This method uses the user's input to determine which move to use and calculate damage that the user's attack would do.
	 * @param moveSelector The input selected by the user used to chose a specific move.
	 * @return The damage the user would deal if the attack's receiver didn't have defense.
	 */
	public void useAMove(int moveSelector){
		switch (moveSelector) {
		case 1:
			message(atk.getDes());
			p = new MovePacket(this, getTarget(), atk);
			return;
		case 2:
			message(swd.getDes());
			p = new MovePacket(this, getTarget(), swd);
			return;
		case 3:
			if(beam.precondition(getMp())) {
				p = new MovePacket(this, getTarget(), beam);
			}
			message(beam.getDes());
			Entities.ui.updatePlayerMp(Entities.numberPrinter.format((double) getMp()));
			return;
		default:
			message("Invalid input.\n");
			message("Choose an action:");
			message("moves(1), flee(2), use an item(3), or super attack(4).");
			selectAction(BattleSim.getSelector());
			return;
		}
	}
	
	/**
	 * This method activates a special move that takes time some time before it can be used.
	 * @return The damage the user would deal if the attack's receiver didn't have defense.
	 */
	public void useSpecialAttack() {
		if (((PreConNumMove) spAtk).precondition(charge)) {
			message(spAtk.getDes());
			p = new MovePacket(this, getTarget(), spAtk);
		} else if(charge < 100) {
			message(spAtk.getDes());
			BattleSim.battle();
		}
	}
	
	/**
	 * Method is used when the user wants to leave the battle before defeating the enemy.
	 * @return Returns whether or not fleeing was successful.
	 */
	public boolean flee() {
		double temp = Math.random();
		if(temp >= 0.0 && temp < 0.7){
			//System.out.println(temp);
			return false;
		}else{
			//System.out.println(temp);
			return true;
		}
	}
	
	/**
	 * This method is used when the user wants to use an item.
	 * @param inputSelector The item the user wants to use.
	 */
	public void useAnItem(int inputSelector){
		switch(inputSelector) {
		case 1://Player uses one health potion.
			p = new ItemPacket(this, getTarget(), items[0]);
			return;
		case 2:
			p = new ItemPacket(this, getTarget(), items[1]);
			return;//Player uses damage potion.
		default:
			message("Invalid input.\n");
			message("Choose an action:");
			message("moves(1), flee(2), use an item(3), or super attack(4).");
			selectAction(BattleSim.getSelector());
		}
		return;
	}
	
	/**
	 * This method is used to increase the player's level and stats when the player gets enough experiencePoints 
	 */
	public void increaseLevel() {
		int levelUpExp = (10 * level)/2;
		if(getExperiencePoints() >= levelUpExp){
			setExperiencePoints(0);
			level++;
			setMaxHealth(getMaxHealth() + 5);
			currentHealth = getMaxHealth();
			attack += 4;
			defense += 2;
			setSpeed(getSpeed() + 2);
			message("");
			//message("The player has " + experiencePoints + " exp.");
			message("The player is now level " + level + ".");
		}
	}

	public int getExperiencePoints() {
		return experiencePoints;
	}

	public void setExperiencePoints(int experiencePoints) {
		this.experiencePoints = experiencePoints;
	}

	@Override
	public int selectAction(int selector) {
		RPGGUI.resetSelector();
		switch (selector) {
		case 1:
			ui.appendToConsole("Chose a move.\n");
			ui.appendToConsole("Attack(1), sword slash(2), or magic beam(3).\n");
			useAMove(BattleSim.getSelector());
			return 0;
		case 2:
			ui.appendToConsole("The player is trying to flee.\n");
			if (flee() == true) {
				ui.appendToConsole("The player successfully flees the battle.\n");
				return -1;
			} else {
				ui.appendToConsole("The player failed to flee.\n");
				return 0;
			}
		case 3:
			ui.appendToConsole("Choose an item:\n");
			ui.appendToConsole("Health potion(1) or damage potion(2).\n");
			RPGGUI.resetSelector();
			useAnItem(BattleSim.getSelector());
			ui.updatePlayerHealth(numberPrinter.format(getCurrentHealth()));
			return 0;
		case 4:
			useSpecialAttack();
			return 0;
		default:
			ui.appendToConsole("Invalid input.\n");
			ui.appendToConsole("You can only type in:\n");
			ui.appendToConsole("moves(1), flee(2), use an item(3), or super attack(4).\n");
			return selectAction(BattleSim.getSelector());
		}
	}

	@Override
	public Entities getTarget() {
		return BattleSim.enemy1[BattleSim.x];
	}
}

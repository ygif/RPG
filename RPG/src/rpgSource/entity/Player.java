package rpgSource.entity;

import rpgSource.BattleSim;
import rpgSource.ConditionalMovePacket;
import rpgSource.ItemPacket;
import rpgSource.Items;
import rpgSource.MovePacket;
import rpgSource.Packet;
import rpgSource.RPGGUI;
import rpgSource.moves.MagicMove;
import rpgSource.moves.Move;
import rpgSource.moves.SpecialAttack;
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
		spAtk = new SpecialAttack(30, "super move", "The player uses the special move.", 
				"The player is not completely charged up yet.", this);
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
	SpecialAttack spAtk;
	
	/**
	 * This method uses the user's input to determine which move to use and calculate damage that the user's attack would do.
	 * @param moveSelector The input selected by the user used to chose a specific move.
	 * @return The damage the user would deal if the attack's receiver didn't have defense.
	 */
	public void useAMove(int moveSelector){
		switch (moveSelector) {
		case 1:
			p = new MovePacket(this, getTarget(), atk);
			return;
		case 2:
			p = new MovePacket(this, getTarget(), swd);
			return;
		case 3:
			p = new ConditionalMovePacket(this, getTarget(), beam);
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
			message("\nChose a move.");
			message("Attack(1), sword slash(2), or magic beam(3).");
			useAMove(BattleSim.getSelector());
			return 0;
		case 2:
			message("The player is trying to flee.");
			if (flee() == true) {
				message("The player successfully flees the battle.");
				return -1;
			} else {
				
				p = new Packet(this) {
					@Override
					public void apply() {
						message("The player failed to flee.");
					}
				};
				return 0;
			}
		case 3:
			message("Choose an item:");
			message("Health potion(1) or damage potion(2).");
			RPGGUI.resetSelector();
			useAnItem(BattleSim.getSelector());
			ui.updatePlayerHealth(numberPrinter.format(getCurrentHealth()));
			return 0;
		case 4:
			p = new ConditionalMovePacket(this, getTarget(), spAtk);
			return 0;
		default:
			message("Invalid input.");
			message("You can only type in:");
			message("moves(1), flee(2), use an item(3), or super attack(4).");
			return selectAction(BattleSim.getSelector());
		}
	}

	@Override
	public Entities getTarget() {
		return BattleSim.enemy1[BattleSim.x];
	}
}

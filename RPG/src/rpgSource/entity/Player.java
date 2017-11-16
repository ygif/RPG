package rpgSource.entity;

import java.util.ArrayList;

import rpgSource.BattleSim;
import rpgSource.RPGGUI;
import rpgSource.battleSystem.ItemPacket;
import rpgSource.battleSystem.Items;
import rpgSource.battleSystem.MovePacket;
import rpgSource.battleSystem.Packet;
import rpgSource.moves.MagicMove;
import rpgSource.moves.Move;
import rpgSource.moves.SpecialAttack;
import rpgSource.util.DescribableList;
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
		items[0] = new Items(2, 15, "Health Potion", "The player drinks a health potion", "Heals a target by 15 health");
		items[1] = new Items(2, 15, "Damage Potion", "The player throws a damage potion at the enemy.", "Damages a target by 15 health");
	}
	
	public void createMoves() {
		m[0] = new PlayerNormAtk(10, "regular attack", "The player attacks the enemy.", this);
		m[1] = new PlayerNormAtk(14, "sword","Use a sword to damage a target" , "The player slashes at the enemy with a sword.", this);
		m[2] = new MagicMove(18, "magic beam", "A beam concentrated magic. Uses MP","The player emits a beam of concentrated magic at the enemy." ,this, 5);
		spAtk = new SpecialAttack(30, "super move", "The player uses the special move.", 
				"The player is not completely charged up yet.", "A powerful attack that needs time charge.", this);
		al = new ArrayList<DescribableList>();
		DescribableList ul1 = new DescribableList("Moves", m);
		DescribableList ul2 = new DescribableList("Flee", new PlayerNormAtk(0, "Flee", "The player tries to flee the battle"));
		DescribableList ul3 = new DescribableList("Items", items);
		DescribableList ul4 = new DescribableList("Special Attack", spAtk);
		
		al.add(ul1);
		al.add(ul2);
		al.add(ul3);
		al.add(ul4);
	}
	
	Items[] items = new Items[2];
	int potions = 2;
	int damagePotions = 1;
	int level = 1;
	private int experiencePoints = 0;
	public int charge = 0;
	public ArrayList<DescribableList> al;
	
	Move[] m = new Move[3];
	SpecialAttack spAtk;
	
	/**
	 * This method uses the user's input to determine which move to use and calculate damage that the user's attack would do.
	 * @param moveSelector The input selected by the user used to chose a specific move.
	 */
	public void useAMove(int moveSelector){
		if(moveSelector < m.length && !(m[moveSelector] instanceof MagicMove)) {
			p = new MovePacket(this, getTarget(), m[moveSelector]);
		} else if(m[moveSelector] instanceof MagicMove) {
			p = new MovePacket(this, getTarget(), m[moveSelector], getMp());
		} else {
			RPGGUI.waitForProceed();
			selectAction(RPGGUI.getSel(), RPGGUI.getSel2());
		}
	}
	
	/**
	 * Method is used when the user wants to leave the battle before defeating the enemy.
	 * @return Returns whether or not fleeing was successful.
	 */
	public boolean flee() {
		double temp = Math.random();
		return (temp >= 0.7 && temp < 1.0) ? true : false;
	}
	
	/**
	 * This method is used when the user wants to use an item.
	 * @param inputSelector The item the user wants to use.
	 */
	public void useAnItem(int inputSelector){
		switch(inputSelector) {
		case 0://Player uses one health potion.
			p = new ItemPacket(this, getTarget(), items[0]);
			return;
		case 1:
			p = new ItemPacket(this, getTarget(), items[1]);
			return;//Player uses damage potion.
		default:
			selectAction(0, 1);
		}
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
	public int selectAction(int sel, int sel2) {
		switch (sel) {
		case 0:
			useAMove(sel2);
			return 0;
		case 1:
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
		case 2:
			useAnItem(sel2);
			ui.updatePlayerHealth(getCurrentHealth());
			return 0;
		case 3:
			p = new MovePacket(this, getTarget(), spAtk, charge);
			return 0;
		default:
			return selectAction(0, 1);
		}
	}

	@Override
	public Entities getTarget() {
		return BattleSim.enemy1[BattleSim.x];
	}
}

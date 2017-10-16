package rpgSource.entity;

import rpgSource.BattleSim;
import rpgSource.Items;
import rpgSource.moves.Move;
import rpgSource.moves.PreConNumMove;
import rpgSource.moves.NormAtk;

/**
 * This class controls everything that the user wants to do.
 * 
 * @author FlightGuy
 *
 */

public class Player extends Entities implements PlayerActions{

	public Player(int health, int attack, int defense, int speed) {
		super(health, attack, defense, speed);
		items[0] = new Items(2, 15, "Health Potion", "The player drinks a health potion");
		items[1] = new Items(2, 15, "Damage Potion", "The player throws a damage potion at the enemy.");
		atk = new NormAtk(10, "regular attack", "The player attacks the enemy.\n", this);
		swd = new NormAtk(14, "sword", "The player slashes at the enemy with a sword.\n", this);
		beam = new NormAtk(18, "magic beam", "The player emits a beam of concentrated magic at the enemy.\n", this);
		spAtk = new PreConNumMove(30, "super move", "The player uses the special move.\n", 
				"The player is not completely charged up yet.\n", this, (a) -> a.doubleValue() < charge);
	}
	
	Items[] items = new Items[2];
	int potions = 2;
	int damagePotions = 1;
	int level = 1;
	private int experiencePoints = 0;
	public int charge = 0;
	
	Move atk;
	Move swd;
	Move beam;
	Move spAtk;
	
	/**
	 * This method uses the user's input to determine which move to use and calculate damage that the user's attack would do.
	 * @param moveSelector The input selected by the user used to chose a specific move.
	 * @return The damage the user would deal if the attack's receiver didn't have defense.
	 */
	public double useAMove(int moveSelector){
		totalDamage = 0;
		switch (moveSelector) {
		case 1:
			Entities.ui.appendToConsole(atk.getDes());
			totalDamage = atk.baseDamage * ((double) attack/10);
			atk.doSomething();
			return totalDamage;
		case 2:
			Entities.ui.appendToConsole(swd.getDes());
			totalDamage = swd.baseDamage * ((double) attack/10);
			swd.doSomething();
			return totalDamage;
		case 3:
			Entities.ui.appendToConsole("");
			totalDamage =  beam.baseDamage * ((double) attack/10);	
			beam.doSomething();
			return totalDamage;
		default:
			Entities.ui.appendToConsole("Invalid input.\n\n");
			Entities.ui.appendToConsole("Choose an action:\n");
			Entities.ui.appendToConsole("moves(1), flee(2), use an item(3), or super attack(4).\n");
			BattleSim.selectAction();
			return 0;
		}
	}
	
	/**
	 * This method activates a special move that takes time some time before it can be used.
	 * @return The damage the user would deal if the attack's receiver didn't have defense.
	 */
	public double useSpecialAttack() {
		totalDamage = 0;
		if (charge >= 100) {
			Entities.ui.appendToConsole(spAtk.getDes());
			totalDamage = spAtk.baseDamage * (attack/10);
			spAtk.doSomething();
			return totalDamage;
		} else if(charge < 100) {
			Entities.ui.appendToConsole(spAtk.getDes());
			BattleSim.battle();
		}
		return totalDamage;
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
	public double useAnItem(int inputSelector){
		switch(inputSelector) {
		case 1:
			double temp = items[0].useItem(); //Player uses one health potion.
			currentHealth += temp;
			if (currentHealth > getMaxHealth()) {
				currentHealth = getMaxHealth();	
				Entities.ui.appendToConsole("The player is at full health.\n");
			}
			Entities.ui.appendToConsole("The player has " + numberPrinter.format(currentHealth) + " health left.\n");
			return 0;
		case 2:
			return items[1].useItem();//Player uses damage potion.
		default:
			Entities.ui.appendToConsole("Invalid input.\n\n");
			Entities.ui.appendToConsole("Choose an action:\n");
			Entities.ui.appendToConsole("moves(1), flee(2), use an item(3), or super attack(4).\n");
			BattleSim.selectAction();
		}
		return 0;
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
			Entities.ui.appendToConsole("\n");
			//Entities.ui.appendToConsole("The player has " + experiencePoints + " exp.\n");
			Entities.ui.appendToConsole("The player is now level " + level + ".\n");
		}
	}

	public int getExperiencePoints() {
		return experiencePoints;
	}

	public void setExperiencePoints(int experiencePoints) {
		this.experiencePoints = experiencePoints;
	}
}

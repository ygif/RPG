package rpgSource.entity;

import rpgSource.BattleSim;
import rpgSource.Items;
import rpgSource.Move;
import rpgSource.normAtk;

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
		atk = new normAtk(10, "regular attack", "The player attacks the enemy.\n", this);
		//
	}
	
	Items[] items = new Items[2];
	int potions = 2;
	int damagePotions = 1;
	int level = 1;
	private int experiencePoints = 0;
	public int charge = 0;
	
	Move atk;
	
	/**
	 * This method uses the user's input to determine which move to use and calculate damage that the user's attack would do.
	 * @param moveSelector The input selected by the user used to chose a specific move.
	 * @return The damage the user would deal if the attack's receiver didn't have defense.
	 */
	public double useAMove(int moveSelector){
		totalDamage = 0;
		switch (moveSelector) {
		case 1:
			message(atk.des);
			totalDamage = atk.baseDamage * ((double) attack/10);
			atk.doSomething();
			/*if (charge < 100) {
				charge += 20;
			}*/
			return totalDamage;
		case 2:
			message("The player slashes at the enemy with a sword.\n");
			totalDamage = 14 * ((double) attack/10);
			if (charge < 100) {
				charge += 20;
			}
			return totalDamage;
		case 3:
			message("The player emits a beam of concentrated magic at the enemy.\n");
			totalDamage =  18 * ((double) attack/10);	
			if (charge < 100) {
				charge += 20;
			}
			return totalDamage;
		default:
			message("Invalid input.\n\n");
			message("Choose an action:\n");
			message("moves(1), flee(2), use an item(3), or super attack(4).\n");
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
			message("The player uses the special move.\n");
			totalDamage = 30 * (attack/10);
			charge = 0;
			return totalDamage;
		} else if(charge < 100) {
			message("The player is not completely charged up yet.\n");
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
				message("The player is at full health.\n");
			}
			message("The player has " + numberPrinter.format(currentHealth) + " health left.\n");
			return 0;
		case 2:
			return items[1].useItem();//Player uses damage potion.
		default:
			message("Invalid input.\n\n");
			message("Choose an action:\n");
			message("moves(1), flee(2), use an item(3), or super attack(4).\n");
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
			message("\n");
			//message("The player has " + experiencePoints + " exp.\n");
			message("The player is now level " + level + ".\n");
		}
	}

	public int getExperiencePoints() {
		return experiencePoints;
	}

	public void setExperiencePoints(int experiencePoints) {
		this.experiencePoints = experiencePoints;
	}
}

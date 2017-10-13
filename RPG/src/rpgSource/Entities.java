package rpgSource;

import java.text.DecimalFormat;

/**
 * This class contains everything an entity in a battle should have.
 * 
 * @author FlightGuy
 *
 */

class LivingThings {
	String name;
	double maxHealth;
	double currentHealth;
	int attack;
	int defense;
	double totalDamage;
	double level;
	int speed;
	static DecimalFormat numberPrinter = new DecimalFormat("###");
	static RPGGUI ui = RPGGUI.getInstance();
	
	LivingThings(int health, int attack, int defense, int speed) {
		maxHealth = health;
		currentHealth = health;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
	}
	
	LivingThings(){
		
	}
	
	/**
	 * This method reduces the health of the entity its ran on based damage and its defense.
	 * @param damage The damage the attack would do if defense is ignored.
	 * @return The entity's new current health.
	 */
	public double reduceHealth(double damage) {
		double tempDamage = damage/*dodgeAttack(damage)*/;
		currentHealth -= Math.floor(((Math.log(defense) * tempDamage) / 4));
		ui.appendToConsole(numberPrinter.format(Math.floor(((Math.log(defense) * tempDamage) / 4))) + " Damage\n");
		currentHealth = Math.floor(currentHealth);
		return currentHealth;
	}
	
	/**
	 * This method reduces health on an entity that uses a move that inflicts recoil damage.
	 * @param inputRecoil The damage the recoil will do to the entity. Defense is ignored.
	 * @return The entity's new current health.
	 */
	public double reduceHealthRecoil(double inputRecoil){
		currentHealth -= inputRecoil;
		return Math.floor(currentHealth);
	}
	
	/**
	 * This method determines if attack to an entity hits it or misses it.
	 * @param damage The damaage an incoming attack would deal if the attack hits and defense is ignored.
	 * @return If the attack misses 0. If the attack hits, the damage the attack would deal if defense is ignored.
	 */
	double dodgeAttack(double damage) {
		double temp = Math.random();
		if (temp >= 0.95 && temp < 1) {
			damage = 0;
			System.out.println("Miss!");
			return damage;
		} else {
			return damage;
		}
	}
	
	/**
	 * This method returns the entity's name.
	 * @return The entity's name.
	 */
	String getName(){
		return name;
	}
	
	public int getDefense(){
		return defense;
	}
	
	public double getCurrentHealth(){
		return currentHealth;
	}
	
	public int getAttack(){
		return attack;
	}
}


package rpgSource.entity;

import java.text.DecimalFormat;
import rpgSource.Packet;
import rpgSource.RPGGUI;

/**
 * This class contains everything an entity in a battle should have.
 * 
 * @author FlightGuy
 *
 */

public class Entities {
	String name;
	private double maxHealth;
	double currentHealth;
	int attack;
	int defense;
	double totalDamage;
	double level;
	private int speed;
	private int maxMp;
	private int mp;
	public Packet p;
	public static DecimalFormat numberPrinter = new DecimalFormat("###");
	public static RPGGUI ui = RPGGUI.getInstance();
	
	Entities(String name, int health, int attack, int defense, int speed) {
		this.name = name;
		setMaxHealth(health);
		currentHealth = health;
		this.attack = attack;
		this.defense = defense;
		this.setSpeed(speed);
		maxMp = setMp(0);
	}
	
	Entities(String name, int health, int attack, int defense, int speed, int mp) {
		this.name = name;
		setMaxHealth(health);
		currentHealth = health;
		this.attack = attack;
		this.defense = defense;
		this.setSpeed(speed);
		maxMp = this.setMp(mp);
	}
	
	Entities(){
		
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
	
	public int reduceMP(int mpUsed) {
		setMp(getMp() - mpUsed);
		if(getMp() < 0) {
			setMp(0);
		}
		return getMp();
	}
	
	public int increaseMP(int incMp) {
		if(maxMp - getMp() < incMp) {
			setMp(maxMp);
			return getMp();
		} else {
			return reduceMP(-incMp);
		}
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
	
	public void restoreHeatlth(int h) {
		currentHealth += h;
		if (currentHealth > getMaxHealth()) {
			currentHealth = getMaxHealth();	
			message("The " + getName() + " is at full health.");
		}
	}
	
	public void message(String s) {
		ui.appendToConsole(s + "\n");
	}
	
	/**
	 * This method returns the entity's name.
	 * @return The entity's name.
	 */
	public String getName(){
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

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public double getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(double maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	public DecimalFormat getNumFormatter() {
		return numberPrinter;
	}

	public int getMp() {
		return mp;
	}

	public int setMp(int mp) {
		this.mp = mp;
		return mp;
	}
	
	public int getMaxMp() {
		return maxMp;
	}
}


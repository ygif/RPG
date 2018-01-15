package rpgSource.entity;

import java.util.ArrayList;

import rpgSource.RPGGUI;
import rpgSource.battleSystem.Packet;
import rpgSource.battleSystem.StatusEffect;
import rpgSource.battleSystem.StatusEffect.StatusEffectType;
import rpgSource.util.Updateable;

/**
 * This class contains everything an entity in a battle should have.
 * 
 * @author FlightGuy
 *
 */

public class Entities implements Updateable{
	String name;
	private int maxHealth;
	int currentHealth;
	int attack;
	int defense;
	int level;
	private int speed;
	private int maxMp;
	private int mp;
	public Packet p;
	public static RPGGUI ui = RPGGUI.getInstance();
	public ArrayList<StatusEffect> se;
	
	Entities(String name, int health, int attack, int defense, int speed) {
		this(name, health, attack, defense, speed, 0);
	}
	
	Entities(String name, int health, int attack, int defense, int speed, int mp) {
		this.name = name;
		setMaxHealth(health);
		currentHealth = health;
		this.attack = attack;
		this.defense = defense;
		this.setSpeed(speed);
		maxMp = this.setMp(mp);
		se = new ArrayList<StatusEffect>();
	}
	
	Entities(){
		
	}
	
	/**
	 * This method reduces the health of the entity its ran on based damage and its defense.
	 * @param damage The damage the attack would do if defense is ignored.
	 * @return The entity's new current health.
	 */
	//TODO: modify how defense works
	public int reduceHealth(int damage) {
		if(!dodgeAttack()) {
			currentHealth -= (int) Math.floor(((Math.log(getDefense()) * damage) / 4));
			ui.appendToConsole((int) Math.floor(((Math.log(getDefense()) * damage) / 4)) + " Damage\n");
		}
		return currentHealth;
	}
	
	/**
	 * This method reduces health on an entity that uses a move that inflicts recoil damage.
	 * @param inputRecoil The damage the recoil will do to the entity. Defense is ignored.
	 * @return The entity's new current health.
	 */
	public int reduceHealthRecoil(int inputRecoil){
		currentHealth -= inputRecoil;
		return currentHealth;
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
	 * @return If the attack misses or hits
	 */
	boolean dodgeAttack() {
		double temp = Math.random();
		if (temp >= 0.0 && temp < 0.95) { return false; }
		message("Miss!");
		return true;
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
	
	@Override
	public void update() {
		for(int i = 0; i < se.size(); i++) {
			se.get(i).update();
			if(se.get(i).getLength() <= 0) {
				se.remove(i);
				i--;
			}
		}
	}
	
	/**
	 * This method returns the entity's name.
	 * @return The entity's name.
	 */
	public String getName(){
		return name;
	}
	
	public int getActualDefense(){
		return defense;
	}
	
	public int getActualAttack(){
		return attack;
	}

	public int getActualSpeed() {
		return speed;
	}
	
	public int getDefense(){
		int temp = defense;
		for (int i = 0; i < se.size(); i++) {
			if(se.get(i).setype == StatusEffectType.ON_STAT && se.get(i).getName().equals("defense")) {
				temp += (int) (se.get(i).value * getActualDefense()) - getActualDefense();
			}
		}
		return temp;
	}
	
	public int getAttack(){
		int temp = attack;
		for (int i = 0; i < se.size(); i++) {
			if(se.get(i).setype == StatusEffectType.ON_STAT && se.get(i).getName().equals("attack")) {
				int dd =(int) ((se.get(i).value * getActualAttack()) - getActualAttack());
				temp += dd;
			}
		}
		return temp;
	}

	public int getSpeed() {
		int temp = speed;
		for (int i = 0; i < se.size(); i++) {
			if(se.get(i).setype == StatusEffectType.ON_STAT && se.get(i).getName().equals("speed")) {
				
				temp += (int) ((se.get(i).value * getActualSpeed()) - getActualSpeed());
			}
		}
		return temp;
	}
	
	public int getCurrentHealth(){
		return currentHealth;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
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


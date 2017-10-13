package rpgSource.entity;

/**
 * This class represents what everything that is controlled by the computer should have.
 * 
 * @author FlightGuy
 *
 */

public abstract class Enemy extends Entities{

	Enemy(int level, int health, int attack, int defense, int speed) {
		super(health, attack, defense, speed);
		this.level = level;
		setMaxHealth(getMaxHealth() + (this.level - 1) * 5);
		currentHealth = getMaxHealth();
		attack += ((this.level - 1) * 2);
		defense += (this.level - 1);
		speed += (this.level - 1) * 2;
	}
	
	boolean turn = false;
	
	public abstract double useAMoveRandom(int s);	
}

package rpgSource;

/**
 * This class represents what everything that is controlled by the computer should have.
 * 
 * @author FlightGuy
 *
 */

public abstract class Enemy extends LivingThings{

	Enemy(int level, int health, int attack, int defense, int speed) {
		super(health, attack, defense, speed);
		this.level = level;
		maxHealth += (this.level - 1) * 5;
		currentHealth = maxHealth;
		attack += ((this.level - 1) * 2);
		defense += (this.level - 1);
		speed += (this.level - 1) * 2;
	}
	
	boolean turn = false;
	
	public abstract double useAMoveRandom(int s);	
}

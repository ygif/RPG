package rpgSource.entity;

import rpgSource.BattleSim;
import rpgSource.moves.Move;
import rpgSource.moves.NormAtk;

public class Dragon extends Enemy{
	
	private Move atk;
	private Move fireball;
	private Move flamethrower;
	
	@Override
	public String toString() {
		return "Dragon";
	}
	
	public Dragon(int level, int health, int attack, int defense, int speed, String name) {
		super(level, health, attack, defense, speed);
		this.name = name;
		atk = new NormAtk(14, "normal attack", "The " + name + " attacks the player.\n", this);
		fireball = new NormAtk(19, "fireball", "The " + name + " spits out a ball of fire at the player.\n", this);
		flamethrower = new NormAtk(25, "flamethrower", "The " + name + " breathes out an enormous amount of flames at the player\n", this);
	}
	
	public boolean flying = false;
	int endFlight;
	
	@Override
	public double useAMoveRandom(int s) {
		if(s >= 0 && s < 45){
			//Attack 
			message(atk.getDes());
			totalDamage = atk.baseDamage * ((double) attack/10);
			return totalDamage;
		}else if(s >= 45 && s < 90){
			//fireball
			message(fireball.getDes());
			totalDamage = fireball.baseDamage * ((double) attack/10);
		}else if (s >= 90 && s < 100) {
			//flamethrower
			message(flamethrower.getDes());
			totalDamage = flamethrower.baseDamage * ((double) attack/10);
		}
		return totalDamage;
	}
	public void fly() {
		flying = true;
		endFlight = BattleSim.turn + 2;
		message("The dragon flies up into the sky.\n");
	}
	public void land(){
		if(flying == true && BattleSim.turn == endFlight){
			flying = false;
			message("The dragon lands on the ground.\n\n");
		}
	}
	@Override
	public double reduceHealth(double damage) {
		if (flying == true) {
			damage = 0;
			message("The player can't damage a dragon while its in flight.\n");
			return currentHealth;
		} else {
			double tempDamage = damage/*dodgeAttack(damage)*/;
			currentHealth -= Math.floor(((Math.log(defense) * tempDamage) / 4));
			message(numberPrinter.format(Math.floor(((Math.log(defense) * tempDamage) / 4))) + " Damage\n");
			currentHealth = Math.floor(currentHealth);
			return currentHealth;
		}
	}
	public double randomActionSelctor() {
		double temp = Math.random();
		if (temp >= 0 && temp < 0.95) {
			double damage = useAMoveRandom((int) (Math.random() * 100));
			return damage;
		} else {
			if(flying == false){
				fly();
				return 0;
			}else{
				message("The dragon doesn't do anything.\n");
				return 0;
			}
		}
	}
}

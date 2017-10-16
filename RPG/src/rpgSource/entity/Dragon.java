package rpgSource.entity;

import rpgSource.BattleSim;

public class Dragon extends Enemy{
	
	@Override
	public String toString() {
		return "Dragon";
	}
	public Dragon(int level, int health, int attack, int defense, int speed, String name) {
		super(level, health, attack, defense, speed);
		this.name = name;
	}
	
	public boolean flying = false;
	int endFlight;
	
	@Override
	public double useAMoveRandom(int s) {
		int temp = s;
		if(temp >= 0 && temp < 45){
			//Attack 
			Entities.ui.appendToConsole("The " + name + " attacks the player.\n");
			totalDamage = 14 * ((double) attack/10);
			return totalDamage;
		}else if(temp >= 45 && temp < 90){
			//fireball
			Entities.ui.appendToConsole("The " + name + " spits out a ball of fire at the player.\n");
			totalDamage = 19 * ((double) attack/10);
		}else if (temp >= 90 && temp < 100) {
			//flamethrower
			Entities.ui.appendToConsole("The " + name + " breathes out an enormous amount of flames at the player\n");
			totalDamage = 25 * ((double) attack/10);
		}
		return totalDamage;
	}
	public void fly() {
		flying = true;
		endFlight = BattleSim.turn + 2;
		Entities.ui.appendToConsole("The dragon flies up into the sky.\n");
	}
	public void land(){
		if(flying == true && BattleSim.turn == endFlight){
			flying = false;
			Entities.ui.appendToConsole("The dragon lands on the ground.\n\n");
		}
	}
	@Override
	public double reduceHealth(double damage) {
		if (flying == true) {
			damage = 0;
			Entities.ui.appendToConsole("The player can't damage a dragon while its in flight.\n");
			return currentHealth;
		} else {
			double tempDamage = damage/*dodgeAttack(damage)*/;
			currentHealth -= Math.floor(((Math.log(defense) * tempDamage) / 4));
			Entities.ui.appendToConsole(numberPrinter.format(Math.floor(((Math.log(defense) * tempDamage) / 4))) + " Damage\n");
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
				Entities.ui.appendToConsole("The dragon doesn't do anything.\n");
				return 0;
			}
		}
	}
}

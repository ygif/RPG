package rpgSource.entity;

import rpgSource.BattleSim;
import rpgSource.MovePacket;
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
	
	public Dragon(String name, int level, int health, int attack, int defense, int speed) {
		super(name, level, health, attack, defense, speed);
		this.name = name;
		atk = new NormAtk(14, "normal attack", "The " + name + " attacks the player.", this);
		fireball = new NormAtk(19, "fireball", "The " + name + " spits out a ball of fire at the player.", "A fireball", this);
		flamethrower = new NormAtk(25, "flamethrower", "The " + name + " breathes out an enormous amount of flames at the player",
				"Release a continuous flame for a little bit",this);
	}
	
	public boolean flying = false;
	int endFlight;
	
	@Override
	public void useAMove(int moveSelector) {
		if(moveSelector >= 0 && moveSelector < 45){
			//Attack 
			p = new MovePacket(this, getTarget(), atk);
		}else if(moveSelector >= 45 && moveSelector < 90){
			//fireball
			p = new MovePacket(this, getTarget(), fireball);
		}else if (moveSelector >= 90 && moveSelector < 100) {
			//flamethrower
			p = new MovePacket(this, getTarget(), flamethrower);
		}
	}
	public void fly() {
		flying = true;
		endFlight = BattleSim.turn + 2;
		message("The dragon flies up into the sky.");
	}
	
	public void land(){
		if(flying == true && BattleSim.turn == endFlight){
			flying = false;
			message("The dragon lands on the ground.\n");
		}
	}
	
	@Override
	public int reduceHealth(int damage) {
		if (flying == true) {
			damage = 0;
			message("The player can't damage a dragon while its in flight.");
			return currentHealth;
		} else if(dodgeAttack()) {
			return currentHealth;
		} else {
			double tempDamage = damage;
			currentHealth -= (int) Math.floor(((Math.log(defense) * tempDamage) / 4));
			message((int) Math.floor(((Math.log(defense) * tempDamage) / 4)) + " Damage");
			return currentHealth;
		}
	}

	@Override
	public int selectAction(int sel, int sel2) {
		if (sel >= 0 && sel < 95) {
			useAMove(sel2);
		} else {
			if(flying == false){
				fly();
			}else{
				message("The dragon doesn't do anything.");
			}
		}
		return 0;
	}

	@Override
	public Entities getTarget() {
		return BattleSim.player1;
	}
}

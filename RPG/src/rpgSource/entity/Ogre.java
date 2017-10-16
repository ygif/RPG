package rpgSource.entity;

import rpgSource.moves.Move;
import rpgSource.moves.NormAtk;
import rpgSource.moves.RCAttack;

public class Ogre extends Enemy{

	Move atk;
	Move club;
	Move ram;
	
	public Ogre(int level, int health, int attack, int defense, int speed, String name) {
		super(level, health, attack, defense, speed);
		this.name = name;
		atk = new NormAtk(12, "normal attack", "The " + name + " attacks the player.\n", this);
		club = new NormAtk(16, "club hit", "The " + name + " hits the player with a club.\n", this);
		ram = new RCAttack(22, "ram", "The " + name + " rams itself into the player\n", this);
	}	
	@Override
	public double useAMoveRandom(int s){
		if(s >= 0 && s < 45){
			//Attack move
			message(atk.getDes());
			totalDamage = atk.baseDamage * ((double) attack/10);
			return totalDamage;
		}else if(s >= 45 && s < 90){
			//Club hit
			message(club.getDes());
			totalDamage = club.baseDamage * ((double) attack/10);
		}else if (s >= 90 && s < 100) {
			//charge attack
			message(ram.getDes());
			totalDamage = ram.baseDamage * ((double) attack/10);
			ram.doSomething();
		}
		return totalDamage;
	}
}

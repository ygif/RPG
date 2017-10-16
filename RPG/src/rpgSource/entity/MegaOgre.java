package rpgSource.entity;

import rpgSource.moves.Move;
import rpgSource.moves.NormAtk;
import rpgSource.moves.RCAttack;

public class MegaOgre extends Ogre {
	
	Move atk;
	Move club;
	Move smash;
	Move ram;

	public MegaOgre(int level, int health, int attack, int defense, int speed, String name) {
		super(level, health, attack, defense, speed, name);
		atk = new NormAtk(15, "normal attack", "The " + name + " attacks the player.\n", this);
		club = new NormAtk(18, "club hit", "The " + name + " hits the player with a club.\n", this);
		smash = new NormAtk(21, "ogre smash", "The " + name + " smashes the player.\n", this);
		ram = new RCAttack(25, "ram", "The " + name + " rams itself into the player\n", this);
	}
	
	@Override
	public double useAMoveRandom(int s){
		if(s >= 0 && s < 30){
			//Attack move
			message(atk.getDes());
			totalDamage = atk.baseDamage * ((double) attack/10);
			return totalDamage;
		}else if(s >= 30 && s < 60){
			//Club hit
			message(club.getDes());
			totalDamage = club.baseDamage * ((double) attack/10);
		}else if(s >= 60 && s < 90) {
			//arm hit
			message(smash.getDes());
			totalDamage = smash.baseDamage * ((double) attack/10);
		}else if (s >= 90 && s < 100) {
			//charge attack
			message(ram.getDes());
			totalDamage = ram.baseDamage * ((double) attack/10);
			ram.doSomething();
		}
		return totalDamage + 1.0;
	}
}

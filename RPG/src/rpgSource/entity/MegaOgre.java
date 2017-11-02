package rpgSource.entity;

import rpgSource.MovePacket;
import rpgSource.moves.Move;
import rpgSource.moves.NormAtk;
import rpgSource.moves.RCAttack;

public class MegaOgre extends Ogre {
	
	Move atk;
	Move club;
	Move smash;
	Move ram;

	public MegaOgre(String name, int level, int health, int attack, int defense, int speed) {
		super(name, level, health, attack, defense, speed);
		atk = new NormAtk(15, "normal attack", "The " + name + " attacks the player.", this);
		club = new NormAtk(18, "club hit", "The " + name + " hits the player with a club.", "Use a club to hit a target", this);
		smash = new NormAtk(21, "ogre smash", "The " + name + " smashes the player.", "A strong punch to the target", this);
		ram = new RCAttack(25, "ram", "The " + name + " rams itself into the player", "Run into the target as fast as possible. Damages the user.", this);
	}
	
	@Override
	public void useAMove(int moveSelector){
		if(moveSelector >= 0 && moveSelector < 30){
			//Attack move
			p = new MovePacket(this, getTarget(), atk);
		}else if(moveSelector >= 30 && moveSelector < 60){
			//Club hit
			p = new MovePacket(this, getTarget(), club);
		}else if(moveSelector >= 60 && moveSelector < 90) {
			//arm hit
			p = new MovePacket(this, getTarget(), smash);
		}else if (moveSelector >= 90 && moveSelector < 100) {
			//charge attack
			p = new MovePacket(this, getTarget(), ram);
		}
	}
}

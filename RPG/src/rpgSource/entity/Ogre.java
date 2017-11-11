package rpgSource.entity;

import rpgSource.BattleSim;
import rpgSource.MovePacket;
import rpgSource.moves.Move;
import rpgSource.moves.NormAtk;
import rpgSource.moves.RCAttack;

public class Ogre extends Enemy{

	Move atk;
	Move club;
	Move ram;
	
	public Ogre(String name, int level, int health, int attack, int defense, int speed) {
		super(name, level, health, attack, defense, speed);
		this.name = name;
		atk = new NormAtk(12, "normal attack", "The " + name + " attacks the player.", this);
		club = new NormAtk(16, "club hit", "The " + name + " hits the player with a club.", "Use a club to hit a target", this);
		ram = new RCAttack(22, "ram", "The " + name + " rams itself into the player", "Run into the target as fast as possible. Damages the user.", this);
	}	
	@Override
	public void useAMove(int moveSelector){
		if(moveSelector >= 0 && moveSelector < 45){
			//Attack move
			p = new MovePacket(this, getTarget(), atk);
		}else if(moveSelector >= 45 && moveSelector < 90){
			//Club hit
			p = new MovePacket(this, getTarget(), club);

		}else if (moveSelector >= 90 && moveSelector < 100) {
			//charge attack
			p = new MovePacket(this, getTarget(), ram);
		}
	}
	
	@Override
	public int selectAction(int sel, int sel2) {
		return 0;
	}
	
	@Override
	public Entities getTarget() {
		return BattleSim.player1;
	}
}

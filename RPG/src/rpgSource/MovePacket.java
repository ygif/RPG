package rpgSource;

import rpgSource.entity.Entities;
import rpgSource.moves.Move;

public class MovePacket extends Packet {

	Move move;

	public MovePacket(Entities u, Entities t, Move m) {
		super(u, t);
		move = m;
	}
	
	public MovePacket(Entities e,Move m) {
		super(e);
		move = m;
	}

	@Override
	public void apply() {
		move.precondition(user.getMp());
		user.message(move.getDes());
		double damage = move.getBaseDamage() * ((double) user.getAttack()/10);
		if(damage > 0) {
			target.reduceHealth(damage);
			user.message("The " + target.getName() + " has " + Entities.numberPrinter.format(target.getCurrentHealth()) + " health.");
			move.doSomething();
		}
	}
}

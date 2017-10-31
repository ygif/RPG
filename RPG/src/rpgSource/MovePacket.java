package rpgSource;

import rpgSource.entity.Entities;
import rpgSource.moves.PreConNumMove;

public class ConditionalMovePacket extends Packet {

	PreConNumMove move;

	public ConditionalMovePacket(Entities u, Entities t, PreConNumMove m) {
		super(u, t);
		move = m;
	}
	
	public ConditionalMovePacket(Entities e, PreConNumMove m) {
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

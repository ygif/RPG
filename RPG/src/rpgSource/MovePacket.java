package rpgSource;

import rpgSource.entity.Entities;
import rpgSource.moves.Move;

public class MovePacket extends Packet {
	
	Move move;

	public MovePacket(Entities u, Entities t, Move m) {
		super(u, t);
		move = m;
	}
	
	public MovePacket(Entities e, Move m) {
		super(e, e);
		move = m;
	}

	@Override
	public void apply() {
		user.message(move.getDes());
		double damage = move.baseDamage * ((double) user.getAttack()/10);
		target.reduceHealth(damage);
		Entities.ui.appendToConsole("The " + target.getName() + " has " + Entities.numberPrinter.format(target.getCurrentHealth()) + " health.\n");
		move.doSomething();
	}
}
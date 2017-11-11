package rpgSource;

import rpgSource.entity.Entities;
import rpgSource.moves.Move;

public class MovePacket extends Packet {

	Move move;
	int value;

	public MovePacket(Entities u, Entities t, Move m) {
		this(u, t, m, 0);
	}
	
	public MovePacket(Entities e, Move m) {
		this(e, e, m);
	}
	
	public MovePacket(Entities u , Entities t, Move m, int v) {
		super(u, t);
		move = m;
		value = v;
	}

	@Override
	public void apply() {
		boolean b = move.precondition(value);
		user.message(move.useMessage());
		double damage = move.getBaseDamage() * ((double) user.getAttack()/10);
		if(damage > 0 && b) {
			target.reduceHealth(damage);
			user.message("The " + target.getName() + " has " + Entities.numberPrinter.format(target.getCurrentHealth()) + " health.");
			move.doSomething();
		}
	}
}

package rpgSource.moves;

import rpgSource.entity.Entities;
import rpgSource.entity.Player;

public class SpecialAttack extends Move{

	public SpecialAttack(int bd, String n, String d, String f, Entities l) {
		super(bd, n, d, f, l, (a) -> ((Player) l).charge == 100);
	}
	
	@Override
	public void doSomething() {
		((Player) user).charge = 0;
	}
}

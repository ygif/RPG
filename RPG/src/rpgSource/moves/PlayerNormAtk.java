package rpgSource.moves;

import rpgSource.entity.Entities;
import rpgSource.entity.Player;

public class PlayerNormAtk extends Move {

	public PlayerNormAtk(int bd, String n, String d) {
		super(bd, n, d, "A standard attack.");
	}

	public PlayerNormAtk(int bd, String n, String d, Entities l) {
		super(bd, n, d, "A standard attack.", null, l);
	}
	
	public PlayerNormAtk(int bd, String n, String d, String u, Entities l) {
		super(bd, n, d, u, null, l);
	}

	@Override
	public void doSomething() {
		((Player) user).charge += ((Player) user).charge < 100 ? 20 : 0;
	}
}

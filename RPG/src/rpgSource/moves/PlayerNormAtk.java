package rpgSource.moves;

import rpgSource.entity.Entities;
import rpgSource.entity.Player;

public class PlayerNormAtk extends Move {

	public PlayerNormAtk(int bd, String n, String u) {
		super(bd, n, "A standard attack.", u);
	}

	public PlayerNormAtk(int bd, String n, String u, Entities l) {
		super(bd, n, "A standard attack.", "", u, l);
	}
	
	public PlayerNormAtk(int bd, String n, String d, String u, Entities l) {
		super(bd, n, d, "", u, l);
	}

	@Override
	public void doSomething() {
		((Player) user).charge += ((Player) user).charge < 100 ? 20 : 0;
	}
}

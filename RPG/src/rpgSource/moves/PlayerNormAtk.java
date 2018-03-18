package rpgSource.moves;

import rpgSource.entity.Entities;
import rpgSource.entity.Player;

public class PlayerNormAtk extends Move {

	public PlayerNormAtk(double mul, String n, String u) {
		super(mul , n, "A standard attack.", u);
	}

	public PlayerNormAtk(double mul, String n, String u, Entities l) {
		super(mul, n, "A standard attack.", "", u, l);
	}
	
	public PlayerNormAtk(double mul, String n, String d, String u, Entities l) {
		super(mul, n, d, "", u, l);
	}

	@Override
	public void doSomething() {
		((Player) user).charge += ((Player) user).charge < 100 ? 20 : 0;
	}
}

package rpgSource;

import rpgSource.entity.Entities;
import rpgSource.entity.Player;

public class normAtk extends Move{

	public normAtk(int bd, String n, String d, Entities l) {
		super(bd,n ,d, l);
	}

	@Override
	public void doSomething() {
		((Player) user).charge += ((Player) user).charge < 100 ? 20 : 0;
	}
}

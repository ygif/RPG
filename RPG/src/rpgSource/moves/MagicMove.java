package rpgSource.moves;

import rpgSource.entity.Entities;

public class MagicMove extends Move {
	
	int mpUsage;

	public MagicMove(int bd, String n, String d, String u, Entities l, int mp) {
		super(bd, n, d, u, l.getName() + " does not have enough mp to use " + n + ".", l, m -> m.intValue() >= mp ? true : false);
		mpUsage = mp;
	}
	
	@Override
	public void doSomething() {
		user.reduceMP(mpUsage);
	}
	
	@Override
	public String[] getExtraInfo() {
		return new String[] {"MP cost: " + mpUsage, "Base Attack: " + getBaseDamage()};
	}
}

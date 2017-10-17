package rpgSource.moves;

import rpgSource.entity.Entities;

public class MagicMove extends PreConNumMove {
	
	int mpUsage;

	public MagicMove(int bd, String n, String d, Entities l, int mp) {
		super(bd, n, d, l.getName() + " does not have enough mp to use " + n + ".", l, m -> m >= mp ? true : false);
		mpUsage = mp;
	}
	
	@Override
	public void doSomething() {
		user.reduceMP(mpUsage);
	}
}

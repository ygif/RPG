package rpgSource.moves;

import rpgSource.entity.Entities;

public class MagicMove extends Move {
	
	int mpUsage;

	public MagicMove(int bd, String n, String d, Entities l, int mp) {
		super(bd, n, d, l.getName() + " does not have enough mp to use " + n + ".", l, m -> m.intValue() >= mp ? true : false);
		mpUsage = mp;
	}
	
	@Override
	public void doSomething() {
		precondition(user.getMp());
		user.reduceMP(mpUsage);
	}
}

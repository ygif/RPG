package rpgSource.moves;

import rpgSource.RPGGUI;
import rpgSource.entity.Entities;

public class RCAttack extends Move {

	public RCAttack(int bd, String n, String d) {
		super(bd, n, d);
		// TODO Auto-generated constructor stub
	}

	public RCAttack(int bd, String n, String d, Entities l) {
		super(bd, n, d, l);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doSomething() {
		double recoil = Math.floor((baseDamage * ((double) user.getAttack()/10)) * 0.1);
		user.reduceHealthRecoil(recoil);
		RPGGUI.getInstance().appendToConsole("The ogre takes " + user.getNumFormatter().format(recoil) + " recoil damage and has " + 
				user.getNumFormatter().format(user.getCurrentHealth()) + " health left.\n");
	}
}

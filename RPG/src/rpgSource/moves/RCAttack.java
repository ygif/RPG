package rpgSource.moves;

import rpgSource.RPGGUI;
import rpgSource.entity.Entities;

/**
 * 
 * @author FlightGuy
 *
 * extend this class to use moves that cause recoil
 */
public class RCAttack extends Move {

	public RCAttack(int bd, String n, String d, String u) {
		super(bd, n, d, u);
	}

	public RCAttack(int bd, String n, String d, String u, Entities l) {
		super(bd, n, d, u, null, l);
	}

	@Override
	public void doSomething() {
		double recoil = Math.floor((getBaseDamage() * ((double) user.getAttack()/10)) * 0.1);
		user.reduceHealthRecoil(recoil);
		RPGGUI.getInstance().appendToConsole("The " + user.getName() +" takes " + user.getNumFormatter().format(recoil) + " recoil damage and has " + 
				user.getNumFormatter().format(user.getCurrentHealth()) + " health left.\n");
	}
}

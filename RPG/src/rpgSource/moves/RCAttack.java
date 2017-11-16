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
		super(bd, n, d, "", u, l);
	}

	@Override
	public void doSomething() {
		int recoil = (int) ((getBaseDamage() * ((double) user.getAttack()/10)) * 0.1);
		user.reduceHealthRecoil(recoil);
		RPGGUI.getInstance().appendToConsole("The " + user.getName() +" takes " + recoil + " recoil damage and has " + 
				user.getCurrentHealth() + " health left.\n");
	}
}

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

	public RCAttack(double mul, String n, String d, String u) {
		super(mul, n, d, u);
	}

	public RCAttack(double mul, String n, String d, String u, Entities l) {
		super(mul, n, d, "", u, l);
	}

	@Override
	public void doSomething() {
		int recoil = (int) ((getMultiplier() * user.getAttack() * 0.1)/ user.getDefense());
		user.reduceHealthRecoil(recoil);
		RPGGUI.getInstance().appendToConsole("The " + user.getName() +" takes " + recoil + " recoil damage and has " + 
				user.getCurrentHealth() + " health left.\n");
	}
}

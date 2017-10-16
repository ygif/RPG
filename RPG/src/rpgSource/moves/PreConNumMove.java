package rpgSource.moves;

import java.util.function.Predicate;

import rpgSource.entity.Entities;
import rpgSource.entity.Player;

public class PreConNumMove extends Move {
	
	Predicate<Double> tester;
	String fail;
	private boolean passed;
	
	public PreConNumMove(int bd, String n, String d, String f) {
		this(bd, n, d, f, null, (a) -> true);
	}

	public PreConNumMove(int bd, String n, String d, String f, Entities l) {
		this(bd, n, d, f, l, (a) -> true);
	}
	
	public PreConNumMove(int bd, String n, String d, String f, Entities l, Predicate<Double> t) {
		super(bd, n, d, l);
		tester = t;
		fail = f;
		passed = false;
	}
	
	public boolean precondition(double d) {
		passed = tester.test(d);
		return passed;
	}

	@Override
	public void doSomething() {
		((Player) user).charge = 0;
	}
	
	@Override
	public String getDes() {
		return passed ? super.getDes() : fail;
	}
}

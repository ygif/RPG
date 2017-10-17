package rpgSource.moves;

import java.util.function.DoubleSupplier;
import java.util.function.Predicate;

import rpgSource.entity.Entities;

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
	
	public boolean precondition(DoubleSupplier ds) {
		passed = tester.test(ds.getAsDouble());
		return passed;
	}

	@Override
	public void doSomething() {
		
	}
	
	@Override
	public String getDes() {
		return passed ? super.getDes() : fail;
	}
}

package rpgSource.moves;

import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.Predicate;

import rpgSource.entity.Entities;

public class PreConNumMove extends Move {
	
	Predicate<Number> tester;
	String fail;
	private boolean passed;
	
	public PreConNumMove(int bd, String n, String d, String f) {
		this(bd, n, d, f, null, (a) -> true);
	}

	public PreConNumMove(int bd, String n, String d, String f, Entities l) {
		this(bd, n, d, f, l, (a) -> true);
	}
	
	public PreConNumMove(int bd, String n, String d, String f, Entities l, Predicate<Number> t) {
		super(bd, n, d, l);
		tester = t;
		fail = f;
		passed = false;
	}
	
	public boolean precondition(double d) {
		passed = tester.test(new Double(d));
		return passed;
	}
	
	public boolean precondition(DoubleSupplier ds) {
		return precondition(ds.getAsDouble());
	}
	
	public boolean precondition(int i) {
		passed = tester.test(new Integer(i));
		return passed;
	}
	
	public boolean precondition(IntSupplier is) {
		return precondition(is.getAsInt());
	}

	@Override
	public void doSomething() {
		
	}
	
	@Override
	public String getDes() {
		return passed ? super.getDes() : fail;
	}
	
	@Override
	public int getBaseDamage() {
		return passed ? super.getBaseDamage() : 0;
	}
}

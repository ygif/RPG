package rpgSource.moves;

import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.Predicate;

import rpgSource.entity.Entities;

public abstract class Move {

	private int baseDamage;
	public String name;
	private String des;
	public Entities user;
	Predicate<Number> tester;
	String fail;
	private boolean passed;
	
	private Move(int bd, String n, String d, Entities l) {
		baseDamage = bd;
		name = n;
		des = d;
		user = l;
	}
	
	public Move(int bd, String n, String d) {
		this(bd, n, d, null, null, (a) -> true);
	}
	
	public Move(int bd, String n, String d, String f) {
		this(bd, n, d, f, null, (a) -> true);
	}

	public Move(int bd, String n, String d, String f, Entities l) {
		this(bd, n, d, f, l, (a) -> true);
	}
	
	public Move(int bd, String n, String d, String f, Entities l, Predicate<Number> t) {
		this(bd, n, d, l);
		tester = t;
		fail = f;
		passed = false;
	}
	
	public abstract void doSomething();
	
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
	
	public String getDes() {
		return passed ? des : fail;
	}
	
	public int getBaseDamage() {
		return passed ? baseDamage : 0;
	}
}

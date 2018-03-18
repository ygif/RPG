package rpgSource.moves;

import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.Predicate;

import rpgSource.entity.Entities;
import rpgSource.util.Describable;

public abstract class Move implements Describable{

	private double multiplier;
	private String name;
	private String des;
	private String useMessage;
	public Entities user;
	Predicate<Number> tester;
	String fail;
	private boolean passed;
	
	public Move(double mul, String n, String d, String u) {
		this(mul, n, d, "", u, null, (a) -> true);
	}
	
	public Move(double mul, String n, String d, String f, String u) {
		this(mul, n, d, f, u, null, (a) -> true);
	}

	public Move(double mul, String n, String d, String f, String u, Entities l) {
		this(mul, n, d, f, u, l, (a) -> true);
	}
	
	public Move(double mul, String n, String d, String f, String u, Entities l, Predicate<Number> t) {
		multiplier = mul;
		name = n;
		des = d;
		fail = f;
		useMessage = u;
		user = l;
		tester = t;
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
	
	public String useMessage() {
		return passed ? useMessage : fail;
	}
	
	public double getMultiplier() {
		return passed ? multiplier : 0;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return des;
	}
	
	public String[] getExtraInfo() {
		return new String[] {"Multiplier: " + multiplier};
	}
}

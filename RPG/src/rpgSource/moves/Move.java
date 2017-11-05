package rpgSource.moves;

import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.Predicate;

import rpgSource.Describable;

import rpgSource.entity.Entities;

public abstract class Move implements Describable{

	private int baseDamage;
	private String name;
	private String des;
	private String useMessage;
	public Entities user;
	Predicate<Number> tester;
	String fail;
	private boolean passed;
	
	private Move(int bd, String n, String d, String u, Entities l) {
		baseDamage = bd;
		name = n;
		des = d;
		user = l;
	}
	
	public Move(int bd, String n, String d, String u) {
		this(bd, n, d, u, null, null, (a) -> true);
	}
	
	public Move(int bd, String n, String d, String f, String u) {
		this(bd, n, d, f, u, null, (a) -> true);
	}

	public Move(int bd, String n, String d, String f, String u, Entities l) {
		this(bd, n, d, f, u, l, (a) -> true);
	}
	
	public Move(int bd, String n, String d, String f, String u, Entities l, Predicate<Number> t) {
		this(bd, n, d, u, l);
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
	
	public String useMessage() {
		return passed ? useMessage : fail;
	}
	
	public int getBaseDamage() {
		return passed ? baseDamage : 0;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return des;
	}
	
	public String[] getExtraInfo() {
		return new String[] {"Base Damage: " + baseDamage};
	}
}

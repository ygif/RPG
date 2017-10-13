package rpgSource;

public abstract class Move {

	int baseDamage;
	String name;
	String des;
	LivingThings user;
	
	public Move(int bd, String n, String d) {
		this(bd, n, d, null);
	}
	
	public Move(int bd, String n, String d, LivingThings l) {
		baseDamage = bd;
		name = n;
		des = d;
		user = l;
	}
	
	abstract void doSomething();
}

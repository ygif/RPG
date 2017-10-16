package rpgSource;

import rpgSource.entity.Entities;

public abstract class Move {

	public int baseDamage;
	public String name;
	public String des;
	public Entities user;
	
	public Move(int bd, String n, String d) {
		this(bd, n, d, null);
	}
	
	public Move(int bd, String n, String d, Entities l) {
		baseDamage = bd;
		name = n;
		des = d;
		user = l;
	}
	
	public abstract void doSomething();
}

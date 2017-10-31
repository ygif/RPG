package rpgSource.moves;

import rpgSource.entity.Entities;

public abstract class Move {

	private int baseDamage;
	public String name;
	private String des;
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
	
	public String getDes() {
		return des;
	}
	
	public int getBaseDamage() {
		return baseDamage;
	}
}

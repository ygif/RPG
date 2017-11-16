package rpgSource.battleSystem;

import rpgSource.entity.Entities;

public abstract class Packet {

	Entities user;
	Entities target;
	
	public Packet(Entities u, Entities t) {
		user = u;
		target = t;
	}
	
	public Packet(Entities e) {
		user = e;
		target = e;
	}
	
	public abstract void apply();
}

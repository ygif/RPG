package rpgSource;

import rpgSource.entity.Entities;

public abstract class Packet {

	Entities user;
	Entities target;
	
	public Packet(Entities u, Entities t) {
		user = u;
		target = t;
	}
	
	public abstract void apply();
}

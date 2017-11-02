package rpgSource.moves;

import rpgSource.entity.Entities;

public class NormAtk extends Move{
	
	public NormAtk(int bd, String n, String d, Entities l) {
		super(bd, n ,d, "A standard attack", null, l);
	}
	
	public NormAtk(int bd, String n, String d, String u, Entities l) {
		super(bd, n ,d, u, null, l);
	}

	@Override
	public void doSomething() {
		
	}
}

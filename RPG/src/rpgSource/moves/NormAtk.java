package rpgSource.moves;

import rpgSource.entity.Entities;

public class NormAtk extends Move{
	
	public NormAtk(int bd, String n, String u, Entities l) {
		super(bd, n , "A standard attack", null, u, l);
	}
	
	public NormAtk(int bd, String n, String d, String u, Entities l) {
		super(bd, n, d, "", u, l);
	}

	@Override
	public void doSomething() {
		
	}
}

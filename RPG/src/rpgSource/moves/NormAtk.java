package rpgSource.moves;

import rpgSource.entity.Entities;

public class NormAtk extends Move{
	
	public NormAtk(double mul, String n, String u, Entities l) {
		super(mul, n , "A standard attack", null, u, l);
	}
	
	public NormAtk(double mul, String n, String d, String u, Entities l) {
		super(mul, n, d, "", u, l);
	}

	@Override
	public void doSomething() {
		
	}
}

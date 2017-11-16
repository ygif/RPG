package rpgSource.battleSystem;

import rpgSource.entity.Entities;

public class StatusEffect {

	Entities target;
	String firstAffected;
	String appliedEffect;
	
	public StatusEffect(Entities e, String fa) {
		this(e, fa, "");
	}
	
	public StatusEffect(Entities e, String fa, String ae) {
		target = e;
		firstAffected = fa;
		appliedEffect = ae;
	}
}

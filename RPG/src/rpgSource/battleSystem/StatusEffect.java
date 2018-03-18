package rpgSource.battleSystem;

import rpgSource.util.Describable;
import rpgSource.util.Updateable;

public class StatusEffect implements Describable, Updateable{

	private String name;
	private String description;
	String appliedEffect;
	private int length;
	public double value;
	public StatusEffectType setype;
	
	public StatusEffect(String name, String des, String fa) {
		this(name, des, fa, "", 1, StatusEffectType.END_OF_TURN);
	}
	
	public StatusEffect(String name, String des, String fa, StatusEffectType set) {
		this(name, des, fa, "", 1, set);
	}
	
	public StatusEffect(String name, String des, String fa, String ae, double v) {
		this(name, des, fa, ae, 3, v, StatusEffectType.END_OF_TURN);
	}
	
	public StatusEffect(String name, String des, String fa, String ae, double v, StatusEffectType set) {
		this(name, des, fa, ae, 3, v, set);
	}
	
	public StatusEffect(String name, String des, String fa, String ae, int l, double v) {
		this(name, des, fa, ae, l, v, StatusEffectType.END_OF_TURN);
	}
	
	public StatusEffect(String name, String des, String fa, String ae, int l, double v, StatusEffectType set) {
		this.name = name;
		description = des;
		//e.message(e.getName() + "got " + name);
		appliedEffect = ae;
		length = l;
		value = v;
		setype = set;
	}
	
	private void shortenLength() {
		length--;
		if(length <= 0) {
			length = 0;
		}
	}
	
	public int getLength() {
		return length;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String[] getExtraInfo() {
		return new String[] {"Status effect value: " + Double.toString(value)};
	}
	
	public enum StatusEffectType {
		END_OF_TURN, ON_STAT, BOTH;
	}

	@Override
	public void update() {
		shortenLength();
	}
}

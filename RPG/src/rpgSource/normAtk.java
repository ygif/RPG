package rpgSource;

public class normAtk extends Move{

	public normAtk(int bd, String n, String d, LivingThings l) {
		super(bd,n ,d, l);
	}

	@Override
	void doSomething() {
		((Player) user).charge += ((Player) user).charge < 100 ? 20 : 0;
	}
}

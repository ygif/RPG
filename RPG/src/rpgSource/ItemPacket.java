package rpgSource;

import rpgSource.entity.Entities;

public class ItemPacket extends Packet {
	
	Items item;

	public ItemPacket(Entities u, Entities t, Items i) {
		super(u, t);
		item = i;
	}
	
	public ItemPacket(Entities e, Items i) {
		super(e, e);
		item = i;
	}

	@Override
	public void apply() {
		if(item.name.equals("Health Potion")) {
			user.restoreHeatlth((int) item.useItem());
			user.message("The " + user.getName() + " has " + user.getCurrentHealth() + " health left.");
		} else {
			int damage = item.useItem();
			target.reduceHealthRecoil(damage);
			target.message(damage + " damage\n");
			target.message("The " + target.getName() + " has " + target.getCurrentHealth() + " health.\n");
		}
	}
}

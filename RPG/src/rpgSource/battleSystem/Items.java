package rpgSource.battleSystem;

import rpgSource.RPGGUI;
import rpgSource.util.Describable;

public class Items implements Describable{
	
	int quantity;
	int damage;
	String name;
	String usageMessage;
	String description;
	
	/**
	 * 
	 * @param quantity The number of the items the player has.
	 * @param damage The amount of damage the item does if defense is ignored.
	 * @param name The name of the item.
	 * @param usageMessage The message displayed when an item is used.
	 */
	public Items(int quantity, int damage, String name, String usageMessage, String description){
		this.quantity = quantity;
		this.damage = damage;
		this.name = name;
		this.usageMessage = usageMessage;
		this.description = description;
	}
	
	/**
	 * This method is ran when the player decides to use an item.
	 * @return The damage the item does if defense is ignored
	 */
	public int useItem(){
		if(quantity < 1){
			RPGGUI.getInstance().appendToConsole("You have ran out of this item.\n");
		} else {
			quantity--;
			RPGGUI.getInstance().appendToConsole(usageMessage + "\n");
			return damage;
		}
		return 0;
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
		return new String[] {"Quantity: " + quantity};
	}
}

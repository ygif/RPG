package rpgSource;

public class Items {
	
	int quantity;
	double damage;
	String name;
	String usageMessage;
	
	/**
	 * 
	 * @param quantity The number of the items the player has.
	 * @param damage The amount of damage the item does if defense is ignored.
	 * @param name The name of the item.
	 * @param usageMessage The message displayed when an item is used.
	 */
	Items(int quantity, double damage, String name, String usageMessage){
		this.quantity = quantity;
		this.damage = damage;
		this.name = name;
		this.usageMessage = usageMessage;
	}
	
	/**
	 * This method is ran when the player decides to use an item.
	 * @return The damage the item does if defense is ignored
	 */
	double useItem(){
		if(quantity < 1){
			RPGGUI.getInstance().appendToConsole("You have ran out of this item.\n");
		} else {
			quantity--;
			RPGGUI.getInstance().appendToConsole(usageMessage + "\n");
			//System.out.println(quantity);
			return damage;
		}
		return 0;
	}
}

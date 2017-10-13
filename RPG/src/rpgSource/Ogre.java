package rpgSource;

public class Ogre extends Enemy{

	public Ogre(int level, int health, int attack, int defense, int speed, String name) {
		super(level, health, attack, defense, speed);
		this.name = name;
	}	
	@Override
	public double useAMoveRandom(int s){
		int temp = s;
		if(temp >= 0 && temp < 45){
			//Attack move
			LivingThings.ui.appendToConsole("The " + name + " attacks the player.\n");
			totalDamage = 12 * ((double) attack/10);
			return totalDamage;
		}else if(temp >= 45 && temp < 90){
			//Club hit
			LivingThings.ui.appendToConsole("The " + name + " hits the player with a club.\n");
			totalDamage = 16 * ((double) attack/10);
		}else if (temp >= 90 && temp < 100) {
			//charge attack
			double recoil;
			LivingThings.ui.appendToConsole("The " + name + " rams itself into the player\n");
			totalDamage = 22 * ((double) attack/10);
			recoil = Math.floor(totalDamage * 0.1);
			reduceHealthRecoil(recoil);
			LivingThings.ui.appendToConsole("The ogre takes " + numberPrinter.format(recoil) + " recoil damage and has " + numberPrinter.format(currentHealth) + " health left.\n");
		}
		return totalDamage;
	}
}

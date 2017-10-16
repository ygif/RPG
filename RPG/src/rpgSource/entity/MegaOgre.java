package rpgSource.entity;

public class MegaOgre extends Ogre {

	public MegaOgre(int level, int health, int attack, int defense, int speed, String name) {
		super(level, health, attack, defense, speed, name);
	}
	
	@Override
	public double useAMoveRandom(int s){
		int temp = s;
		if(temp >= 0 && temp < 30){
			//Attack move
			message("The " + name + " attacks the player.\n");
			totalDamage = 15 * ((double) attack/10);
			return totalDamage;
		}else if(temp >= 30 && temp < 60){
			//Club hit
			message("The " + name + " hits the player with a club.\n");
			totalDamage = 18 * ((double) attack/10);
		}else if(temp >= 60 && temp < 90) {
			//arm hit
			message("The " + name + " smashes the player.\n");
			totalDamage = 21 * ((double) attack/10);
		}else if (temp >= 90 && temp < 100) {
			//charge attack
			double recoil;
			message("The " + name + " rams itself into the player\n");
			totalDamage = 25 * ((double) attack/10);
			recoil = Math.floor(totalDamage * 0.1);
			reduceHealthRecoil(recoil);
			message("The ogre takes " + numberPrinter.format(recoil) + " recoil damage and has " + numberPrinter.format(currentHealth) + " health left.\n");
		}
		return totalDamage + 1.0;
	}
}

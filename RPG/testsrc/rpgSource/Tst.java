package rpgSource;

import org.junit.Test;

import rpgSource.entity.Dragon;
import rpgSource.entity.Ogre;
import rpgSource.entity.Player;

import org.junit.Assert;

import static org.junit.Assert.assertEquals;

import org.junit.Before;

public class Tst {
	
	Player p;
	Ogre o;
	Dragon d;
	
	double cHealth;
	
	@Before
	public void init(){
		cHealth = 0;
		p = new Player(10, 5, 5, 15, 10);
		o = new Ogre("ogre", 1, 15, 2, 6, 2);
		d = new Dragon("dragon", 3, 25, 7, 3, 10);
	}
	
	@Test
	public void testHPMethods() {
		cHealth = o.getCurrentHealth() - (Math.floor(((Math.log(o.getDefense()) * 10) / 4)));
		assertEquals(cHealth, o.reduceHealth(10), 1.0);
		assertEquals(5, p.reduceHealthRecoil(5), 0.0);
		cHealth = 0;
	}
	
	@Test
	public void testAttacking() {
		
	}
	
	@Test
	public void testDragonFlying(){
		d.fly();
		Assert.assertTrue("The 'flying' variable wasn't set correctly.", d.flying);
		assertEquals(d.getCurrentHealth(), d.reduceHealth(5), 0.0);
		d.flying = false;
		cHealth = d.getCurrentHealth() - (Math.floor(((Math.log(d.getDefense()) * 5) / 4)));
		assertEquals(cHealth, d.reduceHealth(5), 0.5);
	}
}

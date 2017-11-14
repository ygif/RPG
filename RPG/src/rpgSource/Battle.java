package rpgSource;

import java.util.Arrays;

import rpgSource.entity.Enemy;
import rpgSource.entity.Entities;
import rpgSource.entity.Player;

public class Battle {
	
	Entities[] ent;
	int turn;
	
	public Battle(Entities[] e) {
		ent = e;
		reorder();
		turn = 1;
	}
	
	public Battle(Player[] p, Enemy[] e) {
		ent = new Entities[p.length + e.length];
		for(int i = 0; i < p.length; i++) {
			ent[i] = p[i];
		}
		for(int i = ent.length - 1; i > e.length - 1; i--) {
			ent[i] = e[i];
		}
		reorder();
		turn = 1;
	}
	
	private void reorder() {
		Arrays.sort(ent, (one, two) -> two.getSpeed() - one.getSpeed());
	}
}

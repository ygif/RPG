package rpgSource;

import java.util.ArrayList;

import rpgSource.moves.Move;

public class ListOfActions extends ArrayList<Move>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1722262773445181833L;
	String name;

	public ListOfActions(String name) {
		this.name = name;
	}
}

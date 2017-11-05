package rpgSource.util;

import java.util.ArrayList;

public class ActionMenu {
	//Use JTabbedPane with this. 
	ArrayList<DescribableList> al;

	public ActionMenu() {
		
	}
	
	void add(DescribableList list) {
		al.add(list);
	}
	
	DescribableList remove() {
		return al.remove(al.size() - 1);
	}
}

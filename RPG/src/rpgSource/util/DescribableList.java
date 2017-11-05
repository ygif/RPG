package rpgSource.util;

import java.util.ArrayList;

import rpgSource.Describable;

public class DescribableList implements Describable {

	String name;
	ArrayList<Describable> al;
	
	public DescribableList(String name, Describable...d) {
		al = new ArrayList<Describable>();
		this.name = name;
		for(Describable de : d) {
			al.add(de);
		}
	}
	
	public DescribableList(String name, Describable d) {
		al = new ArrayList<Describable>();
		this.name = name;
		al.add(d);
	}
	
	public int size() {
		return al.size();
	}
	
	public Describable get(int i) {
		return al.get(i);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public String[] getExtraInfo() {
		return null;
	}

}

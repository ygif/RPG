package rpgSource.entity;

interface PlayerActions extends Actions{
	boolean flee();
	void useAnItem(int inputSelector);
	void increaseLevel();
}

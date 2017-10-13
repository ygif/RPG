package rpgSource.entity;

interface PlayerActions extends Actions{
	boolean flee();
	double useAnItem(int inputSelector);
	double useSpecialAttack();
	void increaseLevel();
}

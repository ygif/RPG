package rpgSource.entity;

interface Actions {
	void useAMove(int moveSelector);
	int selectAction(int selector);
	Entities getTarget();
}

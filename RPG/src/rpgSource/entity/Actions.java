package rpgSource.entity;

interface Actions {
	void useAMove(int moveSelector);
	int selectAction(int sel, int sel2);
	Entities getTarget();
}

package Poker_Game;

public abstract class Game {
	protected Dealer dealer;
	protected Player[] player;
	public String gameType;
	
	Game(Dealer dealer, Player[] player) {
		this.dealer = dealer;
		this.player = player;
	}
	
	public abstract void initialSetting(); //�� ������ �ʱ� ����
	public abstract Player getWinner(); //player �� ���� ã��
	
	public Dealer getDealer() {
		return dealer;
	}
	
	public Player[] getPlayer() {
		return player;
	}
}

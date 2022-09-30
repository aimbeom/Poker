package Poker_Game;

public class Player {
	private String name; //player�� �̸�
	private Card[] receivedCard; //���� ��
	private int cardCount; //���� ��
	private Rank cardRank = new Rank(); //���� rank
	private boolean die;
	private boolean select;
	
	Player(String name){
		this.name = name;
		receivedCard = new Card[5];
		cardCount = 0;
		cardRank = new Rank();
		die = false;
		select = false;
	}
	
	//���� rank �Ǻ�
	public void evaluateRank() {
		cardRank.updateCardRank(receivedCard, cardCount);
	}
		
	//�� ������ �� ����
	public void receiveOneCard(Card card) {
		if(cardCount != 5)
			receivedCard[cardCount++] = card;
	}
		
	//���� ���� face�� ���� sorting
	public void sortCard() {
		Card temp;
		for(int i = cardCount; i > 0; i--) {
			for(int j = 0; j < i - 1; j++) {
				if(receivedCard[j].getFaceNum() < receivedCard[j + 1].getFaceNum()) {
					temp = receivedCard[j + 1];
					receivedCard[j + 1] = receivedCard[j];
					receivedCard[j] = temp;
				}
			}
		}
	}
	
	public void initialPlayer() {
		receivedCard = new Card[5];
		cardCount = 0;
		cardRank = new Rank();
		die = false;
		select = false;
	}
	
	public String getName() {
		return name;
	}
	
	public Card[] getHand() {
		return receivedCard;
	}
	
	public int getCardCount() {
		return cardCount;
	}
	
	public Rank getCardRank() {
		return cardRank;
	}
	
	public void setDie() {
		die = true;
	}
	
	public void setAlive() {
		die = false;
	}
	
	public boolean isDie() {
		return die;
	}
	
	//�÷��̾ call/die�� �������� �� ���� ��ȭ
	public void doSelect() {
		select = true;
	}
	
	//�������� �ʾ��� ��
	public void dontSelect() {
		select = false;
	}
	
	public boolean isSelect() {
		return select;
	}
}

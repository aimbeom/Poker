package Poker_Game;

import java.util.Scanner;

public class DemoGame extends Game{
	DemoGame(Dealer dealer, Player[] player){
		super(dealer, player);
		gameType = "DEMO";
		initialSetting();
	}
	
	public void initialSetting() {
		dealer.shuffleCard();
		
		for(int i = 0; i < player.length; i++)
			player[i].initialPlayer();
		//�� �÷��̾�� 5���� ī�带 ���
		for(int i = 0; i < 5; i++)
			for(int j = 0; j < player.length; j++)
				player[j].receiveOneCard(dealer.dealOneCard());
	}
	
	public Player getWinner() {
		for(int i = 0; i < player.length; i++)
			player[i].evaluateRank();
		
		Player winner = player[0];
		
		for(int i = 1; i < player.length; i++) {
			//������ �� ���ų�
			if(player[i].getCardRank().getRankScore() > winner.getCardRank().getRankScore())
				winner = player[i];
			//������ ������ face�� �� ���� ��� winner
			else if(player[i].getCardRank().getRankScore() == winner.getCardRank().getRankScore()
					&& player[i].getCardRank().getRankFaceScore() > winner.getCardRank().getRankFaceScore())
				winner = player[i];
		}
		
		return winner;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		System.out.println("������ ���� �����Ͻÿ�(1~5)");
		int playerNum = s.nextInt();
		
		int regame = 1;
		
		while(regame == 1) {
			Dealer dealer = new Dealer();
			dealer.shuffleCard();
			Player[] player = new Player[playerNum];
			for(int i = 0; i < player.length; i++)
				player[i] = new Player("KIM");
			DemoGame game = new DemoGame(dealer, player);
			
			
			for(int i = 0; i < player.length; i++) {
				player[i].sortCard();
				System.out.print("Player " + i + " : ");
				for(int j = 0; j < player[i].getCardCount(); j++) {
					System.out.print(player[i].getHand()[j].getCard() + "  ");
				}
				System.out.println();
			}
			
			Player winner = game.getWinner();
			System.out.printf("Winner : %s, Rank : %s\n", winner.getName(), winner.getCardRank().getRank());
			
			System.out.print("������ ����Ͻðڽ��ϱ�?");
			regame = s.nextInt();
		}
	}
}

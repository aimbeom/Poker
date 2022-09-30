package Poker_Game;

import java.util.Scanner;

public class PokerGame extends Game {
	PokerGame(Dealer dealer, Player[] player) {
		super(dealer, player);
		gameType = "POKER";
		initialSetting();
	}
	
	//game �ʱ� ����
	public void initialSetting() {
		dealer.shuffleCard();
		
		for(int i = 0; i < player.length; i++)
			player[i].initialPlayer();
		//�� �÷��̾�� ù 3���� ī�带 ���
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < player.length; j++)
				player[j].receiveOneCard(dealer.dealOneCard());
	}

	public Player getWinner() {
		for(int i = 0; i < player.length; i++) {
			if(player[i].isDie()) continue;
			player[i].evaluateRank();
		}
		
		Player winner = null;
		
		for(int i = 0; i < player.length; i++) {
			if(player[i].isDie()) continue;
			if(winner == null) {
				winner = player[i];
				continue;
			}
			//������ �� ���ų�
			if(player[i].getCardRank().getRankScore() > winner.getCardRank().getRankScore())
				winner = player[i];
			//������ ������ face�� �� ���� ��� winner
			else if(player[i].getCardRank().getRankScore() == winner.getCardRank().getRankScore()
					&& player[i].getCardRank().getRankFaceScore() > winner.getCardRank().getRankFaceScore())
				winner = player[i];
		}
		System.out.println(winner.getName());
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
			Player[] player = new Player[playerNum];
			for(int i = 0; i < player.length; i++)
				player[i] = new Player("Kim");
			PokerGame game = new PokerGame(dealer, player);
			
			for(int i = 0; i < player.length; i++) {
				System.out.print("Player " + i + " : ");
				for(int j = 0; j < player[i].getCardCount(); j++) {
					System.out.print(player[i].getHand()[j].getCard() + "  ");
				}
				System.out.println();
			}
			
			//�� ���� �� ���
			for(int i = 0; i < player.length; i++) {
				player[i].receiveOneCard(dealer.dealOneCard());
			}
			System.out.println();
			for(int i = 0; i < player.length; i++) {
				System.out.print("Player " + i + " : ");
				for(int j = 0; j < player[i].getCardCount(); j++) {
					System.out.print(player[i].getHand()[j].getCard() + "  ");
				}
				System.out.println();
			}
			
			//�� ���� �� ��а� sorting
			for(int i = 0; i < player.length; i++) {
				player[i].receiveOneCard(dealer.dealOneCard());
			}
			System.out.println();
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

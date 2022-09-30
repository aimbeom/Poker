package Poker_Game;

public class Rank {
	private String rank; //���� rank
	private int rankScore; //10���� ���� �� ����
	private int rankFaceScore; //������ ��ĥ ��� ���� ���� face ����
	
	public String getRank() {
		return rank;
	}
	
	public int getRankScore() {
		return rankScore;
	}
	
	public int getRankFaceScore() {
		return rankFaceScore;
	}
	
	public void updateCardRank(Card[] cardSet, int cardCount) {
		//flush�� ���
		if(cardCount == 5 && isFlush(cardSet)) {
			rank = cardSet[4].getFace() + " Flush";
			rankScore = 6;
			rankFaceScore = cardSet[4].getFaceNum();
			//flush�̸鼭 straight�� ���
			if(isStraight(cardSet)) {
				rank = cardSet[4].getFace() + " Straight Flush";
				rankScore = 9;
				rankFaceScore = cardSet[4].getFaceNum();
			}
			//flush�̸鼭 mountain�� ���
			if(isMountain(cardSet)) {
				rank = "Royal Flush";
				rankScore = 10;
				rankFaceScore = 13;
			}
		}
		else {
			//mountain�� ���
			if(cardCount == 5 && isMountain(cardSet)) {
				rank = "Mountain";
				rankScore = 5;
				rankFaceScore = 14;
			}
			//straight�� ���
			else if(cardCount == 5 && isStraight(cardSet)) {
				rank = cardSet[4].getFace() + " Straight";
				rankScore = 5;
				rankFaceScore = cardSet[4].getFaceNum();
			}
			else {
				//���� ���� face�� �� ��
				int[] faceCount = new int[13];
				for(int i = 0; i < cardCount; i++)
					faceCount[cardSet[i].getFaceNum() - 1]++;
				
				//face�� �� ���� ���� ���� ã��
				for(int i = 0; i < 13; i++) {
					String face;
					if(i == 0) face = "A";
					else if(i == 10) face = "J";
					else if(i == 11) face = "Q";
					else if(i == 12) face = "K";
					else face = (i + 1) + "";
					
					//���������� ������ �ȳ��� ��� ���� ū face�� high card
					if(faceCount[i] == 1) {
						rank = face + " High Card";
						rankScore = 1;
						rankFaceScore = i + 1;
					}
					//quads
					if(faceCount[i] == 4) {
						rank = face + " Quads";
						rankScore = 8;
						rankFaceScore = i + 1;
						break;
					}
					//trips or full house
					if(faceCount[i] == 3) {
						rank = face + " Trips";
						rankScore = 4;
						rankFaceScore = i + 1;
						
						//full house �˻�
						for(int j = i + 1; j < 13; j++)
							if(faceCount[j] == 2) {
								rank = face + " Full House";
								rankScore = 7;
								rankFaceScore = i + 1;
								break;
							}
						break;
					}
					//pair, two pair or full house
					if(faceCount[i] == 2) {
						rank = face + " Pair";
						rankScore = 2;
						rankFaceScore = i + 1;
						
						//two pair, full house �˻�
						for(int j = i + 1; j < 13; j++) {
							if(j == 0) face = "A";
							else if(j == 10) face = "J";
							else if(j == 11) face = "Q";
							else if(j == 12) face = "K";
							else face = (j + 1) + "";
							
							if(faceCount[j] == 2) {
								rank = face + " Two Pair";
								rankScore = 3;
								rankFaceScore = j + 1;
								break;
							}
							if(faceCount[j] == 3) {
								rank = face + " Full House";
								rankScore = 7;
								rankFaceScore = j + 1;
								break;
							}
						}
						break;
					}
				}
			}
		}
	}
	
	//flush Ȯ��
	private boolean isFlush(Card[] cardSet) {
		for(int i = 1; i < cardSet.length; i++)
			if(!cardSet[0].getSuit().equals(cardSet[i].getSuit()))
				return false;
		return true;
	}
	
	//straight Ȯ��
	private boolean isStraight(Card[] cardSet) {
		for(int i = 0; i < cardSet.length - 1; i++)
			if(cardSet[i].getFaceNum() + 1 != cardSet[i + 1].getFaceNum())
				return false;
		return true;
	}
	
	//mountain Ȯ��
	private boolean isMountain(Card[] cardSet) {
		if(cardSet[0].getFaceNum() == 1 && cardSet[1].getFaceNum() == 10
				&& cardSet[2].getFaceNum() == 11 && cardSet[3].getFaceNum() == 12
				&& cardSet[4].getFaceNum() == 13)
			return true;
		return false;
	}
}

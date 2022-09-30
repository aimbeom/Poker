package Poker_Game;

import java.util.Random;

public class Dealer {
	private Card[] deck;
	private int currentCard;
	private static final int NUMBER_OF_CARD = 52;
	private static final Random randomNum = new Random();
	
	Dealer(){
		String[] face = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		String[] suit = {"��", "��", "��", "��"};
		
		deck = new Card[NUMBER_OF_CARD];
		currentCard = 0;
		
		for(int i = 0; i < deck.length; i++) {
			deck[i] = new Card(face[i % 13], suit[i / 13]);
		}
	}
	
	void shuffleCard() {
		currentCard = 0;
		
		for(int i = 0; i < deck.length; i++) {
			int change = randomNum.nextInt(NUMBER_OF_CARD);
			
			Card temp = deck[i];
			deck[i] = deck[change];
			deck[change] = temp;
		}
	}
	
	Card dealOneCard() {
		return deck[currentCard++];
	}
	
	Card[] dealFiveCard() {
		Card[] setOfCard = new Card[5];
		
		for(int i = 0; i < 5; i++)
			setOfCard[i] = deck[currentCard++];
		
		return setOfCard;
	}
}

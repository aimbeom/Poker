package Poker_Game;

public class Card {
	private String face;
	private int faceNum;
	private String suit;
	
	Card(String face, String suit){
		this.face = face;
		this.suit = suit;
		
		if(face.equals("A"))
			faceNum = 1;
		else if(face.equals("J"))
			faceNum = 11;
		else if(face.equals("Q"))
			faceNum = 12;
		else if(face.equals("K"))
			faceNum = 13;
		else
			faceNum = Integer.parseInt(face);
	}
	
	public String getCard() {
		return suit + face;
	}
	
	public String getFace() {
		return face;
	}
	
	public int getFaceNum() {
		return faceNum;
	}
	
	public String getSuit() {
		return suit;
	}
}

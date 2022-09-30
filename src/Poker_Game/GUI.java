package Poker_Game;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class InitialScreen extends JFrame {
	private JLabel titleLabel;
	private JLabel subLabel;
	private JLabel pressLabel;

	public InitialScreen() {
		super("Poker Game");
		setLayout(null);

		titleLabel = new JLabel("Poker Game");
		titleLabel.setFont(new Font("���� ���", Font.BOLD, 30));
		titleLabel.setBounds(0, 50, 390, 50);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		add(titleLabel);
		
		subLabel = new JLabel("Team 13");
		subLabel.setFont(new Font("���� ���", Font.BOLD, 16));
		subLabel.setBounds(0, 100, 390, 50);
		subLabel.setHorizontalAlignment(JLabel.CENTER);
		add(subLabel);
		
		pressLabel = new JLabel("<< Press enter to continue >>");
		pressLabel.setFont(new Font("���� ���", Font.BOLD, 12));
		pressLabel.setBounds(0, 150, 390, 50);
		pressLabel.setHorizontalAlignment(JLabel.CENTER);
		add(pressLabel);
		
		//enter�� �Է��ϸ� ���� ȭ������ �Ѿ
		addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if(event.getKeyCode() == KeyEvent.VK_ENTER) {
                	new CountScreen();
                	dispose();
                }
            }
        });

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}

class CountScreen extends JFrame {
	private JLabel titleLabel;
	private JLabel gameLabel;
	private JLabel numberLabel;
	private JButton nextButton;
	private JComboBox gameJCombo;
	private JComboBox numberJCombo;
	private static final String[] gameTypeName = { "Demo", "Poker" };
	private static final String[] numName = { "2 players", "3 players", "4 players", "5 players", "6 players" };
	private static final int[] num = { 2, 3, 4, 5, 6 };
	private int gameType;
	private int playerNum;

	public CountScreen() {
		super("Poker Game");
		setLayout(null);
		
		titleLabel = new JLabel("Select");
		titleLabel.setFont(new Font("���� ���", Font.BOLD, 18));
		titleLabel.setBounds(50, 30, 200, 30);
		add(titleLabel);
		
		gameLabel = new JLabel("Game type");
		gameLabel.setFont(new Font("���� ���", Font.BOLD, 15));
		gameLabel.setBounds(50, 80, 200, 30);
		add(gameLabel);

		numberLabel = new JLabel("The number of players");
		numberLabel.setFont(new Font("���� ���", Font.BOLD, 15));
		numberLabel.setBounds(50, 120, 200, 30);
		add(numberLabel);

		nextButton = new JButton("Next");
		nextButton.setFont(new Font("���� ���", Font.PLAIN, 12));
		nextButton.setBounds(160, 190, 60, 30);
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				new NameSettingScreen(gameType, playerNum);
				dispose();
			}
		});
		add(nextButton);
		
		gameJCombo = new JComboBox(gameTypeName);
		gameJCombo.setMaximumRowCount(3);
		gameJCombo.setBounds(250, 85, 100, 25);
		gameJCombo.setSelectedIndex(0);
		gameType = 0;
		gameJCombo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				gameType = gameJCombo.getSelectedIndex();
			}
		});
		add(gameJCombo);

		numberJCombo = new JComboBox(numName);
		numberJCombo.setMaximumRowCount(3);
		numberJCombo.setBounds(250, 125, 100, 25);
		numberJCombo.setSelectedIndex(0);
		playerNum = 2;
		numberJCombo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				playerNum = num[numberJCombo.getSelectedIndex()];
			}
		});
		add(numberJCombo);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}

class NameSettingScreen extends JFrame {
	private JLabel mainLabel;
	private JButton nextButton;
	private JButton previousButton;
	private int playerNum;
	private int gameType;
	private JTextField[] nameField;
	private JLabel[] playerLabel;
	private String[] playerName;
	private Player[] player;

	public NameSettingScreen(int gameType, int playerNum) {
		super("Poker Game");
		setLayout(null);
		this.playerNum = playerNum;
		this.gameType = gameType;
		
		player = new Player[playerNum];

		mainLabel = new JLabel("Input player's name");
		mainLabel.setFont(new Font("���� ���", Font.BOLD,16));
		mainLabel.setBounds(0, 80, 230, 50);
		mainLabel.setHorizontalAlignment(JLabel.CENTER);
		add(mainLabel);

		nextButton = new JButton("Next");
		nextButton.setFont(new Font("���� ���", Font.PLAIN, 12));
		nextButton.setBounds(120, 190, 80, 30);
		add(nextButton);
		
		previousButton = new JButton("Previous");
		previousButton.setFont(new Font("���� ���", Font.PLAIN, 12));
		previousButton.setBounds(30, 190, 80, 30);
		add(previousButton);

		nextButton.addActionListener(new NextHandler());
		previousButton.addActionListener(new PreviousHandler());

		nameField = new JTextField[playerNum];
		playerLabel = new JLabel[playerNum];
		playerName = new String[playerNum];
		for (int i = 0; i < playerNum; i++) {
			playerLabel[i] = new JLabel(String.format("P%d :", i + 1));
			playerLabel[i].setFont(new Font("���� ���", Font.PLAIN, 12));
			playerLabel[i].setBounds(230, 20 + (35 * i), 100, 30);
			add(playerLabel[i]);

			nameField[i] = new JTextField("");
			nameField[i].setBounds(260, 23 + (35 * i), 100, 25);
			add(nameField[i]);
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	class NextHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			for (int i = 0; i < playerNum; i++) {
				playerName[i] = nameField[i].getText();
				//�̸��� �Է����� ���� ��� Player ��ȣ�� ���Ƿ� ����
				if(playerName[i].equals(""))
					playerName[i] = String.format("Player %s", i + 1);
				player[i] = new Player(playerName[i]);
			}
			
			Game game = null;
			Dealer dealer = new Dealer();
			switch(gameType) {
			case 0:
				game = new DemoGame(dealer, player);
				break;
			case 1:
				game = new PokerGame(dealer, player);
				break;
			}
			new GameScreen(game);
			dispose();
		}
	}
	
	class PreviousHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			new CountScreen();
			dispose();
		}
	}
}

class GameScreen extends JFrame {
	private Game game;
	private Dealer dealer;
	private Player[] player;
	private GameTable gameTable;
	private Controller controller;
	private ResultTable result;

	public GameScreen(Game game) {
		super("Poker Game");
		setLayout(null);
		
		this.game = game;
		this.dealer = game.getDealer();
		this.player = game.getPlayer();
		
		//F1�� ������ ȭ�鿡 ���� ���
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent event) {
				System.out.println(event.getKeyCode());
			}
		});

		JMenu gameMenu = new JMenu("Game");
		JMenuItem newItem = new JMenuItem("New Game...");
		gameMenu.add(newItem);
		newItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				new CountScreen();
				dispose();
			}
		});
		JMenuItem restartItem = new JMenuItem("Restart Game...");
		gameMenu.add(restartItem);
		restartItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				game.initialSetting();
				new GameScreen(game);
				dispose();
			}
		});
		
		JMenu fileMenu = new JMenu("File");
	    JMenuItem rankItem = new JMenuItem("Show Rank...");
	    fileMenu.add(rankItem);
	    rankItem.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent event) {
	    		new RankScreen();
	    	}
	    });


		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		bar.add(gameMenu);
		bar.add(fileMenu);

		gameTable = new GameTable();
		gameTable.setBounds(0, 0, 1200, 437);
		controller = new Controller();
		controller.setBounds(1200, 0, 350, 437);
		add(gameTable);
		add(controller);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1600, 500);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public GameTable getGameTable() {
		return gameTable;
	}
	
	public Controller getController() {
		return controller;
	}
	
	public void repaintFrame() {
		remove(gameTable);
		add(gameTable);
		repaint();
	}
	
	//controller �г� ��ġ�� ��� �г��� ���
    public void getResult() {
    	remove(controller);
    	result = new ResultTable();
    	result.setBounds(1200, 0, 350, 437);
		add(result);
		repaint();
    }

	
	public class ResultTable extends JPanel {
		private JLabel titleLabel;
		private JLabel nameLabel;
		private JButton[] cardButton;
		private JLabel rankLabel;
		private JButton newButton;
		private JButton reButton;

		public ResultTable() {
			setLayout(null);
			
			titleLabel = new JLabel("Winner!");
			titleLabel.setFont(new Font("���� ���", Font.BOLD, 30));
			titleLabel.setBounds(0, 30, 350, 30);
			titleLabel.setHorizontalAlignment(JLabel.CENTER);
			add(titleLabel);
			
			Player winner = game.getWinner();
			
			nameLabel = new JLabel(winner.getName());
			nameLabel.setFont(new Font("���� ���", Font.PLAIN, 23));
			nameLabel.setBounds(0, 90, 350, 30);
			nameLabel.setHorizontalAlignment(JLabel.CENTER);
			add(nameLabel);
			
			cardButton = new JButton[5];
			winner.sortCard();
			Card[] winCard = winner.getHand();
			for(int i = 0; i < winner.getCardCount(); i++) {
				cardButton[i] = new JButton(winCard[i].getCard());
				cardButton[i].setFont(new Font("���� ���", Font.PLAIN, 14));
				cardButton[i].setBounds(8 + (i * 65), 150, 60, 90);
				add(cardButton[i]);
			}
			
			rankLabel = new JLabel(winner.getCardRank().getRank());
			rankLabel.setFont(new Font("���� ���", Font.PLAIN, 20));
			rankLabel.setBounds(0, 270, 350, 30);
			rankLabel.setHorizontalAlignment(JLabel.CENTER);
			add(rankLabel);
			
			newButton = new JButton("New game");
			newButton.setFont(new Font("���� ���", Font.PLAIN, 12));
			newButton.setBounds(35, 380, 130, 30);
			add(newButton);
			newButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					new CountScreen();
					dispose();
				}
			});
			
			reButton = new JButton("Restart game");
			reButton.setFont(new Font("���� ���", Font.PLAIN, 12));
			reButton.setBounds(180, 380, 130, 30);
			add(reButton);
			reButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					game.initialSetting();
					new GameScreen(game);
					dispose();
				}
			});
		}
	}
	
	public class Controller extends JPanel{
		private JLabel titleLabel;
		private JButton openButton;
		private JButton closeButton;
		private JButton sortButton;
		private JButton dealButton;
		private JButton resultButton;
		private JLabel[] nameLabel;
		private JLabel[] stateLabel;
		private JLabel warningLabel;
		
		public Controller() {
			setLayout(null);
			
			titleLabel = new JLabel("Controller");
			titleLabel.setFont(new Font("���� ���", Font.BOLD, 30));
			titleLabel.setBounds(0, 10, 350, 30);
			titleLabel.setHorizontalAlignment(JLabel.CENTER);
			add(titleLabel);
			
			openButton = new JButton("Open all cards");
			openButton.setFont(new Font("���� ���", Font.PLAIN, 12));
			openButton.setBounds(35, 70, 130, 30);
			add(openButton);
			openButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					//��� ī�� �� �����ֱ�
					for(int i = 0; i < player.length; i++) {
						getGameTable().getPlayerTable()[i].getCardLayoutTable().getCardTable().openAllCards();
					}
				}
			});
			
			closeButton = new JButton("Close all cards");
			closeButton.setFont(new Font("���� ���", Font.PLAIN, 12));
			closeButton.setBounds(180, 70, 130, 30);
			add(closeButton);
			closeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					//��� ī�� �� �����
					for(int i = 0; i < player.length; i++) {
						getGameTable().getPlayerTable()[i].getCardLayoutTable().getCardTable().closeAllCards();
					}
				}
			});
			
			sortButton = new JButton("Sort all cards");
			sortButton.setFont(new Font("���� ���", Font.PLAIN, 12));
			sortButton.setBounds(35, 110, 130, 30);
			add(sortButton);
			sortButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					for(int i = 0; i < player.length; i++) {
						player[i].sortCard();
						getGameTable().getPlayerTable()[i].getCardLayoutTable().getCardTable().openAllCards();
						getGameTable().getPlayerTable()[i].getCardLayoutTable().updateCardLayout();
					}
				}
			});
			
			resultButton = new JButton("Game result");
			resultButton.setFont(new Font("���� ���", Font.PLAIN, 12));
			resultButton.setBounds(180, 380, 130, 30);
			add(resultButton);
			resultButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					getResult();
				}
			});
			
			//game type�� poker�� ��� ������Ʈ �߰�
			if(game.gameType.equals("POKER")) {
				dealButton = new JButton("Deal one card");
				dealButton.setFont(new Font("���� ���", Font.PLAIN, 12));
				dealButton.setBounds(35, 150, 130, 30);
				add(dealButton);
				dealButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						//��Ŀ�� ��� ��� �÷��̾ call/die�� ���ƴ��� Ȯ��
						for(int i = 0; i < player.length; i++) {
							if(player[i].getCardCount() == 5) {
								add(warningLabel);
								warningLabel.setText("Already deal five cards");
								repaint();
								return;
							}
							
							if(!player[i].isSelect()) {
								add(warningLabel);
								warningLabel.setText("Every player must select Call/Die");
								repaint();
								return;
							}
						}
						
						for(int i = 0; i < player.length; i++) {
							if(player[i].isDie()) continue; //player�� �׾��ٸ� ī�带 ������� ����
							player[i].receiveOneCard(dealer.dealOneCard());
							getGameTable().getPlayerTable()[i].getCardLayoutTable().getCardTable().addOneCard();
							getGameTable().getPlayerTable()[i].getCardLayoutTable().updateCardLayout();
							changeState(player[i], "");
							player[i].dontSelect();
						}
						
						remove(warningLabel);
						repaint();
					}
				});
				
				nameLabel = new JLabel[player.length];
				stateLabel = new JLabel[player.length];
				
				for(int i = 0; i < player.length; i++) {
					nameLabel[i] = new JLabel(player[i].getName());
					nameLabel[i].setFont(new Font("���� ���", Font.BOLD, 14));
					nameLabel[i].setBounds(35, 190 + (i * 25), 100, 30);
					add(nameLabel[i]);
					
					stateLabel[i] = new JLabel(":  ");
					stateLabel[i].setFont(new Font("���� ���", Font.BOLD, 14));
					stateLabel[i].setBounds(130, 190 + (i * 25), 150, 30);
					add(stateLabel[i]);
				}
				
				warningLabel = new JLabel("");
				warningLabel.setFont(new Font("���� ���", Font.BOLD, 14));
				warningLabel.setBounds(35, 340, 300, 30);
				add(warningLabel);
			}
		}
		
		public void changeState(Player player, String state) {
			int temp = -1;
			
			for(int i = 0; i < GameScreen.this.player.length; i++) {
				if(player.equals(GameScreen.this.player[i]))
					temp = i;
			}
			stateLabel[temp].setText(String.format(":    %s", state));
		}
	}

	public class GameTable extends JPanel { //�÷��̾���� ī�带 ��Ƴ��� panel
		private PlayerTable[] playertables = new PlayerTable[6];

		public GameTable() {
			setBackground(Color.BLACK);
			setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
			setLayout(new GridLayout(2, 3, 1, 1));
			
			int i = 0;
			for (; i < player.length; i++)
				playertables[i] = new PlayerTable(player[i]);
			
			for(; i < 6; i++)
				playertables[i] = new PlayerTable();
			
			add(playertables[2]);
			add(playertables[1]);
			add(playertables[3]);
			add(playertables[4]);
			add(playertables[0]);
			add(playertables[5]);
		}
		
		public PlayerTable[] getPlayerTable() {
			return playertables;
		}
	}

	public class PlayerTable extends JPanel { //player�� �г��Ӱ� ī�带 ����
		private JButton openButton;
		private JButton closeButton;
		private CardLayoutTable cardLayoutTable;
		
		public PlayerTable() {} //�������� �ʴ� ��� ����� ����� ������

		public PlayerTable(Player player) {
			setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
			
			if(game.gameType.equals("DEMO")) add(new DemoTable(player));
			else add(new PokerTable(player));
			cardLayoutTable = new CardLayoutTable(player);
			add(cardLayoutTable);
			
			openButton = new JButton("Open cards");
			openButton.setFont(new Font("���� ���", Font.PLAIN, 12));
			openButton.addActionListener(new OpenHandler());
			add(openButton);
			
			closeButton = new JButton("Close cards");
			closeButton.setFont(new Font("���� ���", Font.PLAIN, 12));
			closeButton.addActionListener(new CloseHandler());
			add(closeButton);
			
		}
		private class OpenHandler implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				//�÷��̾��� ��� ī�� �� ���̱�
				getCardLayoutTable().getCardTable().openAllCards();
			}
		}
		private class CloseHandler implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				//�÷��̾��� ��� ī�� �� ������
				getCardLayoutTable().getCardTable().closeAllCards();
			}
		}
		
		public CardLayoutTable getCardLayoutTable() {
			return cardLayoutTable;
		}
	}
	
	public class DemoTable extends JPanel {
		private JLabel nameLabel;

		public DemoTable(Player player) {
			setPreferredSize(new Dimension(410, 50));
			
			nameLabel = new JLabel(player.getName());
			nameLabel.setFont(new Font("���� ���", Font.PLAIN, 20));
			add(nameLabel);
		}
	}
	
	public class PokerTable extends JPanel {
		private JLabel nameLabel;
		private JLabel stateLabel;
		private JButton callButton;
		private JButton dieButton;

		public PokerTable(Player player) {
			setPreferredSize(new Dimension(410, 50));
			setLayout(null);
			
			nameLabel = new JLabel(player.getName());
			nameLabel.setFont(new Font("���� ���", Font.PLAIN, 20));
			nameLabel.setBounds(0, 0, 300, 50);
			nameLabel.setHorizontalAlignment(JLabel.CENTER);
			add(nameLabel);
			
			callButton = new JButton("Call");
			callButton.setFont(new Font("���� ���", Font.PLAIN, 12));
			callButton.setBounds(270, 0, 100, 23);
			callButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					if(!player.isSelect()) {
						getController().changeState(player, "Call");
						player.doSelect();
						player.setAlive();
					}
				}
			});
			add(callButton);
			
			dieButton = new JButton("Die");
			dieButton.setFont(new Font("���� ���", Font.PLAIN, 12));
			dieButton.setBounds(270, 27, 100, 23);
			dieButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					if(!player.isSelect()) {
						getController().changeState(player, "Die");
						player.doSelect();
						player.setDie();
					}
				}
			});
			add(dieButton);
		}
	}
	
	//ī�尡 �߰��� ������ ��ư�� �Բ� �߰��ؾߵǼ� cardLayout�� ���� �г� �ȿ� cardtable�� ����
	public class CardLayoutTable extends JPanel {
		private Player player;
		private CardLayout layout;
		private CardTable cardTable;
		
		public CardLayoutTable(Player player) {
			layout = new CardLayout();
			setLayout(layout);
			setPreferredSize(new Dimension(400, 95));
			
			this.player = player;
			
			cardTable = new CardTable(player);
			add("Card", cardTable);
		}
		
		public void updateCardLayout() {
			add("Card", cardTable);
			layout.show(this, "Card");
		}
		
		public CardTable getCardTable() {
			return cardTable;
		}
	}
	
	public class CardTable extends JPanel {
		private JButton[] cardButton = new JButton[5];
		private Player player;
		private int cardCount;

		public CardTable(Player player) {
			setLayout(new FlowLayout());
			setPreferredSize(new Dimension(400, 95));
			
			this.player = player;
			cardCount = 0;
			 
			for (int i = 0; i < player.getCardCount(); i++) {
				if(game.gameType.equals("DEMO"))
					cardButton[i] = new JButton("*");
				else
					if(i < 2) cardButton[i] = new JButton("*");
					else cardButton[i] = new JButton(player.getHand()[i].getCard());
				cardButton[i].setFont(new Font("���� ���", Font.PLAIN, 14));
				cardButton[i].setPreferredSize(new Dimension(70, 90));
				CardHandler cardHandler = new CardHandler(cardCount++);
				cardButton[i].addActionListener(cardHandler);
				add(cardButton[i]);
			}
		}
		
		public void addOneCard() {
			if(cardCount < 5) {
				if(game.gameType.equals("DEMO"))
					cardButton[cardCount] = new JButton("*");
				else
					if(cardCount < 2) cardButton[cardCount] = new JButton("*");
					else cardButton[cardCount] = new JButton(player.getHand()[cardCount].getCard());
				cardButton[cardCount].setFont(new Font("���� ���", Font.PLAIN, 14));
				cardButton[cardCount].setPreferredSize(new Dimension(70, 90));
				CardHandler cardHandler = new CardHandler(cardCount);
				cardButton[cardCount].addActionListener(cardHandler);
				add(cardButton[cardCount]);
				cardCount++;
			}
		}
		
		private class CardHandler implements ActionListener {
			private int i;
			
	        public CardHandler(int i) {
	        	this.i = i;
	        }
	         
			public void actionPerformed(ActionEvent event) {
				//ī�� �а� *�̸� �и� �����ְ�, �а� ������ ���¸� �ٽ� *�� ����
				if (cardButton[i].getText().equals("*")) {
					cardButton[i].setText(player.getHand()[i].getCard());
		        }
		        else {
		        	cardButton[i].setText("*");
		        }
			}
		}
		
		public void openAllCards() {
			for (int i = 0; i < player.getCardCount(); i++)
				cardButton[i].setText(player.getHand()[i].getCard());
	    }
	      
	    public void closeAllCards() {
	    	for (int i = 0; i < player.getCardCount(); i++)
	    		cardButton[i].setText("*");
	    }
	}
}

class RankScreen extends JFrame{
	   
	   private ImageIcon image = new ImageIcon("C:\\Users\\������\\eclipse-workspace\\java_class\\rank1.JPG"); //��� �����ϱ�
	   private Image image2 = image.getImage().getScaledInstance(350, 500, Image.SCALE_DEFAULT);
	   JLabel img = new JLabel(new ImageIcon(image2));
	   
	   public RankScreen() {
	      setLayout(new FlowLayout());
	      
	      setTitle("Rank");
	      add(img);
	      
	      setSize(350,500);
	      setResizable(false);
	      setLocationRelativeTo(null);
	      setLayout(null);
	      setVisible(true);
	   }
	   
	   public void paint(Graphics g) {//�׸��� �Լ�
	      g.drawImage(image2, 0, 0, null);
	   }
	}

public class GUI {

	public static void main(String[] args) {
		new InitialScreen();

	}

}

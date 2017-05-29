package MannsGOL;
//AUTHOR: 	Ashley Mann

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

public class GameOfLife extends JFrame implements ActionListener 
{
	//Generated Serial ID
	private static final long serialVersionUID = -3617730115919591399L;
	//Cell Size
	private static int cellSize = 10;	
	//Define Moves Per Second.
	private int speed = 3;
	//Navigation Menu
	private JMenuBar Menu;
	//Statistics Menu
	private JToolBar StatMenu;
	//Menu Buttons
	private JButton gridButton, speedButton, autofillButton, playButton,
					stopButton, resetButton,pointButton, gunButton, shipButton,
					artButton, aboutButton, helpButton, closeButton, exitButton;
	//Define Grid Boolean
	protected static boolean gridVisible;
	//Define Point
	boolean isPoint = true;
	//Define Glider
	boolean isGlider = false;
	static boolean[][] glider;
	//Define SpaceShip
	boolean isSpaceship = false;
	static boolean[][] spaceship;
	//Define Actions Timer.
	private Timer timer;
	//Define Generation Count.
	private int generationCount;
	//Define Generation Count Label.
	private JLabel generationLabel;
	//Define CellsAlive Count.
	private int survivorCount;
	//Define CellsAlive Count Label.
	private JLabel survivorLabel;
	//Define CellsDead Count.
	private int deadCount;
	//Define CellsDead Count Label.
	private JLabel deadLabel;    
	//Define CellsExist Count.
	private int existenceCount;
	//Define CellsExist Count Label.
	private JLabel existLabel;
	//Define the GameOfLifeBoard.
	private static GameOfLifeBoard platform;
	protected static JFrame game;
	
	//Main Frame Settings.	
	public static void main(String[] args) 
	{
		//Declare Frame.
		game = new GameOfLife();
		//Border-less Frame
		game.setUndecorated(true);
		//Stop Operation on Close.
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Set Frame Title.
		game.setTitle("Mann's Game Of Life");
		//Set Frame Size.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		game.setBounds(200, 100, screenSize.width, screenSize.height);
		//Set Frame Position.
		game.setLocationRelativeTo(null);
		//Set Frame Visibility.
		game.setVisible(true);
		
		
	}//End of main. DO NOT TOUCH

	//Main Frame Components.
	public GameOfLife()		//Constructor for Class 
	{	
		//Menu
		Menu = new JMenuBar();
		setJMenuBar(Menu);

		//Menu Styling
		Menu.setBackground(Color.orange);
		Menu.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		//#####################################################################################################
		
		//Speed Button (Allows for Speed Changes)
		gridButton = new JButton("Grid");
		Menu.add(gridButton );
		gridButton.addActionListener(this);

		//Speed Button Styling. 
		gridButton.setPreferredSize(new Dimension(85, 30));
		gridButton.setBackground(Color.ORANGE);  
		gridButton.setForeground(Color.BLACK);  
		gridButton.setFont(new Font("Courier", Font.BOLD, 17));  
		gridButton.setBorder(new LineBorder(Color.ORANGE));

		//#####################################################################################################

		//Speed Button (Allows for Speed Changes)
		speedButton = new JButton("Speed");
		Menu.add(speedButton );
		speedButton.addActionListener(this);

		//Speed Button Styling. 
		speedButton.setPreferredSize(new Dimension(85, 30));
		speedButton.setBackground(Color.ORANGE);  
		speedButton.setForeground(Color.BLACK);  
		speedButton.setFont(new Font("Courier", Font.BOLD, 17));  
		speedButton.setBorder(new LineBorder(Color.ORANGE));

		//#####################################################################################################

		//Auto fill Button (Automatically Fills Random Cells)
		autofillButton = new JButton("Autofill");
		Menu.add(autofillButton,BorderLayout.CENTER);
		autofillButton.addActionListener(this);

		//Auto fill Button Styling.
		autofillButton.setPreferredSize(new Dimension(110, 30));
		autofillButton.setBackground(Color.ORANGE);  
		autofillButton.setForeground(Color.BLACK);  
		autofillButton.setFont(new Font("Courier", Font.BOLD, 17));  
		autofillButton.setBorder(new LineBorder(Color.ORANGE));      

		//#####################################################################################################

		//Play Button
		playButton = new JButton("Play");
		Menu.add(playButton);
		playButton.addActionListener(this);

		//Play Button Styling.
		playButton.setPreferredSize(new Dimension(80, 30));
		playButton.setBackground(Color.ORANGE);  
		playButton.setForeground(Color.BLACK);  
		playButton.setFont(new Font("Courier", Font.BOLD, 17));  
		playButton.setBorder(new LineBorder(Color.ORANGE));

		//#####################################################################################################    

		//Stop Button
		stopButton = new JButton("Stop");
		Menu.add(stopButton);
		stopButton.setEnabled(false);
		stopButton.addActionListener(this);

		//Stop Button Styling
		stopButton.setPreferredSize(new Dimension(80, 30));
		stopButton.setBackground(Color.ORANGE);  
		stopButton.setForeground(Color.BLACK);  
		stopButton.setFont(new Font("Courier", Font.BOLD, 17));  
		stopButton.setBorder(new LineBorder(Color.ORANGE));        

		//#####################################################################################################    

		//Reset Button
		resetButton = new JButton("Reset");
		Menu.add(resetButton); 
		resetButton.addActionListener(this);

		//Reset Button Styling
		resetButton.setPreferredSize(new Dimension(85, 30));
		resetButton.setBackground(Color.ORANGE);  
		resetButton.setForeground(Color.BLACK);  
		resetButton.setFont(new Font("Courier", Font.BOLD, 17));  
		resetButton.setBorder(new LineBorder(Color.ORANGE));

		//##################################################################################################### 

		//Reset Button
		artButton = new JButton("Art");
		Menu.add(artButton); 
		artButton.addActionListener(this);

		//Reset Button Styling
		artButton.setPreferredSize(new Dimension(75, 30));
		artButton.setBackground(Color.ORANGE);  
		artButton.setForeground(Color.BLACK);  
		artButton.setFont(new Font("Courier", Font.BOLD, 17));  
		artButton.setBorder(new LineBorder(Color.ORANGE));

		//##################################################################################################### 

		//Point Button
		pointButton = new JButton("Point");
		Menu.add(pointButton);
		pointButton.addActionListener(this);

		//Point Button Styling
		pointButton.setPreferredSize(new Dimension(75, 30));
		pointButton.setBackground(Color.ORANGE);  
		pointButton.setForeground(Color.RED);  
		pointButton.setFont(new Font("Courier", Font.BOLD, 17));  
		pointButton.setBorder(new LineBorder(Color.ORANGE));        

		//##################################################################################################### 

		//Gun Button
		gunButton = new JButton("Gun");
		Menu.add(gunButton); 
		gunButton.addActionListener(this);

		//Gun Button Styling
		gunButton.setPreferredSize(new Dimension(75, 30));
		gunButton.setBackground(Color.ORANGE);  
		gunButton.setForeground(Color.BLACK);  
		gunButton.setFont(new Font("Courier", Font.BOLD, 17));  
		gunButton.setBorder(new LineBorder(Color.ORANGE));

		//##################################################################################################### 

		//Ship Button
		shipButton = new JButton("Ship");
		Menu.add(shipButton); 
		shipButton.addActionListener(this);

		//Gun Button Styling
		shipButton.setPreferredSize(new Dimension(75, 30));
		shipButton.setBackground(Color.ORANGE);  
		shipButton.setForeground(Color.BLACK);  
		shipButton.setFont(new Font("Courier", Font.BOLD, 17));  
		shipButton.setBorder(new LineBorder(Color.ORANGE));

		//##################################################################################################### 

		//Button Separator.
		//This separator keeps all the buttons evenly spaced.
		Menu.add(Box.createVerticalStrut(20));

		//Ship Button
		aboutButton = new JButton("About Game of Life");
		Menu.add(aboutButton); 
		aboutButton.addActionListener(this);

		//Gun Button Styling
		aboutButton.setPreferredSize(new Dimension(210, 30));
		aboutButton.setBackground(Color.ORANGE);  
		aboutButton.setForeground(Color.BLACK);  
		aboutButton.setFont(new Font("Courier", Font.BOLD, 17));  
		aboutButton.setBorder(new LineBorder(Color.ORANGE));

		//Ship Button
		helpButton = new JButton("Help");
		Menu.add(helpButton); 
		helpButton.addActionListener(this);

		//Gun Button Styling
		helpButton.setPreferredSize(new Dimension(80, 30));
		helpButton.setBackground(Color.ORANGE);  
		helpButton.setForeground(Color.BLACK);  
		helpButton.setFont(new Font("Courier", Font.BOLD, 17));  
		helpButton.setBorder(new LineBorder(Color.ORANGE));
		
		//Exit Button
		exitButton = new JButton("Exit");
		Menu.add(exitButton); 
		exitButton.addActionListener(this);

		//Exit Button Styling
		exitButton.setPreferredSize(new Dimension(80, 30));
		exitButton.setBackground(Color.RED);  
		exitButton.setForeground(Color.BLACK);  
		exitButton.setFont(new Font("Courier", Font.BOLD, 17));  
		exitButton.setBorder(new LineBorder(Color.ORANGE));
		//##################################################################################################### 

		//Generation Counter
		//This is a placeholder value for when the application starts for the first time.
		generationLabel = new JLabel("");

		//Generation Counter Styling
		generationLabel.setBackground(Color.ORANGE);  
		generationLabel.setForeground(Color.BLACK);  
		generationLabel.setFont(new Font("Courier", Font.BOLD, 17));  
		generationLabel.setBorder(null); 

		//#####################################################################################################    

		//Survivor Counter
		//This is a placeholder value for when the application starts for the first time.
		survivorLabel = new JLabel("");

		//Survivor Counter Styling
		survivorLabel.setBackground(Color.ORANGE);  
		survivorLabel.setForeground(Color.BLACK);  
		survivorLabel.setFont(new Font("Courier", Font.BOLD, 17));  
		survivorLabel.setBorder(null);

		//#####################################################################################################  

		//Survivor Counter
		//This is a placeholder value for when the application starts for the first time.        
		deadLabel = new JLabel("");

		//Survivor Counter Styling
		deadLabel.setBackground(Color.ORANGE);  
		deadLabel.setForeground(Color.BLACK);  
		deadLabel.setFont(new Font("Courier", Font.BOLD, 17));  
		deadLabel.setBorder(null);

		//#####################################################################################################  

		//Survivor Counter
		//This is a placeholder value for when the application starts for the first time.
		existLabel = new JLabel("| Cells Total: 000000 |");

		//Survivor Counter Styling
		existLabel.setBackground(Color.ORANGE);  
		existLabel.setForeground(Color.BLACK);  
		existLabel.setFont(new Font("Courier", Font.BOLD, 17));  
		existLabel.setBorder(null);

		//#####################################################################################################  

		//Statistics Menu
		//These are the Statistics at the bottom of the GUI.
		StatMenu = new JToolBar("Label Toolbar");

		//The Vertical Struts keep the statistics in the centre of the tool bar.
		StatMenu.add(Box.createVerticalStrut(20));

		StatMenu.add(generationLabel);
		StatMenu.add(survivorLabel);
		StatMenu.add(deadLabel);
		StatMenu.add(existLabel);

		StatMenu.add(Box.createVerticalStrut(20));

		//Statistics Menu Styling
		StatMenu.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		StatMenu.setBackground(Color.ORANGE);  
		StatMenu.setFloatable(false); 		//As a JToolBar, the Statistics Menu can be made drag-able if you user desires.This can be fixed to other positions on the GUI.
		add(StatMenu, BorderLayout.SOUTH);	//Positions the JToolBar at the bottom of the GUI

		//#####################################################################################################    

		//Setup game board
		platform = new GameOfLifeBoard();
		add(platform);

		//Define Timer.
		timer = new Timer(1000/speed, this);

		//Identifies the Timer as an Action Command.
		timer.setActionCommand("Timer");

		//Starts Timer Immediately.
		timer.setInitialDelay(0);

		//Adds Action Listener Function to Timer.
		timer.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{ 
				if (e.getActionCommand().equals("Timer")) 
				{ 
					//Begins Game of Life
					platform.run();
				} 
			}
		}
				);
	}//End of GameOfLife Class. DO NOT TOUCH

	public void ActiveGameOfLife(boolean CurrentlyActive) 
	{
		//If the play button is pressed, This method becomes active. 
		if (CurrentlyActive) 
		{
			playButton.setEnabled(false);
			stopButton.setEnabled(true);
			timer.start();
		} 
		else	//If the Stop Button is pressed, the Boolean CurrentlyActive becomes false and the following code activates. This code is also active when the game starts to prevent the game starting instantly.
		{
			playButton.setEnabled(true);
			stopButton.setEnabled(false);
			timer.stop();
		}
	}//End of ActiveGameOfLife. DO NOT TOUCH

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource().equals(gridButton))
		{	
			if(!gridVisible)
			{
				gridVisible = true;
				//Grid Button
				gridButton.setForeground(Color.RED);
				gridButton.setFont(new Font("Courier", Font.BOLD, 17));
			}
			else
			{
				gridVisible = false;
				//Grid Button
				gridButton.setForeground(Color.BLACK);
				gridButton.setFont(new Font("Courier", Font.BOLD, 17));
			}
		}
		else if(ae.getSource().equals(speedButton))// If the Speed Button is pressed.
		{

			//Create a JFrame for Speed Options
			final JDialog speedFrame = new JDialog(game,"Speed Options", Dialog.ModalityType.APPLICATION_MODAL);

			//JFrame Styling//Border-less Frame
			speedFrame.setUndecorated(true);
			speedFrame.setSize(450,90);
			speedFrame.setLocationRelativeTo(null);
			speedFrame.setResizable(false);

			//Create a JPanel for the Speed Options:
			JPanel speedPanel = new JPanel();

			//JPanel Styling
			speedPanel.setOpaque(true);
			speedPanel.setBackground(Color.ORANGE);

			//Add JPanel to the JFrame
			speedFrame.add(speedPanel);

			//Make a JLabel in the JPanel
			JLabel SpeedInfo = new JLabel("Number of moves per second:");
			SpeedInfo.setFont(new Font("Courier", Font.BOLD, 17));  
			speedPanel.add(SpeedInfo);

			//List of Speed Options in an Array
			Integer[] speedValues = {3,30,60};

			//JComboBox to display the Speed Options. The selected Speed Option becomes Speed Select.
			final JComboBox<Integer> secondsSelect = new JComboBox<Integer>(speedValues);

			//JComboBox Styling
			secondsSelect.setFont(new Font("Courier", Font.BOLD, 17));
			secondsSelect.setBackground(Color.ORANGE);  
			secondsSelect.setForeground(Color.BLACK); 
			secondsSelect.setBorder(new LineBorder(Color.ORANGE));
			
			//Add JComboBox with the Speeds Array to the JPanel
			speedPanel.add(secondsSelect);

			//Sets the selected value within the JComboBox to the value of speed.
			secondsSelect.setSelectedItem(speed);

			//Action Listener for once a Speed Option has been selected.
			secondsSelect.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae) 
				{
					speed = (Integer)secondsSelect.getSelectedItem();	//Speed equals the value selected from the JComboBox
					timer.setDelay(1000/speed); 						//Adjusts Speed depending on the value selected.

					speedFrame.dispose();		//Close JFrame once a speed option has been elected.
				}
			});
			
			//In case the user does not wish to change the speed, a close button has been added.
			closeButton = new JButton("Close Window");
			speedPanel.add(closeButton);
			closeButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent ae)
				{
					if(ae.getSource().equals(closeButton))
					{
						speedFrame.dispose(); //Close the frame if the close button is pressed. 
					}
				}
			}
			);
			
			speedPanel.add(closeButton, BorderLayout.SOUTH); //Position the close button at the bottom of the screen.
			
			//Gun Button Styling
			closeButton.setPreferredSize(new Dimension(150, 40));
			closeButton.setBackground(Color.BLACK);  
			closeButton.setForeground(Color.ORANGE);  
			closeButton.setFont(new Font("Courier", Font.BOLD, 20));  
			closeButton.setBorder(new LineBorder(Color.ORANGE));
			
			//Display the JFrame if Speed button is pressed.
			speedFrame.setVisible(true);

		}//End of Speed Button Action Event. DO NOT TOUCH.    

		else if (ae.getSource().equals(autofillButton)) //If Auto fill is pressed
		{
			platform.randomFill(10); //Auto fill Refills Board Alive cells.
		} //End of Auto Fill Button Action Event. DO NOT TOUCH.
		else if (ae.getSource().equals(resetButton)) //If Rest Button is pressed.
		{
			//Statistics Values Reset to 0.
			generationCount = 0; //Statistics Values Reset to 0.
			survivorCount = 0;
			deadCount = 0;
			existenceCount = 0;

			//Board is reset thus turning all cells to false.
			platform.resetBoard();

			//Board is repainted to display the new board of dead cells.
			platform.repaint();

			//Game of Life is stopped.
			ActiveGameOfLife(false);
		}//End of Reset Button Action Event. DO NOT TOUCH.
		else if (ae.getSource().equals(artButton))
		{
			platform.artBoard();
		}
		else if (ae.getSource().equals(playButton)) //If Play Button is pressed.
		{
			ActiveGameOfLife(true); //Start Game of Life.
		} //End of Play Button Action Event. DO NOT TOUCH.
		else if (ae.getSource().equals(stopButton)) //If Stop Button is pressed.
		{
			ActiveGameOfLife(false); //Stop Game of Life.
		} //End of Stop Button Action Event. DO NOT TOUCH.
		else if(ae.getSource().equals(pointButton)) //If Point is pressed.
		{
			//Point Button
			pointButton.setForeground(Color.RED); //Point Button Text turns red. 
			pointButton.setFont(new Font("Courier", Font.BOLD, 17)); //Font Size of Text increases by 2 (from 15)

			//Gun Button
			gunButton.setForeground(Color.BLACK); //Gun Button turns black.
			gunButton.setFont(new Font("Courier", Font.BOLD, 17)); //Font Size of Text set to 15.

			//Ship Button
			shipButton.setForeground(Color.BLACK); //Ship Button turns black.
			shipButton.setFont(new Font("Courier", Font.BOLD, 17)); //Font Size of Text set to 15.

			isPoint = true;			//isPoint Boolean set to true.
			isGlider = false; 		//isGlider Boolean set to false.
			isSpaceship = false;	//isSpaceship Boolean set to false.

		} //End of Point Button Action Event. DO NOT TOUCH.
		else if(ae.getSource().equals(gunButton)) //If Gun Button is pressed.
		{
			//Create a two dimensional boolean array. This is the pattern which allows us to spawn in a gun. 
			//Essentially, once the button has been pressed, the point becomes this array below allowing us to draw the gun.
			//Due to the complexity of this boolean array, we named each section such as B1, and every column. this is a 9 x 36 drid of true and false values.
			glider = new boolean[][]{
				/*one*/		{/*b1*/false,false,false,false,false,false, /*b2*/ false,false,false,false,false,false, /*b3*/ false,false,false,false,false,false, /*b4*/ false,false,false,false,false,false, /*b5*/ true ,false,false,false,false,false, /*b6*/ false,false,false,false,false,false,},
				/*two*/ 	{/*b1*/false,false,false,false,false,false, /*b2*/ false,false,false,false,false,false, /*b3*/ false,false,false,false,false,false, /*b4*/ false,false,false,false,true ,false, /*b5*/ true ,false,false,false,false,false, /*b6*/ false,false,false,false,false,false,},
				/*three*/	{/*b1*/false,false,false,false,false,false, /*b2*/ false,false,false,false,false,false, /*b3*/ true ,true ,false,false,false,false, /*b4*/ false,false,true ,true ,false,false, /*b5*/ false,false,false,false,false,false, /*b6*/ false,false,false,false,true ,true ,},
				/*four*/	{/*b1*/false,false,false,false,false,false, /*b2*/ false,false,false,false,false,true , /*b3*/ false,false,false,true ,false,false, /*b4*/ false,false,true ,true ,false,false, /*b5*/ false,false,false,false,false,false, /*b6*/ false,false,false,false,true ,true ,},
				/*five*/	{/*b1*/true ,true ,false,false,false,false, /*b2*/ false,false,false,false,true ,false, /*b3*/ false,false,false,false,true ,false, /*b4*/ false,false,true ,true ,false,false, /*b5*/ false,false,false,false,false,false, /*b6*/ false,false,false,false,false,false,},
				/*six*/		{/*b1*/true ,true ,false,false,false,false, /*b2*/ false,false,false,false,true ,false, /*b3*/ false,false,true ,false,true ,true , /*b4*/ false,false,false,false,true ,false, /*b5*/ true ,false,false,false,false,false, /*b6*/ false,false,false,false,false,false,},
				/*seven*/	{/*b1*/false,false,false,false,false,false, /*b2*/ false,false,false,false,true ,false, /*b3*/ false,false,false,false,true ,false, /*b4*/ false,false,false,false,false,false, /*b5*/ true ,false,false,false,false,false, /*b6*/ false,false,false,false,false,false,},
				/*eight*/	{/*b1*/false,false,false,false,false,false, /*b2*/ false,false,false,false,false,true , /*b3*/ false,false,false,true ,false,false, /*b4*/ false,false,false,false,false,false, /*b5*/ false,false,false,false,false,false, /*b6*/ false,false,false,false,false,false,},
				/*nine*/ 	{/*b1*/false,false,false,false,false,false, /*b2*/ false,false,false,false,false,false, /*b3*/ true ,true ,false,false,false,false, /*b4*/ false,false,false,false,false,false, /*b5*/ false,false,false,false,false,false, /*b6*/ false,false,false,false,false,false,}
			};

			//Gun Button
			gunButton.setForeground(Color.RED); //Gun Button Turns red.
			gunButton.setFont(new Font("Courier", Font.BOLD, 17)); //Font Size of Text set to 17

			//Spaceship
			shipButton.setForeground(Color.BLACK); //Ship Button Turns black.
			shipButton.setFont(new Font("Courier", Font.BOLD, 17));	//Font Size of Text set to 15

			//Point
			pointButton.setForeground(Color.BLACK); //Point Button Turns Black.
			pointButton.setFont(new Font("Courier", Font.BOLD, 17)); //Font Size of Text set to 15

			//Booleans
			isGlider = true;		//isGlider Boolean set to true. 
			isSpaceship = false;	//isSpaceship Boolean set to false.
			isPoint = false;		//isPoint Boolean set to false.

		} //End of GunButton Action Event. DO NOT TOUCH.
		else if(ae.getSource().equals(shipButton)) //If Ship Button is pressed.
		{
			// Create a new two dimensional array. This pattern contains a Game of Life Ship which will move vertically down the screen. 
			// This will be placed when the button is pressed and the user has pressed a point on the screen.
			spaceship = new boolean[][]{
				{true ,false,false,true ,false},
				{false,false,false,false,true },
				{true ,false,false,false,true },
				{false,true ,true ,true ,true }
			};

			//Ship Button
			shipButton.setForeground(Color.RED); // Ship Button Turns red.
			shipButton.setFont(new Font("Courier", Font.BOLD, 17)); //Font Size of Text set to 17 

			//Gun Button
			gunButton.setForeground(Color.BLACK); // Gun Button Turns black.
			gunButton.setFont(new Font("Courier", Font.BOLD, 17)); //Font Size of Text set to 15

			//Disable Point
			pointButton.setForeground(Color.BLACK); // Point Button turns black.
			pointButton.setFont(new Font("Courier", Font.BOLD, 17)); //Font Size of Text set to 15

			//Booleans
			isSpaceship = true;	//isSpaceship Boolean set to true.
			isGlider = false;		//isGlider Boolean set to false. 
			isPoint = false;		//isPoint Boolean set to false.

		}//End of ShipButton Action Event. DO NOT TOUCH.
		else if(ae.getSource().equals(aboutButton)) //If About Button is pressed.
		{
			//Create a JFrame for Speed Options
			final JDialog aboutFrame = new JDialog(game,"About Game Of Life", Dialog.ModalityType.APPLICATION_MODAL);

			//JFrame Styling
			aboutFrame.setSize(1200,690);
			aboutFrame.setLocationRelativeTo(null);
			aboutFrame.setResizable(false);
			aboutFrame.setUndecorated(true);

			//Create a JPanel for the Speed Options:
			JPanel aboutPanel = new JPanel();

			//JPanel Styling
			aboutPanel.setOpaque(true);
			aboutPanel.setBackground(Color.ORANGE);

			//Add JPanel to the JFrame
			aboutFrame.add(aboutPanel);

			//Make a JLabel in the JPanel
			JTextArea aboutInfo = new JTextArea();
			//Border-less Frame
			
			aboutInfo.setText(	"Game Of Life: \n"
								+"--------------------------------------------------------------------------------------------------------\n"
								+"The Game of Life, also known simply as Life,"
								+"is a cellular automaton devised by the British mathematician John Horton Conway in 1970.\n \n"
								+"The 'game' is a zero-player game, meaning that its evolution is determined by its initial state,"
								+"requiring no further input.\n \n"
								+"One interacts with the Game of Life by creating an initial configuration and observing how it evolves,"
								+"or, for advanced 'players', by creating patterns with particular properties.\n"
								+"--------------------------------------------------------------------------------------------------------\n"
								+"Rules of Game Of Life\n"
								+"The universe of the Game of Life is an infinite two-dimensional orthogonal grid of square cells, each of which is in one of two possible states, alive or dead. \n"
								+"     1).   Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.\n"
								+"     2).   Any live cell with two or three live neighbours lives on to the next generation.\n"
								+"     3).   Any live cell with more than three live neighbours dies, as if by overpopulation.\n"
								+"     4).   Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.\n"
								+"--------------------------------------------------------------------------------------------------------\n"
								+"Reference: \n"
								+"CONWAY'S GAME OF LIFE \n"
								+"In-text: (En.wikipedia.org, 2017)\n"
								+"En.wikipedia.org. (2017). Conway's Game of Life. [online] Available at: https://en.wikipedia.org/wiki/Conway's_Game_of_Life [Accessed 20 Mar. 2017]. \n"
								+"--------------------------------------------------------------------------------------------------------"
			);
            
			aboutInfo.setLineWrap(true);
			aboutInfo.setWrapStyleWord(true);
			aboutInfo.setEditable(false);
			aboutInfo.setHighlighter(null);
			
			aboutInfo.setSize(1200 - 100, 650 - 100);
			aboutInfo.setFont(new Font("Courier", Font.BOLD, 17));  
			aboutPanel.add(aboutInfo);
			
			//JTextArea Styling
			aboutInfo.setOpaque(true);
			aboutInfo.setBackground(Color.ORANGE);
			
			closeButton = new JButton("Close Window");
			
			closeButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent ae)
				{
					if(ae.getSource().equals(closeButton))
					{
						aboutFrame.dispose();
					}
				}
			}
			);
			
			aboutPanel.add(closeButton, BorderLayout.SOUTH);
			
			//Gun Button Styling
			closeButton.setPreferredSize(new Dimension(150, 50));
			closeButton.setBackground(Color.BLACK);  
			closeButton.setForeground(Color.ORANGE);  
			closeButton.setFont(new Font("Courier", Font.BOLD, 20));  
			closeButton.setBorder(new LineBorder(Color.ORANGE));
			
			//Display the JFrame if Speed button is pressed.
			aboutFrame.setVisible(true);
		}
		else if(ae.getSource().equals(helpButton)) //If Ship Button is pressed.
		{
			//Create a JFrame for Speed Options
			final JDialog helpFrame = new JDialog(game,"Help", Dialog.ModalityType.APPLICATION_MODAL);

			//JFrame Styling
			helpFrame.setSize(1200,700);
			helpFrame.setLocationRelativeTo(null);
			helpFrame.setResizable(false);
			helpFrame.setUndecorated(true);
			//Create a JPanel for the Speed Options:
			JPanel helpPanel = new JPanel();

			//JPanel Styling
			helpPanel.setOpaque(true);
			helpPanel.setBackground(Color.ORANGE);

			//Add JPanel to the JFrame
			helpFrame.add(helpPanel);

			//Make a JLabel in the JPanel
			JTextArea helpInfo = new JTextArea();
			helpInfo.setText(	"Help: \n"
								+"-----------------------------||-----------------------------------------------------------------------||\n"
								+"Grid Button:	             ||  Displays the Grid.                                                   ||\n"
								+"-----------------------------||-----------------------------------------------------------------------||\n"
								+"Speed Button:                ||  Allows User to set the speed.                                        ||\n"
								+"-----------------------------||-----------------------------------------------------------------------||\n"
								+"Autofill Button:             ||  Automatically fills the board with alive cells.                      ||\n"
								+"-----------------------------||-----------------------------------------------------------------------||\n"
								+"Play Button:                 ||  Runs the Game of Life.                                               ||\n"
								+"-----------------------------||-----------------------------------------------------------------------||\n"
								+"Stop Button:                 ||  Stops the Game of Life.                                              ||\n"
								+"-----------------------------||-----------------------------------------------------------------------||\n"
								+"Reset Button:                ||  Clears the board of cells and resets the game statistics.            ||\n"
								+"-----------------------------||-----------------------------------------------------------------------||\n"
								+"Art Button:                  ||  Displays an ASCII Art Image Translated into the Game of Life Array.  ||\n"
								+"-----------------------------||-----------------------------------------------------------------------||\n"
								+"Cell Button:                 ||  Sets the User Mouse Input to display a single cell on press or drag. ||\n"
								+"-----------------------------||-----------------------------------------------------------------------||\n"
								+"Gun Button:                  ||  Sets the User Mouse Input to display a 'Glider Gun' on press or drag.||\n"
								+"-----------------------------||-----------------------------------------------------------------------||\n"
								+"Ship Button:                 ||  Sets the User Mouse Input to display a 'Spaceship' on press or drag. ||\n"
								+"-----------------------------||-----------------------------------------------------------------------||\n"
								+"About Game of Life Button:   ||  Explains what the Game Of Life is.                                   ||\n"
								+"-----------------------------||-----------------------------------------------------------------------||\n"
			);
            
			helpInfo.setLineWrap(true);
			helpInfo.setWrapStyleWord(true);
			helpInfo.setEditable(false);
			helpInfo.setHighlighter(null);
			
			helpInfo.setSize(1200 - 100, 650 - 100);
			helpInfo.setFont(new Font("Courier", Font.BOLD, 17));  
			helpPanel.add(helpInfo);
			
			//JTextArea Styling
			helpInfo.setOpaque(true);
			helpInfo.setBackground(Color.ORANGE);
			
			closeButton = new JButton("Close Window");
			
			closeButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent ae)
				{
					if(ae.getSource().equals(closeButton))
					{
						helpFrame.dispose();
					}
				}
			}
			);
			
			helpPanel.add(closeButton, BorderLayout.SOUTH);
			
			//Gun Button Styling
			closeButton.setPreferredSize(new Dimension(150, 50));
			closeButton.setBackground(Color.BLACK);  
			closeButton.setForeground(Color.ORANGE);  
			closeButton.setFont(new Font("Courier", Font.BOLD, 20));  
			closeButton.setBorder(new LineBorder(Color.ORANGE));
			
			//Display the JFrame if Speed button is pressed.
			helpFrame.setVisible(true);			
		}
		else if(ae.getSource().equals(exitButton))
		{
			game.dispose();
			System.exit(0);
		}
	}//End of actionPerformed. DO NOT TOUCH

	private class GameOfLifeBoard extends JPanel implements ComponentListener, MouseListener, MouseMotionListener, Runnable 
	{

		//This class contains most Game of Life Components including the main board, the human interaction features and more. 

		//Generic Serial Version ID.
		private static final long serialVersionUID = 882831296469793120L;

		//Defines the Dimensions of the Game of Life Board. This is defined further down. 
		private Dimension GameOfLifeDimensions = new Dimension(getWidth()/cellSize-2, getHeight()/cellSize-2);
		
		//Array Point is the 
		private ArrayList<Point> point = new ArrayList<Point>(0);

		public GameOfLifeBoard() { 	//Game Of Life Constructor.
			//This Constructor inherits the Game Of Life Parameters. 
			//This is the first method which is initiated when the game of life is run.

			//Style Game of Life Board
			setBackground(Color.black);		//Make background of the Game Of Life black.

			addComponentListener(this);		//Calls the addComponentListenerMethod. Without this, the Game Of Life will not compile.
			addMouseListener(this);			//Calls the addMouseListener. Without this, the user cannot interact with the gameboard.
			addMouseMotionListener(this);	//Calls the addMouseMotionListener. Without this, the user cannot create a path with the mouse.

			artBoard();
			randomFill(10);

		}//End of GameOfLifeBoard. DO NOT TOUCH.        

		public void addPoint(int x, int y, MouseEvent me, boolean[][] glider, boolean[][] spaceship) 
		{
			SwingUtilities.isLeftMouseButton(me);		//
			SwingUtilities.isRightMouseButton(me);		//

			if(isGlider){	//if isGlider Boolean is true. The following array will generate at the Point of mouse click. 
							//This Array will print out the Boolean Gun based on the pre-written 9 x 36 array manually written within the Action Events method.
				for(int i=0; i < 9; ++i)     
				{  										
					for(int j=0; j < 36; ++j)
					{
						if(glider[i][j])
						{
							point.add(new Point(x + i, y + j));	//Prints out the array at the mouse press point.
						}
					}		
				}
			}

			else if(isSpaceship){	//if isSpaceship Boolean is true. The following array will generate at the Point of mouse click. 
									//This Array will print out the Boolean ship based on the pre-written 5 x 4  array manually written within the Action Events method.
				for(int k=0; k < 4; ++k)
				{
					for(int l=0; l < 5; ++l)
					{
						if(spaceship[k][l])
						{
							point.add(new Point(x + k, y + l));	//Prints out the array at the mouse press point.
						}
					}		
				}
			}

			else if (SwingUtilities.isLeftMouseButton(me))	//If Left Mouse Button is pressed on the mouse. 
			{
				point.add(new Point(x,y));	//Add Point at Mouse Press
			} 
			else if (SwingUtilities.isRightMouseButton(me))		//If Right Mouse Button is pressed on the mouse. 
			{
				point.remove(new Point(x,y));//Add Point at Mouse Press
				++deadCount;
				--survivorCount;
			}	  	
			repaint();
		} //End of AddPoint

		public void addPoint(MouseEvent me, boolean[][] glider, boolean[][] spaceship) 
		{
			int x = me.getPoint().x/cellSize-1; //Coordinate of X-axis on Mouse Press
			int y = me.getPoint().y/cellSize-1; //Coordinate of Y-axis on Mouse press
			
			if ((x >= 0) && (x < GameOfLifeDimensions.width) && (y >= 0) && (y < GameOfLifeDimensions.height))
			{
				addPoint(x,y,me,glider,spaceship);
			}
		}//End of AddPoint

		public void resetBoard() {
			point.clear();			//Remove all points (Alive Cells) from the board.
			repaint();				//Refresh the board.
		}

		public void artBoard(){
			//Gif
			//Creates image as a new Buffered Image.
			//144,32 are the dimensions of the Image.
			//TYPE_INT_RGB is the type of buffered image.
			
			//This code was discovered online and edited for compatibility with my code. below is the harvard reference:
			//Ascii Art - Java
			//In-text: ([Ascii Art - Java, 2017)
			//Ascii Art - Java. [online] Stackoverflow.com. Available at: http://stackoverflow.com/questions/7098972/ascii-art-java [Accessed 6 Mar. 2017].
			
			BufferedImage image = new BufferedImage(144, 32, BufferedImage.TYPE_INT_RGB);

			Graphics g = image.getGraphics();

			//Font of output ASCII ART.
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 24));

			Graphics2D graphics = (Graphics2D) g;

			graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

			//the Image reading.
			graphics.drawString("I love Java", 6, 24);
			try {
				ImageIO.write(image, "gif", new File("java.gif"));
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "An Error Has Occured. Please Restart the Code.");
			}

			boolean[][] gifArray = new boolean[32][144];
			for (int y = 0 ; y < 32 ; y++) 
			{
				for (int x = 0; x < 144; x++)
				{
					gifArray[y][x] = (image.getRGB(x, y) == -16777216 ? true : image.getRGB(x, y) == -1 ? false : false);
					// If this is the starting grid configuration, then do the below as well
					if (gifArray[y][x]) { point.add(new Point(x + 25,y + 20)); }
				} 
			}
		}

		public void randomFill(int percent)  //If Method is called, randomly fill the board with points.
		{		
			for (int i=0; i<GameOfLifeDimensions.width; i++) 
			{
				for (int j=0; j<GameOfLifeDimensions.height; j++) 
				{
					if (Math.random()*100 < percent) 
					{
						point.add(new Point(i,j));
					}
				}
			}
		}



		@Override
		public void paintComponent(Graphics g) 
		{
			super.paintComponent(g);
			try 
			{
				// Setup grid
				if (GameOfLife.gridVisible)
				{
					g.setColor(Color.white);
					
					repaint();
				}
				else
				{
					g.setColor(Color.red);
					repaint();
				}
				
				for (Point newPoint : point) 
				{
					//Triangle Spawn					
					g.fillOval(cellSize + (cellSize*newPoint.x), cellSize + (cellSize*newPoint.y), cellSize, cellSize);
					
					//Rectangle Spawn
					//g.fillRect(cellSize + (cellSize*newPoint.x), cellSize + (cellSize*newPoint.y), cellSize, cellSize);
				}
			} 
			catch (ConcurrentModificationException cme) {} //
			// Setup grid
			if (GameOfLife.gridVisible)
			{
				g.setColor(Color.gray);
				
				repaint();
			}
			else
			{
				g.setColor(Color.black);
				repaint();
			}
			
			//Rectangle Grid
			for (int i=0; i<=GameOfLifeDimensions.width; i++) 
			{
				g.drawLine(((i*cellSize)+cellSize), cellSize, (i*cellSize)+cellSize, cellSize + (cellSize*GameOfLifeDimensions.height) );
			}
			for (int i=0; i<=GameOfLifeDimensions.height; i++) 
			{
				g.drawLine(cellSize, ((i*cellSize)+cellSize), cellSize*(GameOfLifeDimensions.width+1), ((i*cellSize)+cellSize) );
			}
			
			updateStatisticsMenu();
		}//End of paintComponent


		private void updateStatisticsMenu() {
			
			//Asynchronously Update Statistics Menu
			
			survivorCount = point.size();
			existenceCount = point.size() + deadCount;
			
			//Generation Values
			String generationOutput = String.format("%08d%n", generationCount);
			//Sets Starting Value of Cells Alive Count
			generationLabel.setText("| Generation: " + generationOutput + " |");
			
			//Survivor Values
			String cellOutput = String.format("%08d%n", survivorCount);
			//Sets Starting Value of Cells Alive Count
			survivorLabel.setText("| Cells Alive: " + cellOutput + " |");
			
			//Dead Values
			String deadOutput = String.format("%08d%n", deadCount);
			//Sets Starting Value of existence Count
			deadLabel.setText("| Cells Dead: " + deadOutput + " |");
			
			//Existing Values
			String existOutput = String.format("%08d%n", existenceCount);
			//Sets Starting Value of existence Count
			existLabel.setText("| Cells Total: " + existOutput + " |");
			
		}

		@Override
		public void componentResized(ComponentEvent e) 
		{
			// Setup the game board size with proper boundaries
			GameOfLifeDimensions = new Dimension(getWidth()/cellSize-2, getHeight()/cellSize-2);
			// Add function to check if cells are out of "bounds".
		}

		//DO NOT TOUCH
		@Override
		public void run() 
		{	
			boolean[][] GameOfLifeBoard = new boolean[GameOfLifeDimensions.width][GameOfLifeDimensions.height];
			
			try{
					for (Point current : point) 
					{
						GameOfLifeBoard[current.x][current.y] = true;
					}
			} catch(ArrayIndexOutOfBoundsException ae)
			{
				
			}
			
			ArrayList<Point> survivingCells = new ArrayList<Point>(0);
			// Iterate through the array, follow game of life rules
			for (int i=0; i<GameOfLifeBoard.length; i++) 
			{
				for (int j=0; j<GameOfLifeBoard[0].length; j++) 
				{
					int n = wraparoundloop(GameOfLifeBoard, i, j);

					if(hopeitWorks(n, GameOfLifeBoard[i][j]))
					{
						survivingCells.add(new Point(i, j));
					}
				}
			}
			resetBoard();
			generationCount++;
			survivorCount = survivingCells.size();
			point.addAll(survivingCells);
			repaint();          
		}//End of Run. DO NOT TOUCH.

		public int wraparoundloop(boolean[][] GameOfLifeBoard, int X, int Y){
			//ternary statement
			int value = GameOfLifeBoard[X][Y] ? -1 : 0;
			int rows = GameOfLifeBoard.length;
			int columns = GameOfLifeBoard[0].length;

			for (int a = -1; a <= 1; ++a)
			{
				for(int b = -1; b <= 1; ++b)
				{
					if( GameOfLifeBoard[(rows + X + a) % rows][(columns + Y + b) % columns]){value++;}
				}
			}
			return value;
		}//End of Wrap around Loop. DO NOT TOUCH.

		public boolean hopeitWorks(int n, boolean alive){
			if(alive && (n == 2 || n == 3)){
				return true;
			}
			else if(!alive && (n == 3)){
				++deadCount; 
				return true;
			}
			else return false;
		}//End of Hope it works. DO NOT TOUCH.

		@Override
		public void componentMoved(ComponentEvent e) {
			//This method is unnecessary but needed within the code for compilation.
		}

		@Override
		public void componentShown(ComponentEvent e) {
			//This method is unnecessary but needed within the code for compilation.
		}

		@Override
		public void componentHidden(ComponentEvent e) {
			//This method is unnecessary but needed within the code for compilation.
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			//This method is unnecessary but needed within the code for compilation.
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// Mouse was released (user clicked)
			    addPoint(e,GameOfLife.glider,GameOfLife.spaceship);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			//This method is unnecessary but needed within the code for compilation. 
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			//This method is unnecessary but needed within the code for compilation.
		}

		@Override
		public void mouseExited(MouseEvent e) {
			//This method is unnecessary but needed within the code for compilation.
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// Mouse is being dragged, user wants multiple selections
			try
			{
			    addPoint(e,GameOfLife.glider,GameOfLife.spaceship);
			}
			catch(ArrayIndexOutOfBoundsException ae)
			{
				JOptionPane.showMessageDialog(null, "An array cannot be printed outside the frame.");
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			//This method is unnecessary but needed within the code for compilation.
		}


	}//End of GameOfLifeBoard. DO NOT TOUCH

}//End of GameOfLife Class. DO NOT TOUCH
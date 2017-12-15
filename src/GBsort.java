import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GBsort implements ActionListener{

	private JFrame window;		// the main window
	private JPanel header;		// the header panel
	private JPanel panel;		// the center panel	
	private JPanel bot_panel;	// panel for button holder
	private JPanel btn_holder;	// panel for buttons
	private JButton reset;		// reset button		
	private JButton start;		// play/pause button
	private JButton generate;	// generate random numbers button
	private JLabel header_txt;	// header text
	private JTextArea[] txt;	// bucket array
	private JLabel[] value;		// the label for the moving value
	private JLabel[] value_static;	// the label for the non-moving value
	private JLabel[] lbl;		// the label for the buckets
	private Timer animate;		//  timer for the animation
	private Random rand;
	private int boundsX, boundsY, move = 0, x, y;
	private int animSpeed = 1; // animation speed
	private int[] rand_val;		// stores the random generated values

	public GBsort(){
		init();
	}

	public void init(){
		
		window = new JFrame("animate");

		header = new JPanel();
		panel = new JPanel();
		bot_panel = new JPanel();
		btn_holder = new JPanel();

		reset = new JButton("RESET");
		start = new JButton("PLAY");
		generate = new JButton("GENERATE RANDOM NUMBERS");
		
		txt = new JTextArea[10];
		lbl = new JLabel[10];
		value = new JLabel[10];
		value_static = new JLabel[10];
		header_txt = new JLabel("GENERAL BUCKET SORT", JLabel.CENTER);

		rand = new Random();
		rand_val = new int[10];

		animate = new Timer(animSpeed, new TimerAction());

		window.setLayout(null);
		window.setSize(1080, 600);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(header);
		window.add(panel);

		header.setLayout(null);
		header.setBackground(new Color(255,190,190));
		header.setBounds(0,0,1075,50);
		header.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		header.add(header_txt);

		panel.setLayout(null);
		panel.setBounds(0, 50, 1080, 550);
		panel.setBackground(new Color(190, 255, 204));
		panel.add(bot_panel);
		
		bot_panel.setLayout(new BorderLayout());
		bot_panel.setBounds(25,405,1030,110);
		bot_panel.setBorder(BorderFactory.createEtchedBorder(1));
		bot_panel.add(btn_holder, BorderLayout.CENTER);
		bot_panel.add(generate, BorderLayout.SOUTH);

		generate.addActionListener(this);

		btn_holder.setLayout(new GridLayout(1,2));
		btn_holder.add(start);
		btn_holder.add(reset);

		header_txt.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		header_txt.setFont(new Font("Arial", 1, 28));
		header_txt.setBounds(35,5,1005,40);

		start.setBounds(400,440,130,60);
		start.addActionListener(this);

		reset.setBounds(540,440,130,60);
		reset.addActionListener(this);

		for(int ctr = 0; ctr < 10; ctr++){

			rand_val[ctr] = rand.nextInt(1000);
			value[ctr] = new JLabel(Integer.toString(rand_val[ctr]), JLabel.CENTER);
			value_static[ctr] = new JLabel(value[ctr].getText(), JLabel.CENTER);

			panel.add(value[ctr]);
			panel.add(value_static[ctr]);

			value[ctr].setBounds((ctr+1)*90,60,80,50);
			value[ctr].setFont(new Font("Consolas", 1, 26));
			value[ctr].setBorder(BorderFactory.createLineBorder(Color.BLACK));

			value_static[ctr].setBounds((ctr+1)*90,60,80,50);
			value_static[ctr].setFont(new Font("Consolas", 1, 26));
			value_static[ctr].setBorder(BorderFactory.createLineBorder(Color.BLACK));

		}
		
		for(int ctr = 0; ctr < 10; ctr++){

			txt[ctr] = new JTextArea("");
			txt[ctr].setBounds((ctr+1)*90,210,80,140);
			txt[ctr].setFont(new Font("Consolas",1,30));
			txt[ctr].setLineWrap(true);
			txt[ctr].setEditable(false);
			txt[ctr].setBorder(BorderFactory.createLineBorder(Color.BLACK));

			panel.add(txt[ctr]);

		}

		for(int ctr = 0; ctr < 10; ctr++){

			lbl[ctr] = new JLabel(Integer.toString(ctr+1), JLabel.CENTER);
			lbl[ctr].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			lbl[ctr].setFont(new Font("Arial", 1, 28));
			lbl[ctr].setBounds((ctr+1)*90,350,80,50);
			
			panel.add(lbl[ctr]);

		}

		window.setVisible(true);
	}

	@Override 
	public void actionPerformed(ActionEvent ae){

		if(ae.getSource() == start){	// if play/pause button is clicked
			if(!animate.isRunning()){	// animation is not running

				start.setText("PAUSE");

				switch(move){
					case 0:
						move = 0;
						break;
					case 1:
						move = 1;
						break;

					case 2:
						move = 2;
						break;

					case 3:
						move = 3;
						break;

					case 4:
						move = 4;
						break;

					case 5:
						move = 5;
						break;

					case 6:
						move = 6;
						break;

					case 7:
						move = 7;
						break;

					case 8:
						move = 8;
						break;

					case 9:
						move = 9;
						break;	

					default:
						move = 0;
				}

				animate.start(); // continue the animation

			} else if(animate.isRunning()){ // if animation is running and play/pause button is clicked
				start.setText("PLAY");		// stop the animation
				animate.stop();
			}

		} else if(ae.getSource() == reset){ // if reset button is clicked

			move = 0;					//reset move to 0
			
			if(animate.isRunning()){	// if animation is running
				animate.stop();			// stop animation
			}

			start.setText("PLAY");
			
			for(int ctr = 0; ctr < 10; ctr++){
				panel.add(txt[ctr]);	// add the buckets
				panel.add(value[ctr]);	// add the label that moves

				txt[ctr].setText("");	// clear the buckets
				value[ctr].setBounds(value_static[ctr].getX(), value_static[ctr].getY(), 80, 50);
				value_static[ctr].setText(Integer.toString(rand_val[ctr]));
			}
		} else if(ae.getSource() == generate){ // if generate button is clicked

			if(animate.isRunning()){	// if animation is running
				animate.stop();			// stop animation
				start.setText("PLAY");
			}

			move = 0;					// reset move to 0

			for(int ctr = 0; ctr < 10; ctr++){

				rand_val[ctr] = rand.nextInt(1000); // generate new random values
				panel.add(txt[ctr]);				// add buckets
				panel.add(value[ctr]);				// add the label that moves
				value[ctr].setText(Integer.toString(rand_val[ctr]));	// set the label to random
				value[ctr].setBounds(value_static[ctr].getX(), value_static[ctr].getY(), 80, 50);
				value_static[ctr].setText(Integer.toString(rand_val[ctr]));
				txt[ctr].setText("");
			}
		}
	}
	
	class TimerAction implements ActionListener{

		public void actionPerformed(ActionEvent ae){

				if(move == 0){	// if move = 0
								// move the first box

					if(value[move].getY() < txt[move].getY()-50){  	// move the box down
						value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);

					} else {										// find the appropriate destination
						
						if(Integer.valueOf(value[move].getText()) >= 0 && Integer.valueOf(value[move].getText()) <= 99){
							if(value[move].getY() < txt[move].getY()){
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[0].getY()){
									txt[0].setText(value[move].getText() + "\n" + txt[0].getText());
									panel.remove(value[move]);
									move = 1;
								}
							}
						}if(Integer.valueOf(value[move].getText()) >= 100 && Integer.valueOf(value[move].getText()) <= 199){
							if(value[move].getX() < txt[1].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[1].getY()){
									txt[1].setText(value[move].getText() + "\n" + txt[1].getText());
									panel.remove(value[move]);
									move = 1;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 200 && Integer.valueOf(value[move].getText()) <= 299){
							if(value[move].getX() < txt[2].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[2].getY()){
									txt[2].setText(value[move].getText() + "\n" + txt[2].getText());
									panel.remove(value[move]);
									move = 1;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 300 && Integer.valueOf(value[move].getText()) <= 399){
							if(value[move].getX() < txt[3].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[3].getY()){
									txt[3].setText(value[move].getText() + "\n" + txt[3].getText());
									panel.remove(value[move]);
									move = 1;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 400 && Integer.valueOf(value[move].getText()) <= 499){
							if(value[move].getX() < txt[4].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[4].getY()){
									txt[4].setText(value[move].getText() + "\n" + txt[4].getText());
									panel.remove(value[move]);
									move = 1;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 500 && Integer.valueOf(value[move].getText()) <= 599){
							if(value[move].getX() < txt[5].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[5].getY()){
									txt[5].setText(value[move].getText() + "\n" + txt[5].getText());
									panel.remove(value[move]);
									move = 1;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 600 && Integer.valueOf(value[move].getText()) <= 699){
							if(value[move].getX() < txt[6].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[6].getY()){
									txt[6].setText(value[move].getText() + "\n" + txt[6].getText());
									panel.remove(value[move]);
									move = 1;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 700 && Integer.valueOf(value[move].getText()) <= 799){
							if(value[move].getX() < txt[7].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[7].getY()){
									txt[7].setText(value[move].getText() + "\n" + txt[7].getText());
									panel.remove(value[move]);
									move = 1;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 800 && Integer.valueOf(value[move].getText()) <= 899){
							if(value[move].getX() < txt[8].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[8].getY()){
									txt[8].setText(value[move].getText() + "\n" + txt[8].getText());
									panel.remove(value[move]);
									move = 1;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 900 && Integer.valueOf(value[move].getText()) <= 1000){
							if(value[move].getX() < txt[9].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[9].getY()){
									txt[9].setText(value[move].getText() + "\n" + txt[9].getText());
									panel.remove(value[move]);
									move = 1;
								}
							}
						}
					} // move = 0
					
				} else if(move == 1){ // if move = 1
									  // move the second box

					if(value[move].getY() < txt[move].getY()-50){	// move the box down
						value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);

					} else {										// find the appropriate destination

						if(Integer.valueOf(value[move].getText()) >= 0 && Integer.valueOf(value[move].getText()) <= 99){
							if(value[move].getX() > txt[0].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[0].getY()){
									txt[0].setText(value[move].getText() + "\n" + txt[0].getText());
									panel.remove(value[move]);
									move = 2;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 100 && Integer.valueOf(value[move].getText()) <= 199){
							if(value[move].getY() < txt[1].getY()){
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[1].getY()){
									txt[1].setText(value[move].getText() + "\n" + txt[1].getText());
									panel.remove(value[move]);
									move = 2;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 200 && Integer.valueOf(value[move].getText()) <= 299){
							if(value[move].getX() < txt[2].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[2].getY()){
									txt[2].setText(value[move].getText() + "\n" + txt[2].getText());
									panel.remove(value[move]);
									move = 2;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 300 && Integer.valueOf(value[move].getText()) <= 399){
							if(value[move].getX() < txt[3].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[3].getY()){
									txt[3].setText(value[move].getText() + "\n" + txt[3].getText());
									panel.remove(value[move]);
									move = 2;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 400 && Integer.valueOf(value[move].getText()) <= 499){
							if(value[move].getX() < txt[4].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[4].getY()){
									txt[4].setText(value[move].getText() + "\n" + txt[4].getText());
									panel.remove(value[move]);
									move = 2;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 500 && Integer.valueOf(value[move].getText()) <= 599){
							if(value[move].getX() < txt[5].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[5].getY()){
									txt[5].setText(value[move].getText() + "\n" + txt[5].getText());
									panel.remove(value[move]);
									move = 2;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 600 && Integer.valueOf(value[move].getText()) <= 699){
							if(value[move].getX() < txt[6].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[6].getY()){
									txt[6].setText(value[move].getText() + "\n" + txt[6].getText());
									panel.remove(value[move]);
									move = 2;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 700 && Integer.valueOf(value[move].getText()) <= 799){
							if(value[move].getX() < txt[7].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[7].getY()){
									txt[7].setText(value[move].getText() + "\n" + txt[7].getText());
									panel.remove(value[move]);
									move = 2;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 800 && Integer.valueOf(value[move].getText()) <= 899){
							if(value[move].getX() < txt[8].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[8].getY()){
									txt[8].setText(value[move].getText() + "\n" + txt[8].getText());
									panel.remove(value[move]);
									move = 2;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 900 && Integer.valueOf(value[move].getText()) <= 1000){
							if(value[move].getX() < txt[9].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[9].getY()){
									txt[9].setText(value[move].getText() + "\n" + txt[9].getText());
									panel.remove(value[move]);
									move = 2;
								}
							}
						}
					} //move = 1
						
				} else if(move == 2){ // if move = 2
									  // move the third box

					if(value[move].getY() < txt[move].getY()-50){	// move the box down
						value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);

					} else {										// find the appropriate destination

						if(Integer.valueOf(value[move].getText()) >= 0 && Integer.valueOf(value[move].getText()) <= 99){
							if(value[move].getX() > txt[0].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[0].getY()){
									txt[0].setText(value[move].getText() + "\n" + txt[0].getText());
									panel.remove(value[move]);
									move = 3;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 100 && Integer.valueOf(value[move].getText()) <= 199){
							if(value[move].getX() > txt[1].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[1].getY()){
									txt[1].setText(value[move].getText() + "\n" + txt[1].getText());
									panel.remove(value[move]);
									move = 3;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 200 && Integer.valueOf(value[move].getText()) <= 299){
							if(value[move].getY() < txt[2].getY()){
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[2].getY()){
									txt[2].setText(value[move].getText() + "\n" + txt[2].getText());
									panel.remove(value[move]);
									move = 3;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 300 && Integer.valueOf(value[move].getText()) <= 399){
							if(value[move].getX() < txt[3].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[3].getY()){
									txt[3].setText(value[move].getText() + "\n" + txt[3].getText());
									panel.remove(value[move]);
									move = 3;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 400 && Integer.valueOf(value[move].getText()) <= 499){
							if(value[move].getX() < txt[4].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[4].getY()){
									txt[4].setText(value[move].getText() + "\n" + txt[4].getText());
									panel.remove(value[move]);
									move = 3;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 500 && Integer.valueOf(value[move].getText()) <= 599){
							if(value[move].getX() < txt[5].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[5].getY()){
									txt[5].setText(value[move].getText() + "\n" + txt[5].getText());
									panel.remove(value[move]);
									move = 3;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 600 && Integer.valueOf(value[move].getText()) <= 699){
							if(value[move].getX() < txt[6].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[6].getY()){
									txt[6].setText(value[move].getText() + "\n" + txt[6].getText());
									panel.remove(value[move]);
									move = 3;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 700 && Integer.valueOf(value[move].getText()) <= 799){
							if(value[move].getX() < txt[7].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[7].getY()){
									txt[7].setText(value[move].getText() + "\n" + txt[7].getText());
									panel.remove(value[move]);
									move = 3;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 800 && Integer.valueOf(value[move].getText()) <= 899){
							if(value[move].getX() < txt[8].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[8].getY()){
									txt[8].setText(value[move].getText() + "\n" + txt[8].getText());
									panel.remove(value[move]);
									move = 3;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 900 && Integer.valueOf(value[move].getText()) <= 1000){
							if(value[move].getX() < txt[9].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[9].getY()){
									txt[9].setText(value[move].getText() + "\n" + txt[9].getText());
									panel.remove(value[move]);
									move = 3;
								}
							}
						}
					} // move = 2

				} else if(move == 3){	//the 4th box


					if(value[move].getY() < txt[move].getY()-50){	// move the box down
						value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);

					} else {										// find the appropriate destination

						if(Integer.valueOf(value[move].getText()) >= 0 && Integer.valueOf(value[move].getText()) <= 99){
							if(value[move].getX() > txt[0].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[0].getY()){
									txt[0].setText(value[move].getText() + "\n" + txt[0].getText());
									panel.remove(value[move]);
									move = 4;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 100 && Integer.valueOf(value[move].getText()) <= 199){
							if(value[move].getX() > txt[1].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[1].getY()){
									txt[1].setText(value[move].getText() + "\n" + txt[1].getText());
									panel.remove(value[move]);
									move = 4;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 200 && Integer.valueOf(value[move].getText()) <= 299){
							if(value[move].getX() > txt[2].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[2].getY()){
									txt[2].setText(value[move].getText() + "\n" + txt[2].getText());
									panel.remove(value[move]);
									move = 4;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 300 && Integer.valueOf(value[move].getText()) <= 399){
							if(value[move].getY() < txt[3].getY()){
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[3].getY()){
									txt[3].setText(value[move].getText() + "\n" + txt[3].getText());
									panel.remove(value[move]);
									move = 4;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 400 && Integer.valueOf(value[move].getText()) <= 499){
							if(value[move].getX() < txt[4].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[4].getY()){
									txt[4].setText(value[move].getText() + "\n" + txt[4].getText());
									panel.remove(value[move]);
									move = 4;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 500 && Integer.valueOf(value[move].getText()) <= 599){
							if(value[move].getX() < txt[5].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[5].getY()){
									txt[5].setText(value[move].getText() + "\n" + txt[5].getText());
									panel.remove(value[move]);
									move = 4;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 600 && Integer.valueOf(value[move].getText()) <= 699){
							if(value[move].getX() < txt[6].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[6].getY()){
									txt[6].setText(value[move].getText() + "\n" + txt[6].getText());
									panel.remove(value[move]);
									move = 4;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 700 && Integer.valueOf(value[move].getText()) <= 799){
							if(value[move].getX() < txt[7].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[7].getY()){
									txt[7].setText(value[move].getText() + "\n" + txt[7].getText());
									panel.remove(value[move]);
									move = 4;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 800 && Integer.valueOf(value[move].getText()) <= 899){
							if(value[move].getX() < txt[8].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[8].getY()){
									txt[8].setText(value[move].getText() + "\n" + txt[8].getText());
									panel.remove(value[move]);
									move = 4;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 900 && Integer.valueOf(value[move].getText()) <= 1000){
							if(value[move].getX() < txt[9].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[9].getY()){
									txt[9].setText(value[move].getText() + "\n" + txt[9].getText());
									panel.remove(value[move]);
									move = 4;
								}
							}
						}
					} // move = 3

				} else if(move == 4){	// move the 5th box


					if(value[move].getY() < txt[move].getY()-50){	// move the box down
						value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);

					} else {										// find the appropriate destination

						if(Integer.valueOf(value[move].getText()) >= 0 && Integer.valueOf(value[move].getText()) <= 99){
							if(value[move].getX() > txt[0].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[0].getY()){
									txt[0].setText(value[move].getText() + "\n" + txt[0].getText());
									panel.remove(value[move]);
									move = 5;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 100 && Integer.valueOf(value[move].getText()) <= 199){
							if(value[move].getX() > txt[1].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[1].getY()){
									txt[1].setText(value[move].getText() + "\n" + txt[1].getText());
									panel.remove(value[move]);
									move = 5;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 200 && Integer.valueOf(value[move].getText()) <= 299){
							if(value[move].getX() > txt[2].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[2].getY()){
									txt[2].setText(value[move].getText() + "\n" + txt[2].getText());
									panel.remove(value[move]);
									move = 5;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 300 && Integer.valueOf(value[move].getText()) <= 399){
							if(value[move].getX() > txt[3].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[3].getY()){
									txt[3].setText(value[move].getText() + "\n" + txt[3].getText());
									panel.remove(value[move]);
									move = 5;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 400 && Integer.valueOf(value[move].getText()) <= 499){
							if(value[move].getY() < txt[4].getY()){
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[4].getY()){
									txt[4].setText(value[move].getText() + "\n" + txt[4].getText());
									panel.remove(value[move]);
									move = 5;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 500 && Integer.valueOf(value[move].getText()) <= 599){
							if(value[move].getX() < txt[5].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[5].getY()){
									txt[5].setText(value[move].getText() + "\n" + txt[5].getText());
									panel.remove(value[move]);
									move = 5;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 600 && Integer.valueOf(value[move].getText()) <= 699){
							if(value[move].getX() < txt[6].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[6].getY()){
									txt[6].setText(value[move].getText() + "\n" + txt[6].getText());
									panel.remove(value[move]);
									move = 5;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 700 && Integer.valueOf(value[move].getText()) <= 799){
							if(value[move].getX() < txt[7].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[7].getY()){
									txt[7].setText(value[move].getText() + "\n" + txt[7].getText());
									panel.remove(value[move]);
									move = 5;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 800 && Integer.valueOf(value[move].getText()) <= 899){
							if(value[move].getX() < txt[8].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[8].getY()){
									txt[8].setText(value[move].getText() + "\n" + txt[8].getText());
									panel.remove(value[move]);
									move = 5;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 900 && Integer.valueOf(value[move].getText()) <= 1000){
							if(value[move].getX() < txt[9].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[9].getY()){
									txt[9].setText(value[move].getText() + "\n" + txt[9].getText());
									panel.remove(value[move]);
									move = 5;
								}
							}
						}
					} // move = 4

				} else if(move == 5){ // move the 6th box


					if(value[move].getY() < txt[move].getY()-50){	// move the box down
						value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);

					} else {										// find the appropriate destination

						if(Integer.valueOf(value[move].getText()) >= 0 && Integer.valueOf(value[move].getText()) <= 99){
							if(value[move].getX() > txt[0].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[0].getY()){
									txt[0].setText(value[move].getText() + "\n" + txt[0].getText());
									panel.remove(value[move]);
									move = 6;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 100 && Integer.valueOf(value[move].getText()) <= 199){
							if(value[move].getX() > txt[1].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[1].getY()){
									txt[1].setText(value[move].getText() + "\n" + txt[1].getText());
									panel.remove(value[move]);
									move = 6;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 200 && Integer.valueOf(value[move].getText()) <= 299){
							if(value[move].getX() > txt[2].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[2].getY()){
									txt[2].setText(value[move].getText() + "\n" + txt[2].getText());
									panel.remove(value[move]);
									move = 6;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 300 && Integer.valueOf(value[move].getText()) <= 399){
							if(value[move].getX() > txt[3].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[3].getY()){
									txt[3].setText(value[move].getText() + "\n" + txt[3].getText());
									panel.remove(value[move]);
									move = 6;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 400 && Integer.valueOf(value[move].getText()) <= 499){
							if(value[move].getX() > txt[4].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[4].getY()){
									txt[4].setText(value[move].getText() + "\n" + txt[4].getText());
									panel.remove(value[move]);
									move = 6;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 500 && Integer.valueOf(value[move].getText()) <= 599){
							if(value[move].getY() < txt[5].getY()){
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[5].getY()){
									txt[5].setText(value[move].getText() + "\n" + txt[5].getText());
									panel.remove(value[move]);
									move = 6;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 600 && Integer.valueOf(value[move].getText()) <= 699){
							if(value[move].getX() < txt[6].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[6].getY()){
									txt[6].setText(value[move].getText() + "\n" + txt[6].getText());
									panel.remove(value[move]);
									move = 6;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 700 && Integer.valueOf(value[move].getText()) <= 799){
							if(value[move].getX() < txt[7].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[7].getY()){
									txt[7].setText(value[move].getText() + "\n" + txt[7].getText());
									panel.remove(value[move]);
									move = 6;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 800 && Integer.valueOf(value[move].getText()) <= 899){
							if(value[move].getX() < txt[8].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[8].getY()){
									txt[8].setText(value[move].getText() + "\n" + txt[8].getText());
									panel.remove(value[move]);
									move = 6;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 900 && Integer.valueOf(value[move].getText()) <= 1000){
							if(value[move].getX() < txt[9].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[9].getY()){
									txt[9].setText(value[move].getText() + "\n" + txt[9].getText());
									panel.remove(value[move]);
									move = 6;
								}
							}
						}
					} // move = 5

				} else if(move == 6){ // move the seventh box


					if(value[move].getY() < txt[move].getY()-50){	// move the box down
						value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);

					} else {										// find the appropriate destination

						if(Integer.valueOf(value[move].getText()) >= 0 && Integer.valueOf(value[move].getText()) <= 99){
							if(value[move].getX() > txt[0].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[0].getY()){
									txt[0].setText(value[move].getText() + "\n" + txt[0].getText());
									panel.remove(value[move]);
									move = 7;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 100 && Integer.valueOf(value[move].getText()) <= 199){
							if(value[move].getX() > txt[1].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[1].getY()){
									txt[1].setText(value[move].getText() + "\n" + txt[1].getText());
									panel.remove(value[move]);
									move = 7;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 200 && Integer.valueOf(value[move].getText()) <= 299){
							if(value[move].getX() > txt[2].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[2].getY()){
									txt[2].setText(value[move].getText() + "\n" + txt[2].getText());
									panel.remove(value[move]);
									move = 7;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 300 && Integer.valueOf(value[move].getText()) <= 399){
							if(value[move].getX() > txt[3].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[3].getY()){
									txt[3].setText(value[move].getText() + "\n" + txt[3].getText());
									panel.remove(value[move]);
									move = 7;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 400 && Integer.valueOf(value[move].getText()) <= 499){
							if(value[move].getX() > txt[4].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[4].getY()){
									txt[4].setText(value[move].getText() + "\n" + txt[4].getText());
									panel.remove(value[move]);
									move = 7;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 500 && Integer.valueOf(value[move].getText()) <= 599){
							if(value[move].getX() > txt[5].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[5].getY()){
									txt[5].setText(value[move].getText() + "\n" + txt[5].getText());
									panel.remove(value[move]);
									move = 7;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 600 && Integer.valueOf(value[move].getText()) <= 699){
							if(value[move].getY() < txt[6].getY()){
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[6].getY()){
									txt[6].setText(value[move].getText() + "\n" + txt[6].getText());
									panel.remove(value[move]);
									move = 7;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 700 && Integer.valueOf(value[move].getText()) <= 799){
							if(value[move].getX() < txt[7].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[7].getY()){
									txt[7].setText(value[move].getText() + "\n" + txt[7].getText());
									panel.remove(value[move]);
									move = 7;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 800 && Integer.valueOf(value[move].getText()) <= 899){
							if(value[move].getX() < txt[8].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[8].getY()){
									txt[8].setText(value[move].getText() + "\n" + txt[8].getText());
									panel.remove(value[move]);
									move = 7;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 900 && Integer.valueOf(value[move].getText()) <= 1000){
							if(value[move].getX() < txt[9].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[9].getY()){
									txt[9].setText(value[move].getText() + "\n" + txt[9].getText());
									panel.remove(value[move]);
									move = 7;
								}
							}
						}
					} // move = 6	

				} else if(move == 7){ // move the 8th box


					if(value[move].getY() < txt[move].getY()-50){	// move the box down
						value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);

					} else {										// find the appropriate destination

						if(Integer.valueOf(value[move].getText()) >= 0 && Integer.valueOf(value[move].getText()) <= 99){
							if(value[move].getX() > txt[0].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[0].getY()){
									txt[0].setText(value[move].getText()  + "\n" + txt[0].getText());
									panel.remove(value[move]);
									move = 8;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 100 && Integer.valueOf(value[move].getText()) <= 199){
							if(value[move].getX() > txt[1].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[1].getY()){
									txt[1].setText(value[move].getText()  + "\n" + txt[1].getText());
									panel.remove(value[move]);
									move = 8;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 200 && Integer.valueOf(value[move].getText()) <= 299){
							if(value[move].getX() > txt[2].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[2].getY()){
									txt[2].setText(value[move].getText()  + "\n" + txt[2].getText());
									panel.remove(value[move]);
									move = 8;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 300 && Integer.valueOf(value[move].getText()) <= 399){
							if(value[move].getX() > txt[3].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[3].getY()){
									txt[3].setText(value[move].getText()  + "\n" + txt[3].getText());
									panel.remove(value[move]);
									move = 8;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 400 && Integer.valueOf(value[move].getText()) <= 499){
							if(value[move].getX() > txt[4].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[4].getY()){
									txt[4].setText(value[move].getText()  + "\n" + txt[4].getText());
									panel.remove(value[move]);
									move = 8;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 500 && Integer.valueOf(value[move].getText()) <= 599){
							if(value[move].getX() > txt[5].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[5].getY()){
									txt[5].setText(value[move].getText() + "\n" + txt[5].getText());
									panel.remove(value[move]);
									move = 8;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 600 && Integer.valueOf(value[move].getText()) <= 699){
							if(value[move].getX() > txt[6].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[6].getY()){
									txt[6].setText(value[move].getText()  + "\n" + txt[6].getText());
									panel.remove(value[move]);
									move = 8;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 700 && Integer.valueOf(value[move].getText()) <= 799){
							if(value[move].getY() < txt[7].getY()){
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[7].getY()){
									txt[7].setText(value[move].getText()  + "\n" + txt[7].getText());
									panel.remove(value[move]);
									move = 8;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 800 && Integer.valueOf(value[move].getText()) <= 899){
							if(value[move].getX() < txt[8].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[8].getY()){
									txt[8].setText(value[move].getText() + "\n" + txt[8].getText());
									panel.remove(value[move]);
									move = 8;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 900 && Integer.valueOf(value[move].getText()) <= 1000){
							if(value[move].getX() < txt[9].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[9].getY()){
									txt[9].setText(value[move].getText()  + "\n" + txt[9].getText());
									panel.remove(value[move]);
									move = 8;
								}
							}
						}
					} // move = 7	

				} else if(move == 8){ //move the 9th box

					if(value[move].getY() < txt[move].getY()-50){	// move the box down
						value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);

					} else {										// find the appropriate destination

						if(Integer.valueOf(value[move].getText()) >= 0 && Integer.valueOf(value[move].getText()) <= 99){
							if(value[move].getX() > txt[0].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[0].getY()){
									txt[0].setText(value[move].getText()  + "\n" + txt[0].getText());
									panel.remove(value[move]);
									move = 9;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 100 && Integer.valueOf(value[move].getText()) <= 199){
							if(value[move].getX() > txt[1].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[1].getY()){
									txt[1].setText(value[move].getText()  + "\n" + txt[1].getText());
									panel.remove(value[move]);
									move = 9;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 200 && Integer.valueOf(value[move].getText()) <= 299){
							if(value[move].getX() > txt[2].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[2].getY()){
									txt[2].setText(value[move].getText()  + "\n" + txt[2].getText());
									panel.remove(value[move]);
									move = 9;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 300 && Integer.valueOf(value[move].getText()) <= 399){
							if(value[move].getX() > txt[3].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[3].getY()){
									txt[3].setText(value[move].getText()  + "\n" + txt[3].getText());
									panel.remove(value[move]);
									move = 9;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 400 && Integer.valueOf(value[move].getText()) <= 499){
							if(value[move].getX() > txt[4].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[4].getY()){
									txt[4].setText(value[move].getText()  + "\n" + txt[4].getText());
									panel.remove(value[move]);
									move = 9;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 500 && Integer.valueOf(value[move].getText()) <= 599){
							if(value[move].getX() > txt[5].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[5].getY()){
									txt[5].setText(value[move].getText()  + "\n" + txt[5].getText());
									panel.remove(value[move]);
									move = 9;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 600 && Integer.valueOf(value[move].getText()) <= 699){
							if(value[move].getX() > txt[6].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[6].getY()){
									txt[6].setText(value[move].getText()  + "\n" + txt[6].getText());
									panel.remove(value[move]);
									move = 9;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 700 && Integer.valueOf(value[move].getText()) <= 799){
							if(value[move].getX() > txt[7].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[7].getY()){
									txt[7].setText(value[move].getText()  + "\n" + txt[7].getText());
									panel.remove(value[move]);
									move = 9;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 800 && Integer.valueOf(value[move].getText()) <= 899){
							if(value[move].getY() < txt[8].getY()){
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[8].getY()){
									txt[8].setText(value[move].getText()  + "\n" + txt[8].getText());
									panel.remove(value[move]);
									move = 9;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 900 && Integer.valueOf(value[move].getText()) <= 1000){
							if(value[move].getX() < txt[9].getX()){
								value[move].setBounds(value[move].getX()+1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[9].getY()){
									txt[9].setText(value[move].getText()  + "\n" + txt[9].getText());
									panel.remove(value[move]);
									move = 9;
								}
							}
						}
					} // move 8

				} else if(move == 9){ // move the last box

					if(value[move].getY() < txt[move].getY()-50){	// move the box down
						value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);

					} else {										// find the appropriate destination

						if(Integer.valueOf(value[move].getText()) >= 0 && Integer.valueOf(value[move].getText()) <= 99){
							if(value[move].getX() > txt[0].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[0].getY()){
									txt[0].setText(value[move].getText()  + "\n" + txt[0].getText());
									panel.remove(value[move]);
									move = 10;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 100 && Integer.valueOf(value[move].getText()) <= 199){
							if(value[move].getX() > txt[1].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[1].getY()){
									txt[1].setText(value[move].getText()  + "\n" + txt[1].getText());
									panel.remove(value[move]);
									move = 10;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 200 && Integer.valueOf(value[move].getText()) <= 299){
							if(value[move].getX() > txt[2].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[2].getY()){
									txt[2].setText(value[move].getText()  + "\n" + txt[2].getText());
									panel.remove(value[move]);
									move = 10;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 300 && Integer.valueOf(value[move].getText()) <= 399){
							if(value[move].getX() > txt[3].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[3].getY()){
									txt[3].setText(value[move].getText()  + "\n" + txt[3].getText());
									panel.remove(value[move]);
									move = 10;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 400 && Integer.valueOf(value[move].getText()) <= 499){
							if(value[move].getX() > txt[4].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[4].getY()){
									txt[4].setText(value[move].getText()  + "\n" + txt[4].getText());
									panel.remove(value[move]);
									move = 10;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 500 && Integer.valueOf(value[move].getText()) <= 599){
							if(value[move].getX() > txt[5].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[5].getY()){
									txt[5].setText(value[move].getText()  + "\n" + txt[5].getText());
									panel.remove(value[move]);
									move = 10;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 600 && Integer.valueOf(value[move].getText()) <= 699){
							if(value[move].getX() > txt[6].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[6].getY()){
									txt[6].setText(value[move].getText()  + "\n" + txt[6].getText());
									panel.remove(value[move]);
									move = 10;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 700 && Integer.valueOf(value[move].getText()) <= 799){
							if(value[move].getX() > txt[7].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[7].getY()){
									txt[7].setText(value[move].getText()  + "\n" + txt[7].getText());
									panel.remove(value[move]);
									move = 10;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 800 && Integer.valueOf(value[move].getText()) <= 899){
							if(value[move].getX() > txt[8].getX()){
								value[move].setBounds(value[move].getX()-1, value[move].getY(), 80, 50);
							} else {
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[8].getY()){
									txt[8].setText(value[move].getText()  + "\n" + txt[8].getText());
									panel.remove(value[move]);
									move = 10;
								}
							}
						} else if(Integer.valueOf(value[move].getText()) >= 900 && Integer.valueOf(value[move].getText()) <= 1000){
							if(value[move].getY() < txt[9].getY()){
								value[move].setBounds(value[move].getX(), value[move].getY()+1, 80, 50);
								if(value[move].getY() == txt[9].getY()){
									txt[9].setText(value[move].getText()  + "\n" + txt[9].getText());
									panel.remove(value[move]);
									move = 10;
								}
							}
						}
					} // move = 9

				} else if(move == 10){

					GeneralBucketSort sorter = new GeneralBucketSort(); // create instance of GeneralBucketSort
					int input[] = new int[10];							// declare input
					String message = "CLICK TO SORT NUMBERS";			// string message

					UIManager.put("OptionPane.messageFont",new Font("Calibri", Font.BOLD, 38));
					JOptionPane.showMessageDialog(null, message);

					for(int ctr = 0; ctr < 10; ctr++){
						input[ctr] = Integer.parseInt(value_static[ctr].getText()); // get the value of the boxes
					}

					input = sorter.sort(input, 100); // sort the value of the boxes
					int ctr = 0;

					for(int element : input){
						value_static[ctr].setText(Integer.toString(element)); //display the sorted boxes
						ctr++;
					}

					animate.stop(); // stop animation
				}
			}
		}
}
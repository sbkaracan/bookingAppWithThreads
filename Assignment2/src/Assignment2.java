import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;






public class Assignment2 implements ActionListener{//implements actionlistener because we have some actions on GUI
	
	    private JTextField txtSeats,txtAgents,txtWaiting; //Initializing
	    private JButton btnCreate, btnBook; //Initializing
	    ArrayList<JTextField> arrtxt = new ArrayList<JTextField>(); //Initializing textfields for seats
	    ArrayList<Thread> arrThread = new ArrayList<Thread>(); //Arraylist for threads
	    JFrame frame = new JFrame(); ////Initializing frame
	    JPanel jpan = new JPanel(); //Initializing panel
	    private Random rnd = new Random(); //Initializing random
	    
	    int agentNum;//Initializing number of agent
	    
	    
	    public Assignment2(){
	    	
	    	/*
	    	 
	    	 setting properties of frame and jpanel properties below
	    	 
	    	 
	    	 
	    	 */
	    	
	    	jpan.setBackground(Color.white);
	    	jpan.setLayout(new GridLayout(10,10));
	    	jpan.setPreferredSize(new Dimension(600, 500));
	    	
	    	frame.setSize(600, 600);
	    	frame.setResizable(false);
	    	frame.setLayout(new FlowLayout());

	        
	    	/*
	    	 setting components of gui
	    	 */
	        
	        txtSeats = new JTextField("Number of Seats");
	        frame.add(txtSeats);
	        
	        txtAgents = new JTextField("Number of Agents");
	        frame.add(txtAgents);
	        
	        txtWaiting = new JTextField("Waiting time");
	        frame.add(txtWaiting);
	        
	        btnCreate = new JButton("Create");
	        frame.add(btnCreate);
	        
	        btnBook = new JButton("Book");
	        frame.add(btnBook);
	        
	        
	        frame.setVisible(true);
	    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	    	frame.add(jpan); //added panel into frame
	    	
	    	
	    	
	    	/*
	    	 adding components to action listener.
	    	 */
	        
	        txtAgents.addActionListener(this);
	        txtSeats.addActionListener(this);
	        txtWaiting.addActionListener(this);
	        btnBook.addActionListener(this);
	        btnCreate.addActionListener(this);
	    }

	    public static void main(String[] args) {
	    	
	        new Assignment2();
	        
	        
	        
	    }
	    

		@Override
		public void actionPerformed(ActionEvent arg0) {//method of the action listener.
			if (arg0.getSource().equals(btnCreate)) {//create button's event
				/*
				 I did the things below for being efficient when click the create button 2nd time.
				 */
				jpan.removeAll();//remove all things in panel
				arrThread.clear();//delete the arraylist of threads
				arrtxt.clear();//delete the arraylist of seat
				int seatNum = Integer.parseInt(txtSeats.getText());//seat's text
				createSeats(seatNum);//call another method
				
			}
			else if (arg0.getSource().equals(btnBook)) {// book button's event
				agentNum = Integer.parseInt(txtAgents.getText());//agent nums text
				startThreads(agentNum); // call another method
				
				
				
			}
			
		}
		
		
		public void startThreads(int threadNums) {//method which takes an integer num
			
			createThreads(agentNum);//call another method
			for (int i = 0; i < arrThread.size(); i++) {//for loop which works thread number times.
				
				arrThread.get(i).start();//get threads from arraylist and start every thread.
				
				
			}
		
			
		}
		
		
		
		public void createThreads(int size) {//method which takes an int num
			for (int i = 0; i < size; i++) {
				arrThread.add(new Thread());//add new thread in thread arraylist
				String seattxt = "Agent "+(i+1)+ " booked"; // text of seats
				int maxNum = Integer.parseInt(txtWaiting.getText());//random number's initialization
				
				
				arrThread.set(i, new Thread(new Runnable() {//set the thread and make the run method with runnable
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						bookSeats(seattxt, maxNum);//call bookseats method in run method
					}
				}));
				
			}
			
			
		}
		
		

		public void createSeats(int seatNum) {//method which takes int num
			
			jpan.removeAll();//remove all things from jpanel
			
			for (int i = 1; i <= seatNum; i++) {
				arrtxt.add(new JTextField("Not Booked "+i));//add new text fields in seat arraylist
				arrtxt.get(i-1).setBackground(Color.white);//set background white for all elements of seat arraylist
				
				jpan.add(arrtxt.get(i-1));//add seats into the panel
				
				
				
				/*
				 codes below, works for show the seats on the panel
				 */
				jpan.revalidate();
				jpan.repaint();
				
				
			}
			
			
				 
		}
		
		public void bookSeats(String txt, int rnd) {//method takes 1 string 1 int num
			
			
			for (int i = 0; i < arrtxt.size(); i++) {//for loop which works seat number times
				int max = this.rnd.nextInt(Integer.parseInt(txtWaiting.getText()));//random number. initialized for a variable max
				
				try {
					Thread.sleep(max);//sleep thread max seconds
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				if (arrtxt.get(i).getBackground().equals(Color.white)) {//if seat's background is white(I mean it hasn't been booked)
					arrtxt.get(i).setBackground(Color.red);//set background of seat text field red
					arrtxt.get(i).setText(txt);//settext of the text field
				}
				
				
				
			}
			
			createJOP();//call another method
		}
		
		public void createJOP() {
			/*
			 create 2 arraylists String and int
			 */
			ArrayList<String> str = new ArrayList<String>();
			ArrayList<Integer> nums = new ArrayList<Integer>();
			String text = "";//initialize the text
			int count = 0;//initialize count variable
			
			//-------------------------------------------------------------------------------------------------------
			/*
			 There are nested for loops, the loop inside works seat number times and outside works thread number times. There is a for loop inside the for and it controls 
			 if the text of seats equals the text inside the paranthesis. Add 1 to count variable. It means There is a booked seat.
			 added the count value inside the nums arraylist which is the agents's booked seat number. And make it count = 0 again because we need to work it for the 2nd agent again. 
			 */
			
			for (int i = 0; i < arrThread.size(); i++) {
				for (int j = 0; j < arrtxt.size(); j++) {
					if (arrtxt.get(j).getText().equals("Agent "+(i+1)+ " booked")  ) {
						count++;
						
					}
					
				}
				nums.add(count);
				count = 0;
			}
			
			
			//---------------------------------------------------------------------------------------------------
			
			
			
			/*
			There is a for loop which works thread number times. Add the text into the string arraylist and change the value of text variable.  
			 */
			
			
			for (int i = 0; i < arrThread.size(); i++) {
				
				str.add("Agent "+(i+1)+" booked "+ nums.get(i) +" seats.\n");
				text = text + str.get(i);
			}
			
			
			
			JOptionPane.showMessageDialog(null, text);//Show the text variable as an option pane.
			JOptionPane.getRootFrame().dispose();//Close the other option panes when clicked to the OK button.

		}
		
		
	}



















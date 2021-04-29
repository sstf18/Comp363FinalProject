import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SnakeApp{
	private Login login;
    private Grid grid;
    private GameView gameView;
    private GameController gameController;    
    
     public SnakeApp() {
    	login = new Login(); 
    	//adding a Windows events, 
    	//it works when we finish login, then close login program. 
    	login.addWindowListener(new WindowAdapter() {    			
    		public void windowClosing(WindowEvent e) {
    			//if logged In, then run init(); 
    			if (login.loggedIn) {    				
    				init();
    			}else {
    				//stop login, directly close
    				login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    				   
    			}    	
    		}
    	}); 
    	login.setVisible(true);    	
     }
    
     //this method is used for connecting all the class and method.   
    public void init(){    
    	
    	//create 500*500 's grid
    	grid = new Grid(500,500);
    	//Window's name
		JFrame frame = new JFrame("snakeGame");
		
		//one bug we have fixed;
		//add setDefaultCloseOperation because if the snake does not died, even 
		//if we close the App's window, it will continuous run until snake hit on the well 
   		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   		
   		//an container sets all the components.  
        Container contentPane = frame.getContentPane();        
        //draw grid, snake, food
        gameView = new GameView(grid);
        gameView.init();
        gameView.getCanvas().setPreferredSize(new Dimension(500,500));
        contentPane.add(gameView.getCanvas(),BorderLayout.CENTER);        
        
        //use game controller        
        gameController = new GameController(grid,gameView);
        //listener for key
        frame.addKeyListener(gameController);
        //start thread
        new Thread(gameController).start();
		
        frame.pack();
        frame.setVisible(true);  
		   			      
    }
    
    public static void main(String[] args){
        SnakeApp starter = new SnakeApp(); 
        
    }    
}
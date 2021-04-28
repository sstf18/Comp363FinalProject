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
    	login.addWindowListener(new WindowAdapter() {
    			
    		public void windowClosing(WindowEvent e) {
    			//if logged In, then run init(); 
    			if (login.loggedIn) {
    				
    				init();
    			}else {
    				login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    				super.windowClosing(e);    
    			}    	
    		}
    	}); 
    	login.setVisible(true);
    	
     }
    
    public void init(){    	    	   	   	
    	grid = new Grid(500,500);
		JFrame frame = new JFrame("snakeGame");
   		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();
        
        //draw grid, snake, food
        gameView = new GameView(grid);
        gameView.init();
        gameView.getCanvas().setPreferredSize(new Dimension(500,500));
        contentPane.add(gameView.getCanvas(),BorderLayout.CENTER);
        
        //use game controller
        gameController = new GameController(grid,gameView);
        frame.addKeyListener(gameController);
        new Thread(gameController).start();

        frame.pack();
        frame.setVisible(true);  
		   			      
    }
    
    public static void main(String[] args){
        SnakeApp starter = new SnakeApp();        
        
        
    }
    
}
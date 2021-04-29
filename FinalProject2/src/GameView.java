import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class GameView  {

    private final Grid grid;
    private JPanel canvas;
    //int willContinue;
    public GameView(Grid grid){
        this.grid = grid;
    }    
    //we need init GameView. 
    
    public void init(){
        canvas = new JPanel(){            
            @Override          
            public void paintComponent(Graphics graphics){
                super.paintComponent(graphics);
                setBackground(Color.BLACK);
                //draw snake
                drawSnake(graphics, grid.getSnake());
                //draw food
                drawFood(graphics, grid.getFood());
            }
        };
    }
    
    //draw Snake by connecting many square.
    //make sure each of Node was draw, so use iterator
    public void drawSnake(Graphics graphics, Snake snake){
    	//write it as iterator to represent snake body(green)
    	//snake head is red
    	Iterator<Node> it = snake.body.iterator();
    	Node n = new Node(0,0);
    	n = it.next();
    	this.drawSquare(graphics,n, Color.red);
    	while (it.hasNext()) {
    		n = it.next();
    		this.drawSquare(graphics, n, Color.green);
    	}
    }
    //draw Food by showing a circle
    public void drawFood(Graphics graphics, Node food){
        drawCircle(graphics, food, Color.blue);
    }

    //Here still has some bugs
    //after choose "Yes" button, the old frame will not be closed. 
    //idea: we may add one more switch method, 
    //like if (willContinue == 0){case1:let gameVIew,repaint()..}
    //{case2: system.exit(0)}
    public void showGameOverMessage(){
    	int i = 0;       
            int willContinue = JOptionPane.showConfirmDialog(null,"GameOver, Do you want play agian? ",null,JOptionPane.YES_NO_OPTION);
        	if (willContinue ==0 ) {
        	SnakeApp snakeGame = new SnakeApp();
        	snakeGame.init();        
        	}else {
        		System.exit(0);
        	}            
    }
    //using Graphics class to draw components
    //"fillRect(int x, int y, int width, int height)"    
    
    private void drawSquare(Graphics graphics, Node square, Color color){
        graphics.setColor(color);
        int nodeSize = Settings.DEFAULT_NODE_SIZE;
        graphics.fillRect(square.getX(), square.getY(), nodeSize-1, nodeSize-1);
    }
    private void drawCircle(Graphics graphics, Node circle, Color color){
        graphics.setColor(color);
        int nodeSize = Settings.DEFAULT_NODE_SIZE;
        graphics.fillOval(circle.getX(), circle.getY(), nodeSize, nodeSize );
    }

    public void draw(){
        canvas.repaint();
    }

    public JPanel getCanvas(){
        return canvas;
    }
}
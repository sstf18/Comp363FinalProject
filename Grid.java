public class Grid{
     
    private Snake snake;
    private Node food;
    private int width, height;
    //set the default direction  
    private int direction = Direction.RIGHT.getDirectionsCode();

    Grid(int height, int width){
        this.height = height;
        this.width = width;
        //set the snake start at the center of grid;
        snake = new Snake(height/2,width/2);
        createFood();
    }
   
    //0:Up; 1:Down; 2: Left; 3:Right
    //change the coordinate of head Node (x,y), x,y=1/2 
    //the original point is (0,0) on the top left corner. 
    public boolean move(int directionCode){
        direction = directionCode;
        int x = snake.head.getX();
        //System.out.println("head: "+snake.head);
        int y = snake.head.getY();
        
        switch (directionCode){
            case 0:
                if(canMove(x, y - Settings.DEFAULT_NODE_SIZE))
                	//System.out.println("changed Y:" +y);
                    return true;
                //break;
            case 1:
                if(canMove(x, y + Settings.DEFAULT_NODE_SIZE))
                    return true;
                break;
            case 2:
                if(canMove(x - Settings.DEFAULT_NODE_SIZE, y))
                	//System.out.println("changed X: "+x );
                    return true;
                
                //break;
            case 3:
                if(canMove(x + Settings.DEFAULT_NODE_SIZE, y))
                    return true;
                break;
        }
        return false;
    }
    //use the Linkedlist method to find the tail of list.
    //if ((0<x<width || 0<y<hright)|| ! list contains new node)
    //then using addFirst, remove last to move.
    //if head of snake = food, then add tail. 
    private boolean canMove(int x, int y){
        Node tail = snake.body.getLast();
        if (!(((x > width || x < 0 || y > height || y < 0))|| (snake.body.contains(new Node(x,y))))){
            snake.body.addFirst(snake.head = new Node(x,y));
            snake.body.removeLast();
            if (snake.head.equals(food)){
                snake.body.addLast(tail);
                createFood();
            }
            return  true;
        }
        return false;
    }

    //create new food method 
    //randomly create food in the (500, 500) 
    //here the food is a address
    private void createFood(){
       
        int x = (int) (Math.random()*width );
        int y = (int) (Math.random()*height );
        while (snake.body.contains(new Node(x,y))||x > width||y > height){
           
            x = (int) (Math.random()*width);
            y = (int) (Math.random()*height);
        }
        
       // System.out.println("x: "+x);
       // System.out.println("y: "+y);
        food = new Node(x,y);
        
    }    
    //move auto by default direction"Right";    
    public boolean moveAuto(){
        return move(direction);
    }
    
    public Snake getSnake(){
        return snake;
    }
    public Node getFood(){
        return food;
    }
    
}

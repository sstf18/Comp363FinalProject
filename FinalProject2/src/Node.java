public class Node {
    private int x;
    private int y;

    /*
     * x/y coordinate of Node's upper left corner
     * */
    Node(int x, int y) {
    	//this.x = x;
    	//this.y = y;
    	
        this.x = (x/10)*10;
        this.y = (y/10)*10;
        
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
   
    public boolean equals(Node node){
        return (x == node.getX() && y == node.getY());
    }
}
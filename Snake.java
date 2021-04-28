import java.util.Iterator;
import java.util.LinkedList;

/*
 * This is snake class
 * 
 * */
public class Snake {
	//using LinkedList to connect snake's body     	
	
	public class BodyList extends LinkedList<Node>{
    	//check Node in Snake body 
        public boolean contains(Node node){
            for (Node nodeTemp : this) {
                if (node.equals(nodeTemp))
                    return true;
            }
            return false;
        }
    }   

    Node head;
    BodyList body;
    
    //using node to represent the location of Snake. 
    //linked list is the body of snake
    Snake(int x, int y){
        body = new BodyList();
        head = new Node(x,y);
        body.add(head);
        for (int i = 0; i < 3; i++){
            x = x - Settings.DEFAULT_NODE_SIZE;
            Node bodyNode = new Node(x,y);
            body.add(bodyNode);
        }
        //System.out.print("Body"+body.size());
    }
}
//import javafx.scene.control.skin.TextInputControlSkin;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// creating new thread using "runnable" 
public class GameController implements KeyListener, Runnable {

    private final Grid grid;
    private final GameView gameView;
    private int keyCode = KeyEvent.VK_RIGHT;
    private boolean running;

    public GameController(Grid grid, GameView gameView){
        this.grid = grid;
        this.gameView = gameView;
        this.running = true;
    }
    
    @Override
    public void keyTyped(KeyEvent e) { }
    @Override
    public void keyReleased(KeyEvent e) { }
    
    //using key event
    //pressed a key. setting 
    @Override
    public void keyPressed(KeyEvent e){
        boolean result = true;
       
        if((Math.abs(keyCode - e.getKeyCode()) != KeyEvent.VK_DOWN - KeyEvent.VK_UP)
                && (Math.abs(keyCode - e.getKeyCode()) != KeyEvent.VK_RIGHT - KeyEvent.VK_LEFT)){
            keyCode = e.getKeyCode();
        }
        switch (keyCode){
            case KeyEvent.VK_UP:
                result = grid.move(Direction.UP.getDirectionsCode());
                //System.out.println("result: "+ result);
                break;
            case KeyEvent.VK_DOWN:
                result = grid.move(Direction.DOWN.getDirectionsCode());
                break;
            case KeyEvent.VK_LEFT:
                result = grid.move(Direction.LEFT.getDirectionsCode());
                break;
            case KeyEvent.VK_RIGHT:
                result = grid.move(Direction.RIGHT.getDirectionsCode());
                break;
        }
        
        //if result is "true", draw, gameView.
        //else return gamveorver message
        if (result){
            gameView.draw();
        }
        /*
         * Here is another bug, we fixed 
         * if add this statement, if we don not control this key, 
         * and let the snake hit the well, the bug will not comes out,
         * but if we press the key before the snake hit the well, when 
         * snake died, the game will show "GamveOver message" twice.     
        else {
        	gameView.showGameOverMessage();
        }
        */

    }
    
    //implement run() method
    //while running is true, using thread     
    //thread.sleep( move interval), then catch "interruptedException"  
    //if/else statement for moveAuto,
    //if move then gameView.draw
    @Override
    public void run(){
        while (running){
        	
            try{
                Thread.sleep(Settings.DEFAULT_MOVE_INTERVAL);
                //System.out.println("thread.sleep");
            }catch (InterruptedException e){
                break;
            }
            
            if(grid.moveAuto()){
                gameView.draw();
            }else{
                gameView.showGameOverMessage();
                break;
            }
        }
    }

}
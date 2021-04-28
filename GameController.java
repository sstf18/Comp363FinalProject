//import javafx.scene.control.skin.TextInputControlSkin;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// thread(runnable) 
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
        if (result){
            gameView.draw();
        }else {
            gameView.showGameOverMessage();
        }

    }
    //logic of run, and move auto  
    @Override
    public void run(){
        while (running){
            try{
                Thread.sleep(Settings.DEFAULT_MOVE_INTERVAL);
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
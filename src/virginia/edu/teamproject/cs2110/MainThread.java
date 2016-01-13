package virginia.edu.teamproject.cs2110;



import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread{
	public boolean isRunning;
	
	public GameView gameView;
	
	//fps --> change this 
	public final static int MAX_FPS = 50;
	//frames to be skipped 
	public final static int MAX_FRAME_SKIPS = 5;
	//frame period
	public final static int FRAME_PERIOD = 1000/MAX_FPS;
	
	//constructor
	public MainThread(GameView g){
		
		this.gameView = g;
	}
	
	//set running state
	public void setRunning(boolean r){
		this.isRunning = r;
	}

	@SuppressLint("WrongCall") @Override
	public void run() {
		Canvas canvas;
		long tickCount = 0L;
		while(isRunning){
			canvas = null;
			tickCount ++; 
			this.gameView.onDraw(canvas);
			//update game state do stuff
		
		}
		
	}
	
	
}

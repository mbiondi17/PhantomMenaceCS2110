package virginia.edu.teamproject.cs2110;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.View;

public class GameView extends View {

	public int gameScore = 0;
	public MainThread main;
	
	public GameView(Context context) {
		super(context);
		
		main = new MainThread(this);
		// TODO Auto-generated constructor stub
	}
	
	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context);
		// TODO Auto-generated constructor stub
	}


	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		
		
		super.onDraw(canvas);
	}

	
}

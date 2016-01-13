package virginia.edu.teamproject.cs2110;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawRectangle extends View {
	Paint paintBrush = new Paint();
	
	public DrawRectangle(Context context) {
		super(context);
		paintBrush.setColor(Color.BLACK);
	}
	
	@Override
	public void onDraw(Canvas c) {
		c.drawLine(0,0,20,20,paintBrush);
	}
}

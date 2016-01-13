package virginia.edu.teamproject.cs2110;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class DeathScreenEasy extends ActionBarActivity {

	public TextView highScore;
	public TextView yourScore;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_death_screen_easy);
		highScore = (TextView) findViewById(R.id.textView2);
		highScore.setText("Top Scores" + "\n" + "       " + "1. " + PlayGameEasy.highestScore
				+ "\n" + "       " + "2. " + PlayGameEasy.secondScore + "\n" + "       " + "3. " + PlayGameEasy.thirdScore);
		yourScore = (TextView) findViewById(R.id.textView3);
		yourScore.setText("Your Score: " + PlayGameEasy.score);
	}
	
	
	public void mainMenuPushed(View v) {
    	Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.death_screen, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

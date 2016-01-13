package virginia.edu.teamproject.cs2110;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class DifficultyScreen extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_difficulty_screen);
	}
	
	public void hardPushed(View v) {
    	Intent intent = new Intent(this, PlayGameHard.class);
    	startActivity(intent);
    }
	
	public void mediumPushed(View v) {
    	Intent intent = new Intent(this, PlayGame.class);
    	startActivity(intent);
    }
	
	public void easyPushed(View v) {
    	Intent intent = new Intent(this, PlayGameEasy.class);
    	startActivity(intent);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.difficulty_screen, menu);
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

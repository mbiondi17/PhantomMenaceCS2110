package virginia.edu.teamproject.cs2110;

import android.support.v7.app.ActionBarActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }

    																 //in its current state and resume it later?

    
    public void recordButtonClicked(View v) {
    	Intent intent = new Intent(this, Records.class);
    	startActivity(intent);
    }
    
    public void settingsButtonClicked(View v) {
    	Intent intent = new Intent(this, Settings.class);
    	startActivity(intent);
    }
    
    public void playButtonClicked(View v) {
    	Intent intent = new Intent(this, DifficultyScreen.class);
    	startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

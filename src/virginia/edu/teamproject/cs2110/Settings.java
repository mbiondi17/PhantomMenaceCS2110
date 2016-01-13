package virginia.edu.teamproject.cs2110;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Settings extends Activity {

	public static int bkgmuscheck = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		Intent intent = getIntent();
						//the default will be when music DOES play (button unchecked)
		bkgMusOff();
			
	}

	public void bkgMusOff() { 

		//remember that name you were supposed to remember? Go back and check it. I'm gonna call it box1.
			CheckBox box1 = (CheckBox) findViewById(R.id.checkBox1);									//IF YOU HAVE AN ERROR HERE, 
																								//SEE * AT BOTTOM OF THIS METHOD.
			box1.setOnCheckedChangeListener(new OnCheckedChangeListener(){						//use the import statement on 																						//the SECOND error in this line.

				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){		//need to import a library for
																								//CompoundButton

					if (buttonView.isChecked()) {												//the check box is checked?

						bkgmuscheck = 1;
					}
					else{
						bkgmuscheck = 0;
					}
				}

			});};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
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

package virginia.edu.teamproject.cs2110;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;


import android.support.v7.app.ActionBarActivity;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.SoundPool.Builder;


@TargetApi(Build.VERSION_CODES.HONEYCOMB) public class PlayGameHard extends Activity {

	//DrawRectangle newRectangle;
	//private Timer gameTimer;
	MediaPlayer bkgmus;
	MediaPlayer proximity;
	MediaPlayer coinsound;
	GameView gameView;
	//TextView scoreDisplay = (TextView) findViewById(R.id.scoreDisplay);
	int numGhosts = 1;
	int numLevels = 0;
	public static int score;
	public static int highestScore = 0;
	public static int secondScore = 0;
	public static int thirdScore = 0;
	int ghostsKilled = 0;
	int ghostsToBeGenerated = 2;
	boolean playerIsDead = false;
	boolean ghostIsDead = false;
	public ArrayList<ImageView> ghostList;
	MainThread gameThread;
	private static int lastbkgmuscheck = Settings.bkgmuscheck;
	public TextView scoreText;
	public SharedPreferences prefs;
	public Editor editor;
	public boolean isAlert = false;
	public ImageView coin;
	//public Builder sounds;
	//int coinsoundeffect;
	//int enemyalert;
	//int ghostDeath;
	//int playerDeath;
	
	
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_game_hard);
		Intent intent = getIntent();
		score = 0;
		scoreText = (TextView) findViewById(R.id.TextView1);
		Button up = (Button) findViewById(R.id.button2);
		Button down = (Button) findViewById(R.id.button4);
		Button right = (Button) findViewById(R.id.button1);
		Button left = (Button) findViewById(R.id.button3);
		if(lastbkgmuscheck == 0) {
		bkgmus = MediaPlayer.create(PlayGameHard.this, R.raw.bkgmustocatta);     //Tells the program where to find the music.
        bkgmus.setLooping(true);												 //Tells it to loop the file continuously.
        bkgmus.start();	
        }
        gameView = new GameView(this);
        gameThread = new MainThread(gameView); 
        ghostList = new ArrayList<ImageView>();
        prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        editor = prefs.edit();
        highestScore = prefs.getInt("first", 0);
        secondScore = prefs.getInt("second", 0);
		thirdScore = prefs.getInt("third", 0);
		proximity = MediaPlayer.create(PlayGameHard.this, R.raw.enemyalert);
		proximity.setLooping(false);
		coinsound = MediaPlayer.create(PlayGameHard.this, R.raw.coinsoundeffect);
		coinsound.setLooping(false);
		coin = (ImageView) findViewById(R.id.imageView2);
		coin.setVisibility(4);
		//sounds = new SoundPool.Builder();
		//coinsoundeffect = ((SoundPool) sounds).load(this, R.raw.coinsoundeffect, 1);
		//enemyalert = sounds.load(this, R.raw.enemyalert, 1);
		//ghostDeath = sounds.load(this, R.raw.ghostdies, 1);
		//playerDeath = sounds.load(this, R.raw.womp, 1);
        //ImageView ghost1 = (ImageView) findViewById(R.id.imageView2);
        //ghostList.add(ghost1);
        generateGhost3();
        //Starts the music.
       // scoreDisplay.setText("Score: ");
		//final RelativeLayout background = (RelativeLayout) findViewById(R.id.back);
		//Resources res = getResources();
		//final TypedArray myBackgrounds = res.obtainTypedArray(R.array.myBackgrounds);
		
		//final View character = findViewById(R.id.imageView1);
		//final int positionx = (int) character.getX();
		//final int positiony = (int) character.getY();
		
		//this.gameTimer = new Timer("gameTimer",true);
		//gameTimer.start();
		//newRectangle = new DrawRectangle(this);
		//newRectangle.setBackgroundColor(Color.WHITE);
		//setContentView(newRectangle);
	}
	
	
	
	//public int moveUp(View v) {
		//int positiony = (int) v.getY();
		//positiony-=5;
		//return positiony;
	//}
	
	public double distance(View v1, View v2) {
		double result = (double) Math.sqrt((v1.getX() - v2.getX())*(v1.getX() - v2.getX())+(v1.getY()-v2.getY())*(v1.getY()-v2.getY()));
		return result;
	}
	
	public void clickUp(View v) {
		View character = findViewById(R.id.imageView1);
		View swordup = findViewById(R.id.swordup);
		View sworddown = findViewById(R.id.sworddown);
		View swordright = findViewById(R.id.swordright);
		View swordleft = findViewById(R.id.swordleft);
		
		RelativeLayout r1 = (RelativeLayout) findViewById(R.id.back);
		//View canvas = findViewById(R.id.canvas);
		int positiony = (int)character.getY();
		int positionx = (int)character.getX();
		if(!(positiony <= 80)) {
			if(!(positiony<=360 && positiony>=160 && positionx>=220 && positionx <=700)) {
				if(!(positiony<=600 && positiony>=520 && positionx>=980 && positionx<=1500)){
		character.setY(positiony-40);
		sworddown.setVisibility(4);
		swordleft.setVisibility(4);
		swordright.setVisibility(4);
		swordup.setVisibility(0);
		swordup.setX(positionx);
		swordup.setY(positiony-120);
		//gameView.gameScore += 1;
		
		}
		}
		}
		if(distance(character,coin) < 100 && coin.getVisibility() == 0) {
			coin.setVisibility(4);
			score+=2;
			scoreText.setText("Score: " + score);
			coinsound.start();
			if (score > highestScore) {
				thirdScore = secondScore;
				secondScore = highestScore;
				highestScore = score;
			}
			if (score < highestScore && score > secondScore) {
				thirdScore = secondScore;
				secondScore = score;
			}
			if (score < secondScore && score > thirdScore) {
				thirdScore = score;
			}
			editor.putInt("first", highestScore);
			editor.putInt("second", secondScore);
			editor.putInt("third", thirdScore);
			editor.commit();
		}
		
		Random r = new Random();
		for(ImageView ghost1: ghostList) {
		int ghost1x = (int)ghost1.getX();
		int ghost1y = (int)ghost1.getY();
		
		int rand = r.nextInt(4);
		if(rand == 0){
			if(ghost1y < 880){
				ghost1.setY(ghost1y+80);
			}else{
			ghost1.setY(ghost1y-80);}
		}
		else if (rand == 1){
			if(ghost1y > 120){
				ghost1.setY(ghost1y-80);
			}else{
			ghost1.setY(ghost1y+80);}
		}
		else if (rand == 2){
			if(ghost1x < 1680){
			ghost1.setX(ghost1x+80);
			}else{
			ghost1.setX(ghost1x-80);}
		}
		else {
			if(ghost1x > 140){
			ghost1.setX(ghost1x-80);
			}else{
			ghost1.setX(ghost1x+80);}
		}}
		isAlert = false;
		for(int i = 0; i < ghostList.size(); i++) {
			if(distance(ghostList.get(i), character) < 200 && !isAlert) {
					proximity.start();
					isAlert = true;
				}
		
			if(checkKilled(swordup, ghostList.get(i))) {
				//ghost.setVisibility(8);
				ghostList.get(i).setX(-99999999);
				ghostList.get(i).setY(-99999999);
				r1.removeView(ghostList.get(i));
				score++;
				scoreText.setText("Score: " + score);
				if (score > highestScore) {
					thirdScore = secondScore;
					secondScore = highestScore;
					highestScore = score;
				}
				if (score < highestScore && score > secondScore) {
					thirdScore = secondScore;
					secondScore = score;
				}
				if (score < secondScore && score > thirdScore) {
					thirdScore = score;
				}
				editor.putInt("first", highestScore);
				editor.putInt("second", secondScore);
				editor.putInt("third", thirdScore);
				editor.commit();
				coin.setVisibility(0);
				generateGhost8();
				generateGhost1();
				//generateCoin();
				break;
			}
			if(checkKilled(character, ghostList.get(i))) {
				Intent intent1 = new Intent(this, DeathScreenHard.class);
				startActivity(intent1);
				intent1.putExtra("high_score", highestScore);
			}}
	}
	
	public void clickDown(View v) {
		View character = findViewById(R.id.imageView1);
		int positiony = (int)character.getY();
		int positionx = (int)character.getX();
		View swordup = findViewById(R.id.swordup);
		View sworddown = findViewById(R.id.sworddown);
		View swordright = findViewById(R.id.swordright);
		View swordleft = findViewById(R.id.swordleft);
		RelativeLayout r1 = (RelativeLayout) findViewById(R.id.back);

		if(!(positiony >= 880)) {
			if(!(positionx>=540 && positionx<=700 && positiony<=320 && positiony>=120)){
				if(!(positiony>=440 && positiony<=520 && positionx>=900 && positionx<=1500)){
		character.setY(positiony+40);
		sworddown.setVisibility(0);
		swordleft.setVisibility(4);
		swordright.setVisibility(4);
		swordup.setVisibility(4);
		sworddown.setX(positionx);
		sworddown.setY(positiony+120);
		}}}
		
		if(distance(character,coin) < 100 && coin.getVisibility() == 0) {
			coin.setVisibility(4);
			score+=2;
			scoreText.setText("Score: " + score);
			coinsound.start();
			if (score > highestScore) {
				thirdScore = secondScore;
				secondScore = highestScore;
				highestScore = score;
			}
			if (score < highestScore && score > secondScore) {
				thirdScore = secondScore;
				secondScore = score;
			}
			if (score < secondScore && score > thirdScore) {
				thirdScore = score;
			}
			editor.putInt("first", highestScore);
			editor.putInt("second", secondScore);
			editor.putInt("third", thirdScore);
			editor.commit();
		}
		
		Random r = new Random();
		for(ImageView ghost1: ghostList) {
		int ghost1x = (int)ghost1.getX();
		int ghost1y = (int)ghost1.getY();
		
		int rand = r.nextInt(4);
		if(rand == 0){
			if(ghost1y < 880){
				ghost1.setY(ghost1y+80);
			}else{
			ghost1.setY(ghost1y-80);}
		}
		else if (rand == 1){
			if(ghost1y > 120){
				ghost1.setY(ghost1y-80);
			}else{
			ghost1.setY(ghost1y+80);}
		}
		else if (rand == 2){
			if(ghost1x < 1680){
			ghost1.setX(ghost1x+80);
			}else{
			ghost1.setX(ghost1x-80);}
		}
		else {
			if(ghost1x > 140){
			ghost1.setX(ghost1x-80);
			}else{
			ghost1.setX(ghost1x+80);}
		}
	}
		isAlert = false;
			for(int i = 0; i < ghostList.size(); i++) {
				if(distance(ghostList.get(i), character) < 200 && !isAlert) {
					proximity.start();
					isAlert = true;
				}
				if(checkKilled(sworddown, ghostList.get(i))) {
					//ghost.setVisibility(8);
					ghostList.get(i).setX(-99999999);
					ghostList.get(i).setY(-99999999);
					r1.removeView(ghostList.get(i));
					score++;
					scoreText.setText("Score: " + score);
					if (score > highestScore) {
						thirdScore = secondScore;
						secondScore = highestScore;
						highestScore = score;
					}
					if (score < highestScore && score > secondScore) {
						thirdScore = secondScore;
						secondScore = score;
					}
					if (score < secondScore && score > thirdScore) {
						thirdScore = score;
					}
					editor.putInt("first", highestScore);
					editor.putInt("second", secondScore);
					editor.putInt("third", thirdScore);
					editor.commit();
					coin.setVisibility(0);
					generateGhost2();
					generateGhost4();
					//generateCoin();
					break;
				}
				if(checkKilled(character, ghostList.get(i))) {
					Intent intent1 = new Intent(this, DeathScreenHard.class);
					startActivity(intent1);
				}
			}}
	
	public void clickRight(View v) {
		View character = findViewById(R.id.imageView1);
		int positionx = (int)character.getX();
		int positiony = (int)character.getY();
		View swordup = findViewById(R.id.swordup);
		View sworddown = findViewById(R.id.sworddown);
		View swordright = findViewById(R.id.swordright);
		View swordleft = findViewById(R.id.swordleft);
		RelativeLayout r1 = (RelativeLayout) findViewById(R.id.back);

		if(!(positionx >= 1680)) {
			if(!(positionx >= 180 && positionx <=700 && positiony <= 320 && positiony >=160)) {
				if(!(positionx>=180 && positionx<=540 && positiony<=160)){
					if(!(positionx>=860 && positionx<=940 && positiony>=480)) {
		character.setX(positionx+40);
		sworddown.setVisibility(4);
		swordleft.setVisibility(4);
		swordright.setVisibility(0);
		swordup.setVisibility(4);
		swordright.setX(positionx+120);
		swordright.setY(positiony);
			}}}
		}
		
		if(distance(character,coin) < 100 && coin.getVisibility() == 0) {
			coin.setVisibility(4);
			score+=2;
			scoreText.setText("Score: " + score);
			coinsound.start();
			if (score > highestScore) {
				thirdScore = secondScore;
				secondScore = highestScore;
				highestScore = score;
			}
			if (score < highestScore && score > secondScore) {
				thirdScore = secondScore;
				secondScore = score;
			}
			if (score < secondScore && score > thirdScore) {
				thirdScore = score;
			}
			editor.putInt("first", highestScore);
			editor.putInt("second", secondScore);
			editor.putInt("third", thirdScore);
			editor.commit();
		}
		
		Random r = new Random();
		for(ImageView ghost1: ghostList) {
		int ghost1x = (int)ghost1.getX();
		int ghost1y = (int)ghost1.getY();
		
		int rand = r.nextInt(4);
		if(rand == 0){
			if(ghost1y < 880){
				ghost1.setY(ghost1y+80);
			}else{
			ghost1.setY(ghost1y-80);}
		}
		else if (rand == 1){
			if(ghost1y > 120){
				ghost1.setY(ghost1y-80);
			}else{
			ghost1.setY(ghost1y+20);}
		}
		else if (rand == 2){
			if(ghost1x < 1680){
			ghost1.setX(ghost1x+80);
			}else{
			ghost1.setX(ghost1x-80);}
		}
		else {
			if(ghost1x > 140){
			ghost1.setX(ghost1x-80);
			}else{
			ghost1.setX(ghost1x+80);}
		}
		}
		isAlert = false;
		for(int i = 0; i < ghostList.size(); i++) {
			if(distance(ghostList.get(i), character) < 200 && !isAlert) {
				proximity.start();
				isAlert = true;
			}
			if(checkKilled(swordright, ghostList.get(i))) {
				//ghost.setVisibility(8);
				ghostList.get(i).setX(-99999999);
				ghostList.get(i).setY(-99999999);
				r1.removeView(ghostList.get(i));
				score++;
				scoreText.setText("Score: " + score);
				if (score > highestScore) {
					thirdScore = secondScore;
					secondScore = highestScore;
					highestScore = score;
				}
				if (score < highestScore && score > secondScore) {
					thirdScore = secondScore;
					secondScore = score;
				}
				if (score < secondScore && score > thirdScore) {
					thirdScore = score;
				}
				editor.putInt("first", highestScore);
				editor.putInt("second", secondScore);
				editor.putInt("third", thirdScore);
				editor.commit();
				coin.setVisibility(0);
				generateGhost5();
				generateGhost3();
				//generateCoin();
				break;
			}
			if(checkKilled(character, ghostList.get(i))) {
				Intent intent1 = new Intent(this, DeathScreenHard.class);
				startActivity(intent1);
			}}
	}
	
	public void clickLeft(View v) {
		View character = findViewById(R.id.imageView1);
		int positionx = (int)character.getX();
		int positiony = (int)character.getY();
		View swordup = findViewById(R.id.swordup);
		View sworddown = findViewById(R.id.sworddown);
		View swordright = findViewById(R.id.swordright);
		View swordleft = findViewById(R.id.swordleft);
		RelativeLayout r1 = (RelativeLayout) findViewById(R.id.back);
			if(!(positionx <= 140)) {
			if(!(positionx>=220 && positionx<=740 && positiony<=320 && positiony>=160)) {
				if(!(positionx<=580 && positionx>=220 && positiony<=160)){
					if(!(positionx<=1020 && positionx>=940 && positiony>=520)){
						if(!(positionx>=1500 && positionx<=1540 && positiony<=560 && positiony>=480)){
		character.setX(positionx-40);
		sworddown.setVisibility(4);
		swordleft.setVisibility(0);
		swordright.setVisibility(4);
		swordup.setVisibility(4);
		swordleft.setX(positionx-120);
		swordleft.setY(positiony);
		}}}}}
		
			if(distance(character,coin) < 100 && coin.getVisibility() == 0) {
				coin.setVisibility(4);
				score+=2;
				scoreText.setText("Score: " + score);
				coinsound.start();
				if (score > highestScore) {
					thirdScore = secondScore;
					secondScore = highestScore;
					highestScore = score;
				}
				if (score < highestScore && score > secondScore) {
					thirdScore = secondScore;
					secondScore = score;
				}
				if (score < secondScore && score > thirdScore) {
					thirdScore = score;
				}
				editor.putInt("first", highestScore);
				editor.putInt("second", secondScore);
				editor.putInt("third", thirdScore);
				editor.commit();
			}
			
		Random r = new Random();
		for(ImageView ghost1: ghostList) {
		int ghost1x = (int)ghost1.getX();
		int ghost1y = (int)ghost1.getY();
		
		int rand = r.nextInt(4);
		if(rand == 0){
			if(ghost1y < 880){
				ghost1.setY(ghost1y+80);
			}else{
			ghost1.setY(ghost1y-80);}
		}
		else if (rand == 1){
			if(ghost1y > 120){
				ghost1.setY(ghost1y-80);
			}else{
			ghost1.setY(ghost1y+80);}
		}
		else if (rand == 2){
			if(ghost1x < 1680){
			ghost1.setX(ghost1x+80);
			}else{
			ghost1.setX(ghost1x-80);}
		}
		else {
			if(ghost1x > 140){
			ghost1.setX(ghost1x-80);
			}else{
			ghost1.setX(ghost1x+80);}
		}
	}
		isAlert = false;
		for(int i = 0; i < ghostList.size(); i++) {
			if(distance(ghostList.get(i), character) < 200 && !isAlert) {
				proximity.start();
				isAlert = true;
			}
			if(checkKilled(swordleft, ghostList.get(i))) {
				//ghost.setVisibility(8);
				ghostList.get(i).setX(-99999999);
				ghostList.get(i).setY(-99999999);
				r1.removeView(ghostList.get(i));
				score++;
				scoreText.setText("Score: " + score);
				if (score > highestScore) {
					thirdScore = secondScore;
					secondScore = highestScore;
					highestScore = score;
				}
				if (score < highestScore && score > secondScore) {
					thirdScore = secondScore;
					secondScore = score;
				}
				if (score < secondScore && score > thirdScore) {
					thirdScore = score;
				}
				editor.putInt("first", highestScore);
				editor.putInt("second", secondScore);
				editor.putInt("third", thirdScore);
				editor.commit();
				ghostList.remove(i);
				coin.setVisibility(0);
				generateGhost7();
				generateGhost6();
				//generateCoin();
				break;
			}
			if(checkKilled(character, ghostList.get(i))) {
				Intent intent1 = new Intent(this, DeathScreenHard.class);
				startActivity(intent1);
			}}}
	
	

	public ImageView generateCoin() {
			final ImageView newCoin = new ImageView(this);
			newCoin.setImageResource(R.drawable.coin);
			newCoin.setX(1100);
			newCoin.setY(160);
			int height = 90;
			int width = 90;
			RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(width,height);		
			newCoin.setLayoutParams(parms);
			RelativeLayout r1 = (RelativeLayout) findViewById(R.id.back);
			r1.addView(newCoin);
			return newCoin;

			/*newCoin.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					score += 5;
					newCoin.setVisibility(8);
				}
			});*/
					
					//sounds.play(coinsoundeffect, 1.0f, 1.0f, 0, 0, 1.5f);      	//If this doesn't work, take out the "f"s?
				}
			

	
	public ImageView generateGhost1() {
		ImageView newghost = new ImageView(this);
		newghost.setImageResource(R.drawable.enemyghost2);
		newghost.setX(250);
		newghost.setY(225);
		int height = 90;
		int width = 90;
		RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(width,height);		
		newghost.setLayoutParams(parms);
		RelativeLayout r1 = (RelativeLayout) findViewById(R.id.back);
		r1.addView(newghost);
		ghostList.add(newghost);
		return newghost;
	}
	
	public ImageView generateGhost2() {
		ImageView newghost = new ImageView(this);
		newghost.setImageResource(R.drawable.enemyghost2);
		newghost.setX(1100);
		newghost.setY(225);
		int height = 90;
		int width = 90;
		RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(width,height);		
		newghost.setLayoutParams(parms);
		RelativeLayout r1 = (RelativeLayout) findViewById(R.id.back);
		r1.addView(newghost);
		ghostList.add(newghost);
		return newghost;
	}
	
	public ImageView generateGhost3() {
		ImageView newghost = new ImageView(this);
		newghost.setImageResource(R.drawable.enemyghost2);
		newghost.setX(600);
		newghost.setY(750);
		int height = 90;
		int width = 90;
		RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(width,height);		
		newghost.setLayoutParams(parms);
		RelativeLayout r1 = (RelativeLayout) findViewById(R.id.back);
		r1.addView(newghost);
		ghostList.add(newghost);
		return newghost;
	}
	
	public ImageView generateGhost4() {
		ImageView newghost = new ImageView(this);
		newghost.setImageResource(R.drawable.enemyghost2);
		newghost.setX(250);
		newghost.setY(750);
		int height = 90;
		int width = 90;
		RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(width,height);		
		newghost.setLayoutParams(parms);
		RelativeLayout r1 = (RelativeLayout) findViewById(R.id.back);
		r1.addView(newghost);
		ghostList.add(newghost);
		return newghost;
	}
	
	public ImageView generateGhost5() {
		ImageView newghost = new ImageView(this);
		newghost.setImageResource(R.drawable.enemyghost2);
		newghost.setX(1500);
		newghost.setY(225);
		int height = 90;
		int width = 90;
		RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(width,height);		
		newghost.setLayoutParams(parms);
		RelativeLayout r1 = (RelativeLayout) findViewById(R.id.back);
		r1.addView(newghost);
		ghostList.add(newghost);
		return newghost;
	}
	
	public ImageView generateGhost6() {
		ImageView newghost = new ImageView(this);
		newghost.setImageResource(R.drawable.enemyghost2);
		newghost.setX(1500);
		newghost.setY(750);
		int height = 90;
		int width = 90;
		RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(width,height);		
		newghost.setLayoutParams(parms);
		RelativeLayout r1 = (RelativeLayout) findViewById(R.id.back);
		r1.addView(newghost);
		ghostList.add(newghost);
		return newghost;
	}
	
	public ImageView generateGhost7() {
		ImageView newghost = new ImageView(this);
		newghost.setImageResource(R.drawable.enemyghost2);
		newghost.setX(600);
		newghost.setY(225);
		int height = 90;
		int width = 90;
		RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(width,height);		
		newghost.setLayoutParams(parms);
		RelativeLayout r1 = (RelativeLayout) findViewById(R.id.back);
		r1.addView(newghost);
		ghostList.add(newghost);
		return newghost;
	}
	
	public ImageView generateGhost8() {
		ImageView newghost = new ImageView(this);
		newghost.setImageResource(R.drawable.enemyghost2);
		newghost.setX(1100);
		newghost.setY(750);
		int height = 90;
		int width = 90;
		RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(width,height);		
		newghost.setLayoutParams(parms);
		RelativeLayout r1 = (RelativeLayout) findViewById(R.id.back);
		r1.addView(newghost);
		ghostList.add(newghost);
		return newghost;
	}
	
	public boolean checkKilled(View v1, View v2) {
		if(distance(v1,v2) <= 50) {
			return true;
		}
		else {
			return false;
		}
	}
	
	protected void onPause() {
    	super.onPause();
    	if(lastbkgmuscheck == 0) {
    	bkgmus.release();
    	}
    	finish();	
    	//Apparently, this will pause the game and music
    	}	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play_game, menu);
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

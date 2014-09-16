package nhannv.activity.hotgirl;


import java.util.ArrayList;

import nhannv.com.adapter.ImageSlideAdapter;
import quangdat.basic.hotgirl.R;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class FullView extends Activity implements AnimationListener{
	ImageSlideAdapter adapter;
	ViewPager viewpaper;
	public static ImageView autoPlayButton;
	int index;
	public int animationNum=0;//animation number of type
	ArrayList<String> urls;
	boolean pressBack=false;
	public static boolean autoPlayState = false;
	ArrayList<Animation> anim;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.full_screen_view_image);
		//findview
		autoPlayButton = (ImageView) findViewById(R.id.auto_play_fsvi_iv);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		//get intent
		Intent mainIntent = getIntent();
		index = mainIntent.getIntExtra("index", 0);
		urls = mainIntent.getStringArrayListExtra("urls");
		Log.i("url cur",urls.get(index));
		//set viewpaper
		adapter = new ImageSlideAdapter(this, urls);
		viewpaper = (ViewPager) findViewById(R.id.image_view_paper);
		viewpaper.setAdapter(adapter);
		viewpaper.setCurrentItem(index);
		//set autoplay
		autoPlayButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				autoPlayState=!autoPlayState;
				// TODO Auto-generated method stub
			new loadDelay().execute("");
			if(autoPlayState==true){
				autoPlayButton.setImageResource(R.drawable.ic_action_pause_over_video);
			}else{
				autoPlayButton.setImageResource(R.drawable.ic_action_play_over_video);
			}
				
			}
		});
		//init animation
		anim = new ArrayList<>();
		initAnim(anim);
				
		
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		pressBack=true;
	}
	public class loadDelay extends AsyncTask<String, String, String>{
		int i=viewpaper.getCurrentItem();
		@Override
		protected String doInBackground(String... params) {
			
			int lenght=urls.size();
			int numa=0;
			//loop view
			while(i<lenght){
				//put index image,num of ani
				publishProgress(String.valueOf(i),String.valueOf(numa));
				/**check stop
				 */
				if(pressBack==true||autoPlayState==false){
					break;
				}
				SystemClock.sleep(4000);
				//if finish restart from 0
				if(i==lenght-1){
					i=0;
				}
				//set animation number
				if(numa<anim.size()-1){
					numa++;
				}else{
					i++;
					numa=0;
				}
			}
			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			viewpaper.setCurrentItem(viewpaper.getCurrentItem());
			
		}
		@Override
		protected void onProgressUpdate(String... values) {
			
			int i = Integer.valueOf(values[0]);
			int j = Integer.valueOf(values[1]);
			//Log.i("size", String.valueOf(values.length));
			viewpaper.startAnimation(anim.get(j));
			viewpaper.setCurrentItem(i);
			super.onProgressUpdate(values);
		}
		
	}
	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAnimationEnd(Animation animation) {
		
	}
	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}
	public void initAnim(ArrayList<Animation> anim){
		// load the animation
		Animation animation;
		animation= AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.slide_down);
		// set animation listener
		animation.setAnimationListener(this);
		anim.add(animation);
		animation= AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.rotate);
		anim.add(animation);
		animation= AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.zoom_in);
		anim.add(animation);
		
		animation= AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.zoom_out);
		anim.add(animation);
		
		animation= AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.slide_up);
		anim.add(animation);
	}
	
}

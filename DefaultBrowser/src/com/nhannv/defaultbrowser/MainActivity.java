package com.nhannv.defaultbrowser;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements ActionBar.OnNavigationListener{
	private WebView view;
	private EditText typeUrl;
	private Button clear;
	private ActionBar actionBar;
	private ArrayList<String> urls;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		urls = new ArrayList<>();
		setContentView(R.layout.activity_main);
		//set actionbar
		actionBar = getActionBar();
		//disable tittle
		actionBar.setDisplayShowTitleEnabled(false);
		//handleIntent(getIntent());
		//actionBar.setHomeButtonEnabled(true);
		view = (WebView) findViewById(R.id.web_view_act_wv);
		typeUrl = (EditText) findViewById(R.id.url_type_am_act);
		clear = (Button) findViewById(R.id.clear_am_bt);
		actionBar.hide();
		clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				typeUrl.setText("");

			}
		});
		// typeUrl.setText("http://vnexpress.net/rss/");
		view.loadUrl("http://google.com");
		typeUrl.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					String urltmp;
					//hide keyboard
					InputMethodManager imm = (InputMethodManager)getSystemService(
						      Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(typeUrl.getWindowToken(), 0);
					if (!typeUrl.getText().toString().contains("http")) {
						urltmp = "http://www." + typeUrl.getText().toString();
					} else {
						urltmp = typeUrl.getText().toString();

					}
					// url= url.substring(0, url.length()-1);
					Log.i("url", "|" + urltmp + "|");
					view.loadUrl(urltmp);
					typeUrl.setText(urltmp);
					urls.add(urltmp);
					
					
				}
				return false;
			}
		});
		view.getSettings().setJavaScriptEnabled(true);
		view.getSettings().setBuiltInZoomControls(true);
//		WebSettings webSettings = view.getSettings();
//	    webSettings.setPluginState(WebSettings.PluginState.ON);
//	    webSettings.setJavaScriptEnabled(true);
//	    webSettings.setUseWideViewPort(true);
//	    webSettings.setLoadWithOverviewMode(true);
		view.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				typeUrl.setText(url);
				if(url.contains("mp4")||url.contains("3gp")||url.contains("flv")){
					Intent video = new Intent(getApplicationContext(), PlayVideo.class);
					video.putExtra("url", url);
					startActivity(video);
				}
				return super.shouldOverrideUrlLoading(view, url);
			}
		});
		//view.setWebChromeClient(new WebChromeClient());
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onPostResume() {
		// TODO Auto-generated method stub
		super.onPostResume();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.actionbar_menu, menu);
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
		return super.onCreateOptionsMenu(menu);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int key = item.getItemId();
		switch (key) {
		case R.id.flash_enable_disable_m:
			PluginState state = view.getSettings().getPluginState();
			if (state == PluginState.OFF) {
				view.getSettings().setPluginState(PluginState.ON);
			} else {
				view.getSettings().setPluginState(PluginState.OFF);
			}
			break;

		case R.id.javascript_en_di_m:
			Boolean stateJavascript = view.getSettings().getJavaScriptEnabled();
			view.getSettings().setJavaScriptEnabled(!stateJavascript);
			
			break;
		default:
			break;
		}
		 view.loadUrl(typeUrl.getText().toString());
		return super.onOptionsItemSelected(item);
	}
		@Override
		public boolean onNavigationItemSelected(int itemPosition, long itemId) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public void onBackPressed() {
			// TODO Auto-generated method stub
			//super.onBackPressed();
			if(urls.size()>0){
				view.loadUrl(urls.remove(urls.size()-1));
			}else{
				super.onBackPressed();
			}
		}
}

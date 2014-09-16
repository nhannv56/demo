package quangdat.basic.hotgirl;

import java.util.ArrayList;
import java.util.List;

import nhannv.activity.hotgirl.FullView;
import quangdat.basic.adapter.AdapterImage;
import quangdat.basic.base.ItemImage;
import quangdat.basic.base.Variable;
import quangdat.basic.parse.myJsoup;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.etsy.android.grid.StaggeredGridView;

public class MainActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {
	private NavigationDrawerFragment mNavigationDrawerFragment;
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,
						PlaceholderFragment.newInstance(position + 1)).commit();
	}

	public void onSectionAttached(int number) {
		mTitle = Variable.menu_item[number - 1];
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment implements
			OnScrollListener {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		public StaggeredGridView gridView;
		List<ItemImage> lstData = new ArrayList<ItemImage>();
		public AdapterImage adapter;
		// String link_cate ="http://quaphe.net/models/page-";
		static int page = 1;
		static int index;

		// int index = getArguments().getInt(ARG_SECTION_NUMBER) - 1;

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */

		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			index = getArguments().getInt(ARG_SECTION_NUMBER) - 1;
			// Log.i("TAG", Integer.valueOf(index));
			gridView = (StaggeredGridView) rootView.findViewById(R.id.gridView);
			adapter = new AdapterImage(getActivity(), R.layout.item_image,
					lstData);

			gridView.setAdapter(adapter);
			gridView.setOnScrollListener(this);
			// updateLink(index);
			
			new TaskLoadDate().execute(Variable.link_item[index] + page);
			return rootView;
		}

		// private int updateLink(int index) {
		// String link_cate = Variable.link_item[index];
		// new TaskLoadDate().execute(link_cate + page);
		// return index;
		// }

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}

		class TaskLoadDate extends AsyncTask<String, Void, Void> {
			myJsoup jsoup;
			List<ItemImage> mlstData;
			ProgressDialog progress = new ProgressDialog(getActivity());
			@Override
			protected void onPreExecute() {
				mlstData = new ArrayList<ItemImage>();
				jsoup = new myJsoup();
				super.onPreExecute();
				progress.setIcon(R.drawable.ic_launcher);
				progress.setTitle("Hot Girl");
				progress.setMessage("Loading ... ");
				progress.setIndeterminate(false);
				progress.show();
			}

			@Override
			protected Void doInBackground(String... link_cate) {
				Log.e("Tag", link_cate[0] + "/");
				mlstData = jsoup.parseImageItemRoot(link_cate[0] + "/");
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				// adapter.clear();
				progress.dismiss();
				adapter.addAll(mlstData);
				adapter.notifyDataSetChanged();
				gridView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {

						// Intent intent = new Intent(getActivity()
						// .getApplicationContext(), ShowActivity.class);
						Intent intent = new Intent(getActivity()
								.getApplicationContext(), FullView.class);
						Log.e("tag", lstData.get(position).getThumb_item() + "");
						// Bundle bundle = new Bundle();
						// bundle.putString("thumb_item", lstData.get(position)
						// .getThumb_item());
						//
						// bundle.putString("link_item", lstData.get(position)
						// .getLink_item());
						// bundle.putString("title_item", lstData.get(position)
						// .getTitle_item());
						// intent.putExtras(bundle);
						// get url
						int lenght = lstData.size();
						ArrayList<String> urls = new ArrayList<String>();
						for (int i = 0; i < lenght; i++) {
							String url = lstData.get(i).getThumb_item();
							urls.add(url);
						}
						intent.putStringArrayListExtra("urls", urls);
						intent.putExtra("index", position);
						startActivity(intent);

					}
				});
			}
		}

		int firstVisibleItem, visibleItemCount, totalItemCount;

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			if (firstVisibleItem + visibleItemCount >= totalItemCount
					&& !Variable.isError && scrollState == SCROLL_STATE_IDLE) {
				page = page + 1;
				new TaskLoadDate().execute(Variable.link_item[index] + page);

			}

		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			this.firstVisibleItem = firstVisibleItem;
			this.visibleItemCount = visibleItemCount;
			this.totalItemCount = totalItemCount;
		}
	}

}

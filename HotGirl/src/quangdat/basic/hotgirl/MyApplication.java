package quangdat.basic.hotgirl;

import quangdat.basic.base.Variable;
import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MyApplication extends Application{

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		 // Create global configuration and initialize ImageLoader with this configuration
        Variable.config = new ImageLoaderConfiguration.Builder(getApplicationContext()).build();
        Variable.imageLoader.getInstance().init(Variable.config);
	}
}

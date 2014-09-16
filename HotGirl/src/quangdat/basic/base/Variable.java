package quangdat.basic.base;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import quangdat.basic.hotgirl.R;

public class Variable {
	public static ImageLoader imageLoader;
	public static ImageLoaderConfiguration config;
	public static DisplayImageOptions options;
	public static boolean isError;
	public static String menu_item[] = { "Trang chủ ", "Người Mẫu",
			"Ngẫu Nhiên", "Sexy", "Nude", "Hàng Ngoại", "Bikini", "Dịu Dàng",
			"Áo Dài", "Xem thêm", "Người Đẹp" };
	public static String link_item[] = { "http://quaphe.net/page-",
			"http://quaphe.net/models/page-", "http://quaphe.net/random/page-",
			"http://quaphe.net/sexy/page-", "http://quaphe.net/bannude/page-",
			"http://quaphe.net/hangngoai/page-",
			"http://quaphe.net/bikini/page-",
			"http://quaphe.net/diudang/page-", "http://quaphe.net/aodai/page-",
			"http://quaphe.net/xitin/page-",
			"http://www.tamdiem.net/nguoi-dep/" };
	public static final int image_item[] = { R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.ic_launcher };
}

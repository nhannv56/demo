package nhannv.com.internet;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class CheckInternet {
	public static boolean checkInternet(Context context) {
		ConnectivityManager connectM = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectM != null) {
			NetworkInfo[] networks = connectM.getAllNetworkInfo();
			if (networks != null) {

				for (int i = 0; i < networks.length; i++) {
					if (networks[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}
}

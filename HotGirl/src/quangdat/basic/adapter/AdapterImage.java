package quangdat.basic.adapter;

import java.util.List;

import quangdat.basic.base.ItemImage;
import quangdat.basic.base.Variable;
import quangdat.basic.hotgirl.R;
import android.content.Context;
import android.graphics.Bitmap.Config;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.etsy.android.grid.util.DynamicHeightImageView;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class AdapterImage extends ArrayAdapter<ItemImage> {

	Context mContext;
	int layoutId;
	List<ItemImage> lstData;
	LayoutInflater inflater;
	

	class ViewHolder {
		private DynamicHeightImageView thumbs_item;
		//private TextView title_item;

		public ViewHolder(View view) {
			thumbs_item = (DynamicHeightImageView) view.findViewById(R.id.thumb_item);
			//title_item = (TextView) view.findViewById(R.id.title_item);
		}

		public void Buidld(String pathImage, String title) {
			if (pathImage == null) {
				thumbs_item.setImageResource(R.drawable.ic_launcher);
			} else {
				Variable.imageLoader.displayImage(pathImage, thumbs_item, Variable.options);
			}
			//title_item.setText(title);
		}
	}

	public AdapterImage(Context context, int layoutId, List<ItemImage> lstData) {
		super(context, layoutId, lstData);
		this.mContext = context;
		this.layoutId = layoutId;
		this.lstData = lstData;
		inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Variable.config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).build();
		ImageLoader.getInstance().init(Variable.config);
		Variable.options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(android.R.drawable.gallery_thumb)
				.showImageOnLoading(android.R.drawable.gallery_thumb)
				.showImageOnFail(android.R.drawable.gallery_thumb)
				.cacheOnDisk(true).considerExifParams(true)
				.imageScaleType(ImageScaleType.EXACTLY)
				.bitmapConfig(Config.RGB_565).build();
		Variable.imageLoader = ImageLoader.getInstance();
		Variable.imageLoader.init(Variable.config);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(layoutId, parent, false);
			holder = new ViewHolder(convertView);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ItemImage item = lstData.get(position);
		holder.Buidld(item.getThumb_item(), item.getTitle_item());
		return convertView;
	}

}

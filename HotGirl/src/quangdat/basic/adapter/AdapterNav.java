package quangdat.basic.adapter;

import java.util.List;

import quangdat.basic.base.MenuNav;
import quangdat.basic.hotgirl.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterNav extends ArrayAdapter<MenuNav> {
	Context context;
	int layoutId;
	List<MenuNav> lstData;
	LayoutInflater inflater;
	ViewHolder viewHolder;

	public AdapterNav(Context context, int layoutId, List<MenuNav> lstData) {
		super(context, layoutId, lstData);
		this.context = context;
		this.layoutId = layoutId;
		this.lstData = lstData;
		inflater = (LayoutInflater) context
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(layoutId, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.ivImg_nav = (ImageView) convertView
					.findViewById(R.id.ivImg_nav);
			viewHolder.tvTitle_nav = (TextView) convertView
					.findViewById(R.id.tvTitle_nav);
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder)convertView.getTag();
		}
		MenuNav item = lstData.get(position);
		viewHolder.ivImg_nav.setImageResource(item.getImageId());
		viewHolder.tvTitle_nav.setText(item.getTitle());
		return convertView;
	}

	class ViewHolder {
		ImageView ivImg_nav;
		TextView tvTitle_nav;
	}
}
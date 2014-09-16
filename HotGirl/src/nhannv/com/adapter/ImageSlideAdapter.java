package nhannv.com.adapter;

import java.util.ArrayList;

import quangdat.basic.hotgirl.R;

import nhannv.activity.hotgirl.FullView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.nostra13.universalimageloader.core.ImageLoader;

public class ImageSlideAdapter extends PagerAdapter {
	
	private Context context;
	private ArrayList<String> urlImage;
	public static float scale = 1;
	private ImageLoader imageLoader;

	public ImageSlideAdapter(Context context, ArrayList<String> url) {
		this.context = context;
		urlImage = url;
		// ImageLoaderConfiguration config = new
		// ImageLoaderConfiguration.Builder(
		// context.getApplicationContext()).build();
		// ImageLoader.getInstance().init(config);
		imageLoader = ImageLoader.getInstance();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return urlImage.size();
	}

	@Override
	public Object instantiateItem(View container, int position) {
		final ImageView view = new ImageView(context);
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		imageLoader.displayImage(urlImage.get(position), view);
		
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FullView.autoPlayState = false;
				FullView.autoPlayButton
						.setImageResource(R.drawable.ic_action_play_over_video);
			}
		});
		ZoomImage setZoom = new ZoomImage();
		setZoom.zoomImage(view);
		((ViewPager) container).addView(view, 0);
		return view;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == ((View) arg1);
	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewPager) arg0).removeView((View) arg2);
	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	public class ZoomImage {

		Matrix matrix = new Matrix();
		Matrix savedMatrix = new Matrix();
		PointF startPoint = new PointF();
		PointF midPoint = new PointF();
		float oldDist = 1f;
		static final int NONE = 0;
		static final int DRAG = 1;
		static final int ZOOM = 2;
		int mode = NONE;

		public void zoomImage(ImageView imageDetail) {

			/** * set on touch listner on image */
			imageDetail.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					
					ImageView view = (ImageView) v;
					view.setScaleType(ScaleType.MATRIX);
					System.out.println("matrix=" + savedMatrix.toString());
					switch (event.getAction() & MotionEvent.ACTION_MASK) {
					case MotionEvent.ACTION_DOWN:
						savedMatrix.set(matrix);
						startPoint.set(event.getX(), event.getY());
						mode = DRAG;
						break;
					case MotionEvent.ACTION_POINTER_DOWN:
						oldDist = spacing(event);
						if (oldDist > 10f) {
							savedMatrix.set(matrix);
							midPoint(midPoint, event);
							mode = ZOOM;
						}
						break;
					case MotionEvent.ACTION_UP:
					case MotionEvent.ACTION_POINTER_UP:
						mode = NONE;
						break;
					case MotionEvent.ACTION_MOVE:
						if (mode == DRAG) {
							matrix.set(savedMatrix);
							matrix.postTranslate(event.getX() - startPoint.x,
									event.getY() - startPoint.y);
						} else if (mode == ZOOM) {
							float newDist = spacing(event);
							if (newDist > 10f) {
								matrix.set(savedMatrix);
								float scale = newDist / oldDist;
								matrix.postScale(scale, scale, midPoint.x,
										midPoint.y);
							}
						}
						break;
					}
					view.setImageMatrix(matrix);
					return true;
				}

				@SuppressLint("FloatMath")
				private float spacing(MotionEvent event) {
					float x = event.getX(0) - event.getX(1);
					float y = event.getY(0) - event.getY(1);
					return FloatMath.sqrt(x * x + y * y);
				}

				private void midPoint(PointF point, MotionEvent event) {
					float x = event.getX(0) + event.getX(1);
					float y = event.getY(0) + event.getY(1);
					point.set(x / 2, y / 2);
				}
			});
		}
	}
}

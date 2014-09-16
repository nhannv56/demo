package quangdat.basic.parse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;
import quangdat.basic.base.ItemImage;
import quangdat.basic.base.ItemNext;
import quangdat.basic.base.Variable;

public class myJsoup {
	private Document doc;

	public myJsoup() {
		super();
	}

	public List<ItemImage> parseImageItemRoot(String link_category) {
		List<ItemImage> lstData = new ArrayList<ItemImage>();
		try {
			doc = Jsoup.connect(link_category).get();
			Elements elements = doc.select("div.grid-item-photo");
			for (Element element : elements) {
				String title_item = element.select("h2").text();
				String link_item = element.select("a").attr("href");
				String thumb_item = element.select("img").attr("src");
				lstData.add(new ItemImage(thumb_item, link_item, title_item));
			}

		} catch (IOException e) {
			e.printStackTrace();
			Variable.isError = true;
			return null;
		}

		return lstData;

	}

	public List<ItemNext> parseNext(String link_category) {
		List<ItemNext> lstData = new ArrayList<ItemNext>();
		try {
			doc = Jsoup.connect(link_category).get();
			Elements elements = doc.select("div.photo-next-prev");
			for (Element element : elements) {
				
				String link_back = element.select("a").attr("href");
				Log.e("TAG LInk back", link_back);
				String link_next = element.select("a").attr("href");
				Log.e("TAG LInk next", link_next);
				// String link_img = element.select("img").attr("src");
				lstData.add(new ItemNext(link_back, link_next));
			}

		} catch (IOException e) {
			e.printStackTrace();
			Variable.isError = true;
			return null;
		}
		return lstData;
	}

}

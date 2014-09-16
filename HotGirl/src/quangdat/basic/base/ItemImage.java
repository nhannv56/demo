package quangdat.basic.base;

public class ItemImage {
	private String thumb_item;
	private String link_item;
	private String title_item;

	public ItemImage(String thumb_item, String link_item, String title_item) {
		super();
		this.thumb_item = thumb_item;
		this.link_item = link_item;
		this.title_item = title_item;
	}

	public String getThumb_item() {
		return thumb_item;
	}

	public void setThumb_item(String thumb_item) {
		this.thumb_item = thumb_item;
	}

	public String getLink_item() {
		//return "http://quaphe.net/"+link_item;
		return link_item;
	}

	public void setLink_item(String link_item) {
		this.link_item = link_item;
	}

	public String getTitle_item() {
		return title_item;
	}

	public void setTitle_item(String title_item) {
		this.title_item = title_item;
	}

}

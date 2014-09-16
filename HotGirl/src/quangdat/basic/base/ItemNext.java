package quangdat.basic.base;

public class ItemNext {
	private String link_back, link_next;

	public ItemNext() {
		super();
	}

	public ItemNext(String link_back, String link_next) {
		super();
		this.link_back = link_back;
		this.link_next = link_next;
		
	}

	public String getLink_back() {
		return link_back;
	}

	public void setLink_back(String link_back) {
		this.link_back = link_back;
	}

	public String getLink_next() {
		return link_next;
	}

	public void setLink_next(String link_next) {
		this.link_next = link_next;
	}

}

package quangdat.basic.base;

public class MenuNav {
	private String title;
	private int imageId;

	public MenuNav() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MenuNav(String title, int imageId) {
		super();
		this.title = title;
		this.imageId = imageId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

}

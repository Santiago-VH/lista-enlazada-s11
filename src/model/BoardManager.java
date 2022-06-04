package model;

public class BoardManager {

	private Link link;
	
	public BoardManager() {
		this.link= new Link(1,false);
	}

	public void organizeList(int columns, int rows) {
		link.createList(columns, rows, link);
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	
}

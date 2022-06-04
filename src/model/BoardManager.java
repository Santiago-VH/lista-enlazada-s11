package model;

public class BoardManager {

	private Link firstLink;
	
	public BoardManager() {
		this.firstLink= new Link(1,false);
	}

	public void organizeList(int columns, int rows) {
		firstLink.createList(columns, rows, firstLink);
	}

	public Link getFirstLink() {
		return firstLink;
	}

	public void setFirstLink(Link firstLink) {
		this.firstLink = firstLink;
	}



	
}

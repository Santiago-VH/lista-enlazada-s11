package model;

public class Link {

	private Link next;
	private Link previous;
	private int id;
	private boolean isPlayer;
	
	public Link(int id, boolean isPlayer) {
		this.id=id;
		isPlayer=false;
	}
	
	public void createList(int columns, int rows, Link firstLink) {
		Link current = firstLink;
		firstLink.setPlayer(true);
		for (int i = 1; i < columns*rows; i++) {
			int id = i + 1;
			Link newLink = new Link(id,false);
			newLink.setPrevious(current);
			current.setNext(newLink);
			current = current.getNext();
		}
		current.setNext(firstLink);
		firstLink.setPrevious(current);
	}
	
	public Link searchPlayer(int columns, int rows) {
		Link current = this;
		boolean stop=false;
		for (int i = 0; i < columns*rows&&stop==false; i++) {
			if (current.isPlayer() == true) {
				stop=true;
				return current;
			}
			current = current.getNext();
		}
		return null;
	}
	
	public int getPlayerPosition(int columns, int rows) {
		Link current = this;
		for(int i=0; i<columns*rows;i++) {
			if(current.isPlayer()) {
				return current.getId();
			}
		}
		return 0;
	}
	

	public Link getNext() {
		return next;
	}

	public void setNext(Link next) {
		this.next = next;
	}

	public Link getPrevious() {
		return previous;
	}

	public void setPrevious(Link previous) {
		this.previous = previous;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isPlayer() {
		return isPlayer;
	}

	public void setPlayer(boolean isPlayer) {
		this.isPlayer = isPlayer;
	}
	
}

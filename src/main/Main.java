package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.BoardManager;
import model.Link;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BoardManager manager = new BoardManager();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Numero de columnas \n");
		int columns = Integer.parseInt(br.readLine());

		while (columns < 0) {
			System.out.println("No digites numeros negativos, intenta denuevo \n");
			columns = Integer.parseInt(br.readLine());
		}
		
		System.out.println("Numero de filas\n");
		int rows = Integer.parseInt(br.readLine());

		while (rows < 0) {
			System.out.println("No digites numeros negativos, intenta denuevo \n");
			rows = Integer.parseInt(br.readLine());
		}
		
		while (rows*columns <= 0) {
			System.out.println("Porfavor no digite ceros en el programa, reinicielo y digite valores validos\n");
			rows = Integer.parseInt(br.readLine());
		}
		
		manager.organizeList(columns, rows);
		
		int option;
		boolean stop=false;
		
		
		do { 
			System.out.println("Qué deseas hacer? \n\n"
							+ "1.Tirar el dado \n"
							+ "2.Mostrar el tablero\n"
							+ "3.Salir");
			option=Integer.parseInt(br.readLine());

			do {
			switchMenu(columns, rows, option, stop, manager);
			option = Integer.parseInt(br.readLine());
			} while(option!=1);
				
			int diceValue = throwDice();	
				System.out.println("Sacaste "+diceValue+"\n");
				
				
				Link current;
				current=manager.getFirstLink().searchPlayer(columns, rows);
				current.setPlayer(false);
				moveForward(diceValue, current);
			
		} while(manager.getFirstLink().getPlayerPosition(columns, rows)!=rows*columns&&stop==false);
		
		System.out.println("finish");
	}
	
	public static void switchMenu(int columns, int rows, int option, boolean stop, BoardManager manager) {

		switch (option) {

		case 2:
			System.out.println(showList(columns, rows, manager));
			break;

		case 3:
			stop=true;
			System.out.println("El sistema lo sacará al terminar el turno");
			break;

		}
	}
	
	public static Link moveForward(int diceValue, Link current) {
		if (diceValue == 0 ) {
			current.setPlayer(true);
		} else {
			return moveForward(diceValue - 1, current.getNext());

		}
		return current;
	}
	
	public static int throwDice() {
		int diceValue = (int) (Math.random() * (6) + 1);
		return diceValue;

	}
	
	public static String showList(int columns, int rows, BoardManager manager) {
		String boardView = "";
		boolean isPar = false;
		Link current = manager.getFirstLink();
		for (int i = 0; i < rows * columns; i++) {
			if (current.getId() % columns == 0 && !isPar) {
				if (current.isPlayer()) {
					boardView += "[J]  " + "\n";
				} else {
					boardView += "[" + current.getId() + "] \n";
				}
				isPar = true;

			} else if (current.getId() % columns != 0 && !isPar) {
				if (current.isPlayer()) {
					boardView += "[J]  ";
				} else {
					boardView += "[" + current.getId() + "]  ";
				}

			} else if (current.getId() % columns == 0 && isPar) {

				Link temp = current;
				do {

					if (temp.isPlayer()) {
						boardView += "[J]  ";
					}else {
						boardView += "[" + temp.getId() + "]  ";
					}
					
					temp = temp.getPrevious();

				} while (temp.getId() % columns != 0);
				boardView += "\n";
				isPar = false;
			}
			current = current.getNext();

		}
		return boardView;
	}
}

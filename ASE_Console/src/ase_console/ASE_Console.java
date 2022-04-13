/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package ase_console;

import java.time.LocalDate;

import makeYourDay.core.Address;
import makeYourDay.core.Coordinate;
import makeYourDay.core.Note;
import makeYourDay.core.Position;
import makeYourDay.core.Task;
import makeYourDay.enums.Direction;
import makeYourDay.enums.Priority;
import makeYourDay.exceptions.InvalidHouseNumberException;
import makeYourDay.kanban.Categorie;

/**
 *
 * @author angenjo1
 */
public class ASE_Console {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		String helloWorld = "hello world";
		Position cor = new Position(Direction.N, (short) 17, (byte) 28, (byte) 54, Direction.W, (short) 161, (byte) 22,
				(byte) 42);

		Task task1 = new Task("Einkaufen",LocalDate.now(), Priority.HIGH);

		
		Categorie cat = new Categorie(Priority.LOW);
		cat.addItem(0, task1);
		task1 = cat.removeItem(0);
		Priority prio = task1.getPriority();
		System.out.print(prio.getPriorityValue());
		Note note = new Note("TEST");
		int i = note.MAX_TEXT_LENGTH;
	}

}

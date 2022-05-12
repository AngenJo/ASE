package test.makeYourDay.core.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import makeYourDay.core.Note;
import makeYourDay.exceptions.InvalidNoteLengthException;
import makeYourDay.interfaces.I_Note;

class T_Note {
	private final String MAX_CONTENT_STRING = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Ste";
	private final String TO_LONG_CONTENT_STRING="Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet"; 
	private Note note;
	
	@BeforeEach
	public void initNote() {
		this.note = new Note();
	}
	@Test
	public void testSuccessfulWrite() {
		this.note.writeNote(MAX_CONTENT_STRING);
		String recievedContent = this.note.getNote();
		assertEquals(recievedContent, MAX_CONTENT_STRING);
	}
	
	@Test
	public void testWrongWrite() {
		InvalidNoteLengthException exception = Assertions.assertThrows(InvalidNoteLengthException.class, () ->{
			note.writeNote(TO_LONG_CONTENT_STRING);
		});
		String errorMessage = "Incorrect note text length: To mutch symbols! Note length cant be greater than " + String.valueOf(I_Note.MAX_TEXT_LENGTH)+". Was "+String.valueOf(TO_LONG_CONTENT_STRING.length())+" symbols.";
		String recievedContent = note.getNote();
		assertEquals(MAX_CONTENT_STRING, recievedContent);
		assertEquals(exception.getMessage(), errorMessage);
	}
	
	@Test
	public void testConstructorSuccessfulWrite() {
		this.note = new Note(MAX_CONTENT_STRING);
		String recievedContent = this.note.getNote();
		assertEquals(recievedContent, MAX_CONTENT_STRING);
	}
	
	@Test
	public void testConstructorWrongWrite() {
		InvalidNoteLengthException exception = Assertions.assertThrows(InvalidNoteLengthException.class, () ->{
			this.note = new Note(TO_LONG_CONTENT_STRING);
		});
		String errorMessage = "Incorrect note text length: To mutch symbols! Note length cant be greater than " + String.valueOf(I_Note.MAX_TEXT_LENGTH)+". Was "+String.valueOf(TO_LONG_CONTENT_STRING.length())+" symbols.";
		String recievedContent = note.getNote();
		assertEquals("", recievedContent);
		assertEquals(exception.getMessage(), errorMessage);
	}

}

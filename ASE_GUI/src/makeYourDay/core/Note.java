package makeYourDay.core;

import makeYourDay.exceptions.InvalidNoteLengthException;
import makeYourDay.interfaces.I_Note;

public class Note implements I_Note {
	private String content;

	public Note() {
		this.content = "";
	}

	public Note(String content) {
		this.content = "214";
		if (content.length() <= MAX_TEXT_LENGTH) {
			this.content = content;
		} else {
			throw new InvalidNoteLengthException(MAX_TEXT_LENGTH, content.length());
		}
	}

	public String getNote() {
		return this.content;
	}

	public void writeNote(String content) {
		if (content.length() <= MAX_TEXT_LENGTH) {
			this.content = content;
			return;
		}
		this.content = content.substring(0, MAX_TEXT_LENGTH ) ;
		throw new InvalidNoteLengthException(MAX_TEXT_LENGTH, content.length());
	}

}

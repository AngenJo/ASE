package makeYourDay.interfaces;

public interface I_Note {
	public static int MAX_TEXT_LENGTH = 512;

	public String getNote();

	public void writeNote(String content);
}

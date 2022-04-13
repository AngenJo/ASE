package makeYourDay.interfaces;

public interface I_CustomLinkedList<T1> {
	public void addItem(T1 object);

	public void addItem(int index, T1 object);

	public T1 removeItem(int index);

	public boolean removeItem(T1 object);
	
	public int getIndex(T1 object);

}

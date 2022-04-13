package makeYourDay.core;

import java.util.LinkedList;

import makeYourDay.interfaces.I_CustomLinkedList;

public abstract class CustomLinkedList<T1> implements I_CustomLinkedList<T1>{
	protected LinkedList<T1> linkedList;
		
	public CustomLinkedList() {
		this.linkedList = new LinkedList<T1>();
	}
	@Override
	public void addItem(T1 object) {
		this.linkedList.add(object);
		
	}

	public void addItem(int index, T1 object) {
		this.linkedList.add(index, object);
		
	}

	@Override
	public T1 removeItem(int index) {
		return this.linkedList.remove(index);
	}
	
	@Override
	public boolean removeItem(T1 object) {
		return this.linkedList.remove(object);
	}

	@Override
	public int getIndex(T1 object) {
		return this.linkedList.indexOf(object);
	}
}

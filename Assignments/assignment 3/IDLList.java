//Name : YASH NAVADIYA 
//CWID: 10455328
//class = CS570-B
//Assignment - 3 

package Doublyll;

import java.util.ArrayList;

public class IDLList<E> {

	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices = new ArrayList<>();

	private static class Node<E> {

		E data;
		private Node<E> next = null;
		private Node<E> prev = null;

		Node(E elem) {
			this.data = elem;
		}

		Node(E elem, Node<E> prev, Node<E> next) {
			this.data = elem;
			this.prev = prev;
			this.next = next;
		}
	}

	// this function will create an empty double-linked list
	public IDLList() {
		this.head = null;
		this.tail = null;
		size = 0;
	}

	// this function will Add an element at specified index
	public boolean add(int index, E elem) {
		if (index < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (index > size) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> n1 = indices.get(index);
		Node<E> newnode = new Node<E>(elem, n1.prev, n1);
		n1.prev.next = newnode;
		n1.prev = newnode;
		indices.add(index, newnode);
		size++;
		return true;
	}

	// this function will Add an element at the head
	public boolean add(E elem) {
		if (head == null) {
			Node<E> newnode = new Node<E>(elem);
			newnode.data = elem;
			head = newnode;
			tail = newnode;
			indices.add(head);
			size++;
			return true;
		} else {
			Node<E> newnode = new Node<E>(elem);
			newnode.next = head;
			head.prev = newnode;
			head = newnode;
			indices.add(0, newnode);
			size++;
			return true;
		}

	}

	// this function will Add an element as the new last element of the list
	public boolean append(E elem) {
		if (head == null) {
			Node<E> n1 = new Node<E>(elem);
			n1.data = elem;
			head = n1;
			tail = n1;
			indices.add(head);
			size++;
			return true;
		} else {
			Node<E> newNode = new Node<E>(elem);
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
			indices.add(tail);
			size++;
			return true;
		}
	}

	// this function will return the object at position index from the head
	public E get(int index) {
		Node<E> getvalue = indices.get(index);
		return getvalue.data;
	}

	// this function will return the object at the head
	public E getHead() {
		Node<E> getvalue = indices.get(0);
		return getvalue.data;
	}

	// this function will return the object at the tail
	public E getLast() {
		Node<E> getvalue = tail;
		return getvalue.data;
	}

	// this function will return the size of the list
	public int size() {
		int a = indices.size();
		return a;
	}

	// this function will remove and return the element at the head
	public E remove() {
		if (size == 0) {
			throw new IndexOutOfBoundsException();
		}
		if (tail == head) {
			Node<E> tmp = head;
			head = null;
			tail = null;
			size--;
			return tmp.data;
		}
		Node<E> n1 = head;
		head = head.next;
		head.prev = null;
		indices.remove(n1);
		size--;
		return n1.data;
	}

	// this function will remove and return the element at the tail
	public E removeLast() {
		if (size == 0) {
			throw new IndexOutOfBoundsException();
		}
		if (tail == head) {
			Node<E> temp = head;
			head = null;
			tail = null;
			size--;
			return temp.data;
		}
		Node<E> n1 = tail;
		tail = tail.prev;
		tail.next = null;
		indices.remove(n1);
		size--;
		return n1.data;
	}

	// this function will remove and return the element at the index index
	public E removeAt(int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			E tempnode = this.remove();
			return tempnode;
		}
		Node<E> tempnode = indices.get(index);
		tempnode.prev.next = tempnode.next;
		tempnode.next.prev = tempnode.prev;
		indices.remove(tempnode);
		size--;
		return tempnode.data;
	}

	// this function will remove the first occurrence of in the list and return true
	public boolean remove(E elem) {
		Node<E> tmp = head;
		int i = 0;
		while (tmp.next != null) {
			if (tmp.data.equals(elem)) {
				if (i == 0) {
					head = head.next;
					size--;
					indices.remove(0);
					return true;
				}
				tmp.next.prev = tmp.prev;
				tmp.prev.next = tmp.next;
				size--;
				indices.remove(i);
				return true;
			}
			tmp = tmp.next;
			i++;
		}
		return false;
	}

	// this function will represent string of all the elements in the list
	public String toString() {
		Node<E> referencenode = head;
		StringBuilder result = new StringBuilder();
		while (referencenode != null) {
			result.append(referencenode.data);
			if (referencenode.next != null) {
				result.append(" -> ");
			}
			referencenode = referencenode.next;
		}
		return result.toString();
	}
}

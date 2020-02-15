// Yash Navadiya
// CWID - 10455328
// Subject - CS570B(Data Structure)
// Assignment - 5

package Treap;

import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {

	private class Node<E> {
		// Data fields for Node class.
		public E data;
		public int priority;
		public Node<E> left;
		public Node<E> right;

		// Constructor
		/**
		 * 
		 * @param data
		 * @param priority
		 */
		public Node(E data, int priority) {
			if (data == null) {
				throw new IllegalArgumentException();
			} else {
				this.data = data;
				this.priority = priority;
				this.left = null;
				this.right = null;
			}
		}

		// Methods

		/**
		 * rotate the treap right and update data
		 * 
		 * @return
		 */

		public Node<E> rotateRight() {
			Node<E> temp = this.left;
			Node<E> left = temp.right;
			temp.right = this;
			this.left = left;
			return temp;
		}

		/**
		 * rotate the treap left and update data
		 * 
		 * @return
		 */
		public Node<E> rotateLeft() {
			Node<E> tmp = this.right;
			Node<E> right = tmp.left;
			tmp.left = this;
			this.right = right;
			return tmp;
		}

		public String toString() {
			return this.data.toString();
		}
	}

	// data fields for treap class
	private Random priorityGenerator;
	private Node<E> root;

	/**
	 * this method create an empty treap
	 */
	public Treap() {
		priorityGenerator = new Random();
		root = null;
	}

	/**
	 * 
	 * @param seed
	 */
	public Treap(long seed) {
		priorityGenerator = new Random(seed);
		root = null;
	}

	// methods for treap class
	/**
	 * 
	 * @param stack
	 * @param current
	 */
	private void reheap(Stack<Node<E>> stack, Node<E> current) {
		if (stack.isEmpty()) {
			root = current;
		} else if (current.priority < stack.peek().priority) {
			return;
		} else {
			if (stack.peek().right == current) {
				Node<E> removed = stack.pop();
				removed.rotateLeft();
				if (!stack.isEmpty()) {
					if (stack.peek().right == removed) {
						stack.peek().right = current;
					} else {
						stack.peek().left = current;
					}
				}
			} else {
				Node<E> removed = stack.pop();
				removed.rotateRight();
				if (!stack.isEmpty()) {
					if (stack.peek().right == removed) {
						stack.peek().right = current;
					} else {
						stack.peek().left = current;
					}
				}
			}
			reheap(stack, current);
		}
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	boolean add(E key) {
		return add(key, priorityGenerator.nextInt());
	}

	/**
	 * 
	 * @param key
	 * @param priority
	 * @return
	 */
	public boolean add(E key, int priority) {
		if (root == null) {
			root = new Node<E>(key, priority);
			return true;
		}

		Node<E> temporary = root;
		boolean insert = false;
		Stack<Node<E>> s = new Stack<Node<E>>();

		while (insert == false) {
			if (key.compareTo(temporary.data) == 0) {
				break;
			}

			if (key.compareTo(temporary.data) < 0) {
				s.push(temporary);
				if (temporary.left == null) {
					temporary.left = new Node<E>(key, priority);
					temporary = temporary.left;
					insert = true;
				} else {
					temporary = temporary.left;
				}
			} else {
				s.push(temporary);
				if (temporary.right == null) {
					temporary.right = new Node<E>(key, priority);
					temporary = temporary.right;
					insert = true;
				} else {
					temporary = temporary.right;
				}
			}
		}

		reheap(s, temporary);
		return insert;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public boolean delete(E key) {
		if (find(key) == false || root == null) {
			return false;
		} else {
			root = delete(root, key);
			return true;
		}
	}

	/**
	 * 
	 * @param n
	 * @param key
	 * @return
	 */
	private Node<E> delete(Node<E> n, E key) {
		if (n != null) {
			int compare = key.compareTo(n.data);
			if (compare > 0) {
				n.right = delete(n.right, key);
			} else if (compare < 0) {
				n.left = delete(n.left, key);
			} else {
				if (n.right == null) {
					return n.left;
				} else if (n.left == null) {
					return n.right;
				} else {
					Node<E> temp = n.right;
					while (temp.left != null) {
						temp = temp.left;
					}
					n.data = temp.data;
					n.right = delete(n.right, n.data);
				}
			}
		}
		return n;
	}

	/**
	 * 
	 * @param root
	 * @param key
	 * @return
	 */
	public boolean find(Node<E> root, E key) {
		if (root == null) {
			return false;
		}
		if (key.compareTo(root.data) == 0) {
			return true;
		} else if (key.compareTo(root.data) < 0) {
			return find(root.left, key);
		} else {
			return find(root.right, key);
		}
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public boolean find(E key) {
		return find(root, key);
	}

	/**
	 * 
	 * @param node
	 * @param depth
	 * @param sb
	 */
	private void traverse(Node<E> node, int depth, StringBuilder sb) {
		for (int i = 1; i < depth; i++) {
			sb.append("  ");
		}
		if (node == null) {
			sb.append("null\n");
		} else {
			sb.append("( key = " + node.toString() + ", ");
			sb.append(" Priority =");
			sb.append(node.priority);
			sb.append(" ) ");
			sb.append("\n");
			traverse(node.left, depth + 1, sb);
			traverse(node.right, depth + 1, sb);
		}
	}

	/**
	 * 
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		traverse(root, 1, s);
		return s.toString();
	}

	public static void main(String[] args) {
		Treap<Integer> test = new Treap<Integer>();
		test.add(1, 84);
		test.add(4, 19);
		test.add(5, 83);
		test.add(2, 31);
		test.add(6, 70);
		System.out.println(test.toString());
		System.out.println(test.find(5));
		test.add(3, 12);
		System.out.println(test.toString());
		System.out.println(test.delete(1));
		System.out.println(test.toString());

	}

}

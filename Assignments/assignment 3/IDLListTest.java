package Doublyll;

public class IDLListTest {

	public static void main(String[] args) {
		IDLList<Integer> list = new IDLList<Integer>();
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(10);
		list.add(1);
		System.out.println(list.toString());
		list.append(34);
		System.out.println(list.toString());
		list.add(3, 56);
		System.out.println(list.toString());
		System.out.println(list.remove());
		System.out.println(list.toString());
		System.out.println(list.removeLast());
		System.out.println(list.toString());
		System.out.println(list.removeAt(3));
		System.out.println(list.toString());
		System.out.println(list.remove(6));
		System.out.println(list.toString());

	}

}

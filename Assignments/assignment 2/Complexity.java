// Name : Yashnavadiya
//  Cwid : 10455328
// Subject : CS570B
// Assignment 2 : complexity

package complexity;

public class Complexity {

	//  method that has time complexity O(n^2) 
	 public static void method1(int n) {
			int counter = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.println("Operation " + counter);
					counter++;
					}
				}
			System.out.println("Time Complexity  of method 1 is O(n^2) that is : " + counter);
		 }
	 
	 
	// method that has time complexity O(n^3)
	 public static void method2(int n) {
		 int counter = 0;
		 for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
					for (int k = 0; k < n; k++) {
						System.out.println("Operation " + counter);
						counter++;
					}
				}
		 	}
		 System.out.println("Time Complexity  of method 2 is O(n^3) that is : " + counter);
	 }
	 
	// method that has time complexity O(log n)
	 public static void method3(int n) {
		 int counter = 0;
		 for (int i = 1; i < n; i = i * 2) {
			 System.out.println("Operation " + counter);
			 counter++;
		 }
		 System.out.println("Time Complexity  of method 3 is O(log n) that is " + counter);
	 }
	 

	// method that has time complexity O(n log n)
	 public static void method4(int n) {
		 int counter = 0;
		 for (int i = 1; i <= n; i++) {
			    for(int j = 1; j < n; j = j * 2) {
			    	System.out.println("Operation " + counter);
			    	counter++;
			    }
		}
		 System.out.println("Time Complexity  of method 4 is O(n log n) that is " + counter);
	 
	 }

	// method that has time complexity O(log log n)
	 public static void method5(int n) {
	 int counter = 0;
	 for (double j = 2; j < n; j = j * j) {
		 System.out.println("Operation " + counter);
		 counter++;
	}
	 System.out.println("Time Complexity  of method 5 is O(log log n) that is " + counter);
	 
	 }

public static void main(String[] args) {
		 
		 System.out.println("complexity for method 1 :");
		 Complexity.method1(3);
		 System.out.println("complexity for method 2 :");
		 Complexity.method2(3);
		 System.out.println("complexity for method 3 :");
		 Complexity.method3(16);
		 System.out.println("complexity for method 4 :");
		 Complexity.method4(16);
		 System.out.println("complexity for method 5 :");
		 Complexity.method5(16);
	 }	 
}


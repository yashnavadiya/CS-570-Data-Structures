// YashNavadiya
//CWID - 10455328
//Assignment 1 - CS 570B

package binarynumber;

public class BinaryNumber {
	
	private int data[];
	private boolean overflow;
	
	
	//Creating a binary number of length "length" and consisting only of zeros.	
	
	public BinaryNumber(int length) 
	{
		data = new int[length];
		for(int i=0;i<length;i++) {
			data[i]=0;
		}
	}
	
	
	//A constructor for creating a binary number given a string.
	
	public BinaryNumber(String str)
	{
		int length = str.length();
		data = new int[length];
		for(int k=0; k<length; k++) 
		{
			int a = Character.getNumericValue(str.charAt(k)) ;
			if(a == 0 || a== 1 ) {
			data[k] = a;
			}
			else {
				System.out.println("Error : It's not a binary number, please check the number");
			}
		}
	}
	
	//length of a binary number.

	 public  int getLength()
	 {
       int a = data.length;
		 return a;
	 } 
	
	 
	 // get digit by entering the index value
	 
	 public int getDigit(int index)
	 {
			if(index > data.length) {
				System.out.println("Error : This index is  Out of bound");
				System.exit(1);
				return 0;
	 		}
			else {
			return data[index];
			}
	}
	 
	 
	 //for performing right shift operation
	 
	 public void shiftR(int amount) 
	 {
		 BinaryNumber Shift = new BinaryNumber(data.length + amount);
			for (int i = amount; i < Shift.getLength(); i++) {
				Shift.data[i] = data[i - amount];
			}
			this.data = Shift.data;
			System.out.println("number after shift operation is : "+ this.toString());
		 }
	 
	 
	 // for converting a binary number to a decimal number
	 
	 public int toDecimal() 
	 {
			int value = 0;
			for (int i = 0; i < data.length; i ++) {
				value = value * 2 + data[i];
			}
			return value;
		}
	 
	 
	 // addition of two binary numbers
	 
	 public void add(BinaryNumber aBinaryNumber)
	 {
			if (aBinaryNumber.getLength() != data.length) {
				System.out.println("Error : check the lengths of both numbers");
			}
	        int b = 0;
			int total[] = new int[data.length];
			for (int i = data.length - 1;  i > -1 && i < data.length ; i --) 
			{
				int temp = b + data[i] + aBinaryNumber.getDigit(i);
			
				if (temp == 0) {
					total[i] = 0;
					b = 0;
				}
				
				if (temp == 1) {
					total[i] = 1;
					b = 0;
				}
				
				if (temp == 2) {
					total[i] = 0;
					b = 1;
				}
				
				if (temp == 3) {
					total[i] = 1;
					b = 1;
				}
			}
			data = total;
			
			if (b == 1) 
			{
				overflow = true;
			}
		}
	 
	 
     public void clearOverﬂow() 
     {
    	 overflow = false;
       }
     
     
     // for converting a binary number to a string
     
     public String toString() 
     {
 		if (overflow == true) {
 			return "Overflow";
 		} 
 		else {
 			String binaryString = "";
 			for (int i = 0; i < data.length; i++) {
 				binaryString = binaryString + data[i];
 			}
 			return binaryString;
 		}
 	}
     
     
     public static void main(String[] args) 
     {
 		BinaryNumber Number1 = new BinaryNumber("1010");
 		
 		BinaryNumber Number2 = new BinaryNumber("1110");
 		
 		BinaryNumber Number3 = new BinaryNumber("0001");
 		
 		Number1.toString();
 		
 		System.out.println();
 	
 		System.out.println("Length of binary number is "  + Number2.getLength());
 		
 		System.out.println("Digit at given index is " + Number2.getDigit(3));
 		
 		Number2.shiftR(4);
 		
 		Number1.add(Number3);
 		
 		System.out.println(Number1.toString());
 		
 		System.out.println("Binary to Decimal number is : " + Number2.toDecimal());
 	}
}

	 


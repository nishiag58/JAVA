import java.util.*;

class Calculator{
	
	public static void main(String args[]){
		
		double num1,num2,result;
		char op, cont;
		
		do{
			
		Scanner data= new Scanner(System.in);
		
		System.out.println("******ARITHMETIC CALCULATOR MENU******");
		System.out.println("For Addition-Press a");
		System.out.println("For Subtraction-Press s");
		System.out.println("For Multiplication-Press m");
		System.out.println("For Division-Press d");
		
		System.out.println("Enter your choice of arithmetic operator");
		op =data.next().charAt(0);
		
		
		System.out.println("Enter first number");
		num1 = data.nextInt();
		
		System.out.println("Enter second number");
		num2 = data.nextInt();
		
		
		switch(op){
			
			case 'a':
			result=num1+num2;
			System.out.println("Addition of "+num1+" & "+num2+" is "+result+" ");
			break;
			
			case 's':
			result=num1-num2;
			System.out.println("Subtraction of "+num1+" & "+num2+" is "+result+" ");
			break;
			
			case 'm':
			result=num1*num2;
			System.out.println("Multiplication of "+num1+" & "+num2+" is "+result+" ");
			break;
			
			case 'd':
			  if(num2==0){
				System.out.println("Can not divide by zero");
			  }
			  else{
			   result=num1/num2;
			   System.out.println("Division of "+num1+" by "+num2+" gives "+result+" ");
			   break;
			  }
			
		    default:
			System.out.println("wrong arithmetic choice");
		  }
		  System.out.println("Do you want to calculate more?If yes then press y ...if no then press n");
		  cont=data.next().charAt(0);
		  if(cont!='y' && cont!='n')
			  System.out.println("Wrong choice");
		}while(cont=='y');
	}
}
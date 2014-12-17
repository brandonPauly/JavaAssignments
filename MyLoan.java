package algs12;
import stdlib.*;

/*
Brandon Pauly
CSC 300 - 402
Dr. Tomuro
Assignment 2
*/

public class MyLoan
{
  private double amountBorrowed;
  private double yearlyRate;
  private int years;
  
  public MyLoan(){}
  public MyLoan(double amt, double rt, int yrs)
  {
	  amountBorrowed = amt;
	  yearlyRate = rt;
	  years = yrs;
  }

  public double getAmountBorrowed()
  { return amountBorrowed; }
  
  
  public double getYearlyRate()
  { return yearlyRate; }

  
  public int getYears()
  { return years; }
  
  
  public double monthlyPayment()
  { 
	  double i = ((yearlyRate/100)/12);
	  int n = (years*12);
	  
	  return amountBorrowed*((i*(Math.pow(1+i, n)))/((Math.pow(1+i, n)-1)));
  }

  
  public double totalPayment()
  {
	  return monthlyPayment()*(years*12);
  }

  
  public String toString()
  {
	  return "Loan: $" + amountBorrowed + " at " + yearlyRate + "% for " + years + " years";
  }


  //
  //** static methods
  //
  public static void main(String[] args)
  {
    // Declare constants for two pre-set interest rates.
    final double RATE15 = 5.75;
    final double RATE30 = 6.25;

    // opening message
    StdOut.println("***** Welcome to the Loan analyzer! *****");

    String ans = "Y";

    do {
      StdOut.print("\n  Enter the principle amount to borrow: ");
      double amount = StdIn.readDouble();

      MyLoan x = new MyLoan(amount, RATE15, 15);
      
      MyLoan y = new MyLoan(amount, RATE30, 30);
      
      StdOut.println("\n\n============ ANALYSES ============");
      
      StdOut.println(x.toString());
      StdOut.println("Monthly Payment = " + x.monthlyPayment());
      StdOut.println("Total Payment = " + x.totalPayment() + "\n");
      
      StdOut.println(y.toString());
      StdOut.println("Monthly Payment = " + y.monthlyPayment());
      StdOut.println("Total Payment = " + y.totalPayment());
      StdOut.println("==================================");
      













      StdOut.print("\n      ** Do you want to continue (y/n)? ");
      ans = StdIn.readString();

      // class to convert a char to upper-case
    } while (ans.toUpperCase().equals("Y"));

    StdOut.println("\n********** Thank you. Come again! **********");

  } // end main()

}
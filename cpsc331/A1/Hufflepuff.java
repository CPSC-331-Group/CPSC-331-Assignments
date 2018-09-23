package cpsc331.A1;

public class Hufflepuff {
  public static int eval(int n) {
    // Assertion: A non-negative integer n has been given as input.
	  if(n>-1) {
    if (n == 0) {
      return 10;
    } else if (n == 1) {
      return 9;
    } else if (n == 2) {
      return 8;
    } else if (n == 3) {
      return 7;
    } else {
      int hocus = 10;
      int pocus = 9;
      int abra = 8;
      int kadabra = 7;
      int i = 3;
      while (i < n) {
        int shazam = 4*kadabra - 6*abra + 4*pocus - hocus;
        hocus = pocus;
        pocus = abra;
        abra = kadabra;
        kadabra = shazam;
        i += 1;
      }
      return kadabra;
    }
	  }
	  else {
		  throw new IllegalArgumentException("Silly muggle! The input integer cannot be negative.");
	  }
    // Assertion:
    // 1. A non-negative integer n has been given as input.
    // 2. The nth Hufflepuff number, Hn, has been returned as output.
  }

  public static void main(String[] args) {
		Boolean IllegalArgument=false;        //Initiate a boolean to check for illegal argument
		if(args.length != 0){         //If the argument length is not 0
	  if (args[0].matches("-?\\d+(\\.\\d+)?"))        //If the first argument are numbers
		{
		IllegalArgument=false;        //Illegal argument boolean remains false
		 }
		else{       //Else set the boolean to true and throw an exception
		  IllegalArgument=true;
			throw new IllegalArgumentException("Silly muggle! One integer input is required.");
		}
  }else{        //If the number of arguments was 0, throw an exception
			IllegalArgument=true;
			throw new IllegalArgumentException("Silly muggle! One integer input is required.");
		}
		if(!IllegalArgument){       //Check the boolean and if the boolean was false then proceed
      if (args.length == 1 && args[0].matches("\\d+")) {       //If the number of arguments is 1 and the first argument are digits
        System.out.println(eval(Integer.parseInt(args[0])));       //Print the integer returned from calling sHuffle method with first argument
      } else if (Integer.parseInt(args[0]) < 0) {       //If the first argument is a negative integer, print error
		throw new IllegalArgumentException("Silly muggle! The input integer cannot be negative.");
  } else {        //Else throw an illegal argument exception
        throw new IllegalArgumentException("Silly muggle! One integer input is required.");
      }
		}
  }
}

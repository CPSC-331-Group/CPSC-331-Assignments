import cpsc331.A1;

public class SHufflepuff {
    protected static int sHuffle(int n){
      // Assertion: A non-negative integer n has been given as input.
      if (n == 0) {
        return 10;
      } else if (n == 1) {
        return 9;
      } else if (n == 2) {
        return 8;
      } else if (n == 3) {
        return 7;
      } else {
        return 4*sHuffle(n - 1) - 6*sHuffle(n - 2) + 4*sHuffle(n - 3) - sHuffle(n - 4);
      }
      // Assertion:
      // 1. A non-negative integer n has been given as input.
      // 2. The nth Hufflepuff number, Hn, has been returned as output.
    }
    public static void main(String[] args) {
	  try{       //Try the following code
		  Integer.parseInt(args[0]);
      if (args.length > 0 && args[0].matches("\\d+")) {
        System.out.println(sHuffle(Integer.parseInt(args[0])));
      } else if (Integer.parseInt(args[0]) < 0) {
        System.out.println("Silly muggle! The input integer cannot be negative.");
		//throw new IllegalArgumentException();
      } else {
        System.out.println("something else went wrong");
      }
	  }
	  catch(IllegalArgumentException ie){
		throw new IllegalArgumentException("Silly muggle! One integer input is required.");
		}
		catch(Exception ie){
		System.out.println("something else went wrong");
	  }
    }
}

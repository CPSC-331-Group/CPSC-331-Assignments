import cpsc331.A1;

public class Hufflepuff {
  public static int eval(int n) {
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
    // Assertion:
    // 1. A non-negative integer n has been given as input.
    // 2. The nth Hufflepuff number, Hn, has been returned as output.
  }

  public static void main(String[] args) {
  try{
   Integer.parseInt(args[0]);
    if (args.length > 0 && args[0].matches("\\d+")) {
      System.out.println(eval(Integer.parseInt(args[0])));
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

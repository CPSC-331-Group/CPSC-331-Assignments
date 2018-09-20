import cpsc331.A1;

public class SHufflepuff {
    protected int sHuffle(int n) {
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
    }
    public static void main(String[] args) {
      if (args.length > 0 && args[0] == "\\d+") {
        System.out.println(sHuffle(Integer.parseInt(args[0])));
      } else if (Integer.parseInt(args[0]) < 0) {
        System.out.println("Silly muggle! The input integer cannot be negative.")
      } else {
        System.out.println("Silly muggle! One integer input is required.")
      }
    }
}
#test
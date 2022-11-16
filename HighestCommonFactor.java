/**
 * A class to find HCF of an array of integers, it finds a HCF for the array by finding HCF between 2 integers at a time
 *
 */
public class HighestCommonFactor {
    public static void main(String args[]) {
      int numbers [] = {8, 4, 16};
      HighestCommonFactor hcf = new HighestCommonFactor();
      System.out.println("hcf: " + hcf.highestCommonFactor(numbers));
    }
   
    public int findGCD(int a, int b) {
        // any number can be divided by 1, so default gcd is 1
        int gcd = 1;
        if(a == 0 && b > 0) gcd = b;
        if(b == 0 && a > 0) gcd = a;
        for(int i = 2; i <= a && i <= b; i++) {
            // If i can divide both numbers(leave no remainder), then i is the highest common divisor for both numbers
            if(a % i == 0 && b % i == 0)
                gcd = i;
        }
        return gcd;
    }
   
    public int highestCommonFactor(int []numbers) {
        // Find GCD of first 2 integers in array
        int gcd = findGCD(numbers[0], numbers[1]);
        // Then take that gcd and find gcd with the rest of the numbers
        for(int i = 2; i < numbers.length; i++) {
            gcd = findGCD(gcd, numbers[i]);
        }
        return gcd;
    }
}
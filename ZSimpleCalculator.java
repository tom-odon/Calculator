import java.util.Scanner;
public class ZSimpleCalculator {
  int val = 0;            // the current value
  int old = 0;            // the old value 
  char op = '=';          // the previous operation
  boolean isClean = true; // is the new value clean?
  
  public String toString () {
    return String.format ("val:%d old:%d op:%c isClean:%b", this.val, this.old, this.op, this.isClean);
  }
  public void process (char c) {
    if (isClear (c)) {

      this.val = 0;
      this.old = 0;
      this.op = '=';
      this.isClean = true;
      
    } else if (isDigit (c)) {
      
      int d = evalDigit (c);
      if (this.isClean) {
        // start a new value
        this.old = this.val;  
        this.val = d;
      } else {
        // add to the existing value
        this.val = (this.val * 10) + d;
      }
      this.isClean = false;
      
    } else if (isOp (c)) {
      
      int v = evalOp (this.op, this.old, this.val);
      if (! this.isClean) {
        // start a new value
        this.old = this.val;  
      }
      this.val = v;
      this.op = c;
      this.isClean = true;
      
    }
  }

  public static boolean isOp (char c) {
    switch (c) {
    case '=' : return true;
    case '+' : return true;
    case '-' : return true;
    case '*' : return true;
    }
    return false;
  }
  public static int evalOp (char c, int m, int n) {
    switch (c) {
    case '=' : return n; // m is the old value, n is the new value
    case '+' : return m + n;
    case '-' : return m - n;
    case '*' : return m * n;
    }
    throw new Error ();
  }
  public static boolean isDigit (char c) {    
    return c >= '0'  && c <= '9';
  }
  public static int evalDigit (char c) {    
    return c - '0';
  }
  public static boolean isClear (char c) {    
    return c == 'c';
  }
  public static void main (String[] args) {
    ZSimpleCalculator eval = new ZSimpleCalculator ();
    Scanner in = new Scanner (System.in);
    while (in.hasNext ()) {
      String s = in.nextLine ();
      for (int i = 0; i < s.length(); i++){
          char c = s.charAt(i);  
          eval.process (c);
          System.err.println (eval.toString ());
      }
    }
    in.close ();
  }
}
package ambiente;

import java.util.Scanner;

/**
 *
 * @author Rodrigo Santos Rodrigues
 */
public class IOPadrao {

   /**
    * 
    */
   public static final String STR_VAZIA = "";
   private static Scanner scan = new Scanner(System.in);

   /**
    *
    */
   public static void escreveLinha() {
      System.out.println();
   }

   /**
    *
    * @param txt
    */
   public static void escreve(String txt) {
      System.out.print(txt);
   }

   /**
    *
    * @param ch
    */
   public static void escreve(char ch) {
      System.out.print(ch);
   }

   /**
    * 
    * @param inteiro
    */
   public static void escreve(int inteiro) {
      System.out.print(inteiro);
   }

   /**
    *
    * @return
    */
   public static String leTeclado() {
      return scan.next();
   }
}

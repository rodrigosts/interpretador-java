/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpretadortiny;

import java.io.IOException;

/**
 *
 * @author Rodrigo Santos Rodrigues
 */
public class Main {

   /**
    * @param args the command line arguments
    * @throws IOException
    */
   public static void main(String[] args) throws IOException {
      String url = "codigosTiny\\exemplo8.tiny";
      Interpretador inte = new Interpretador(url);
      inte.leArquivo();
      inte.executa();
   }
}

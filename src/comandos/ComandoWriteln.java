
package comandos;

import ambiente.IOPadrao;

/**
 *
 * @author Rodrigo Santos Rodrigues
 */
public class ComandoWriteln extends Comando {

   @Override
   public int executa() {
      IOPadrao.escreveLinha();
      return 1;
   }

}

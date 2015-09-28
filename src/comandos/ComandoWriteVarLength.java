package comandos;

import ambiente.IOPadrao;
import ambiente.Memoria;

/**
 *
 * @author Rodrigo Santos Rodrigues
 */
public class ComandoWriteVarLength extends ComandoWriteVar{

   protected ComandoWriteVarLength(){

   }

   public ComandoWriteVarLength (Memoria mem, String nome){
      super (mem, nome);
   }

      @Override
   public int executa() {
      setValor(getMem().pesquisarVariavel(getNome()));
      IOPadrao.escreve(getValor().tamanho());
      return 1;
   }
}

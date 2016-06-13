package commandos;

import ambiente.IOPadrao;
import ambiente.Memoria;

/**
 *
 * @author Rodrigo Santos Rodrigues
 */
public class ComandoWriteVarIndex extends ComandoWriteVar {

   private int index;

   /**
    *
    */
   protected ComandoWriteVarIndex() {
   }

   /**
    *
    * @param mem
    * @param nome
    * @param index
    */
   public ComandoWriteVarIndex(Memoria mem, String nome, int index) {
      super(mem, nome);
      this.index = index;
   }

   /**
    * 
    * @return
    */
   @Override
   public int executa() {
      setValor(getMem().pesquisarVariavel(getNome()));
      if (index < getValor().leValorString().length()) {
         IOPadrao.escreve(getValor().leValorString().charAt(index));
      } else {
         IOPadrao.escreve(IOPadrao.STR_VAZIA);
      }
      return 1;
   }
}

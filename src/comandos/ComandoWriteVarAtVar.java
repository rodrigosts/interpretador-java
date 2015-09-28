package comandos;

import ambiente.IOPadrao;
import ambiente.Memoria;
import variaveis.Variavel;

/**
 *
 * @author Rodrigo Santos Rodrigues
 */
public class ComandoWriteVarAtVar extends ComandoWriteVar {

   private String nome_index;
   private Variavel var_index;

   protected ComandoWriteVarAtVar() {
   }

   public ComandoWriteVarAtVar(Memoria mem, String nome, String nome_index) {
      super(mem, nome);
      this.nome_index = nome_index;
   }

   @Override
   public int executa() {
      setValor(getMem().pesquisarVariavel(getNome()));
      var_index = getMem().pesquisarVariavel(nome_index);
      if (var_index.leValorString().length() < getValor().leValorString().length()) {
         IOPadrao.escreve(getValor().leValorString().charAt(var_index.leValorInt()));
      } else {
         IOPadrao.escreve(IOPadrao.STR_VAZIA);
      }
      return 1;
   }
}

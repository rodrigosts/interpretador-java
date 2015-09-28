package comandos;

import ambiente.IOPadrao;
import ambiente.Memoria;

/**
 *
 * @author Rodrigo Santos Rodrigues
 */
public class ComandoWriteVar extends ComandoWrite {

   private Memoria mem;
   private String nome;

   /**
    * 
    */
   protected ComandoWriteVar() {
   }

   ;

   public ComandoWriteVar(Memoria mem, String nome) {
      this.mem = mem;
      this.nome = nome;
   }

   protected Memoria getMem() {
      return mem;
   }

   protected String getNome() {
      return nome;
   }

   @Override
   public int executa() {
      setValor(mem.pesquisarVariavel(nome));
      IOPadrao.escreve(getValor().leValorString());
      return 1;
   }
}

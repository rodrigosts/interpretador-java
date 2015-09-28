package expressoes;

import ambiente.Memoria;

/**
 *
 * @author Rodrigo Santos Rodrigues
 */
public class ExpressaoVariavel extends Expressao {

   private Memoria mem;
   private String nome;

   protected ExpressaoVariavel() {
   }

   public ExpressaoVariavel(Memoria mem, String nome) {
      this.mem = mem;
      this.nome = nome;
   }

   @Override
   public int calcula() {
      return mem.pesquisarVariavel(nome).leValorInt();
   }
}

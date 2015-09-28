package expressoes;

/**
 *
 * @author Rodrigo Santos Rodrigues
 */
public class ExpressaoLiteral extends Expressao {

   private int valor;

   protected ExpressaoLiteral() {
   }

   public ExpressaoLiteral(int valor) {
      this.valor = valor;
   }

   @Override
   public int calcula() {
      return valor;
   }
}

package expressoes;

/**
 *
 * @author Rodrigo Santos Rodrigues
 */
public class ExpressaoBinaria extends Expressao{

   private Expressao exp1;
   private Expressao exp2;
   private Operacao operacao;

   protected ExpressaoBinaria(){}

   public ExpressaoBinaria(Operacao operacao, Expressao exp1, Expressao exp2){
      this.exp1 = exp1;
      this.exp2 = exp2;
      this.operacao = operacao;
   }

   @Override
   public int calcula() {
      switch (operacao){
         case ADICAO:
            return (exp1.calcula() + exp2.calcula());
         case SUBTRACAO:
            return (exp1.calcula() - exp2.calcula());
         case MULTIPLICACAO:
            return (exp1.calcula() * exp2.calcula());
         case DIVISAO:
            return (exp1.calcula() / exp2.calcula());
         default:
            return 0;
      }
   }

}

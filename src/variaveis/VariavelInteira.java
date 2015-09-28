
package variaveis;

/**
 *
 * @author Rodrigo Santos Rodrigues
 */
public class VariavelInteira extends Variavel{

   protected int valor;

   private VariavelInteira(){};

   /**
    *
    */
   public VariavelInteira(String nome) {
      super.setNome(nome);
      valor = Variavel.INT_PADRAO;
   }

   /**
    *
    * @param valor
    */
   public VariavelInteira(String nome, int valor) {
      super.setNome(nome);
      this.valor = valor;
   }

   /**
    * Set the value of valor
    *
    * @param valor new value of valor
    */
   public void setValor(int valor) {
      this.valor = valor;
   }

   @Override
   public int leValorInt(){
      return valor;
   }

   @Override
   public String leValorString() {
      return String.valueOf(valor);
   }

   @Override
   public int tamanho() {
      return String.valueOf(valor).length();
   }

}

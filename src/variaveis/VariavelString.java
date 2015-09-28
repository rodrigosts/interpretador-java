
package variaveis;

/**
 *
 * @author Rodrigo Santos Rodrigues
 */
public class VariavelString extends Variavel{

   private String valor;

   private VariavelString(){};

   /**
    *
    */
   public VariavelString(String nome) {
      super.setNome(nome);
      valor = Variavel.STR_VAZIA;
   }

   /**
    *
    * @param valor
    */
   public VariavelString(String nome, String valor) {
      super.setNome(nome);
      this.valor = valor;
   }


   @Override
   public String leValorString() {
      return valor;
   }

   @Override
   public int leValorInt() {
      return Variavel.STR_INT;
   }

   @Override
   public int tamanho() {
      return valor.length();
   }

}


package variaveis;

/**
 *
 * @author Rodrigo Santos Rodrigues
 */
public abstract class Variavel {

   private String nome;

   public static final String STR_VAZIA = "";
   public static final int INT_PADRAO = 0;
   public static final int STR_INT = 0;


   /**
    * Get the value of nome
    *
    * @return the value of nome
    */
   public String getNome() {
      return nome;
   }

   /**
    * Set the value of nome
    *
    * @param nome new value of nome
    */
   public void setNome(String nome) {
      this.nome = nome;
   }

   /**
    *
    * @return
    */
   public abstract String leValorString();

   /**
    * 
    * @return
    */
   public abstract int leValorInt();

   /**
    * 
    * @return
    */
   public abstract int tamanho();

}

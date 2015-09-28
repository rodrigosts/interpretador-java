package comandos;

/**
 *
 * @author Rodrigo Santos Rodrigues
 */
public class Label extends Comando {

   private String nome;

   protected Label() {
   }

   public Label(String nome) {
      this.nome = nome;
   }

   public String getNome(){
      return nome;
   }

   public int executa() {
      return 1;
   }
}

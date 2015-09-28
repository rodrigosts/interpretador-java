package comandos;

/**
 *
 * @author Rodrigo Santos Rodrigues
 */
public class ComandoGoto extends Comando {

   private int offset;
   private String label;

   protected ComandoGoto() {
   }

   public ComandoGoto(String label) {
      this.label = label;
   }

   public void setOffset(int offset) {
      this.offset = offset;
   }

   public String getLabel() {
      return label;
   }

   @Override
   public int executa() {
      return offset;
   }
}

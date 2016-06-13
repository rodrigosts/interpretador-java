package commandos;

/**
 *
 * @author Rodrigo Santos Rodrigues
 */
public class ComandoEndw extends Comando{

   private int offset;
   
   private ComandoEndw(){
      
   }

   /**
    *
    * @param offset
    */
   public ComandoEndw(int offset){
      this.offset = offset;
   }

   @Override
   public int executa() {
      return offset;
   }

}

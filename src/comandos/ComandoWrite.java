package comandos;

import ambiente.IOPadrao;
import variaveis.Variavel;
import variaveis.VariavelString;

/**
 *
 * @author Rodrigo Santos Rodrigues
 */
public class ComandoWrite extends Comando {

   private Variavel valor;

   /**
    * 
    */
   protected ComandoWrite(){};

   protected Variavel getValor(){
      return valor;
   }

   protected void setValor(Variavel valor) {
      this.valor = valor;
   }

   public ComandoWrite(String valor){
      this.valor = new VariavelString (String.valueOf(System.currentTimeMillis()).concat("Literal"), valor);
   }

   @Override
   public int executa() {
      IOPadrao.escreve(valor.leValorString());
      return 1;
   }
}

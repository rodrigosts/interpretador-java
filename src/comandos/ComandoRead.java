
package commandos;

import ambiente.IOPadrao;
import ambiente.Memoria;
import variaveis.Variavel;
import variaveis.VariavelInteira;
import variaveis.VariavelString;

/**
 *
 * @author Rodrigo Santos Rodrigues
 */
public class ComandoRead extends Comando{

   private Memoria mem;
   private String nome;

   private ComandoRead(){};

   /**
    *
    * @param mem
    * @param nome
    */
   public ComandoRead(Memoria mem, String nome) {
      this.mem = mem;
      this.nome = nome;
   }

   @Override
   public int executa() {
      String leitura = IOPadrao.leTeclado();
      Variavel var;
      try{
         int valor = Integer.parseInt(leitura);
         var = new VariavelInteira(nome, valor);
      }catch (NumberFormatException e){
         var = new VariavelString(nome, leitura);
      }
      mem.inserirVariavel(var);
      return 1;
   }

}

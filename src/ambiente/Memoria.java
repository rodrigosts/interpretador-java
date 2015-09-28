
package ambiente;

import java.util.HashMap;
import variaveis.Variavel;
import variaveis.VariavelInteira;

/**
 *
 * @author Rodrigo Santos Rodrigues
 */
public class Memoria {

   private HashMap<String, Variavel> map;

   public Memoria() {
      map = new HashMap<String, Variavel>();
   }

   /**
    *
    * @param nome
    * @return
    */
   public Variavel pesquisarVariavel (String nome){
      if(map.containsKey(nome)){
         return map.get(nome);
      }else{
         VariavelInteira var = new VariavelInteira(nome);
         map.put(nome, var);
         return var;
      }
   }

   /**
    * 
    * @param var
    */
   public void inserirVariavel (Variavel var){
      if(map.containsKey(var.getNome())){
         map.remove(var.getNome());
         map.put(var.getNome(), var);
      }else{
         map.put(var.getNome(), var);
      }
   }

}

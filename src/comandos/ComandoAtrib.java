package commandos;

import ambiente.Memoria;
import expressoes.Expressao;
import variaveis.VariavelInteira;

/**
 *
 * @author Rodrigo Santos Rodrigues
 */
public class ComandoAtrib extends Comando{

   private Expressao exp;
   private String nome;
   private Memoria mem;

   protected ComandoAtrib(){}

   public ComandoAtrib(Memoria mem, String nome, Expressao exp){
      this.mem = mem;
      this.nome = nome;
      this.exp = exp;
   }

   public int executa() {
      VariavelInteira var = new VariavelInteira(nome, exp.calcula());
      mem.inserirVariavel(var);
      return 1;
   }

}

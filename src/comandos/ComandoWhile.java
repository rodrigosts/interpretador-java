package comandos;

import ambiente.Memoria;
import variaveis.Variavel;

/**
 *
 * @author Rodrigo Santos Rodrigues
 */
public class ComandoWhile extends Comando {

   private int offset;
   private String nome1;
   private String nome2;
   private Condicao condicao;
   private Memoria mem;

   private ComandoWhile() {
   }

   public ComandoWhile(Memoria mem, Condicao condicao, String nome1, String nome2) {
      this.mem = mem;
      this.condicao = condicao;
      this.nome1 = nome1;
      this.nome2 = nome2;
   }

   public void setOffset(int offset) {
      this.offset = offset;
   }

   private boolean confereCondicao() {
      int valor1, valor2;
      Variavel var1, var2;
      try {
         valor1 = Integer.parseInt(nome1);
      } catch (NumberFormatException e) {
         if (nome1.contains(".length")) {
            var1 = mem.pesquisarVariavel(nome1.replace(".length", ""));
            valor1 = var1.tamanho();
         } else {
            var1 = mem.pesquisarVariavel(nome1);
            valor1 = var1.leValorInt();
         }
      }
      try {
         valor2 = Integer.parseInt(nome2);
      } catch (NumberFormatException e) {
         if (nome2.contains(".length")) {
            var2 = mem.pesquisarVariavel(nome2.replace(".length", ""));
            valor2 = var2.tamanho();
         } else {
            var2 = mem.pesquisarVariavel(nome2);
            valor2 = var2.leValorInt();
         }
      }
      switch (condicao) {
         case MENOR:
            return (valor1 < valor2);
         case MENOR_IGUAL:
            return (valor1 <= valor2);
         case MAIOR:
            return (valor1 > valor2);
         case MAIOR_IGUAL:
            return (valor1 >= valor2);
         case IGUAL:
            return (valor1 == valor2);
         case DIFERENTE:
            return (valor1 != valor2);
      }
      return false;
   }

   @Override
   public int executa() {
      if (confereCondicao()) {
         return 1;
      } else {
         return offset;
      }
   }
}

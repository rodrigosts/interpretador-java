package interpretadortiny;

import ambiente.Memoria;
import comandos.Comando;
import comandos.ComandoAtrib;
import comandos.ComandoEndp;
import comandos.ComandoEndw;
import comandos.ComandoGoto;
import comandos.ComandoRead;
import comandos.ComandoWhile;
import comandos.ComandoWrite;
import comandos.ComandoWriteVar;
import comandos.ComandoWriteVarAtVar;
import comandos.ComandoWriteVarIndex;
import comandos.ComandoWriteln;
import comandos.Condicao;
import comandos.Label;
import expressoes.Expressao;
import expressoes.ExpressaoBinaria;
import expressoes.ExpressaoLiteral;
import expressoes.ExpressaoVariavel;
import expressoes.Operacao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodrigo Santos Rodrigues
 */
class Interpretador {

   private Stack<Expressao> pilha;
   private String palavraAtual;
   private ArquivoFonte arq;
   private Memoria escopo;
   private ArrayList<Comando> comandos;
   private HashMap<String, Integer> labels;
   private HashMap<ComandoGoto, Integer> gotos;
   private Stack<ComandoWhile> whiles_obj;
   private Stack<Integer> whiles_int;
   private String url;

   public Interpretador(String url) {
      this.url = url;
      escopo = new Memoria();
      comandos = new ArrayList<Comando>();
      labels = new HashMap<String, Integer>();
      whiles_obj = new Stack<ComandoWhile>();
      whiles_int = new Stack<Integer>();
      gotos = new HashMap<ComandoGoto, Integer>();
   }

   public void leArquivo() {
      arq = new ArquivoFonte(url);
      Comando comando;
      palavraAtual = arq.proximaPalavra();
      try {
         while (!arq.fimdoArquivo()) {
            if (palavraAtual.equals(TipoComando.READ)) { // ok
               comando = comandoRead();
               comandos.add(comando);
               saltaLinha();
            } else if (palavraAtual.equals(TipoComando.WRITE)) { // ok
               comando = comandoWrite();
               comandos.add(comando);
               saltaLinha();
            } else if (palavraAtual.equals(TipoComando.WRITELN)) { // ok
               comando = comandoWriteln();
               comandos.add(comando);
               saltaLinha();
            } else if (palavraAtual.equals(TipoComando.ENDP)) { // ok
               comando = comandoEndp();
               comandos.add(comando);
               saltaLinha();
            } else if (palavraAtual.equals(TipoComando.WHILE)) { // ok
               comando = comandoWhile();
               comandos.add(comando);
               saltaLinha();
            } else if (palavraAtual.equals(TipoComando.GOTO)) { // ok
               comando = comandoGoto();
               comandos.add(comando);
               saltaLinha();
            } else if (palavraAtual.equals(TipoComando.ENDW)) {
               comando = comandoEndw();
               comandos.add(comando);
               saltaLinha();
            } else if (variavel(palavraAtual)) {
               String palavra = arq.proximaPalavraIgnoraEspaco();
               if (palavra.equals("=")) {
                  comando = comandoAtrib();
               } else {
                  comando = comandoLabel();
                  palavraAtual = arq.proximaPalavra();
               }
               comandos.add(comando);
            } else {
               palavraAtual = arq.proximaPalavra();
            }
         }
         ligarGotos();
      } catch (IOException ex) {
         Logger.getLogger(Interpretador.class.getName()).log(Level.SEVERE, null, ex);
      }

   }

   private Expressao trataExpressao() {
      palavraAtual = arq.proximaPalavraIgnoraEspaco();
      pilha = new Stack<Expressao>();
      expressao();
      Expressao raizArvoreExpressao = pilha.pop();
      return raizArvoreExpressao;
   }

   private void expressao() {
      termo();
      while (palavraAtual.equals("+") || palavraAtual.equals("-")) {
         Operacao op = palavraAtual.equals("+") ? Operacao.ADICAO : Operacao.SUBTRACAO;
         palavraAtual = arq.proximaPalavraIgnoraEspaco();
         termo();
         Expressao exp2 = pilha.pop();
         Expressao exp1 = pilha.pop();
         pilha.push(new ExpressaoBinaria(op, exp1, exp2));
      }
   }

   private void termo() {
      fator();
      while (palavraAtual.equals("*") || palavraAtual.equals("/")) {
         Operacao op = palavraAtual.equals("*") ? Operacao.MULTIPLICACAO : Operacao.DIVISAO;
         palavraAtual = arq.proximaPalavraIgnoraEspaco();
         fator();
         Expressao exp2 = pilha.pop();
         Expressao exp1 = pilha.pop();
         pilha.push(new ExpressaoBinaria(op, exp1, exp2));
      }
   }

   private void fator() {
      if (variavel(palavraAtual)) {
         pilha.push(new ExpressaoVariavel(escopo, palavraAtual));
         palavraAtual = arq.proximaPalavraIgnoraEspaco();
      } else if (numero(palavraAtual)) {
         pilha.push(new ExpressaoLiteral(Integer.parseInt(palavraAtual)));
         palavraAtual = arq.proximaPalavraIgnoraEspaco();
      } else if (palavraAtual.equals("(")) {
         palavraAtual = arq.proximaPalavraIgnoraEspaco();
         expressao();
         if (palavraAtual.equals(")")) {
            palavraAtual = arq.proximaPalavraIgnoraEspaco();
         }
      }
   }

   private boolean variavel(String str) {
      for (int i = 0; i
              < str.length(); i++) {
         if (!((str.charAt(i) >= 65 && str.charAt(i) <= 90) || ((str.charAt(i) >= 97 && str.charAt(i) <= 122)))) {
            return false;
         }
      }
      return true;
   }

   private boolean numero(String str) {
      try {
         int a = Integer.parseInt(str);
      } catch (NumberFormatException e) {
         return false;
      }
      return true;
   }

   private void saltaLinha() throws IOException {
      palavraAtual = arq.proximaPalavra();
   }

   public void executa() {
      int pc = 0;
      while (pc < comandos.size()) {
         pc += comandos.get(pc).executa();
      }
   }

   private Comando comandoRead() {
      Comando comando;
      String leitura;
      arq.saltaPalavra();
      palavraAtual = arq.proximaPalavra();
      comando = new ComandoRead(escopo, palavraAtual);
      arq.saltaPalavra();
      return comando;
   }

   private Comando comandoWrite() {
      Comando comando;
      arq.saltaPalavra();
      String leitura;
      palavraAtual = arq.proximaPalavra();
      if (palavraAtual.equals("'")) {
         leitura = "";
         String aux = arq.proximaPalavra();
         while (!aux.equals("'")) {
            leitura = leitura.concat(aux);
            aux = arq.proximaPalavra();
         }
         comando = new ComandoWrite(leitura);
         arq.saltaPalavra();
      } else {
         if (arq.proximaPalavra().equals("[")) {
            String indice = arq.proximaPalavra();
            if (variavel(indice)) {
               comando = new ComandoWriteVarAtVar(escopo, palavraAtual, indice);
            } else {
               int index = Integer.parseInt(indice);
               comando = new ComandoWriteVarIndex(escopo, palavraAtual, index);
            }
            arq.saltaPalavra();
            arq.saltaPalavra();
         } else {
            comando = new ComandoWriteVar(escopo, palavraAtual);
         }
      }
      return comando;
   }

   private Comando comandoWhile() {
      ComandoWhile comando;
      arq.saltaPalavra();
      String var1 = arq.proximaPalavraIgnoraEspaco();
      String opr = arq.proximaPalavraIgnoraEspaco();
      String var2 = arq.proximaPalavraIgnoraEspaco();
      if (var2.equals("=")) {
         opr = opr.concat(var2);
         var2 = arq.proximaPalavra();
      }
      Condicao op = condicao(opr);
      comando = new ComandoWhile(escopo, op, var1, var2);
      arq.saltaPalavra();
      mapearWhile(comando);
      return comando;
   }

   private Comando comandoAtrib() {
      String nome = palavraAtual;
      Comando comando;
      //arq.saltaPalavra();
      Expressao xp = trataExpressao();
      comando = new ComandoAtrib(escopo, nome, xp);
      return comando;
   }

   private Comando comandoLabel() {
      Comando comando;
      comando = new Label(palavraAtual);
      mapearLabel();
      return comando;
   }

   private Comando comandoEndw() {
      ComandoEndw comando;
      int pc_while = whiles_int.pop();
      int offset = pc_while - comandos.size();
      comando = new ComandoEndw(offset);
      ComandoWhile comandowhile = whiles_obj.pop();
      comandowhile.setOffset(1 + (-offset));
      return comando;
   }

   private Comando comandoGoto() {
      ComandoGoto comando;
      palavraAtual = arq.proximaPalavraIgnoraEspaco();
      comando = new ComandoGoto(palavraAtual);
      mapearGoto(comando);
      return comando;
   }

   private Comando comandoWriteln() {
      return new ComandoWriteln();
   }

   private Comando comandoEndp() {
      return new ComandoEndp();
   }

   private void mapearLabel() {
      Integer put = labels.put(palavraAtual, comandos.size());
   }

   private void mapearGoto(ComandoGoto comando) {
      gotos.put(comando, comandos.size());
   }

   private Condicao condicao(String opr) {

      if (opr.equals("<")) {
         return Condicao.MENOR;
      } else if (opr.equals("<=")) {
         return Condicao.MENOR_IGUAL;
      } else if ((opr.equals(">"))) {
         return Condicao.MAIOR;
      } else if ((opr.equals(">="))) {
         return Condicao.MAIOR_IGUAL;
      } else if ((opr.equals("=="))) {
         return Condicao.IGUAL;
      } else {
         return Condicao.DIFERENTE;
      }
   }

   private void mapearWhile(ComandoWhile comando) {
      whiles_obj.push(comando);
      whiles_int.push(new Integer(comandos.size()));
   }

   private void ligarGotos() {
      Set<ComandoGoto> keySet = gotos.keySet();
      for (ComandoGoto comando : keySet) {
         int pont = labels.get(comando.getLabel());
         int offset = pont - gotos.get(comando);
         comando.setOffset(offset);
      }
   }
}

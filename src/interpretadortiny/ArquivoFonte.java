/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpretadortiny;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodrigo Santos Rodrigues
 */
public class ArquivoFonte {

   private BufferedReader reader;
   private FileReader file;
   private String linha;
   private int linha_i;
   private StringTokenizer tokens;
   private int token;
   private final String DELIMS = "+-*/()=<>' :[]";

   public ArquivoFonte(String url) {
      try {
         file = new FileReader(url);
         reader = new BufferedReader(file);
         tokens = new StringTokenizer("", "");
         linha = "";
      } catch (FileNotFoundException ex) {
         Logger.getLogger(ArquivoFonte.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   public String proximaPalavra() {
      try {
         while (!tokens.hasMoreTokens() && reader.ready()) {
            proximaLinha();
            tokens = new StringTokenizer(linha, DELIMS, true);
         }
      } catch (IOException ex) {
         Logger.getLogger(ArquivoFonte.class.getName()).log(Level.SEVERE, null, ex);
      }
      if (tokens.hasMoreTokens()) {
         return tokens.nextToken();
      }
      return "ENDP";
   }

      public String proximaPalavraIgnoraEspaco() {
      try {
         while (!tokens.hasMoreTokens() && reader.ready()) {
            proximaLinha();
            tokens = new StringTokenizer(linha, DELIMS, true);
         }
      } catch (IOException ex) {
         Logger.getLogger(ArquivoFonte.class.getName()).log(Level.SEVERE, null, ex);
      }
      if (tokens.hasMoreTokens()) {
         String tok = tokens.nextToken();
         while (tok.equals(" ") && tokens.hasMoreTokens()){
            tok = tokens.nextToken();
         }
         return tok;
      }
      return "ENDP";
   }

   public void saltaPalavra() {
      String proximaPalavra = proximaPalavra();
      if (proximaPalavra.equals(" ")) {
         proximaPalavra = proximaPalavra();
      }
   }

   public boolean fimdoArquivo() throws IOException {
      return !reader.ready();
   }

   public String proximaLinha() throws IOException {
      if (reader.ready()) {
         linha = reader.readLine();
         linha_i++;
         return linha;
      }
      return "";
   }
}

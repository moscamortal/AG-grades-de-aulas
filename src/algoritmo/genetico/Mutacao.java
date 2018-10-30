/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo.genetico;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author leandro
 */
public class Mutacao {

    Random gerador = new Random();
    ManiLista manilista = new ManiLista();
    ManiArquivo ManipularArquivos = new ManiArquivo();

    public ArrayList< Professor> ProfessoresAulas = ManipularArquivos.getProfessor();

    String NovaPopulacaoTemp = "";
    ArrayList<Integer> ProfessorPeriodoTemp = new ArrayList<Integer>();
    ArrayList<Character> Dias = new ArrayList<Character>();

    int DiasTotaisProfs = manilista.ValidarDiasDisponiveis(ProfessoresAulas);

    public String[] Mutar(String Populacao[]) {
        String NovaPopulacao[] = new String[ManipularArquivos.getProfessor().size() * 5];
        Arrays.fill(NovaPopulacao, "");
        String NovoIndividuoMutar = "";
        int teste = 0;

        for (int i = 0; i < ManipularArquivos.getProfessor().size() * 5; i++) { // Executa quantidade de Professor x 5
            String[] Controlador = new String[Populacao[i].length() / 5];
            Arrays.fill(Controlador, "");

            for (int x = 0; x < Populacao[i].length() / 5; x++) {
                String p = Populacao[i];

                int randomNum = gerador.nextInt(10);
                if (randomNum == 1) {

                    String b = p.substring(x * 5, (x * 5) + 5);
                    Controlador[x] = MutarIndividuo(b);

                } else {
                    String b = p.substring(x * 5, (x * 5) + 5);
                    Controlador[x] = b;
                }

            }
            for (int y = 0; y < Controlador.length; y++) {
                NovaPopulacao[i] = NovaPopulacao[i] + Controlador[y];
            }
        }
        return NovaPopulacao;
    }

    public String MutarIndividuo(String Individuo) {

        Random gerador = new Random();
        int IndiceAleatorio2;
        int IndiceAleatorio;

        String NovoIndividuo;

        char TempChar;
        char chars[] = Individuo.toCharArray();
        
        for(int a = 0; a < Individuo.length(); a++){
            Dias.add((chars[a]));
        }
        IndiceAleatorio = gerador.nextInt(Dias.size());

        //Alatorio1 =  Integer.valueOf(String.valueOf(Dias.get(IndiceAleatorio)));
        Dias.remove(IndiceAleatorio);

        IndiceAleatorio2 = gerador.nextInt(Dias.size());
       
        TempChar = chars[IndiceAleatorio];
        chars[IndiceAleatorio] = chars[IndiceAleatorio2];
        chars[IndiceAleatorio2] = TempChar;

        NovoIndividuo = String.copyValueOf(chars);

        Dias.clear();
        return NovoIndividuo;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo.genetico;

import static algoritmo.genetico.Fitness.VetorPontuacao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author leandro
 */
public class Reproducao {

    Random gerador = new Random();
    ManiArquivo ManipularArquivos = new ManiArquivo();
    ManiLista Listas = new ManiLista();
    public ArrayList< Professor> ProfessoresAulas = ManipularArquivos.getProfessor();

    public String[] Reproduzir(String Melhor, String Populacao[], int IndiceMelhor) {
        String NovaPopulacao[] = new String[ManipularArquivos.getProfessor().size() * 5];
        Arrays.fill(NovaPopulacao, "");

        for (int i = 0; i < ManipularArquivos.getProfessor().size() * 5; i++) { // Executa quantidade de Professor x 5
            String[] Controlador = new String[Populacao[i].length() / 5];
            Arrays.fill(Controlador, "");

            VetorPontuacao[i] = 1;
            
            //NovaPopulacao[i] = Cruzador(Populacao[IndiceMelhor],Populacao[i]);
            for (int x = 0; x < Populacao[i].length() / 5; x++) {
                String p = Populacao[i];
                String melhor;

                String b = p.substring(x * 5, (x * 5) + 5);
                melhor = Populacao[IndiceMelhor].substring(x * 5, (x * 5) + 5);

                Controlador[x] = Cruzador(melhor, b);
                Controlador[x] = Listas.EncontrarCaracter(Controlador[x], ProfessoresAulas.get(x).Periodos);

            }
            for (int y = 0; y < Controlador.length; y++) {
                NovaPopulacao[i] = NovaPopulacao[i] + Controlador[y];
            }
        }
        return NovaPopulacao;
    }

    public String Cruzador(String Melhor, String Populacao) {
        String NovoIndividuo;
        String individuoTempMelhor;
        String individuoTempNormal;

        int randomNum = gerador.nextInt(5);

        individuoTempMelhor = Melhor.substring(0, randomNum);
        individuoTempNormal = Populacao.substring(randomNum, Populacao.length());
        NovoIndividuo = individuoTempMelhor + individuoTempNormal;

        return NovoIndividuo;
    }
    
    public void Roleta(){
        int Total=0;
        int[] VetorPontuacaoTemp = new int[VetorPontuacao.length];
        
        for(int i = 0; i < VetorPontuacao.length; i++){
            Total = Total + VetorPontuacao[i];
        }
        
        for(int i = 0; i < VetorPontuacao.length; i++){
            VetorPontuacaoTemp[i] = Total / VetorPontuacao[i];
        }
        
        
    }

}

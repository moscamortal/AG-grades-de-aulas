package algoritmo.genetico;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author leandro
 */
public class Fitness {

    ManiLista Listas = new ManiLista();
    public static int FitnessMelhor;
    public static int[] VetorPontuacao;

    public int SelecionarMaisApto(String FitnessAtual, String Populacao[]) {
        int[] VetorPontuascao;
        int IndiceMelhor = 0;
        int Melhor = -50;
        VetorPontuacao = new int[Populacao.length];

        for (int i = 0; i < Populacao.length; i++) {
            VetorPontuacao[i] = Fitness(Populacao[i], FitnessAtual);
        }

        //VetorPontuacao[0] = 0;
        for (int i = 0; i < VetorPontuacao.length; i++) {

            if (Melhor < VetorPontuacao[i]) {

                Melhor = VetorPontuacao[i];
                IndiceMelhor = i;
            }
        }

        Fitness.FitnessMelhor = VetorPontuacao[IndiceMelhor];
        VetorPontuascao = VetorPontuacao;
        return IndiceMelhor;

    }

    public int Fitness(String Populacao, String restricoes) {
        int pontos = 0;

        //perde pontos pelas restricoes de dias
        for (int i = 0; i < Populacao.length(); i++) { // Funciona
            int a = Integer.valueOf(String.valueOf(Populacao.charAt(i)));
            int b = Integer.valueOf(String.valueOf(restricoes.charAt(i)));

            if (Integer.valueOf(String.valueOf(Populacao.charAt(i))) > 0
                    && Integer.valueOf(String.valueOf(restricoes.charAt(i))) == 1) {
                pontos = pontos - 1;
            }
        }

        //perde pontos pelas restricoes de periodo
        ArrayList<String> vetorTemp = new ArrayList();

        for (int i = 0; i < 5; i++) {
            for (int x = 0; x < Populacao.length(); x = x + 5) {
                vetorTemp.add(String.valueOf(Populacao.charAt(x + i)));
            }
            pontos = pontos + Listas.ValidarQuantidadeLetras(vetorTemp);

            vetorTemp.clear();
        }

       for (int j = 0; j < Populacao.length() / 5; j++) {
            String p = Populacao;

            String b = p.substring(j * 5, (j * 5) + 5);

            pontos = pontos + Listas.ContarVezesAparece(b, j);

        }

        /*   while (i < Populacao.length() / 5) {

            for (int x = 5; x < Populacao.length(); x = x + 5) {
                String a = String.valueOf(Populacao.charAt(i));
                String b = String.valueOf(Populacao.charAt(i + x));

                if (String.valueOf(Populacao.charAt(i * 5)).equalsIgnoreCase(String.valueOf(Populacao.charAt((i * 5) + 4)))
                        && !String.valueOf(Populacao.charAt(i)).equalsIgnoreCase("0")) {
                    pontos = pontos - 1;

                }
            }

            i++;
        }*/
        if (pontos
                == 0) {
            int a = 1;
        }
        return pontos;
    }

    public int[] getFitness() {
        return VetorPontuacao;
    }
}

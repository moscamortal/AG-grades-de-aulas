package algoritmo.genetico;

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

    public static int FitnessMelhor;
    public static int[] VetorPontuacao;
    
    public int SelecionarMaisApto(String FitnessAtual, String Populacao[]) {
        int Melhor = 0;
        VetorPontuacao = new int[Populacao.length];

        for (int i = 0; i < Populacao.length; i++) {
            VetorPontuacao[i] = Fitness(Populacao[i], FitnessAtual);
        }

        for (int i = 0; i < VetorPontuacao.length; i++) {

            if (Melhor < VetorPontuacao[i]) {
                Melhor = i;
            }
        }

        this.FitnessMelhor = VetorPontuacao[Melhor];
        return Melhor;

    }

    public int Fitness(String Populacao, String restricoes) {
        int pontos = 0;

        //perde pontos pelas restricoes de dias
        for (int i = 0; i < Populacao.length(); i++) {
            int a = Integer.valueOf(String.valueOf(Populacao.charAt(i)));
            int b = Integer.valueOf(String.valueOf(restricoes.charAt(i)));

            if (Integer.valueOf(String.valueOf(Populacao.charAt(i))) > 0 && Integer.valueOf(String.valueOf(restricoes.charAt(i))) == 1) {
                pontos = pontos - 1;
                //  System.out.println("Pontos por restricoes " + pontos);
            }
        }
        //perde pontos pelas restricoes de periodo
        for (int x = 0; x < Populacao.length(); x++) {

            for (int j = 0; j < Populacao.length() - 5; j++) {

                int a = Integer.valueOf(String.valueOf(Populacao.charAt(j)));
                int b = Integer.valueOf(String.valueOf(Populacao.charAt(j)));
                int c = Integer.valueOf(String.valueOf(Populacao.charAt(j + 5)));
                
                if (Integer.valueOf(String.valueOf(Populacao.charAt(j))) > 0
                        && Integer.valueOf(String.valueOf(Populacao.charAt(j)))
                        == Integer.valueOf(String.valueOf(Populacao.charAt(j + 5)))) {

                    pontos = pontos - 1;
                }
            }
        }
        return pontos;
    }

    /*blic int[] CompararFitness(String FitnessAtual, String Populacao[], int TamanhoPopulacao, int TamanhoIndividuo) {

        int[] VetorPontuacao = new int[TamanhoPopulacao];
        int menor = TamanhoPopulacao;
        int indiceMenor = 0;

        for (int x = 0; x < TamanhoPopulacao; x++) {

            for (int i = 0; i < TamanhoIndividuo; i++) {

                if (Populacao[x].charAt(i) != FitnessAtual.charAt(i)) {
                    VetorPontuacao[x] = VetorPontuacao[x] + 1;
                }

            }

        }

        /*if (Populacao[indiceMenor] != FitnessAtual && VetorPontuacao[indiceMenor] == 0) {
            VetorPontuacao[indiceMenor] = VetorPontuacao[indiceMenor] + 1;
        }*/
 /* return VetorPontuacao;
    }

 /* public int SelecionarMaisApto(int VetorAptdao[], String Fitness, String[] Populacao) {

        int IndiceMelhor = 0;
        int Melhor = VetorAptdao[0];
        for (int i = 0; i < VetorAptdao.length; i++) {

            if (Melhor > VetorAptdao[i]) {
                Melhor = VetorAptdao[i];
                IndiceMelhor = i;
            }     
        }
        
        if (FitnessMelhor == 0 && !Populacao[IndiceMelhor].equalsIgnoreCase(Fitness) && Melhor == 0) {
                FitnessMelhor++;
            }

        this.FitnessMelhor = Melhor;
        return IndiceMelhor;
    }*/
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo.genetico;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author leandro
 */
public class AlgoritmoGenetico {

    /**
     * @param args the command line arguments
     */
    // TODO code application logic here
    static ArrayList< String> leitura = new ArrayList();
    static String comLetras;
    static String semNumeros;
    static int IndiceMelhor;
    static int FitnessMelhor = -1;
    static String[] PopulacaoPosInicializada;
    static int Ciclos = 0;

    public static void main(String[] args) throws FileNotFoundException {

        String ListaString;
        String populacao;
        String Fitness;
        ArrayList< Professor> ProfessoresAulas = new ArrayList();

        ManiArquivo ManipularArquivos = new ManiArquivo();

        //----- Manipula o Text Lido 
        ManiLista listas = new ManiLista();
        InicializaPopulacao pop = new InicializaPopulacao(); // Inicializa a populacao

        ListaString = listas.listaToString(ManipularArquivos.carregarProfessor()); //Converte para String a Lista de numeros lidos do arquiv ode texto
        populacao = listas.removerLetras(ListaString); //Retirar tudo q nao for numero do arquivo de texto lido
        Fitness = listas.inverterString(populacao); //Inverte os 0 por 1, para temos a Fitness

        int primeiraRodada = 0;

        int i = 0;
        do { // Validação por 1000 populações
            do {
                i++;
                // System.out.println(2);
                if (primeiraRodada == 0) {
                    primeiraRodada++;
                    //  System.out.println(3);
                    //----- Gerar populacao
                    String PopulacaoAleatoriaInicial[];
                    PopulacaoAleatoriaInicial = pop.GerarPopulacaoAleatoria(populacao.length(), ManipularArquivos.getProfessor());

                    //----- Inicio do Fitness
                    Fitness fitness = new Fitness();

                    //IndiceMelhor = fitness.SelecionarMaisApto(fitness.CompararFitness(Fitness, PopulacaoAleatoriaInicial, PopulacaoAleatoriaInicial.length, populacao.length()), Fitness, PopulacaoAleatoriaInicial);
                    IndiceMelhor = fitness.SelecionarMaisApto(Fitness, PopulacaoAleatoriaInicial);

                    //----- Inicio da reprodução 
                    Reproducao repdorucao = new Reproducao();

                    PopulacaoPosInicializada = repdorucao.Reproduzir(PopulacaoAleatoriaInicial[IndiceMelhor], PopulacaoAleatoriaInicial, IndiceMelhor);

                    //----- Inicio Mutacao
                    Mutacao mutacao = new Mutacao();
                    mutacao.Mutar(PopulacaoPosInicializada);
                    // int aaa = 1;

                    //----- Validação da Fitness
                    FitnessMelhor = fitness.FitnessMelhor;
                    Ciclos++;
                    i++;

                } else if (FitnessMelhor != 0) {
                    if (i % 5000 == 1) {
                        PopulacaoPosInicializada = pop.GerarPopulacaoAleatoria(populacao.length(), ManipularArquivos.getProfessor());
                    }
                    // System.out.println(4);
                    //----- Inicio do Fitness
                    Fitness fitness = new Fitness();

                    IndiceMelhor = fitness.SelecionarMaisApto(Fitness, PopulacaoPosInicializada);

                    //System.out.println(PopulacaoPosInicializada[IndiceMelhor]);
                    FitnessMelhor = fitness.FitnessMelhor;
                    //---- Inicio da Reprodução
                    Reproducao repdorucao = new Reproducao();
                    PopulacaoPosInicializada = repdorucao.Reproduzir(PopulacaoPosInicializada[IndiceMelhor], PopulacaoPosInicializada, IndiceMelhor);

                    //----- Inicio da Mutação
                    Mutacao mutacao = new Mutacao();

                    String[] Mutar = mutacao.Mutar(PopulacaoPosInicializada);

                    //----- Validação da Fitness
                    IndiceMelhor = fitness.SelecionarMaisApto(Fitness, PopulacaoPosInicializada);
                    FitnessMelhor = fitness.FitnessMelhor;
                    Ciclos++;

                }

            } while (FitnessMelhor != 0 && i < 100000);
        } while (FitnessMelhor != 0 && i < 100000);

        if (FitnessMelhor == 0) {

            ProfessoresAulas = ManipularArquivos.getProfessor();
            ArrayList< Professor> ProfessorImpresso = new ArrayList();

            System.out.println("VocÊ chjegou a resultado ideal\n");
            //System.out.println(PopulacaoPosInicializada[IndiceMelhor]);
            System.out.println("Em " + Ciclos + " Ciclos\n");

            int contator = 0;
            System.out.println("Prof.\t\tSEG\tTER\tQUA\tQUI\tSEX");

            for (int var = 0; var < ManiArquivo.ProfessoresAulas.size(); var++) {

                if (contator == 0) {

                    Professor p = listas.StringParaProfessor(PopulacaoPosInicializada[IndiceMelhor].substring(0, 5), ProfessoresAulas.get(var).Nome);
                    //Professor p = listas.StringParaProfessor(PopulacaoPosInicializada[IndiceMelhor].substring(0, 5), ProfessoresAulas.get(var).Nome);
                    ProfessorImpresso.add(p);
                    /* System.out.println(listas.DiasAulas(ProfessorImpresso.get(var)));
                     */
                    System.out.println(p.Nome + "\t\t" + p.Segunda + "\t" + p.Terca + "\t" + p.Quarta + "\t" + p.Quinta + "\t" + p.Sexta);
                    contator = contator + 5;

                } else {
                    Professor p = listas.StringParaProfessor(PopulacaoPosInicializada[IndiceMelhor].substring(contator, contator + 5), ProfessoresAulas.get(var).Nome);
                    System.out.println(p.Nome + "\t\t" + p.Segunda + "\t" + p.Terca + "\t" + p.Quarta + "\t" + p.Quinta + "\t" + p.Sexta);
                    ProfessorImpresso.add(p);
                    //  System.out.println(listas.DiasAulas(ProfessorImpresso.get(var)));
                    contator = contator + 5;

                }
            }


            /*    for (int u = 0; u < PopulacaoPosInicializada.length; u++) {
                System.out.println(PopulacaoPosInicializada[u]);
            }
            System.out.println(IndiceMelhor);
             */
        } else {
            System.out.println("Não existe solução possivel");
            System.out.println("Em " + i + " Ciclos");
            System.out.println(Fitness);

            for (int u = 0; u < PopulacaoPosInicializada.length; u++) {
                System.out.println(PopulacaoPosInicializada[u]);
            }
            System.out.println("");
            System.out.println(Fitness);
            System.out.println(PopulacaoPosInicializada[0]);
            System.out.println(PopulacaoPosInicializada[IndiceMelhor]);

        }

        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNext()) {
        }
    }

}

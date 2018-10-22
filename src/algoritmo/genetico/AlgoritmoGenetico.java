/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo.genetico;

import java.io.FileNotFoundException;
import java.util.ArrayList;

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

        //System.out.println(populacao);
        //System.out.println(Fitness);
        int primeiraRodada = 0;

        int i = 0;
        do { // Validação por 1000 populações
            //  System.out.println(1);
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
                    IndiceMelhor = fitness.SelecionarMaisApto(Fitness,PopulacaoAleatoriaInicial);

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
          //          System.out.println(Fitness);
                } else if (FitnessMelhor != 0) {
                    // System.out.println(4);
                    //----- Inicio do Fitness
                    Fitness fitness = new Fitness();

                    IndiceMelhor = fitness.SelecionarMaisApto(Fitness,PopulacaoPosInicializada);
                    //System.out.println(PopulacaoPosInicializada[IndiceMelhor]);

                    //---- Inicio da Reprodução
                    Reproducao repdorucao = new Reproducao();
                    PopulacaoPosInicializada = repdorucao.Reproduzir(PopulacaoPosInicializada[IndiceMelhor], PopulacaoPosInicializada, IndiceMelhor);

                    //----- Inicio da Mutação
                    Mutacao mutacao = new Mutacao();

                    String[] Mutar = mutacao.Mutar(PopulacaoPosInicializada);

                    //----- Validação da Fitness
                    FitnessMelhor = fitness.FitnessMelhor;
                    Ciclos++;
                    //i++;
                    //System.out.println(Fitness);
                    //  System.out.println(PopulacaoPosInicializada[IndiceMelhor]);
                }
                /*if (!PopulacaoPosInicializada[IndiceMelhor].equals(Fitness) && FitnessMelhor == 0) {
                    FitnessMelhor++;
                }*/
                //System.out.println("Ciclos " + i);

            } while (FitnessMelhor != 0 && i < 1000000);
        } while (FitnessMelhor != 0 && i < 1000000);

        if (FitnessMelhor == 0) {

            ProfessoresAulas = ManipularArquivos.getProfessor();
            ArrayList< Professor> ProfessorImpresso = new ArrayList();

            System.out.println("VocÊ chjegou a resultado ideal");
            System.out.println(PopulacaoPosInicializada[IndiceMelhor]);
            System.out.println("Em " + Ciclos + " Ciclos");

            int contator = 0;
            for (int var = 0; var < ManiArquivo.ProfessoresAulas.size(); var++) {
                System.out.println("");
                if (contator == 0) {
                    Professor p = listas.StringParaProfessor(PopulacaoPosInicializada[IndiceMelhor].substring(0, 5), ProfessoresAulas.get(var).Nome);
                    ProfessorImpresso.add(p);
                    System.out.println(listas.DiasAulas(ProfessorImpresso.get(var)));
                    contator = contator + 5;
                } else {
                    Professor p = listas.StringParaProfessor(PopulacaoPosInicializada[IndiceMelhor].substring(contator, contator + 5), ProfessoresAulas.get(var).Nome);
                    ProfessorImpresso.add(p);
                    System.out.println(listas.DiasAulas(ProfessorImpresso.get(var)));
                    contator = contator + 5;

                }
            }
            //System.out.println(Fitness);
            //System.out.println(PopulacaoPosInicializada[IndiceMelhor]);

        } else if (PopulacaoPosInicializada[0].equalsIgnoreCase(Fitness) && FitnessMelhor == 0) {

            ProfessoresAulas = ManipularArquivos.getProfessor();
            ArrayList< Professor> ProfessorImpresso = new ArrayList();

            System.out.println("VocÊ chjegou a resultado ideal");
            System.out.println(PopulacaoPosInicializada[IndiceMelhor]);
            System.out.println("Em " + Ciclos + " Ciclos");

            int contator = 0;
            for (int var = 0; var < ManiArquivo.ProfessoresAulas.size(); var++) {
                System.out.println("");
                if (contator == 0) {
                    Professor p = listas.StringParaProfessor(Fitness.substring(0, 5), ProfessoresAulas.get(var).Nome);
                    ProfessorImpresso.add(p);
                    System.out.println(listas.DiasAulas(ProfessorImpresso.get(var)));
                    contator = contator + 5;
                } else {
                    Professor p = listas.StringParaProfessor(Fitness.substring(contator, contator + 5), ProfessoresAulas.get(var).Nome);
                    ProfessorImpresso.add(p);
                    System.out.println(listas.DiasAulas(ProfessorImpresso.get(var)));
                    contator = contator + 5;

                }
            }
            System.out.println(Fitness);
            System.out.println(PopulacaoPosInicializada[0]);
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

    }
}

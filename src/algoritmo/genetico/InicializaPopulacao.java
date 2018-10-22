/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo.genetico;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author leandro
 */
public class InicializaPopulacao {

    ManiArquivo ManipularArquivos = new ManiArquivo();
    ManiLista ManipularLista = new ManiLista();

    public String[] GerarPopulacaoAleatoria(int QntNumeros, ArrayList< Professor> ProfessoresAulas) {

        ArrayList< Professor> ProfessoresAulasTemp = new ArrayList();

        ManiLista ManipularLista = new ManiLista();
        int TamanhoVetor = QntNumeros;
        String populacao[] = new String[TamanhoVetor];

        ProfessoresAulas = ManipularArquivos.getProfessor();
        int AlatorioAtual;
        String ProfessorTemp = "";
        int DiasDisponiveisProfessorTemp;
        int DiasContator = 0;
        int ControlaPopulacao = 0;
        String PopulacaoTemp = "";

        //ManipularLista.ValidarDiasDisponiveis(ProfessoresAulas);
        for (int y = 0; y < ProfessoresAulas.size() * 5; y++) {

            for (int i = 0; i < ProfessoresAulas.size(); i++) {
                DiasDisponiveisProfessorTemp = ProfessoresAulas.get(i).DiasAulas;
                int controlador = 0;
                ArrayList<Integer> ProfessorPeriodoTemp = new ArrayList<Integer>();
                for (int x = 0; x < 5; x++) {
                    Random gerador = new Random();


                    if (ProfessorPeriodoTemp.size() == 0) {
                        ProfessorPeriodoTemp.addAll(ProfessoresAulas.get(i).Periodos);
                    }

                    while (ProfessorPeriodoTemp.size() < 5 && controlador == 0) {
                        ProfessorPeriodoTemp.add(0);

                    }
                    controlador++;
                    //ProfessorPeriodoTemp
                    AlatorioAtual = gerador.nextInt(ProfessorPeriodoTemp.size()); // Gera o Numero aleatorio

                    if (ProfessorPeriodoTemp.get(AlatorioAtual) != 0) {
                        DiasContator++;
                    }

                    if (DiasContator <= DiasDisponiveisProfessorTemp) {

                        ProfessorTemp = ProfessorTemp + String.valueOf(ProfessorPeriodoTemp.get(AlatorioAtual));
                        ProfessorPeriodoTemp.remove(AlatorioAtual);

                    } else {
                        ProfessorTemp = ProfessorTemp + "0";
                    }


                }

                if (ControlaPopulacao <= ProfessoresAulas.size()) {
                    PopulacaoTemp = PopulacaoTemp + ProfessorTemp;
                    ControlaPopulacao++;
                    if (ControlaPopulacao == ProfessoresAulas.size()) {
                        ControlaPopulacao = 0;
                        populacao[y] = PopulacaoTemp;
                        PopulacaoTemp = "";
                        DiasDisponiveisProfessorTemp = ProfessoresAulas.get(i).DiasAulas;
                    }
                }
                Professor teste = (ManipularLista.StringParaProfessor(ProfessorTemp, ProfessoresAulas.get(i).Nome));
                teste.DiasAulas = DiasDisponiveisProfessorTemp;
                ProfessoresAulasTemp.add(teste);
                DiasContator = 0;
                ProfessorTemp = "";
            }
        }

        return populacao;
    }
}

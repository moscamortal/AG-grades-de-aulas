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
public class MutacaoAntiga {

    Random gerador = new Random();
    ManiLista manilista = new ManiLista();
    ManiArquivo ManipularArquivos = new ManiArquivo();

    public ArrayList< Professor> ProfessoresAulas = ManipularArquivos.getProfessor();

    int DiasTotaisProfs = manilista.ValidarDiasDisponiveis(ProfessoresAulas);

    public String[] Mutar(String Populacao[]) {

        for (int i = 0; i < Populacao.length; i++) {
            String IndividuoTemp = null;

            for (int x = 0; x < Populacao[i].length(); x++) {
                int randomNum = gerador.nextInt(100);

                if (randomNum == 1) {

                    if (IndividuoTemp == null) {

                        IndividuoTemp = manilista.inverterString(String.valueOf(Populacao[i].charAt(x)));

                        if (ManipularArquivos.ContadorLetras(IndividuoTemp) > DiasTotaisProfs) {
                            int a = manilista.EncontrarLetra(IndividuoTemp);
                            IndividuoTemp = IndividuoTemp.replace(IndividuoTemp.charAt(a), '0');
                        }

                    } else if (ManipularArquivos.ContadorLetras(IndividuoTemp) < DiasTotaisProfs) {

                        IndividuoTemp = IndividuoTemp + manilista.inverterString(String.valueOf(Populacao[i].charAt(x)));

                    } else if (ManipularArquivos.ContadorLetras(IndividuoTemp) == DiasTotaisProfs) {

                        IndividuoTemp = IndividuoTemp + manilista.inverterString(String.valueOf(Populacao[i].charAt(x)));
                        int a = manilista.EncontrarLetra(IndividuoTemp);
                        IndividuoTemp = IndividuoTemp.replace(IndividuoTemp.charAt(a), '0');

                    }
                } else if (IndividuoTemp == null) {
                    IndividuoTemp = String.valueOf(Populacao[i].charAt(x));
                } else {
                    IndividuoTemp = IndividuoTemp + String.valueOf(Populacao[i].charAt(x));
                }
                if (ManipularArquivos.ContadorLetras(IndividuoTemp) > DiasTotaisProfs) {
                    int a = manilista.EncontrarLetra(IndividuoTemp);
                    IndividuoTemp = IndividuoTemp.replace(IndividuoTemp.charAt(a), '0');
                }
                /* if (Populacao[x].length() < ProfessoresAulas.size() * 5) {
                    int aaa = 1;
                }
                if (Populacao[x].length() > ProfessoresAulas.size() * 5) {
                    int aaa = 1;
                }*/
            }

            Populacao[i] = IndividuoTemp;
            //System.out.println(Populacao[i]);        
        }

        return Populacao;

    }
}

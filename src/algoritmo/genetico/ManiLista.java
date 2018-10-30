package algoritmo.genetico;

import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author leandro
 */
public class ManiLista {

    ManiArquivo ManipularArquivos = new ManiArquivo();
    // public static int DiasSolucao[] = new int[];

    public String listaToString(ArrayList<String> stockList) {

        String[] stockArr = new String[stockList.size()];
        stockArr = stockList.toArray(stockArr);
        String aa = "";

        for (String comLetras : stockArr) {
            //   System.out.println(comLetras);
            aa = aa + comLetras;

        }
        return (aa);
    }

    public String removerLetras(String s) {

        String aa = s.replaceAll("[^0-9]", "");
        //System.out.println(aa);

        return (aa);
    }

    public String inverterString(String aa) {

        String invertida = aa.replaceAll("[1]", "2");
        invertida = invertida.replaceAll("[0]", "1");
        invertida = invertida.replaceAll("[2]", "0");

        //System.out.println(invertida);
        return (invertida);
        //gerarVetor(invertida);
    }

    public String DiasAulas(Professor professor) {
        String aulas = "Professor: " + professor.Nome + " terá aulas nos dias: ";
        if (!"0".equals(professor.Segunda)) {
            aulas = aulas + "Segunda-Feira,";
        }
        if (!"0".equals(professor.Terca)) {
            aulas = aulas + "Terca-Feira, ";
        }
        if (!"0".equals(professor.Quarta)) {
            aulas = aulas + "Quarta-Feira, ";
        }
        if (!"0".equals(professor.Quinta)) {
            aulas = aulas + "Quinta-Feira, ";
        }
        if (!"0".equals(professor.Sexta)) {
            aulas = aulas + "Sexta-Feira ";
        }

        return aulas;
    }

    public int ValidarDiasDisponiveis(ArrayList< Professor> Professores) {
        int AulasTotais = 0;

        for (int i = 0; i < Professores.size(); i++) {
            AulasTotais = AulasTotais + Professores.get(i).DiasAulas;
        }
        return AulasTotais;
    }

    public String ProfessorParaLista(Professor p) {
        String ProfessorString;
        ProfessorString = p.Segunda + p.Terca + p.Quarta + p.Quinta + p.Sexta;

        return ProfessorString;
    }

    public Professor StringParaProfessor(String Professor, String NomeProfessor) {
        Professor p = new Professor(NomeProfessor,
                Professor.substring(0, 1),
                Professor.substring(1, 2),
                Professor.substring(2, 3),
                Professor.substring(3, 4),
                Professor.substring(4, 5).replaceAll("(\\r|\\n)", ""));

        return p;
    }

    public int EncontrarLetra(String StringSerBuscadoLetra) {

        ArrayList<Integer> lista = new ArrayList<>();
        int a;

        int separador = StringSerBuscadoLetra.length() / ManipularArquivos.getProfessor().size();

        for (int i = 0; i < StringSerBuscadoLetra.length(); i++) { // 5x
            if (StringSerBuscadoLetra.charAt(i) != '0') {

                lista.add(i);

            }
        }
        Random r = new Random();
        if (lista.size() == 0) {
            a = 0;

        } else {
            a = (lista.get(r.nextInt(lista.size())));
        }
        return a;
    }

    public int EncontrarLetraEspecifica(String StringSerBuscadoLetra, char LetraBusca) {
        ArrayList<Integer> lista = new ArrayList<>();
        int a;

        String Novotexto = "";
        int ContadorLetras = 0;
        ArrayList<Integer> VetorTemp = new ArrayList<Integer>();

        for (int i = 0; i < StringSerBuscadoLetra.length(); i++) { // 5x
            if (StringSerBuscadoLetra.charAt(i) == LetraBusca) {
                lista.add(i);

            }
        }
        Random r = new Random();
        if (lista.size() == 0) {
            a = 0;

        } else {
            a = (lista.get(r.nextInt(lista.size())));
        }
        return a;
    }

    public void SelecionarPeriodo(ArrayList<Integer> Periodos) {
        ArrayList<Integer> lista = new ArrayList<>();
        for (int i = 0; i < Periodos.size(); i++) { // 5x

            if (Periodos.get(i) != 0) {

                lista.add(i);

            }
        }

        Random r = new Random();
        int a;
        if (Periodos.size() == 0) {
            a = 0;

        } else {
            a = Periodos.get(r.nextInt(Periodos.size()));
        }
    }

    public int PrimeiroUm(String Frase, char Letra) {
        int posicaoUm;
        if (Frase == null) {
            return 0;
        }
        for (int i = 0; i < Frase.length(); i++) {
            if (Frase.charAt(i) == Letra) {
                posicaoUm = i;
                return posicaoUm;
            }
        }
        return 0;
    }

    public String EncontrarCaracter(String Frase, ArrayList<Integer> Periodos, int prof) { // Validador se o resultado é valido
        int posicaoUm;
        int[] vezesAparece = new int[Periodos.size()];
        ManiArquivo Arquivo = new ManiArquivo();
        ArrayList< Professor> ProfessoresAulas = Arquivo.getProfessor();
        Random gerador = new Random();

        for (int x = 0; x < Periodos.size(); x++) {

            for (int i = 0; i < Frase.length(); i++) { // Roda a quantidade de vezes de periodos que o professor tem
                String temp = String.valueOf(Periodos.get(x));

                if (Frase.charAt(i) == temp.charAt(0)) { // Compara cada caracter da Frase com o periodo I do professor
                    vezesAparece[x]++;
                }

            }

        }
       /* for (int x = 0; x < Periodos.size(); x++) {
            for (int i = 0; i < Periodos.size()-i; i++) {
                if (Periodos.get(i) == Periodos.get(x) && vezesAparece[x] > 1) {
                    vezesAparece[i] = 0;
                }
            }
        }*/
        for (int x = 0; x < vezesAparece.length; x++) {

            if (vezesAparece[x] > 1) {

                String temp = String.valueOf(Periodos.get(x));

                int separador = EncontrarLetraEspecifica(Frase, temp.charAt(0));

                Frase = Frase.substring(0, separador) + "0" + Frase.substring(separador + 1, Frase.length());
                vezesAparece[x]--;

            }

        }
        for (int x = 0; x < vezesAparece.length; x++) {

            if (vezesAparece[x] < 1) {
                String temp = String.valueOf(Periodos.get(x));

                int separador = EncontrarLetraEspecifica(Frase, '0');

                Frase = Frase.substring(0, separador) + String.valueOf(Periodos.get(x)) + Frase.substring(separador + 1, Frase.length());
                vezesAparece[x]++;

            }

        }

        if (Arquivo.ContadorLetras(Frase) > ProfessoresAulas.get(prof).DiasAulas) {
            int randomNum = gerador.nextInt(Periodos.size());
            Frase.replace(Frase.charAt(randomNum), '0');
        }
        return Frase;
    }

    public int ValidarQuantidadeLetras(ArrayList Frase) {
        int pontos = 0;

        for (int i = 0; i < Frase.size(); i++) {

            for (int x = i + 1; x < Frase.size(); x++) {

                if (Frase.get(i).equals(Frase.get(x)) && !Frase.get(i).equals("0")) {
                    pontos = pontos - 1;
                }
            }
        }
        return pontos;
    }

    public int ContarVezesAparece(String Frase, int IndiceProfessor) {
        ArrayList<Professor> Professores = ManipularArquivos.getProfessor();
        int vezesQueAparece = 0;
        int pontos = 0;
        for (int i = 0; i < Frase.length(); i++) {

            if (Frase.charAt(i) != '0') {
                vezesQueAparece++;
            }

        }
        if (vezesQueAparece < Professores.get(IndiceProfessor).Periodos.size()) {
            pontos = -1;
        }
        return pontos;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo.genetico;

import static algoritmo.genetico.AlgoritmoGenetico.leitura;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Leandro Stival
 */
public class ManiArquivo {

    public static ArrayList< Professor> ProfessoresAulas = new ArrayList();
    public static ArrayList< String> DiasAulas = new ArrayList< String>();
    int[] Periodos;

    public ArrayList< Professor> getProfessor() {
        return ProfessoresAulas;
    }

    public ArrayList<String[]> carregarDias() throws FileNotFoundException {

        Scanner ler = new Scanner("b.txt");
        String nome = ler.nextLine();
        String ab[];
        ArrayList<String[]> ProfessorTemp = new ArrayList<String[]>();

        try (BufferedReader br = new BufferedReader(new FileReader("b.txt"))) {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                ab = sCurrentLine.split(Pattern.quote(","));
                ProfessorTemp.add(ab);
                System.out.println(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ProfessorTemp;
    }

    /* Scanner Scanner = new Scanner(new FileReader("b.txt")).useDelimiter("\\,");

         while (scanner.hasNext()) {

            String Nome = scanner.next();
            Nome = Nome.replaceAll("(\\r|\\n)", "");
            a = Integer.parseInt(scanner.next());

            if (Nome.equalsIgnoreCase(ProfessorNome)) {
                return a;
return Periodos.length;
            }
            i++;
        }*/
    public ArrayList< String> carregarProfessor() throws FileNotFoundException {
        ArrayList<String[]> ProfessorTemp = new ArrayList<String[]>();

        Scanner scanner = new Scanner(new FileReader("a.txt")).useDelimiter("\\,");
        int x = 0;
        String[] Periodos;
        Scanner ler = new Scanner("a.txt");
        String nome = ler.nextLine();

        while (scanner.hasNext()) {
            String Nome = scanner.next();
            Nome = Nome.replaceAll("(\\r|\\n)", "");
            String Segunda = scanner.next();
            String Terça = scanner.next();
            String Quarta = scanner.next();
            String Quinta = scanner.next();
            String Sexta = scanner.next();

            Professor p = new Professor(Nome, Segunda, Terça, Quarta, Quinta, Sexta);
            //  p.DiasAulas = carregarDias(Nome);
            ProfessorTemp = carregarDias();
            ArrayList <Integer> PeriodosLista = new ArrayList <Integer>();
            for (int u = 0; u < ProfessorTemp.size(); u++) {
                Periodos = ProfessorTemp.get(u);
                if (Periodos[0].equalsIgnoreCase(Nome)) {
                    int PeriodosTemp[] = new int[Periodos.length - 1];
                    int j = 0;
                    for (int y = 1; y < Periodos.length; y++) {
                        PeriodosTemp[j] = Integer.valueOf(Periodos[y]);
                        j++;
                    }
                    for (int i = 0; i < PeriodosTemp.length; i++) {
                        PeriodosLista.add(PeriodosTemp[i]);
                    }
                    p.Periodos = PeriodosLista;
                    p.DiasAulas = PeriodosTemp.length;
                }

            }

            ProfessoresAulas.add(p);
            //System.out.println(ProfessoresAulas.get(x).Nome);
            x++;

        }

        try {
            int i = 0;
            FileReader arq = new FileReader(nome);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();
            leitura.add(linha);

            while (linha != null) {

                linha = lerArq.readLine();
                leitura.add(linha);
            }
            arq.close();

        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
        return leitura;
    }

    public static void gerarVetor(String invertida) {
        int n = invertida.length();
        int x = 0;
        int i;
        int temp;

        int v[] = new int[n];

        for (i = 0; i < n; i++) {
            x = i + 1;
            temp = Integer.parseInt(invertida.substring(i, x));
            x++;
            v[i] = temp;
        }
    }

    public int ContadorLetras(String DiasDaSemana) { // Conta quantas vezes o numero 1 aparece na Strinf recebida e retorna a quantidade de vezes

        int total = 0;
        for (int i = 0; i < DiasDaSemana.length(); i++) {
            char ch = DiasDaSemana.charAt(i);
            String x1 = String.valueOf(ch);
            if (!x1.equalsIgnoreCase("0")) {
                total = total + 1;
            }
        }
        return total;

    }
}

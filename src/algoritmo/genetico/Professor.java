/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo.genetico;

import java.util.ArrayList;

/**
 *
 * @author Leandro Stival
 */
public class Professor {

    String Nome;
    String Segunda;
    String Terca;
    String Quarta;
    String Quinta;
    String Sexta;
    int DiasAulas;
    ArrayList <Integer> Periodos;
   // int[] Periodos;

    public Professor(String Nome, String Segunda, String Terça, String Quarta, String Quinta, String Sexta) {
        this.Nome = Nome;
        this.Segunda = Segunda;
        this.Terca = Terça;
        this.Quarta = Quarta;
        this.Quinta = Quinta;
        this.Sexta = Sexta;
    }

    public void getProfessor() {

    }
}

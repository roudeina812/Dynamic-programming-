package com.company;

import java.util.ArrayList;
import  java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int fin=0;
        double score = 70;
        List<ArrayList<Integer>> solution = new ArrayList<>();
        ex1.select();
        ex1.somme(fin,solution);
        for(int i =0 ; i<solution.size(); i++)
        {
            ex1.AfficheArray(solution.get(i));
        }//pour effacer
        ArrayList<Integer> farouk = new ArrayList<>();
        long end = System.currentTimeMillis()+60000;
        long start=System.currentTimeMillis();
        boolean result=true;
        int compteur = 0;
        do {
            System.out.println("\nIl vous reste "+((end-start)/1000)+" secondes");
            farouk.clear();
            do {
                result = ex1.choose(farouk);
            } while (!result);
            ex1.AfficheArray(farouk);
             Collections.sort(farouk);
            if (ex1.contains(farouk, solution)) {
                System.out.println("solution existant ");
                compteur++;
            } else
                System.out.println("solution non existant ");
            score = ex1.calculer_score(farouk,solution,score,compteur);
            start=System.currentTimeMillis();
        } while(end>start && compteur!= solution.size());
        if(end<=start)
            System.out.println("Temps ecoulé :( ");
        else if ( compteur == solution.size())
            System.out.println("Bravo !! c'est la meuilleure solution ");

        System.out.println("votre score" + score);

        // write your code here
    }

}

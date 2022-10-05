package com.company;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;
import java.util.Random;
import java.util.Scanner;

public class ex1 {
    public static Random rand = new Random();
    public static boolean[][] dpTable;
    public static List<Integer> numbers=new ArrayList<>();
    public static int sum;
    //this method read the sum value and numbers from keyboard
    //numbers are given as String then we use split to extract numbers
    public static Scanner s=new Scanner(System.in);
    public static boolean choose (ArrayList<Integer>user)
    {
        boolean result;
        System.out.println("choisir la liste des nombres ");
        try {
            result=true;
            String expression = s.next();
            String[] tabNumbers = expression.split(",");
            for (String x : tabNumbers) {
                //convert from string to Integer
                Integer val = Integer.valueOf(x);
                //or
                //int val=Integer.parseInt();
                if (val < 0 ) //numbers must be positives
                    throw new Exception("All the set numbers must be  positives");
                user.add(val);


            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = false;
        }
        return result;
    }
    public static void NumList (int a)
    {
        int upperbound = 10;
        for(int i=0; i<a ; i++)
        {
            int random = rand.nextInt(upperbound)+1;
            numbers.add(random);
        }
    }
    //fonction pour donner somme par hasard
    public static void sumSelect ()
    {
        int upperbound = 25;
        sum=rand.nextInt(upperbound)+1;

    }
    // fonction pour saisi les nombres et le sum
    public static void select ()
    {

        sumSelect();
        NumList(6);

        int max=0;
        for(int i=0;i< numbers.size();i++)
        {
            if(max< numbers.get(i))
                max= numbers.get(i);
        }
        if(max>sum)
        {numbers.clear();
            sum=0;
            select();}

        for(int i=0 ; i < numbers.size();i++ )
            System.out.print(numbers.get(i)+"   ");
        System.out.println("\nsomme : "+sum);
    }
    //fill matrix
    public static void fillDpTable()
    {
        dpTable=new boolean[numbers.size()+1][sum+1];
        //The first line of the dpTable matrix is false except the first column
        //from an empty set we can not find sum>0
        for(int i=1;i<=sum;i++)
            dpTable[0][i]=false;
        //the fist column is True
        //zero is on all subsets
        for(int i=0;i<dpTable.length;i++)
            dpTable[i][0]=true;
        //fill the others cases in the dpTable matrix
        for(int i=1;i<dpTable.length;i++)
            for(int j=1;j<dpTable[i].length;j++)
            {
                if(j<numbers.get(i-1)) //we save the previous found sums
                    dpTable[i][j]=dpTable[i-1][j];
                else
                if(dpTable[i-1][j])
                    dpTable[i][j]=true;
                else
                    dpTable[i][j]=dpTable[i-1][j-numbers.get(i-1)];
            }
    }

    //contains user in solution
    public static boolean contains (ArrayList<Integer>user , List<ArrayList<Integer>> sol)
    {
        boolean contain=false;
        int i=0;
        while(i< sol.size()&&!contain)
        {
            if(sol.get(i).equals(user))
                contain=true;
            else
                i++;
        }

        return contain;
    }
    public static boolean hasSolution(){

        return dpTable[numbers.size()][sum];
    }
    public static void translation ()
    {
        int fin = numbers.get(0);
        for(int i=1 ; i< numbers.size();i++)
        {
            numbers.set(i-1,numbers.get(i));
        }
        numbers.set(numbers.size()-1,fin);
    }
    public static ArrayList<Integer> printSolution(int i ,int j){

        ArrayList<Integer> S= new ArrayList<>();
        while(i>0&&j>0)
        {
            if(dpTable[i][j]&&dpTable[i-1][j])
                //if the current row of the column j is true and the row above is also true
                //so we just go to the line above
                i--;
            else
            {
                S.add(numbers.get(i-1));
                j-=numbers.get(i-1);
                i--;
            }

        }
        return S;
    }

    public static boolean trouve (ArrayList<Integer> l,List<ArrayList<Integer>> sol)
    {
        boolean contient = false;
        for (int i=0 ;i< sol.size();i++)
        {
            if(sol.get(i).equals(l))
                contient =true ;

        }
        return contient;
    }
    public static void AfficheArray (ArrayList<Integer> AR)
    {
        System.out.print(" Votre choix : [ ");
        for(int i=0 ;i< AR.size()-1 ; i++)
        {
            System.out.print(AR.get(i) +",");

        }
        System.out.print(AR.get(AR.size()-1)+ " ] ");
        System.out.println("\n");
    }
    public static double calculer_score (ArrayList<Integer>user ,List<ArrayList<Integer>>solution,double score,int com)
    {

        int a = solution.size();

        if(!contains(user,solution))
            return score;

        if (com==a)
            score+=100;

        else
        {if (com>(a/2))
            score=score+score*0.75;
        else
        { if (com==(a/2))
            score=score+score/2;
        else
            score=score+score*0.25;
        }
        }
        return score;

    }
    public static void somme (int fin ,List<ArrayList<Integer>> solution)
    {
        if(fin== numbers.size())
        {
            System.out.print("vas-y \n");
        }
        else
        {
            ArrayList<Integer> l = new ArrayList<>();

            int j = sum;
            int i = numbers.size();
            fillDpTable();
                if (dpTable[i][j])
                {
                    l = printSolution(i, j);
                    Collections.sort(l);
                    if(!trouve(l,solution)) {  solution.add(l); }

                }

            translation();
            fin ++;
            somme(fin, solution);
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Diego
 */
public class Tarea {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<String> erSeparada = new ArrayList();
        ArrayList<String> alfabeto = new ArrayList();
        
        Scanner leer = new Scanner(System.in);
        String er = leer.nextLine();
        
        for (int i = 0; i < er.length(); i++) {
            char caracter = er.charAt(i);
            int ascii = (int) caracter;
            if ((!alfabeto.contains(Character.toString(caracter))) && ((ascii > 64 && ascii < 91) 
                    || (ascii > 96 && ascii < 123))){
                alfabeto.add(Character.toString(caracter));
            }
            if (((ascii > 64 && ascii < 91) || (ascii > 96 && ascii < 123)) ||
                    ascii == 46 || ascii == 42 || ascii == 124 || ascii == 95
                    || ascii == 48 || ascii == 45) {                
                erSeparada.add(Character.toString(caracter)); 
            }else{
                System.out.println("Ingresastes un simbolo nada que ver xd");
                break;
            }           
        }
        //ParseER parse = new ParseER(erSeparada);
        //parse.parsear();
        /*
        for (int i = 0; i < erSeparada.size(); i++) {
            System.out.println(erSeparada.get(i));
        }
            */
        System.out.println("El alfabeto es:");
        for (int i = 0; i < alfabeto.size(); i++) {
            System.out.println(alfabeto.get(i));
        }
        }
    }
    



import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eduardovalenzuela
 */
public class InfiPost {
    
    private Integer precedencia(char c){
        switch (c) {
            case '(':
                return 1;
            case '|':
                return 2;
            case '.':
                return 3;
            case '*':
                return 4;
            default:
                break;
        }
        return null;
        
    }

    private Integer Precedencia(char c) {
            Integer precedenciaObtenida = precedencia(c);
            return precedenciaObtenida == null ? 6 : precedenciaObtenida;
    }
    public String Conversion(String cadena){
        String cadenaFinal = new String();
        Stack<Character> pila = new Stack<>();

        for (char c : cadena.toCharArray()) {
            switch (c) {
                case '(':
                    pila.push(c);
                    break;
                case ')':
                    while (!pila.peek().equals('(')) {
                            cadenaFinal += pila.pop();
                    }
                    pila.pop();
                    break;
                default:
                    while (pila.size() > 0) {
                            char charPila = pila.peek();

                            Integer precedenciaCharPila = Precedencia(charPila);
                            Integer precedenciaCharActual = Precedencia(c);

                            if (precedenciaCharPila >= precedenciaCharActual) {
                                    cadenaFinal += pila.pop();
                            } else {
                                    break;
                            }
                    }
                    pila.push(c);
                    break;
            }

        }

        while (pila.size() > 0)
                cadenaFinal += pila.pop();

        return cadenaFinal;
        
    }
    
}

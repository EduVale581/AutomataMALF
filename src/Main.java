
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author eduardovalenzuela
 */


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String archivoFile = "src/Conca.txt"; 
        boolean expresionValida = true;
        ArrayList<String> alfabeto = new ArrayList();
        AutomataNoDeterminista afnd;
        AutomataFinitoDeterminista afd = null;
        
        FileReader archivo = new FileReader(archivoFile);
        BufferedReader archivoLeido = new BufferedReader(archivo);
        String cadena, er;
        int contador=0; int linea = 1;
        while((cadena = archivoLeido.readLine())!=null){
            if (contador==0) {
                er = cadena;
                for (int i = 0; i < er.length(); i++) {
                    char caracter = er.charAt(i);
                    int ascii = (int) caracter;
                    if ((!alfabeto.contains(Character.toString(caracter))) && ((ascii > 64 && ascii < 91) 
                            || (ascii > 96 && ascii < 123))){
                        alfabeto.add(Character.toString(caracter));
                    }
                    if (((ascii > 64 && ascii < 91) || (ascii > 96 && ascii < 123)) ||
                            ascii == 46 || ascii == 42 || ascii == 124 || ascii == 95
                            || ascii == 48 || ascii == 45 || ascii == 40 || ascii == 41 || ascii == 48) {                
                    }else{
                        System.out.println("Ingresastes un simbolo nada que ver xd");
                        return;
                    }           
                }
                if (expresionValida) {
                    ParseER parse = new ParseER(er,alfabeto);
                    Conversion nueva = new Conversion();
                    afnd = parse.automatas.get(0);
                    for (int j = 0; j < afnd.getAlfabeto().size(); j++) {
                        afnd.addTanciciones(new Transicion(afnd.getInicio(), afnd.getInicio(), afnd.getAlfabeto().get(j).charAt(0)));
                    }
                    afd = nueva.convertirAFNDaAFD(afnd);
                    afnd.mostrarAFND();
                    System.out.println();
                    afd.mostrar();
                    System.out.println("");
                    System.out.println("Ocurrencias: ");
                }
                contador++;
            }
            else{
                Ocurrencia ocurrencia = new Ocurrencia();
                ocurrencia.setAfd(afd);
                ocurrencia.setCadena(cadena);
                ocurrencia.leer(linea);
                linea++;
            }  
        }
    }
    
}
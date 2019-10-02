
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eduardovalenzuela
 */
public class Estado {
    
    private int identificador;
    private ArrayList<Transicion> tanciciones= new ArrayList<>();
    private boolean verificacion;

    public Estado(int identificador) {
        this.identificador = identificador;
        this.verificacion = false;
    }
    
    public Estado(int identificador, ArrayList<Transicion> transiciones) {
        this.identificador = identificador;
        this.tanciciones = transiciones;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public ArrayList<Transicion> getTanciciones() {
        return tanciciones;
    }

    public void setTanciciones(ArrayList<Transicion> tanciciones) {
        this.tanciciones = tanciciones;
    }

    public boolean isVerificacion() {
        return verificacion;
    }

    public void setVerificacion(boolean verificacion) {
        this.verificacion = verificacion;
    }
    
    
    
    
    
    
    
    
    
    
    
    
}

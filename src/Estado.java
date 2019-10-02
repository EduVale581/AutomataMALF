
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
   // private ArrayList<Transicion> tanciciones= new ArrayList<>();
    private boolean verificacion;

    public Estado(int identificador) {
        this.identificador = identificador;
        this.verificacion = false;
    }
    

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public boolean isVerificacion() {
        return verificacion;
    }

    public void setVerificacion(boolean verificacion) {
        this.verificacion = verificacion;
    }
    
    
    
    
    
    
    
    
    
    
    
    
}

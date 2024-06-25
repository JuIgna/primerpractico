package profeDoc;

public class Identificador {
    String nombre;
    TipoDato tipoDato;
    boolean inicializada;
    boolean utilizada;

    public Identificador(String nombre, TipoDato tipoDato) {
        this.nombre = nombre;
        this.tipoDato = tipoDato;
        this.inicializada = false;
        this.utilizada = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoDato getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(TipoDato tipoDato) {
        this.tipoDato = tipoDato;
    }

    public boolean isInicializada() {
        return inicializada;
    }

    public void setInicializada(boolean inicializada) {
        this.inicializada = inicializada;
    }

    public boolean isUtilizada() {
        return utilizada;
    }

    public void setUtilizada(boolean utilizada) {
        this.utilizada = utilizada;
    }

    
}

package profeDoc;

import java.util.List;

public class Funcion extends Identificador{

    List<TipoDato> argumentos;

    public Funcion(String nombre, TipoDato tipoDato, List<TipoDato> argumentos) {
        super(nombre, tipoDato); // invoca al constructor de la clase identificadores
        this.argumentos = argumentos;
    }

    public List<TipoDato> getArgumentos() {
        return argumentos;
    }

    @Override
    public String toString() {
        return super.toString() + ", Argumentos: " + argumentos;
    }
}

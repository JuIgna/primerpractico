package profeDoc;

import java.util.HashMap;
import java.util.Map;

public class Contexto {
    // map que asocia nombres de identificadores a objetos de la clase Identificador
    Map<String, Identificador> identificadores;


    // Constructor para inicializar el map de identificadores
    public Contexto() {
        this.identificadores = new HashMap<>();
    }

    // añadir un identificador al contexto
    public void addIdentificador(Identificador identificador) {
        identificadores.put(identificador.nombre, identificador);
    }

    //funcion para buscar el identificador por nombre y tipo de dato o solo por nombre (else if)
    public Identificador buscarIdentificador(Identificador id) {
        for (Identificador identificador : identificadores.values()) {
            if (identificador.getNombre().equals(id.getNombre()) && identificador.getTipoDato() == id.getTipoDato()) {
                return identificador;
            }else if(identificador.getNombre().equals(id.getNombre()) && id.getTipoDato() == null){
                return identificador;
            }

        }
        return null;
    }

    // metodo get para obtener le map
    public Map<String, Identificador> getIdentificadores() {
        return identificadores;
    }

}

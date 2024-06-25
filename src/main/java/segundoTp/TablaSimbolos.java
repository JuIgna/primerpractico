package segundoTp;

import java.util.ArrayList;
import java.util.List;

public class TablaSimbolos {
    private List<Contexto> contextos;

    public TablaSimbolos() {
        this.contextos = new ArrayList<>();
        this.contextos.add(new Contexto());
    }

    public void addContexto() {
        contextos.add(new Contexto());
    }

    public void delContexto() {
        if (contextos.size() > 1) {
            contextos.remove(contextos.size() - 1);
        }
    }


    public Identificador buscarIdentificador(Identificador id) {
        for (int i = contextos.size() - 1; i >= 0; i--) {
            Identificador identificador = contextos.get(i).buscarIdentificador(id);
            if (identificador != null) {
                return identificador;
            }
        }
        return null;
    }

    public TipoDato buscarTipoIdentificador(String nombre) {
        Identificador id = new Identificador(nombre, null);

        for (int i = contextos.size() - 1; i >= 0; i--) {
            Identificador encontrado = contextos.get(i).buscarIdentificador(id);
            if (encontrado != null) {
                return encontrado.getTipoDato();
            }
        }

        return null;
    }

    public void identificadorInicializado(Identificador id) {
        for (int i = contextos.size() - 1; i >= 0; i--) {
            Identificador identificador = contextos.get(i).buscarIdentificador(id);
            if (identificador != null) {
                identificador.setInicializada(true);
                return;
            }
        }
    }

    public List<Contexto> getContextos() {
        return contextos;
    }


    public void identificadorUtilizado(Identificador id) {
        for (int i = contextos.size() - 1; i >= 0; i--) {
            Identificador identificador = contextos.get(i).buscarIdentificador(id);
            if (identificador != null) {
                identificador.setUtilizada(true);
                return;
            }
        }
    }


    public Identificador buscarIdentificadorLocal(Identificador id) {
        return contextos.get(contextos.size() - 1).buscarIdentificador(id);
    }

    public void addIdentificador(Identificador identificador) {
        contextos.get(contextos.size() - 1).addIdentificador(identificador);
    }

    
}

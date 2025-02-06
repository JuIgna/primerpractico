package primerpractico;


import org.antlr.v4.runtime.*;

import java.io.PrintWriter;

class ManejadorErrores extends BaseErrorListener {
    private PrintWriter escritorErrores;
    private int errores = 0;

    ManejadorErrores(PrintWriter escritorErrores) {
        this.escritorErrores = escritorErrores;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
            Object simboloOfensivo,
            int linea,
            int posicionEnLinea,
            String mensaje,
            RecognitionException e) {
        String tipoError = "Error sintáctico";
        if (mensaje.contains("missing ';'")) {
            tipoError = "Error sintáctico: Falta de un punto y coma";
        } else if (mensaje.contains("missing '('")) {
            tipoError = "Error sintáctico: Falta de apertura de paréntesis";
        }else if (mensaje.contains("missing ')'")) {
            tipoError = "Error sintáctico: Falta de cierre de paréntesis";
        } else if (mensaje.contains("mismatched input")) {
            tipoError = "Error sintáctico: Formato incorrecto en lista de declaración de variables";
        } else if (mensaje.contains("missing '}'")) {
            tipoError = "Error: Falta '}' para cerrar un bloque en la línea " + linea;
        } else if (mensaje.contains("missing '{'")) {
            tipoError = "Error: Falta '{' para abrir un bloque en la línea " + linea;
        }
        escritorErrores.println(tipoError + " en línea " + linea + ":" + posicionEnLinea + " - " + mensaje);
        errores++;
    }

    public boolean verificarErrores(){
        return errores == 0;
    }
}
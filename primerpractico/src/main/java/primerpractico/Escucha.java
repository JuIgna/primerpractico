package primerpractico;

import profeDoc.Contexto;
import profeDoc.Identificador;
import profeDoc.TablaSimbolos;
import profeDoc.TipoDato;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class Escucha extends compiladoresBaseListener {
    private TablaSimbolos tablaSimbolos = new TablaSimbolos();
    private PrintWriter escritorErrores;

    Escucha(PrintWriter escritorErrores) {
        this.escritorErrores = escritorErrores;
    }

    @Override
    public void enterPrograma(compiladoresParser.ProgramaContext ctx) {
        tablaSimbolos.addContexto();
    }

    @Override
    public void exitParametro(compiladoresParser.ParametroContext ctx) {
        String tipo = ctx.tipo().getText();

        TipoDato tipoDato;
        try {
            tipoDato = TipoDato.valueOf(tipo.toUpperCase());
        } catch (IllegalArgumentException e) {
            return;
        }

        Identificador identificador = new Identificador(ctx.ID().getText(), tipoDato);

        if (tablaSimbolos.buscarIdentificador(identificador) == null) {
            tablaSimbolos.addIdentificador(identificador);
            tablaSimbolos.identificadorInicializado(identificador);
        } else {
            escritorErrores.println("Error semántico: Identificador '" + identificador.getNombre() + "' ya ha sido declarado. En linea: " + ctx.ID().getSymbol().getLine());
        }
    }

    @Override
    public void exitDeclaracion(compiladoresParser.DeclaracionContext ctx) {
        String tipo = ctx.tipo().getText();
        TipoDato tipoDato;
        try {
            tipoDato = TipoDato.valueOf(tipo.toUpperCase());
        } catch (IllegalArgumentException e) {
            return;
        }

        String nombre = ctx.ID().getText();
        Identificador identificador = new Identificador(nombre, tipoDato);

        if (tablaSimbolos.buscarIdentificador(identificador) == null) {
            tablaSimbolos.addIdentificador(identificador);
            System.out.println("Identificador:  " + identificador.getNombre());
        } else {
            escritorErrores.println("Error semántico: Identificador '" + nombre + "' ya ha sido declarado. En linea: " + ctx.ID().getSymbol().getLine());
        }

        if (ctx.expresion() != null) {
            tablaSimbolos.identificadorInicializado(identificador);
        } else {
            escritorErrores.println("Error semántico: Identificador '" + nombre + "' no ha sido inicializado. En linea: " + ctx.ID().getSymbol().getLine());
        }
    }

    @Override
    public void exitAsignacion(compiladoresParser.AsignacionContext ctx) {
        String nombre = ctx.ID().getText();
        TipoDato tipoDato = tablaSimbolos.buscarTipoIdentificador(nombre);

        if (tipoDato != null) {
            Identificador identificador = new Identificador(nombre, tipoDato);
            tablaSimbolos.identificadorInicializado(identificador);
        } else {
            escritorErrores.println("Error semántico: Identificador '" + nombre + "' no ha sido declarado. En linea: " + ctx.ID().getSymbol().getLine());
        }
    }

    @Override
    public void exitLlamadaFuncion(compiladoresParser.LlamadaFuncionContext ctx) {
        String nombre = ctx.ID().getText();
        boolean encontrado = false;

        System.out.println(nombre);

        for (TipoDato tipoDato : TipoDato.values()) {
            Identificador identificador = new Identificador(nombre, tipoDato);
            Identificador idEncontrado = tablaSimbolos.buscarIdentificador(identificador);
            if (idEncontrado != null && idEncontrado.isInicializada()) {
                encontrado = true;
                tablaSimbolos.identificadorUtilizado(identificador);
                break;
            }
        }
        if (!encontrado) {
            escritorErrores.println("Error semántico: Identificador '" + nombre + "'. La función no está creada. En linea: " + ctx.ID().getSymbol().getLine());
        }
    }

    @Override
    public void exitDeclaracionFuncion(compiladoresParser.DeclaracionFuncionContext ctx) {
        String nombre = ctx.ID().getText();
        String tipo = ctx.tipo().getText();

        TipoDato tipoDato;
        try {
            tipoDato = TipoDato.valueOf(tipo.toUpperCase());
        } catch (IllegalArgumentException e) {
            return;
        }

        Identificador identificador = new Identificador(nombre, tipoDato);

        if (tablaSimbolos.buscarIdentificador(identificador) == null) {
            tablaSimbolos.addIdentificador(identificador);
            tablaSimbolos.identificadorInicializado(identificador); // Marcar la función como inicializada
        } else {
            escritorErrores.println("Error semántico: Identificador '" + nombre + "' ya ha sido declarado. En linea: " + ctx.ID().getSymbol().getLine());
        }
    }

    @Override
    public void exitExpresion(compiladoresParser.ExpresionContext ctx) {
        if (ctx.ID() != null) {
            String id = ctx.ID().getText();
            TipoDato tipoDato = tablaSimbolos.buscarTipoIdentificador(id);
            Identificador identificador = tablaSimbolos.buscarIdentificador(new Identificador(id, tipoDato));
            if (identificador == null) {
                escritorErrores.println("Error semántico: Identificador '" + id + "' se utiliza sin haberse creado . En linea: " + ctx.ID().getSymbol().getLine());
            } else if (!identificador.isInicializada()){
                escritorErrores.println("Error semántico: Identificador '" + id + "' se utiliza sin inicializarse. En linea: " + ctx.ID().getSymbol().getLine());
                tablaSimbolos.identificadorUtilizado(identificador);
            } else {
                tablaSimbolos.identificadorUtilizado(identificador);
            }
        }
    }

    public void exitPrograma(compiladoresParser.ProgramaContext ctx) {
        Set<Identificador> identificadores = new HashSet<>();
        for (Contexto contexto : tablaSimbolos.getContextos()) {
            identificadores.addAll(contexto.getIdentificadores().values());
        }

        // Verificar si han sido utilizados
        for (Identificador identificador : identificadores) {
            System.out.println(identificador.getNombre());
            if (!identificador.isUtilizada()) {
                escritorErrores.println("Error semántico: Identificador " + identificador.getNombre() + " de tipo " + identificador.getTipoDato() + " ha sido declarado pero no utilizado.");
            }
        }

        tablaSimbolos.delContexto();
    }
}

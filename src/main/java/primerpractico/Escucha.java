package primerpractico;

import segundoTp.Contexto;
import segundoTp.Identificador;
import segundoTp.TablaSimbolos;
import segundoTp.TipoDato;

import javax.naming.Context;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Escucha extends compiladoresBaseListener {
    private TablaSimbolos tablaSimbolos = new TablaSimbolos();
    private PrintWriter escritorErrores;
    private int errores = 0;
    private int warnings = 0;

    Escucha(PrintWriter escritorErrores) {
        this.escritorErrores = escritorErrores;
    }

    @Override
    public void enterPrograma(compiladoresParser.ProgramaContext ctx) {
        tablaSimbolos.addContexto();
    }

    private int BalanceLlaves = 0;
    private int BalanceParentesis = 0;



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
            errores++;
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
            errores++;
            escritorErrores.println("Error semántico: Identificador '" + nombre + "' ya ha sido declarado. En linea: " + ctx.ID().getSymbol().getLine());
        }

        if (ctx.expresion() != null) {
            tablaSimbolos.identificadorInicializado(identificador);
        } else {
            errores++;
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
            errores++;
            escritorErrores.println("Error semántico: Identificador '" + nombre + "' no ha sido declarado. En linea: " + ctx.ID().getSymbol().getLine());
        }
    }

    @Override
    public void exitLlamadaFuncion(compiladoresParser.LlamadaFuncionContext ctx) {
        String nombre = ctx.ID().getText();
        boolean encontrado = false;

        for (Contexto contexto : contextoAuxiliar) {
            Identificador idEncontrado = contexto.buscarIdentificador(new Identificador(nombre, null));
            if (idEncontrado != null) {
                encontrado = true;
                tablaSimbolos.identificadorUtilizado(idEncontrado);
                break;
            }
        }

        if (!encontrado) {
            errores++;
            escritorErrores.println("Error semántico: Identificador '" + nombre + "'. La función no está creada. En línea: " + ctx.ID().getSymbol().getLine());
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
            errores++;
            escritorErrores.println("Error semántico: Identificador '" + nombre + "' ya ha sido declarado. En linea: " + ctx.ID().getSymbol().getLine());
        }
    }

    @Override
    public void exitExpresion(compiladoresParser.ExpresionContext ctx) {
        if (ctx.expresionLogica() != null) {
            TipoDato tipo = obtenerTipoExpresionLogica(ctx.expresionLogica());
            if (tipo != TipoDato.BOOL) {
                errores++;
                escritorErrores.println("Error semántico: La expresión lógica debe ser de tipo booleano. En línea: " + ctx.getStart().getLine());
            }
        }
    }

    public TipoDato obtenerTipoExpresionLogica(compiladoresParser.ExpresionLogicaContext ctx) {
        TipoDato tipo = obtenerTipoExpresionComparacion(ctx.expresionComparacion(0));

        for (int i = 1; i < ctx.expresionComparacion().size(); i++) {
            TipoDato tipoDer = obtenerTipoExpresionComparacion(ctx.expresionComparacion(i));

            if (tipo != TipoDato.BOOL || tipoDer != TipoDato.BOOL) {
                return null; // Error: solo se permiten booleanos en operaciones lógicas
            }
        }
        return TipoDato.BOOL;
    }

    public TipoDato obtenerTipoExpresionComparacion(compiladoresParser.ExpresionComparacionContext ctx) {
        TipoDato tipoIzq = obtenerTipoExpresionAritmetica(ctx.expresionAritmetica(0));

        if (ctx.COMP() != null) {
            TipoDato tipoDer = obtenerTipoExpresionAritmetica(ctx.expresionAritmetica(1));

            if (tipoIzq == null || tipoDer == null) {
                return null; // Error de tipo
            }
            if ((tipoIzq == TipoDato.INT || tipoIzq == TipoDato.DOUBLE) &&
                    (tipoDer == TipoDato.INT || tipoDer == TipoDato.DOUBLE)) {
                return TipoDato.BOOL; // Comparación válida
            }
            return null; // Error: tipos incompatibles
        }
        return tipoIzq;
    }

    public TipoDato obtenerTipoExpresionAritmetica(compiladoresParser.ExpresionAritmeticaContext ctx) {
        TipoDato tipo = obtenerTipoTermino(ctx.termino(0));

        for (int i = 1; i < ctx.termino().size(); i++) {
            TipoDato tipoDer = obtenerTipoTermino(ctx.termino(i));

            if (tipo == null || tipoDer == null) {
                return null; // Error de tipo
            }
            if (tipo == TipoDato.INT && tipoDer == TipoDato.INT) {
                tipo = TipoDato.INT; // Sigue siendo entero
            } else if ((tipo == TipoDato.INT || tipo == TipoDato.DOUBLE) &&
                    (tipoDer == TipoDato.INT || tipoDer == TipoDato.DOUBLE)) {
                tipo = TipoDato.DOUBLE; // Promoción a double
            } else {
                return null; // Error de tipo
            }
        }
        return tipo;
    }

    public TipoDato obtenerTipoTermino(compiladoresParser.TerminoContext ctx) {
        TipoDato tipo = obtenerTipoFactor(ctx.factor(0));

        for (int i = 1; i < ctx.factor().size(); i++) {
            TipoDato tipoDer = obtenerTipoFactor(ctx.factor(i));

            if (tipo == null || tipoDer == null) {
                return null; // Error de tipo
            }
            if (tipo == TipoDato.INT && tipoDer == TipoDato.INT) {
                tipo = TipoDato.INT; // Sigue siendo entero
            } else if ((tipo == TipoDato.INT || tipo == TipoDato.DOUBLE) &&
                    (tipoDer == TipoDato.INT || tipoDer == TipoDato.DOUBLE)) {
                tipo = TipoDato.DOUBLE; // Promoción a double
            } else {
                return null; // Error de tipo
            }
        }
        return tipo;
    }

    public TipoDato obtenerTipoFactor(compiladoresParser.FactorContext ctx) {
        if (ctx.ID() != null) {
            return tablaSimbolos.buscarTipoIdentificador(ctx.ID().getText());
        } else if (ctx.NUMERO() != null) {
            return TipoDato.INT;
        } else if (ctx.DOUBLE_LITERAL() != null) {
            return TipoDato.DOUBLE;
        } else if (ctx.expresion() != null) {
            return obtenerTipoExpresionLogica(ctx.expresion().expresionLogica());
        }
        return null;
    }




    public void exitPrograma(compiladoresParser.ProgramaContext ctx) {
        Set<Identificador> identificadores = new HashSet<>();
        for (Contexto contexto : tablaSimbolos.getContextos()) {
            identificadores.addAll(contexto.getIdentificadores().values());
        }

        // Verificar si han sido utilizados
        for (Identificador identificador : identificadores) {
            if (!identificador.isUtilizada()) {
                warnings++;
                escritorErrores.println("Advertencia: Identificador " + identificador.getNombre() + " de tipo " + identificador.getTipoDato() + " ha sido declarado pero no utilizado.");
            }
        }
        Contexto contexto = tablaSimbolos.getContextoActual();

        contextoAuxiliar.add(contexto);

        imprimirTablaSimbolos("output/tabla_simbolos.txt");

        tablaSimbolos.delContexto();

        if(BalanceLlaves != 0){
            System.err.println("❌ Error: Las llaves no estan balanceadas");
        }
        if(BalanceParentesis != 0){
            System.err.println("❌ Error: Los parentesis no estan balanceados");
        }
    }

    @Override
    public void enterBloque(compiladoresParser.BloqueContext ctx) {
        tablaSimbolos.addContexto();
    }

    private List<Contexto> contextoAuxiliar = new ArrayList<Contexto>();

    @Override
    public void exitBloque(compiladoresParser.BloqueContext ctx) {
        Contexto contexto = tablaSimbolos.getContextoActual();

        contextoAuxiliar.add(contexto);
        tablaSimbolos.delContexto();
    }

    @Override public void enterCuerpoFuncion(compiladoresParser.CuerpoFuncionContext ctx) {
        tablaSimbolos.addContexto();

    }

    @Override public void exitCuerpoFuncion(compiladoresParser.CuerpoFuncionContext ctx) {
        Contexto contexto = tablaSimbolos.getContextoActual();

        contextoAuxiliar.add(contexto);
        String funcionNombre = ctx.ID().getText();
        TipoDato tipo = TipoDato.valueOf(ctx.tipo().getText().toUpperCase());
        Identificador identificador = new Identificador(funcionNombre, tipo);
        tablaSimbolos.addIdentificador(identificador);
        tablaSimbolos.delContexto();
    }

    @Override public void enterInstruccion(compiladoresParser.InstruccionContext ctx) {
        System.out.println("Instruccion: " + ctx.getText());
    }

    @Override public void exitInstruccion(compiladoresParser.InstruccionContext ctx) {
        System.out.println("Instruccion exit: " + ctx.getText());
    }



    public void imprimirTablaSimbolos(String archivoSalida) {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(archivoSalida))) {
            escritor.println("Tabla de Símbolos:");
            escritor.println("------------------");

            int cont = 0;

            for (int i = contextoAuxiliar.size() - 1; i >= 0; i--) {
                Contexto contexto = contextoAuxiliar.get(i);
                escritor.printf("Contexto: " + cont++ + "\n");
                escritor.println("------------------");

                // Obtener los identificadores dentro de este contexto
                Map<String, Identificador> identificadores = contexto.getIdentificadores();
                for (Identificador identificador : identificadores.values()) {
                    escritor.printf("Nombre: %s, Tipo: %s\n",
                            identificador.getNombre(),
                            identificador.getTipoDato() != null ? identificador.getTipoDato() : "Desconocido");
                }
                escritor.println(); // Espacio entre contextos
            }
        } catch (IOException e) {
            System.err.println("Error al escribir la tabla de símbolos: " + e.getMessage());
        }
    }

    @Override public void exitForLoop(compiladoresParser.ForLoopContext ctx) {
        // ⚠️ Verificar existencia del bloque
        if (ctx.bloque() == null) {
            System.err.println("❌ Error: Falta el bloque de código para el FOR.");
        }

        // ✅ Verificación de llaves
        String bloqueCodigo = ctx.bloque().getText();
        if (!bloqueCodigo.startsWith("{")) {
            System.err.println("❌ Error: El bloque del FOR debe estar encerrado entre '{' y '}'");
            BalanceLlaves++;
        } else if(!bloqueCodigo.endsWith("}")){
            System.err.println("❌ Error: El bloque del FOR debe estar encerrado entre '{' y '}'");
            BalanceLlaves--;
        }

        // ✅ Verificación de paréntesis
        if (ctx.PA() == null ||
                ctx.PA().getText().contains("missing")) {
            System.err.println("❌ Error: Paréntesis desbalanceados o faltantes en el bloque FOR.");
            BalanceParentesis++;
        } else if(ctx.PC() == null || ctx.PC().getText().contains("missing")){
            System.err.println("❌ Error: Paréntesis desbalanceados o faltantes en el bloque FOR.");
            BalanceParentesis--;
        }
    }

    @Override public void exitWhileLoop(compiladoresParser.WhileLoopContext ctx) {
        String bloqueCodigo = ctx.bloque().getText();
        System.out.println("Codigo del bloque WHILE: " + bloqueCodigo);
        if (!bloqueCodigo.startsWith("{")) {
            System.err.println("❌ Error: El bloque del WHILE debe estar encerrado entre '{' y '}'.");
            BalanceLlaves++;
        } else if(!bloqueCodigo.endsWith("}")){
            System.err.println("❌ Error: El bloque del WHILE debe estar encerrado entre '{' y '}'.");
            BalanceLlaves--;
        }

        System.out.println("Expresión: " + (ctx.PA() != null ? ctx.PA().getText() : "❌ Falta '('"));

        // ✅ Verificación de paréntesis
        if (ctx.PA() == null ||
                ctx.PA().getText().contains("missing")) {
            System.err.println("❌ Error: Paréntesis desbalanceados o faltantes en el bloque WHILE.");
            BalanceParentesis++;
        } else if(ctx.PC() == null || ctx.PC().getText().contains("missing")){
            System.err.println("❌ Error: Paréntesis desbalanceados o faltantes en el bloque WHILE.");
            BalanceParentesis--;
        }
    }

    @Override public void exitIfElse(compiladoresParser.IfElseContext ctx) {
        // 🔹 Verificar si el bloque del IF existe
        if (ctx.bloque(0) == null) {
            System.err.println("❌ Error: Falta el bloque de código para el IF.");
        }

        // 🔹 Obtener el texto del bloque para verificar balanceo
        String bloqueIf = ctx.bloque(0).getText();
        System.out.println("Código del bloque IF: " + bloqueIf);

        // 🔹 Verificar si el bloque empieza con '{' y termina con '}'
        if (!bloqueIf.startsWith("{")) {
            System.err.println("❌ Error: El bloque del IF debe estar encerrado entre '{' y '}'.");
            BalanceLlaves++;
        } else if(!bloqueIf.endsWith("}")){
            System.err.println("❌ Error: El bloque del IF debe estar encerrado entre '{' y '}'.");
            BalanceLlaves--;
        }

        if (ctx.PA() == null || ctx.PC() == null ||
                ctx.PA().getText().contains("missing") || ctx.PC().getText().contains("missing")) {

            System.err.println("❌ Error: Paréntesis desbalanceados o faltantes en el bloque IF.");
        } else {
            System.out.println("Paréntesis correctos: " + ctx.PA().getText() + " ... " + ctx.PC().getText());
        }
        ;

        if (ctx.ELSE() != null) {
            if (ctx.bloque(1) == null) {
                System.err.println("❌ Error: Falta el bloque de código para el ELSE.");
            } else {
                String bloqueElse = ctx.bloque(1).getText();
                System.out.println("Código del bloque ELSE: " + bloqueElse);

                if (!bloqueElse.startsWith("{") || !bloqueElse.endsWith("}")) {
                    System.err.println("❌ Error: El bloque del ELSE debe estar encerrado entre '{' y '}'.");
                }
            }
        }
    }






    public boolean verificarErrores() {
        return errores == 0;
    }

    public boolean verificarWarnings() {
        return warnings == 0;
    }
}

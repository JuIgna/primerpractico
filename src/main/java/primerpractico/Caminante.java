package primerpractico;

import segundoTp.*;

import java.util.*;

public class Caminante extends compiladoresBaseVisitor<String> {
    private final StringBuilder codigoTresDirecciones = new StringBuilder();
    private int tempCounter = 0;
    private int labelCounter = 0; // Contador de etiquetas

    private String nuevaTemporal() {
        return "t" + (tempCounter++);
    }

    private String nuevaEtiqueta() {
        return "L" + labelCounter++;
    }

    public String getCodigoGenerado() {
        return codigoTresDirecciones.toString();
    }

    @Override
    public String visitDeclaracion(compiladoresParser.DeclaracionContext ctx) {
        String tipo = ctx.tipo().getText();
        String nombre = ctx.ID().getText();
        String expresion = ctx.expresion() != null ? visit(ctx.expresion()) : "0";

        if (ctx.expresion() != null) {
            String temp = visit(ctx.expresion());
            if (temp != null) {
                codigoTresDirecciones.append(nombre).append(" = ").append(temp).append(";\n");
            }
        } else {
            codigoTresDirecciones.append(nombre).append(" = 0;\n");
        }

        return tipo + " " + nombre + " = " + expresion + ";";
    }

    @Override
    public String visitAsignacion(compiladoresParser.AsignacionContext ctx) {
        String nombre = ctx.ID().getText();
        String temp = visit(ctx.expresion());
        codigoTresDirecciones.append(nombre).append(" = ").append(temp).append(";");
        codigoTresDirecciones.append("\n");
        return nombre + " = " + temp + ";";
    }

    // Declaración de un mapa para almacenar las expresiones ya evaluadas y su resultado.
    Map<String, String> cacheExpresiones = new HashMap<>();

    // Método que visita una expresión general.
    @Override
    public String visitExpresion(compiladoresParser.ExpresionContext ctx) {
        // Obtenemos el texto de la expresión
        String expresionTexto = ctx.getText();

        // Si la expresión ya fue evaluada previamente, retornamos el valor almacenado en el cache.
        if (cacheExpresiones.containsKey(expresionTexto)) {
            return cacheExpresiones.get(expresionTexto);
        }

        // Si no estaba en el cache, procesamos la expresión lógica asociada.
        String resultado = visit(ctx.expresionLogica());

        // Almacenamos el resultado de la expresión en el cache.
        cacheExpresiones.put(expresionTexto, resultado);

        return resultado;
    }

    // Método que visita una expresión lógica.
    @Override
    public String visitExpresionLogica(compiladoresParser.ExpresionLogicaContext ctx) {
        // Obtenemos el texto de la expresión lógica.
        String expresionTexto = ctx.getText();

        // Si la expresión lógica ya fue evaluada, retornamos el resultado del cache.
        if (cacheExpresiones.containsKey(expresionTexto)) {
            return cacheExpresiones.get(expresionTexto);
        }

        // Evaluamos la primera expresión de comparación.
        String temp = visit(ctx.expresionComparacion(0));

        // Iteramos sobre las expresiones de comparación adicionales (si existen).
        for (int i = 1; i < ctx.expresionComparacion().size(); i++) {
            String temp2 = visit(ctx.expresionComparacion(i)); // Evaluamos la siguiente expresión de comparación.

            // Obtenemos el operador lógico (por ejemplo, AND, OR).
            String operador = ctx.op_logicas(i - 1).getText();

            // Generamos una nueva variable temporal para almacenar el resultado de la operación lógica.
            String nuevaTemp = nuevaTemporal();

            // Agregamos el código de tres direcciones (representación intermedia).
            codigoTresDirecciones.append(nuevaTemp).append(" = ").append(temp).append(" ").append(operador).append(" ").append(temp2).append(";\n");

            // Actualizamos el valor de temp para las siguientes iteraciones.
            temp = nuevaTemp;
        }

        // Almacenamos el resultado de la expresión lógica en el cache.
        cacheExpresiones.put(expresionTexto, temp);

        return temp;
    }

    // Método que visita una expresión de comparación.
    @Override
    public String visitExpresionComparacion(compiladoresParser.ExpresionComparacionContext ctx) {
        // Evaluamos la primera expresión aritmética.
        String temp = visit(ctx.expresionAritmetica(0));

        // Si existe un operador de comparación (como ==, !=, <, >), evaluamos la siguiente expresión.
        if (ctx.COMP() != null) {
            String temp2 = visit(ctx.expresionAritmetica(1)); // Evaluamos la segunda expresión aritmética.

            // Obtenemos el operador de comparación.
            String operador = ctx.COMP().getText();

            // Generamos una nueva variable temporal para almacenar el resultado de la comparación.
            String nuevaTemp = nuevaTemporal();

            // Agregamos el código de tres direcciones correspondiente.
            codigoTresDirecciones.append(nuevaTemp).append(" = ").append(temp).append(" ").append(operador).append(" ").append(temp2).append(";\n");

            // Actualizamos el valor de temp con el resultado de la comparación.
            temp = nuevaTemp;
        }

        return temp;
    }

    // Método que visita una expresión aritmética.
    @Override
    public String visitExpresionAritmetica(compiladoresParser.ExpresionAritmeticaContext ctx) {
        // Evaluamos el primer término de la expresión aritmética.
        String temp = visit(ctx.termino(0));

        // Iteramos sobre los términos restantes, si los hay.
        for (int i = 1; i < ctx.termino().size(); i++) {
            String temp2 = visit(ctx.termino(i)); // Evaluamos el siguiente término.

            // Obtenemos el operador (Suma o Resta).
            String operador = ctx.getChild(2 * i - 1).getText();

            // Generamos una nueva variable temporal para almacenar el resultado de la operación aritmética.
            String nuevaTemp = nuevaTemporal();

            // Agregamos el código de tres direcciones correspondiente.
            codigoTresDirecciones.append(nuevaTemp).append(" = ").append(temp).append(" ").append(operador).append(" ").append(temp2).append(";\n");

            // Actualizamos el valor de temp para las siguientes iteraciones.
            temp = nuevaTemp;
        }

        return temp;
    }

    // Método que visita un término en la expresión aritmética (multiplicación, división o módulo).
    @Override
    public String visitTermino(compiladoresParser.TerminoContext ctx) {
        // Evaluamos el primer factor.
        String temp = visit(ctx.factor(0));

        // Iteramos sobre los factores restantes.
        for (int i = 1; i < ctx.factor().size(); i++) {
            String temp2 = visit(ctx.factor(i)); // Evaluamos el siguiente factor.

            // Obtenemos el operador (Multiplicación, División o Módulo).
            String operador = ctx.getChild(2 * i - 1).getText();

            // Generamos una nueva variable temporal para almacenar el resultado de la operación.
            String nuevaTemp = nuevaTemporal();

            // Agregamos el código de tres direcciones correspondiente.
            codigoTresDirecciones.append(nuevaTemp).append(" = ").append(temp).append(" ").append(operador).append(" ").append(temp2).append(";\n");

            // Actualizamos el valor de temp para las siguientes iteraciones.
            temp = nuevaTemp;
        }

        return temp;
    }

    @Override
    public String visitFactor(compiladoresParser.FactorContext ctx) {
        if (ctx.expresion() != null) {
            return visit(ctx.expresion());
        } else if (ctx.ID() != null) {
            return ctx.ID().getText();
        } else if (ctx.STRING() != null) {
            return ctx.STRING().getText();
        } else if (ctx.llamadaPrints() != null) {
            return visit(ctx.llamadaPrints());
        } else if (ctx.llamadaFuncion() != null) {
            return visit(ctx.llamadaFuncion());
        } else if (ctx.incrementoDecremento() != null) {
            return visit(ctx.incrementoDecremento());
        } else if (ctx.NUMERO() != null) {
            return (ctx.RESTA() != null ? "-" : "") + ctx.NUMERO().getText();
        } else if (ctx.DOUBLE_LITERAL() != null) {
            return ctx.DOUBLE_LITERAL().getText();
        } else if (ctx.booleano() != null) {
            return ctx.booleano().getText();
        }

        return null;
    }


    @Override
    public String visitEstructuraControl(compiladoresParser.EstructuraControlContext ctx) {
        if (ctx.ifElse() != null) {
            return visit(ctx.ifElse());
        } else if (ctx.whileLoop() != null) {
            return visit(ctx.whileLoop());
        } else if (ctx.forLoop() != null) {
            return visit(ctx.forLoop());
        }
        return null;
    }

    @Override
    public String visitIfElse(compiladoresParser.IfElseContext ctx) {
        String condicion = visit(ctx.expresion());
        String etiquetaVerdadero = nuevaEtiqueta();
        String etiquetaFalso = nuevaEtiqueta();
        String etiquetaFin = nuevaEtiqueta();

        // 🔹 Generar código tres direcciones para el IF
        codigoTresDirecciones.append("if ").append(condicion).append(" goto ").append(etiquetaVerdadero).append(";\n");
        visit(ctx.bloque(0));  // Procesar el bloque IF
        codigoTresDirecciones.append("goto ").append(etiquetaFalso).append(";\n");

        // 🔹 Procesar el ELSE, si existe
        codigoTresDirecciones.append(etiquetaVerdadero).append(":\n");

        // Salto al final
        codigoTresDirecciones.append("goto ").append(etiquetaFin).append("\n");;

        // Bloque falso
        codigoTresDirecciones.append(etiquetaFalso).append(":\n");
        if (ctx.ELSE() != null) {
            visit(ctx.bloque(1));  // Ejecutar el bloque ELSE
        }

        // 🔹 Fin del if-else
        codigoTresDirecciones.append(etiquetaFin).append(":\n");

        return null;
    }




    @Override
    public String visitWhileLoop(compiladoresParser.WhileLoopContext ctx) {
        String etiquetaInicio = nuevaEtiqueta();
        String etiquetaCondicion = nuevaEtiqueta();
        String etiquetaFin = nuevaEtiqueta();

        String condicion = visit(ctx.expresion());

        // Generación de código en tres direcciones
        codigoTresDirecciones.append(etiquetaInicio).append(":\n");
        codigoTresDirecciones.append("if ").append(condicion).append(" goto ").append(etiquetaCondicion).append(";\n");
        codigoTresDirecciones.append("goto ").append(etiquetaFin).append(";\n");
        codigoTresDirecciones.append(etiquetaCondicion).append(":\n");
        visit(ctx.bloque());
        codigoTresDirecciones.append("goto ").append(etiquetaInicio).append(";\n");
        codigoTresDirecciones.append(etiquetaFin).append(":\n");

        return null;
    }

    @Override
    public String visitForLoop(compiladoresParser.ForLoopContext ctx) {
        String etiquetaInicio = nuevaEtiqueta();
        String etiquetaCondicion = nuevaEtiqueta();
        String etiquetaFin = nuevaEtiqueta();

        // ✅ Inicialización (Verifica si existe antes de visitar)
        StringBuilder inicializacion = new StringBuilder();

        String resultadoInicializacion = visit(ctx.inicializacion());
        inicializacion.append(resultadoInicializacion).append("\n");


        // ✅ Condición (si no hay, se asume "1")
        String condicion = visit(ctx.condicion());

        // ✅ Actualización (Verifica si existe antes de visitar)
        StringBuilder actualizacion = new StringBuilder();
        actualizacion.append(visit(ctx.actualizacion())).append("\n");


        codigoTresDirecciones.append(etiquetaInicio).append(":\n");
        codigoTresDirecciones.append("if ").append(condicion).append(" goto ").append(etiquetaCondicion).append(";\n");
        codigoTresDirecciones.append("goto ").append(etiquetaFin).append(":\n");
        codigoTresDirecciones.append(etiquetaCondicion).append(":\n");
        visit(ctx.bloque());
        codigoTresDirecciones.append("goto ").append(etiquetaInicio).append(";\n");
        codigoTresDirecciones.append(etiquetaFin).append(":\n");

        return null;
    }

    @Override
    public String visitInicializacion(compiladoresParser.InicializacionContext ctx) {
        // Verifica si es una declaración o una asignación
        if (ctx.declaracion() != null) {
            // Es una declaración
            return visit(ctx.declaracion());
        } else if (ctx.asignacion() != null) {
            // Es una asignación
            return visit(ctx.asignacion());
        } else {
            // Caso inesperado
            return null;
        }
    }

    @Override
    public String visitActualizacion(compiladoresParser.ActualizacionContext ctx) {
        StringBuilder actualizaciones = new StringBuilder();

        // Iterar sobre los elementos separados por coma
        for (int i = 0; i < ctx.asignacion().size(); i++) {
            actualizaciones.append(visit(ctx.asignacion(i)));  // Visitar cada asignación
            if (i < ctx.asignacion().size() - 1) {
                actualizaciones.append(", ");  // Agregar coma si no es el último
            }
        }

        // Ahora, procesamos los incrementos y decrementos
        for (int i = 0; i < ctx.incrementoDecremento().size(); i++) {
            actualizaciones.append(visit(ctx.incrementoDecremento(i)));  // Visitar cada incremento/decremento
            if (i < ctx.incrementoDecremento().size() - 1) {
                actualizaciones.append(", ");  // Agregar coma si no es el último
            }
        }

        return actualizaciones.toString();  // Retornar las actualizaciones generadas
    }


    @Override
    public String visitIncrementoDecremento(compiladoresParser.IncrementoDecrementoContext ctx) {
        String id = ctx.ID().getText();  // Obtener el identificador
        String operador = ctx.INCREMENTO() != null ? "++" : "--";  // Ver si es incremento o decremento
        return id + operador;  // Retornar el incremento o decremento como código
    }


    @Override
    public String visitBloque(compiladoresParser.BloqueContext ctx) {
        StringBuilder bloqueCodigo = new StringBuilder();

        // Verificar si hay instrucciones dentro del bloque
        if (ctx.instrucciones() != null) {
            for (compiladoresParser.InstruccionContext instruccion : ctx.instrucciones().instruccion()) {
                bloqueCodigo.append(visit(instruccion)).append("\n");  // Visitar cada instrucción
            }
        }

        return bloqueCodigo.toString();  // Retornar el código generado para el bloque
    }

    @Override
    public String visitCondicion(compiladoresParser.CondicionContext ctx) {
        if (ctx.expresion() != null) {
            // Si existe una expresión en la condición, la procesamos
            return visit(ctx.expresion());  // Visitamos la expresión de la condición
        } else {
            // Si no hay expresión, retornamos un valor predeterminado (por ejemplo, "1")
            return "1";  // Condición siempre verdadera
        }
    }

    @Override
    public String visitReturn(compiladoresParser.ReturnContext ctx) {;
        // Instrucción de retorno
        codigoTresDirecciones.append("return");
        // Verificar si hay una expresión
        if (ctx.expresion() != null) {
            // Si hay expresión, generar el código de tres direcciones para la expresión
            String resultadoExpresion = visit(ctx.expresion());  // Asumiendo que visit() genera el código de tres direcciones para la expresión
            codigoTresDirecciones.append(" ").append(resultadoExpresion);
        }
        codigoTresDirecciones.append(";\n");

        return null;
    }

    @Override
    public String visitDeclaracionFuncion(compiladoresParser.DeclaracionFuncionContext ctx) {
        String tipo = ctx.tipo().getText();
        String id = ctx.ID().getText();

        TipoDato tipoDato = obtenerTipoDato(tipo);
        codigoTresDirecciones.append("function ").append(id).append(":\n");

        codigoTresDirecciones.append("end function;\n");
        return null;
    }

    private TipoDato obtenerTipoDato(String tipo) {
        switch (tipo) {
            case "int":
                return TipoDato.INT;
            case "double":
                return TipoDato.DOUBLE;
            case "bool":
                return TipoDato.BOOL;
            case "void":
                return TipoDato.VOID;
            default:
                throw new RuntimeException("Tipo no soportado: " + tipo);
        }
    }

    public String visitLlamadaFuncion(compiladoresParser.LlamadaFuncionContext ctx) {
        String nombreFuncion = ctx.ID().getText();

        // Procesar los argumentos de la función
        List<String> argumentos = new ArrayList<>();
        for (compiladoresParser.ExpresionContext exp : ctx.expresion()) {
            String argumentoEvaluado = visit(exp); // Evaluamos cada argumento
            System.out.println("Argumento evaluado: " + argumentoEvaluado);  // Depuración
            argumentos.add(argumentoEvaluado);
        }

        // Generar la llamada a la función
        StringBuilder llamada = new StringBuilder("call ").append(nombreFuncion);
        if (!argumentos.isEmpty()) {
            llamada.append(" (").append(String.join(", ", argumentos)).append(")");
        }
        llamada.append(";\n");

        // Agregar la llamada al código de tres direcciones siempre
        codigoTresDirecciones.append(llamada.toString());

        String temporal = nuevaTemporal();
        codigoTresDirecciones.append(temporal).append(" = return;\n");

        return null; // Las funciones void no devuelven valores
    }

    @Override
    public String visitCuerpoFuncion(compiladoresParser.CuerpoFuncionContext ctx) {
        // Obtener el nombre de la función (ID) y su tipo
        String tipo = ctx.tipo().getText().toUpperCase();
        String id = ctx.ID().getText();

        // Crear la declaración de la función
        if (tipo.equals("VOID")) {
            codigoTresDirecciones.append("function ").append(id).append(" (void):\n");
        } else {
            codigoTresDirecciones.append("function ").append(id).append(":\n");
        }

        // Procesar el bloque de la función
        if (ctx.bloque() != null) {
            visit(ctx.bloque());
        }

        // Finalizar la declaración de la función
        codigoTresDirecciones.append("end function\n");

        return null;
    }


}

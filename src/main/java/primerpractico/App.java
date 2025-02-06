package primerpractico;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class App {
    public static void main(String[] args) throws Exception {
        // Lectura del archivo de entrada con código C
        CharStream input = CharStreams.fromFileName("input/entrada5.txt");

        compiladoresLexer lexer = new compiladoresLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        compiladoresParser parser = new compiladoresParser(tokens);

        try (PrintWriter escritorErrores = new PrintWriter(new FileWriter("output/errores.txt"))) {
            ManejadorErrores manejadorErrores = new ManejadorErrores(escritorErrores);
            parser.removeErrorListeners();
            parser.addErrorListener(manejadorErrores);

            // Crear el escucha y agregarlo al parser
            Escucha escucha = new Escucha(escritorErrores);
            parser.addParseListener(escucha); // Agregar el listener

            // Parsear el código
            ParseTree tree = parser.programa();

            // Continuar si solo hay warnings
            if (!escucha.verificarWarnings()) {
                System.out.println("Advertencias Detectadas, se reanuda la ejecucion, verifique el archivo errores.txt");
            }

            if(!escucha.verificarErrores() || !manejadorErrores.verificarErrores()){
                System.out.println("Errores Detectados, se reanuda la ejecucion, verifique el archivo errores.txt");
            }

            // Usar el visitor para generar el código de tres direcciones
            Caminante visitor = new Caminante();
            visitor.visit(tree);
            System.out.println(tree.toStringTree(parser));

            // Imprimir la tabla de símbolos en un archivo
            //escucha.imprimirTablaSimbolos("output/tabla_simbolos.txt");

            // Escribir el código de tres direcciones en un archivo
            String codigoGenerado = visitor.getCodigoGenerado();
            try (PrintWriter escritorCodigo = new PrintWriter(new FileWriter("output/codigo_3direcciones.txt"))) {
                escritorCodigo.print(codigoGenerado);
                System.out.println("Código de tres direcciones generado en 'codigo_3direcciones.txt'.");
            } catch (IOException e) {
                System.err.println("Error al escribir el archivo de código de tres direcciones.");
                e.printStackTrace();
            }

            // Optimización del código generado
            Optimizador optimizador = new Optimizador(codigoGenerado);
            String codigoOptimizado = optimizador.optimizar();

            try (PrintWriter escritorOptimizado = new PrintWriter(new FileWriter("output/codigo_optimizado.txt"))) {
                escritorOptimizado.print(codigoOptimizado);
                System.out.println("Código optimizado generado en 'codigo_optimizado.txt'.");
            } catch (IOException e) {
                System.err.println("Error al escribir el archivo de código optimizado.");
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

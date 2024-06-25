package primerpractico;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class App {
    public static void main(String[] args) throws Exception {
        
        // Lectura del archivo de entrada con codico c
        CharStream input = CharStreams.fromFileName("input/entrada.txt");

        compiladoresLexer lexer = new compiladoresLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        compiladoresParser parser = new compiladoresParser(tokens);

        try (PrintWriter escritorErrores = new PrintWriter(new FileWriter("errores.txt"))) {
            parser.removeErrorListeners();
            parser.addErrorListener(new ManejadorErrores(escritorErrores));

            ParseTree tree = parser.programa();
            ParseTreeWalker walker = new ParseTreeWalker();
            Escucha escucha = new Escucha(escritorErrores);
            walker.walk(escucha, tree);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
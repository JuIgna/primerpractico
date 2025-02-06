// Generated from C:/Users/Fede/Desktop/primerpractico-1/src/main/java/primerpractico/compiladores.g4 by ANTLR 4.13.1
package primerpractico;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link compiladoresParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface compiladoresVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(compiladoresParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#instrucciones}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstrucciones(compiladoresParser.InstruccionesContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccion(compiladoresParser.InstruccionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#bloque}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloque(compiladoresParser.BloqueContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#error}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitError(compiladoresParser.ErrorContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#declaracionFuncion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracionFuncion(compiladoresParser.DeclaracionFuncionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#llamadaFuncion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLlamadaFuncion(compiladoresParser.LlamadaFuncionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#cuerpoFuncion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCuerpoFuncion(compiladoresParser.CuerpoFuncionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#parametros}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametros(compiladoresParser.ParametrosContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#parametro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametro(compiladoresParser.ParametroContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#tipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo(compiladoresParser.TipoContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#declaracion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracion(compiladoresParser.DeclaracionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#asignacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignacion(compiladoresParser.AsignacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#estructuraControl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEstructuraControl(compiladoresParser.EstructuraControlContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#ifElse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfElse(compiladoresParser.IfElseContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#whileLoop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileLoop(compiladoresParser.WhileLoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#forLoop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForLoop(compiladoresParser.ForLoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#inicializacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInicializacion(compiladoresParser.InicializacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#condicion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondicion(compiladoresParser.CondicionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#actualizacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActualizacion(compiladoresParser.ActualizacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresion(compiladoresParser.ExpresionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#expresionLogica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionLogica(compiladoresParser.ExpresionLogicaContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#expresionComparacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionComparacion(compiladoresParser.ExpresionComparacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#expresionAritmetica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionAritmetica(compiladoresParser.ExpresionAritmeticaContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#termino}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermino(compiladoresParser.TerminoContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(compiladoresParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#op_aritmeticos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_aritmeticos(compiladoresParser.Op_aritmeticosContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#op_logicas}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_logicas(compiladoresParser.Op_logicasContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#llamadaPrints}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLlamadaPrints(compiladoresParser.LlamadaPrintsContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#argumentos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentos(compiladoresParser.ArgumentosContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#booleano}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleano(compiladoresParser.BooleanoContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#incrementoDecremento}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIncrementoDecremento(compiladoresParser.IncrementoDecrementoContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#return}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn(compiladoresParser.ReturnContext ctx);
}
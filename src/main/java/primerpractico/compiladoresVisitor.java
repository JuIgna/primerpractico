// Generated from c:/Users/Fede/Desktop/practicoTc/primerpractico/src/main/java/primerpractico/compiladores.g4 by ANTLR 4.13.1

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
	 * Visit a parse tree produced by {@link compiladoresParser#declaracionFuncion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracionFuncion(compiladoresParser.DeclaracionFuncionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#tipoFuncion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipoFuncion(compiladoresParser.TipoFuncionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#listaParametros}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListaParametros(compiladoresParser.ListaParametrosContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#tipoparametros}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipoparametros(compiladoresParser.TipoparametrosContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#declaraciones}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaraciones(compiladoresParser.DeclaracionesContext ctx);
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
	 * Visit a parse tree produced by {@link compiladoresParser#expresiones}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresiones(compiladoresParser.ExpresionesContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#llamadaFuncion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLlamadaFuncion(compiladoresParser.LlamadaFuncionContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(compiladoresParser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(compiladoresParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#t}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitT(compiladoresParser.TContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(compiladoresParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#condiciones}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondiciones(compiladoresParser.CondicionesContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#condicionesDeclaraciones}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondicionesDeclaraciones(compiladoresParser.CondicionesDeclaracionesContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond(compiladoresParser.CondContext ctx);
	/**
	 * Visit a parse tree produced by {@link compiladoresParser#returnCond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnCond(compiladoresParser.ReturnCondContext ctx);
}
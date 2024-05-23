// Generated from c:/Users/Fede/Desktop/practicoTc/primerpractico/src/main/java/primerpractico/compiladores.g4 by ANTLR 4.13.1

package primerpractico;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link compiladoresParser}.
 */
public interface compiladoresListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(compiladoresParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(compiladoresParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#instrucciones}.
	 * @param ctx the parse tree
	 */
	void enterInstrucciones(compiladoresParser.InstruccionesContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#instrucciones}.
	 * @param ctx the parse tree
	 */
	void exitInstrucciones(compiladoresParser.InstruccionesContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#declaracionFuncion}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracionFuncion(compiladoresParser.DeclaracionFuncionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#declaracionFuncion}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracionFuncion(compiladoresParser.DeclaracionFuncionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#tipoFuncion}.
	 * @param ctx the parse tree
	 */
	void enterTipoFuncion(compiladoresParser.TipoFuncionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#tipoFuncion}.
	 * @param ctx the parse tree
	 */
	void exitTipoFuncion(compiladoresParser.TipoFuncionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#listaParametros}.
	 * @param ctx the parse tree
	 */
	void enterListaParametros(compiladoresParser.ListaParametrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#listaParametros}.
	 * @param ctx the parse tree
	 */
	void exitListaParametros(compiladoresParser.ListaParametrosContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#tipoparametros}.
	 * @param ctx the parse tree
	 */
	void enterTipoparametros(compiladoresParser.TipoparametrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#tipoparametros}.
	 * @param ctx the parse tree
	 */
	void exitTipoparametros(compiladoresParser.TipoparametrosContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#declaraciones}.
	 * @param ctx the parse tree
	 */
	void enterDeclaraciones(compiladoresParser.DeclaracionesContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#declaraciones}.
	 * @param ctx the parse tree
	 */
	void exitDeclaraciones(compiladoresParser.DeclaracionesContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracion(compiladoresParser.DeclaracionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracion(compiladoresParser.DeclaracionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void enterAsignacion(compiladoresParser.AsignacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void exitAsignacion(compiladoresParser.AsignacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#expresiones}.
	 * @param ctx the parse tree
	 */
	void enterExpresiones(compiladoresParser.ExpresionesContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#expresiones}.
	 * @param ctx the parse tree
	 */
	void exitExpresiones(compiladoresParser.ExpresionesContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#llamadaFuncion}.
	 * @param ctx the parse tree
	 */
	void enterLlamadaFuncion(compiladoresParser.LlamadaFuncionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#llamadaFuncion}.
	 * @param ctx the parse tree
	 */
	void exitLlamadaFuncion(compiladoresParser.LlamadaFuncionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(compiladoresParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(compiladoresParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(compiladoresParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(compiladoresParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#t}.
	 * @param ctx the parse tree
	 */
	void enterT(compiladoresParser.TContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#t}.
	 * @param ctx the parse tree
	 */
	void exitT(compiladoresParser.TContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(compiladoresParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(compiladoresParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#condiciones}.
	 * @param ctx the parse tree
	 */
	void enterCondiciones(compiladoresParser.CondicionesContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#condiciones}.
	 * @param ctx the parse tree
	 */
	void exitCondiciones(compiladoresParser.CondicionesContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#condicionesDeclaraciones}.
	 * @param ctx the parse tree
	 */
	void enterCondicionesDeclaraciones(compiladoresParser.CondicionesDeclaracionesContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#condicionesDeclaraciones}.
	 * @param ctx the parse tree
	 */
	void exitCondicionesDeclaraciones(compiladoresParser.CondicionesDeclaracionesContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond(compiladoresParser.CondContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond(compiladoresParser.CondContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#returnCond}.
	 * @param ctx the parse tree
	 */
	void enterReturnCond(compiladoresParser.ReturnCondContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#returnCond}.
	 * @param ctx the parse tree
	 */
	void exitReturnCond(compiladoresParser.ReturnCondContext ctx);
}
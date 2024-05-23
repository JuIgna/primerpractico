grammar compiladores;

@header {
package primerpractico;
}

fragment DIGITO: [0-9];
fragment CARACTER: [a-zA-Z];

NUMERO: DIGITO+;

INT: 'int';
DOUBLE: 'double';
VOID: 'void';
IF: 'if';
ELSE: 'else';
FOR: 'for';
WHILE: 'while';
RETURN: 'return';
BOOL: 'bool';

// Operators
PYC: ';';
COMA: ',';
IGUAL: '=';
SUMA: '+';
RESTA: '-';
MULT: '*';
DIV: '/';
MOD: '%';
PA: '(';
PC: ')';
LLA: '{';
LLC: '}';
COMP: '==' | '!=' | '<' | '>' | '<=' | '>=';
ID: (CARACTER | '_') (CARACTER | DIGITO | '_')*;
PUNTO: '.';

WS: [ \t\r\n]+ -> skip;

programa: instrucciones EOF;

instrucciones:
	declaracionFuncion LLA declaraciones LLC (
		instrucciones
		| EOF
	)
	| declaracionFuncion instrucciones;

declaracionFuncion:
	tipoFuncion ID PA PC (PYC |)
	| tipoFuncion ID PA listaParametros PC (PYC |);

tipoFuncion: VOID | tipoparametros;

listaParametros:
	tipoparametros ID
	| tipoparametros ID COMA listaParametros;

tipoparametros: INT | DOUBLE | BOOL;

declaraciones:
	declaracion declaraciones
	| asignacion declaraciones
	| condiciones declaraciones
	| returnCond declaraciones
	|;

declaracion:
	tipoparametros ID PYC
	| tipoparametros ID expresiones;

asignacion: ID expresiones;

expresiones: exp (llamadaFuncion | PYC) expresiones | EOF |;

llamadaFuncion: PA exp PC;

exp: term;

term: factor t;

t:
	SUMA term
	| RESTA term
	| COMP term
	| MULT term
	| DIV term
	| PUNTO term
	| COMA term
	|;

factor: NUMERO | ID | IGUAL exp |;

condiciones:
	condicionesDeclaraciones (
		PA cond PC LLA asignacion LLC
		| LLA asignacion LLC
	);

condicionesDeclaraciones: IF | FOR | WHILE | ELSE;

cond:
	ID exp cond
	| tipoparametros ID exp PYC cond
	| ID exp PYC cond
	|;

returnCond: RETURN expresiones;
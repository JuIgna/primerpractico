grammar compiladores;

@header {
package primerproyecto;
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

AND: '&&';
OR: '||';

TRUE: 'true';
FALSE: 'false';

WS: [ \t\r\n]+ -> skip;

programa: instrucciones EOF;

instrucciones:
	declaracionFuncion LLA declaraciones LLC (
		instrucciones
		| EOF
	)
	| declaracionFuncion instrucciones;

declaracionFuncion:
	declaracionFunc ID PA PC (PYC |)
	| declaracionFunc ID PA listaParametros PC (PYC |);

declaracionFunc: VOID | parametros;

listaParametros:
	parametros ID
	| parametros ID COMA listaParametros;

parametros: INT | DOUBLE | BOOL;

declaraciones:
	declaracion declaraciones
	| asignacion declaraciones
	| condiciones declaraciones
	| returnCond declaraciones
	|;

declaracion: parametros ID PYC | parametros ID exp PYC;

asignacion: ID expresiones;

expresiones: exp (llamadaFuncion | PYC) expresiones | EOF |;

exp: term;

term: factor t;

booleanos: TRUE | FALSE;

logicos: AND | OR;

t:
	SUMA term
	| RESTA term
	| COMP term
	| MULT term
	| DIV term
	| PUNTO term
	| COMA term
	| logicos
	|;

factor: NUMERO | ID | IGUAL exp | booleanos |;

condiciones:
	condicionesDeclaraciones (
		PA cond PC LLA asignacion (cond |) LLC
		| LLA asignacion LLC
	);

condicionesDeclaraciones: IF | FOR | WHILE | ELSE;

cond:
	ID exp cond
	| parametros ID exp PYC cond
	| ID exp PYC cond
	|;

llamadaFuncion: PA exp PC;

returnCond: RETURN expresiones;
grammar compiladores;
/*
 @header { package primerpractico; }
 */

fragment DIGITO: [0-9];
fragment CARACTER: [a-zA-Z];

NUMERO: DIGITO+;
DOUBLE_LITERAL: DIGITO+ '.' DIGITO* | DIGITO* '.' DIGITO+;

INT: 'int';
DOUBLE: 'double';
VOID: 'void';
IF: 'if';
ELSE: 'else';
FOR: 'for';
WHILE: 'while';
RETURN: 'return';
BOOL: 'bool';
TRUE: 'true';
FALSE: 'false';
PRINTF: 'printf';

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
STRING: '"' ( ~["\\] | '\\' .)* '"';
AND: '&&';
OR: '||';
INCREMENTO: SUMA SUMA;
DECREMENTO: RESTA RESTA;

WS: [ \t\r\n]+ -> skip;

programa: (cuerpoFuncion | instruccion)* EOF;

instrucciones: (instruccion)*;

instruccion:
	declaracionFuncion
	| bloque
	| declaracion
	| asignacion
	| estructuraControl
	| llamadaFuncion PYC
	| expresion PYC
	| return
	;

bloque: LLA instrucciones? LLC;

error: ~LLC+;

declaracionFuncion: tipo ID PA parametros? PC PYC;

llamadaFuncion: ID PA (expresion (COMA expresion)*)? PC;

cuerpoFuncion: tipo ID PA parametros? PC bloque;

parametros: parametro (COMA parametro)*;

parametro: tipo ID;

tipo: INT | DOUBLE | BOOL | VOID;

declaracion: tipo ID (IGUAL expresion)? PYC;

asignacion: ID IGUAL expresion PYC;

estructuraControl: ifElse | whileLoop | forLoop;

ifElse: IF PA expresion PC bloque (ELSE bloque)?;

whileLoop: WHILE PA expresion PC bloque;

forLoop:
    FOR PA inicializacion PYC? condicion? PYC actualizacion? PC bloque;

inicializacion:
    declaracion
    | asignacion
    |
    ;

condicion:
    expresion
    |
    ;

actualizacion:
    (asignacion | incrementoDecremento) (COMA (asignacion | incrementoDecremento))*;


/*forLoop:
    FOR PA (declaracion | asignacion)? (PYC)? expresion? (PYC)?
    (asignacion | incrementoDecremento)? PC bloque;*/



expresion:
    expresionLogica
    ;

expresionLogica:
    expresionComparacion (op_logicas expresionComparacion)*
    ;

expresionComparacion:
    expresionAritmetica (COMP expresionAritmetica)?
    ;

expresionAritmetica:
    termino ((SUMA | RESTA) termino)*
    ;

termino:
    factor ((MULT | DIV | MOD) factor)*
    ;

factor:
    PA expresion PC
    | booleano
    | ID
    | STRING
    | llamadaPrints
    | llamadaFuncion
    | incrementoDecremento
    | RESTA? NUMERO
    | DOUBLE_LITERAL
    ;

op_aritmeticos: SUMA | RESTA | MULT | DIV | MOD;

op_logicas: AND | OR;




llamadaPrints:
	PRINTF PA ((STRING (COMA expresion)* | expresion))? PC;

argumentos: expresion (COMA expresion)*;

booleano: TRUE | FALSE;

incrementoDecremento: ID (INCREMENTO | DECREMENTO);

return: RETURN expresion? PYC
        ;
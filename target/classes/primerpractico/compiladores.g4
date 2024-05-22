grammar compiladores; 

  

@header { 

package primerpractico; 

} 

fragment LETRA : [A-Za-z] ; 
fragment DIGITO : [0-9] ; 


LLA: '{'; 
LLC: '}'; 
PYC: ';'; 
INT: 'int'; 
IGUAL: '='; 

  
NUMERO : DIGITO+ ; 

OTRO : . ; 

  
ID : (LETRA | '_')(LETRA | DIGITO | '_')* ; 

  

programa : instrucciones EOF;  

  

instrucciones: instruccion instrucciones 
            | 
            ; 


instruccion: LLA instrucciones LLC 
          |declaracion             
          ; 

  

declaracion:  INT ID PYC; 


asignacion: ID IGUAL NUMERO PYC;
/** Optional javadoc style comment */
grammar RuleEvaluation ;

expression
    :   left=expression (AND|OR) right=expression # AndOr
    |   NOT expression #Not
    |   IDENTIFIER #ID
    |   LPAREN expression RPAREN #Parenthesis
    ;

OR : '|' ;
AND : '&' ;
NOT : '!' ;
LPAREN : '(' ;
RPAREN : ')' ;
IDENTIFIER : [a-zA-Z_0-9][a-zA-Z_0-9]* ;
WS : [ \t\r\n\f]+ -> skip ;
/*-***
 *
 * This file defines a stand-alone lexical analyzer for a subset of the Pascal
 * programming language.  This is the same lexer that will later be integrated
 * with a CUP-based parser.  Here the lexer is driven by the simple Java test
 * program in ./PascalLexerTest.java, q.v.  See 330 Lecture Notes 2 and the
 * Assignment 2 writeup for further discussion.
 *
 */

/* USER CODE */

import java_cup.runtime.*;


%%


/* Options and declarations */

/*-*
 * LEXICAL FUNCTIONS:
 */

%cup
%line
%column
%unicode
%class Lexer

%{

Symbol newSym(int tokenId) {
    return new Symbol(tokenId, yyline, yycolumn);
}

Symbol newSym(int tokenId, Object value) {
    return new Symbol(tokenId, yyline, yycolumn, value);
}

%}


/*
 * PATTERN DEFINITIONS:
 */
tab     = \\t
newLine = \\n
bSlash  = \\
letter  = [a-zA-Z]
digits  = [0-9]
whitespace = [ \n\t\r]

/* comments */
comment = {bSlash}{bSlash}.*\n
commentContent = ( [^*] | \*\\+ [^\*\\] )*
cmtOpen = "\\*"
cmtClose = "*\\"
multiComment = {cmtOpen}{commentContent}{cmtClose}

/* Identifier */
id = {letter}[{letter}|{digits}]*

/* Integer Literal */
intlit = {digits}+

/* Character Literal  */
charContent = [^\\\n\t\"\'] | \\.
charlit = \'{charContent}\'

/* Floating Literal */
floatlit = {digits}+.{digits}+

/* String Literal */
strContent = {charContent}*
strlit = \"{strContent}\"

%%


/*
 * LEXICAL RULES:
 */

/* Keywords */
"{"             {return newSym(sym.L_CURLY, new String(yytext()) );}
"}"             {return newSym(sym.R_CURLY, new String(yytext()) );}
"("             {return newSym(sym.L_PAREN, new String(yytext()) );}
")"             {return newSym(sym.R_PAREN, new String(yytext()) );}
"["             {return newSym(sym.L_SQBRAC, new String(yytext()) );}
"]"             {return newSym(sym.R_SQBRAC, new String(yytext()) );}
","             {return newSym(sym.COMMA, new String(yytext()) );}
":"             {return newSym(sym.COLON, new String(yytext()) );}
";"             {return newSym(sym.SEMI_COLON, new String(yytext()) );}
"?"             {return newSym(sym.QUESTION, new String(yytext()) );}
"while"         {return newSym(sym.WHILE, new String(yytext()) );}
"if"            {return newSym(sym.IF, new String(yytext()) );}
"else"          {return newSym(sym.ELSE, new String(yytext()) );}
"class"         {return newSym(sym.CLASS, new String(yytext()) );}
"print"         {return newSym(sym.PRINT, new String(yytext()) );}
"printline"     {return newSym(sym.PRINTLINE, new String(yytext()) );}
"return"        {return newSym(sym.RETURN, new String(yytext()) );}
"final"         {return newSym(sym.FINAL, new String(yytext()) );}
"void"          {return newSym(sym.VOID, new String(yytext()) );}
"int"           {return newSym(sym.INT, new String(yytext()) );}
"char"          {return newSym(sym.CHAR, new String(yytext()) );}
"bool"          {return newSym(sym.BOOL, new String(yytext()) );}
"float"         {return newSym(sym.FLOAT, new String(yytext()) );}
"read"          {return newSym(sym.READ, new String(yytext()) );}


/* Unary Operators */
"++"            {return newSym(sym.AUTO_INCREMENT, new String(yytext()) );}
"--"            {return newSym(sym.AUTO_DECREMENT, new String(yytext()) );}
"~"             {return newSym(sym.BIN_NEG, new String(yytext()) );}

/* Arithmetic Operators */
"-"             {return newSym(sym.MINUS_OP, new String(yytext()) );}
"+"             {return newSym(sym.PLUS_OP, new String(yytext()) );}
"*"             {return newSym(sym.MULTIPLY_OP, new String(yytext()) );}
"/"             {return newSym(sym.DIVIDE_OP, new String(yytext()) );}

/* Literals */
"true"          {return newSym(sym.TRUE, new String(yytext()) );}
"false"         {return newSym(sym.FALSE, new String(yytext()) );}

/* Relational and Logical Operators */
"&&"            {return newSym(sym.AND_OP, new String(yytext()) );}
"||"            {return newSym(sym.OR_OP, new String(yytext()) );}
"=="            {return newSym(sym.EQUAL_OP, new String(yytext()) );}
"<>"            {return newSym(sym.NOT_EQUAL_OP, new String(yytext()) );}
">="            {return newSym(sym.GTE_OP, new String(yytext()) );}
"<="            {return newSym(sym.LTE_OP, new String(yytext()) );}
">"             {return newSym(sym.GT_OP, new String(yytext()) );}
"<"             {return newSym(sym.LT_OP, new String(yytext()) );}

"="             {return newSym(sym.ASSIGN, new String(yytext()) );}

/* */
{id}            {return newSym(sym.ID, yytext());}
{whitespace}    { /* Ignore whitespace. */ }
{comment}       { /* Comment */ }
{multiComment}  { /* Comment */ }
{intlit}        {return newSym(sym.INTLIT, new Integer(yytext()));}
{charlit}       {return newSym(sym.CHARLIT, new Character(yytext().charAt(1)));}
{strlit}        {return newSym(sym.STRINGLIT, new String(yytext()));}
{floatlit}      {return newSym(sym.FLOATLIT, new Double(yytext().substring(0, yytext().length()-1)));}
.               { System.out.println("Illegal char, '" + yytext() + "' line: " + yyline + ", column: " + yychar); }
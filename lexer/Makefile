JAVA=java
JAVAC=javac
JFLEX=$(JAVA) -jar jflex-full-1.8.2.jar
CUPJAR=./java-cup-11b.jar
CUP=$(JAVA) -jar $(CUPJAR)
CP=.:$(CUPJAR)

default: run

.SUFFIXES: $(SUFFIXES) .class .java

.java.class:
		$(JAVAC) -cp $(CP) $*.java

FILE=    Lexer.java parser.java sym.java\
	ScannerTest.java ScannerTest.java Token.java Program.java Expr.java BinOp.java Name.java Stmt.java

run: basicOps.txt basicExpressions.txt basicStmts.txt

all: Lexer.java parser.java $(FILE:java=class)

basicOps.txt: all
		$(JAVA) -cp $(CP) ScannerTest basicOps.txt > basicOps-output.txt
		cat basicOps.txt
		cat -n basicOps-output.txt

basicExpressions.txt: all
		$(JAVA) -cp $(CP) ScannerTest basicExpressions.txt > basicExpressions-output.txt
		cat basicExpressions.txt
		cat -n basicExpressions-output.txt

basicStmts.txt: all
		$(JAVA) -cp $(CP) ScannerTest basicStmts.txt > basicStmts-output.txt
		cat basicStmts.txt
		cat -n basicStmts-output.txt

clean:
		rm -f *.class *~ *.bak Lexer.java parser.java sym.java *-output.txt

Lexer.java: tokens.jflex
		$(JFLEX) tokens.jflex

parser.java: grammar.cup
		$(CUP) -interface < grammar.cup

parserD.java: grammar.cup
		$(CUP) -interface -dump < grammar.cup
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
	LexerTest.java ScannerTest.java Token.java Program.java Expr.java BinOp.java Name.java Stmt.java

run: sampleFile.txt

all: Lexer.java parser.java $(FILE:java=class)

sampleFile.txt: all
		$(JAVA) -cp $(CP) ScannerTest sampleFile.txt > sampleFile-output.txt
		cat sampleFile.txt
		cat -n sampleFile-output.txt

clean:
		rm -f *.class *~ *.bak Lexer.java parser.java sym.java *-output.txt

Lexer.java: tokens.jflex
		$(JFLEX) tokens.jflex

parser.java: grammar.cup
		$(CUP) -interface < grammar.cup

parserD.java: grammar.cup
		$(CUP) -interface -dump < grammar.cup
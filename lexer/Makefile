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

FILE=    Lexer.java      parser.java    sym.java \
    LexerTest.java

run: besicRegex.txt basicTerminals.txt basicFails.txt basicSyntax.txt

all: Lexer.java parser.java $(FILE:java=class)

besicRegex.txt: all
		$(JAVA) -cp $(CP) LexerTest besicRegex.txt > besicRegex-output.txt
		cat besicRegex.txt
		cat -n besicRegex-output.txt

basicTerminals.txt: all
		$(JAVA) -cp $(CP) LexerTest basicTerminals.txt > basicTerminals-output.txt
		cat basicTerminals.txt
		cat -n basicTerminals-output.txt

basicFails.txt: all
		$(JAVA) -cp $(CP) LexerTest basicFails.txt > basicFails-output.txt
		cat basicFails.txt
		cat -n basicFails-output.txt

basicSyntax.txt: all
		$(JAVA) -cp $(CP) LexerTest basicSyntax.txt > basicSyntax-output.txt
		cat basicSyntax.txt
		cat -n basicSyntax-output.txt

clean:
		rm -f *.class *~ *.bak Lexer.java parser.java sym.java

Lexer.java: tokens.jflex
		$(JFLEX) tokens.jflex

parser.java: grammar.cup
		$(CUP) -interface < grammar.cup

parserD.java: grammar.cup
		$(CUP) -interface -dump < grammar.cup

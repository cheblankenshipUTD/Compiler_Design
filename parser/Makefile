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
	ScannerTest.java ScannerTest.java Token.java Program.java Expr.java BinOp.java Name.java Stmt.java Argdecl.java Fielddecl.java Methoddecl.java Memberdecls.java

run: Phase2_empty.txt Phase2_fields.txt Phase2_full.txt Phase2_methods.txt Phase2_fail.txt

all: Lexer.java parser.java $(FILE:java=class)

Phase2_empty.txt: all
		$(JAVA) -cp $(CP) ScannerTest Phase2_empty.txt > Phase2_empty-output.txt
		cat Phase2_empty.txt
		cat -n Phase2_empty-output.txt

Phase2_fields.txt: all
		$(JAVA) -cp $(CP) ScannerTest Phase2_fields.txt > Phase2_fields-output.txt
		cat Phase2_fields.txt
		cat -n Phase2_fields-output.txt

Phase2_full.txt: all
		$(JAVA) -cp $(CP) ScannerTest Phase2_full.txt > Phase2_full-output.txt
		cat Phase2_full.txt
		cat -n Phase2_full-output.txt

Phase2_methods.txt: all
		$(JAVA) -cp $(CP) ScannerTest Phase2_methods.txt > Phase2_methods-output.txt
		cat Phase2_methods.txt
		cat -n Phase2_methods-output.txt

Phase2_fail.txt: all
		$(JAVA) -cp $(CP) ScannerTest Phase2_fail.txt > Phase2_fail-output.txt
		cat Phase2_fail.txt
		cat -n Phase2_fail-output.txt

clean:
		rm -f *.class *~ *.bak Lexer.java parser.java sym.java *-output.txt

Lexer.java: tokens.jflex
		$(JFLEX) tokens.jflex

parser.java: grammar.cup
		$(CUP) -interface < grammar.cup

parserD.java: grammar.cup
		$(CUP) -interface -dump < grammar.cup
JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		Lexer.java \
		BinaryTree.java \
		Parser.java \
		Main.java \

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
	
run:
	java Main

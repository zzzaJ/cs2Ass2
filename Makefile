# MakeFile for Assignment 2
# BSSDIN001

SRCDIR = src
BINDIR = bin
DOCDIR = doc

JC = javac
JFLAGS = -g -d $(BINDIR) -cp $(BINDIR) -cp $(SRCDIR)

vpath %.java $(SRCDIR)
vpath %.class $(BINDIR)

.SUFFIXES: .java .class

.java.class:	
	$(JC) $(JFLAGS) $<
	
CLASSES = WordDictionary.class \
	  WordRecord.class \
	  Score.class \
	  WordApp.class \
	  WordRecordThread.class \
	  WordPanel.class \
	  ScoreUpdaterThread.class \
	  
classes: $(CLASSES:.java=.class)

doc: $(BINDIR)
	javadoc -d $(DOCDIR) ./src/WordRecordThread.java -cp $(BINDIR) -cp $(SRCDIR) 
	javadoc -d $(DOCDIR) ./src/ScoreUpdaterThread.java  -cp $(BINDIR) -cp $(SRCDIR) 
	
clean:
	$(RM) $(BINDIR)/*.class
	$(RM) $(SRC)/*.java~
	$(RM) $(DOCDIR)/*

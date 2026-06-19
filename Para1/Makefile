#####
## if you want to specify a directory java installed explicitly,
## set the java binary directory here
#####
JAVABIN= D:/javajdk203/bin/
#  JAVABIN=$(HOME)/parawork/jdk-25.0.3+9/bin/
#  JAVABIN=/usr/lib/jvm/default-java/bin

#####
## set javafx-sdk directory your installed
#####
JAVAFXMODULE=../javafx-sdk-25.0.3/lib

#####
##  set proxy server address and port number, if your machine connects
##  to the internet via proxy
JAVADOCPROXY=
#  JAVADOCPROXY=-J-Dhttp.proxyHost=proxy.csc.titech.ac.jp -J-Dhttp.proxyPort=8080

######
### CSC room setting
######
#ifneq ($(words $(findstring cs-pc, $(shell hostname)) $(findstring cs-kan, $(shell hostname))), 0)
#	ESTPLACE="working in CSC room"
#	JAVAFXMODULE=/usr/local/javafx-sdk-25.0.3/lib
##JAVABIN=
###JAVADOCPROXY= -J-Dhttp.proxyHost=proxy.csc.titech.ac.jp -J-Dhttp.proxyPort=8080
#else
#	ESTPLACE="working on your PC"
#endif

# OS-dependent commands and separator
ifeq ($(OS),Windows_NT)
	SEP=;
	RM=rd /s /q
	FIND=echo
else
	SEP=:
	RM=rm -rf
	FIND=find
endif

JAVA=$(JAVABIN)java $(JAVAFLAGS)
JAVAC=$(JAVABIN)javac $(JAVACFLAGS)
JAVADOC0=$(JAVABIN)javadoc $(JAVADOCFLAGS)
MKDIR=mkdir
MAKE=make

MODULEPATH=$(JAVAFXMODULE)
CLASSPATH=lib/*$(SEP)
CLASSFLAGS=-classpath "bin$(SEP)$(CLASSPATH)resource"
JAVACCLASSFLAGS=-classpath "$(CLASSPATH)"
MODULEFLAGS=--module-path $(MODULEPATH) --add-modules javafx.controls,javafx.swing
JAVACFLAGS= -encoding utf8 -d bin -sourcepath src $(MODULEFLAGS) $(JAVACCLASSFLAGS) -Xlint:deprecation -Xdiags:verbose -Xlint:unchecked
JAVAFLAGS = $(MODULEFLAGS) $(CLASSFLAGS)  --enable-native-access=javafx.graphics
JAVADOCFLAGS= -Xmaxwarns 10 -html5 -encoding utf-8 -charset utf-8 -package -d javadoc -sourcepath src $(JAVADOCPROXY) -link https://docs.oracle.com/javase/jp/25/docs/api -link https://openjfx.io/javadoc/25 $(MODULEFLAGS) $(CLASSFLAGS)

SERVADDR=localhost
NUMBER=`echo \`whoami\`0 | md5 | sed -e "s/[^0-9]//g" |cut -c 1-6`
PARAMETER=

.PHONY: clean doc

.SUFFIXES: .class .java

.java.class:
	echo $@

.class:
	@echo $(ESTPLACE)
	$(MAKE) bin
	$(eval CLS := $(subst /,.,$(@:src/%=%)))
	$(JAVAC) $@.java 
	$(JAVA) $(CLS)  $(PARAMETER)

ALL::	Paint

Paint:
	$(MAKE) src/para/paint/$@

Calculator:
	$(MAKE) src/para/calc/$@

bin:
	$(MKDIR) bin

javadoc:
	$(MKDIR) javadoc

clean: javadoc bin
	$(RM) bin
	$(RM) javadoc

cleanall: javadoc bin
	$(FIND) . -name "._*" -exec $(RM) {} \;
	$(RM) bin
	$(RM) javadoc

doc:: javadoc
	$(FIND) . -name "._*" -exec $(RM) {} \;
	$(JAVADOC0) para.paint para.calc

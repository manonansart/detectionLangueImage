all : before compil clean

rapport.pdf : rapport.tex
	pdflatex -shell-escape rapport.tex && pdflatex -shell-escape rapport.tex 
clean : 
	rm rapport.aux rapport.log rapport.maf rapport.mtc* rapport.out rapport.toc rapport.pyg

before:
	rm -f rapport.pdf

clean:
	rm -f rapport.mtc*
	rm -f rapport.toc
	rm -f rapport.maf
	rm -f rapport.bbl
	rm -f rapport.blg
	rm -f rapport.pyg
	rm -f *.aux *.log *.out

compil: rapport.pdf

rapport.pdf: 
	pdflatex -shell-escape rapport.tex;
	bibtex rapport;
	pdflatex -shell-escape rapport.tex;
	pdflatex -shell-escape rapport.tex;


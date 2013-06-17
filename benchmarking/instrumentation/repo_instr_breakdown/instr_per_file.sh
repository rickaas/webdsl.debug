#!/bin/bash

prefix=`dirname "$(cd ${0%/*}/ && echo $PWD/${0##*/})"`

function MakePlot {
gnuplot -persist << EOF
#set size 2;
#set terminal wxt size 600,600

# write PNG
#set term png enhanced size 800,600
#set output "$OUTPUT_PNG"
##set size 2;

# write eps + tex
set term epslatex color
set output "$OUTPUT_FILENAME.tex"

#set term epslatex color standalone
#set output "standalone.tex"

#set term post eps
#set output "graph.eps"

#set terminal latex
#set output 'out.latex.tex'

# black/white
#set terminal postscript
#set output 'out.postscript.bw.ps'

#set terminal postscript enhanced color
#set output 'out.postscript.color.ps'

# write pdf / does not work
#set term pdf
#set output 'out.pdf'

#set terminal pslatex
#set output 'out.pslatex.tex'


#set terminal postscript eps color enhanced
#set output 'out.postscript.eps.color.eps';

#pstex
#pstricks
#texdraw
#tpic


# Where to put the legend
# and what it should contain
#set key invert reverse Left outside
#set key Left #outside
set key autotitle columnheader box

set title "YellowGrass instrumentation duration per source file term-size"

set ylabel "duration in seconds"
set xlabel "term-size of source file"
set grid y

#unset xtics



#set size ratio 1.5

f(x) = a*x + b

fit f(x) "$ORG_DATA" using 5:9 via a, b;

title_f(a,b) = sprintf('f(x) = %.4fx + %.4f', a, b)


#plot "$DATA" using 5:9, f(x) t title_f(a,b);
plot "$DATA" using 5:9 title "duration per file" with points pointsize 1 linewidth 5  \\
#    , f(x) title title_f(a,b) lw 2 
    ;
#"<(sed -n '1,2000p' $DATA)"

EOF
}

basedir="$prefix"
DATA="$basedir/foo_1.file.stats"
ORG_DATA="$basedir/original.foo_1.file.stats"
OUTPUT_PNG="$basedir/duration_per_file.png"
OUTPUT_FILENAME="$basedir/di_duration_per_file"
MakePlot

epstopdf "$OUTPUT_FILENAME.eps"

# After 5 iterations the fit converged.
# final sum of squares of residuals : 5.66091
# rel. change during last iteration : -5.89267e-08
# 
# degrees of freedom    (FIT_NDF)                        : 429
# rms of residuals      (FIT_STDFIT) = sqrt(WSSR/ndf)    : 0.114872
# variance of residuals (reduced chisquare) = WSSR/ndf   : 0.0131956
# 
# Final set of parameters            Asymptotic Standard Error
# =======================            ==========================
# 
# a               = 0.000301784      +/- 1.074e-05    (3.56%)
# b               = 0.00466406       +/- 0.006655     (142.7%)
# 
# 
# correlation matrix of the fit parameters:
# 
#                a      b      
# a               1.000 
# b              -0.556  1.000 

# Final set of parameters            Asymptotic Standard Error
# =======================            ==========================
# with extreme points (roughly on line)
# a               = 0.000303457      +/- 6e-06        (1.977%)
# b               = 0.00554181       +/- 0.005638     (101.7%)
# 
# 
# correlation matrix of the fit parameters:
# 
#                a      b      
# a               1.000 
# b              -0.410  1.000 


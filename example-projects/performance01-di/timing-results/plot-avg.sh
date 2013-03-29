#!/bin/sh

BASE=$1
#LOGNAME=1454
GROUP=$2

#location=$BASE/archive/3000/avg.3000.filterWhereUsers.txt
prefix=$BASE/archive/$GROUP/avg.$GROUP
# file headers:
# datasetsize	duration

gnuplot -persist << EOF

set title "Duration of page request vs Data set size (test id: $GROUP)"
set xlabel "Data set size (1000 records)"
set ylabel "Duration (ms)"
#set yr [0:6000]
set yrange [0:*]

set auto x


set key under box
#unset key
#unset xtics
#set xtics border in scale 1,0.5 nomirror rotate by -45  offset character 0, 0, 0\
#         norangelimit\
#         ("15items" 1,\
#          "20items" 2,\
#          "30items" 4,\
#          "40items" 6)
set xtics border in scale 1,0.5 nomirror rotate by -45 offset character 0, 0, 0     norangelimit

set style fill solid

# headers:
# logname	datasetsize	duration

plot "$prefix.orderByUsers.txt"        using 3:xticlabels(2) ti col with histogram title "orderByUsers", \
     "$prefix.listUsers.txt"           using 3:xticlabels(2) ti col with histogram title "listUsers", \
     "$prefix.filterWhereUsers.txt"    using 3:xticlabels(2) ti col with histogram title "filterWhereUsers"

EOF

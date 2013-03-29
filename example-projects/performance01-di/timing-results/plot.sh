#!/bin/sh

BASE=$1
#LOGNAME=1454
GROUP=$2
LOGNAME=$3
prefix=$BASE/archive/$GROUP/$LOGNAME/frames/FrameDuration.$LOGNAME

gnuplot -persist << EOF

set title "Duration of page request"
set xlabel "request instance over time"
set ylabel "Duration (ms)"
#set yr [0:6000]
set yrange [0:*]

#unset key
unset xtics
set style fill solid
plot "$prefix.orderByUsers.trace"        using (\$5-\$4) with histogram title "orderByUsers", \
     "$prefix.listUsers.trace"           using (\$5-\$4) with histogram title "listUsers", \
     "$prefix.filterWhereUsers.trace"    using (\$5-\$4) with histogram title "filterWhereUsers"

EOF

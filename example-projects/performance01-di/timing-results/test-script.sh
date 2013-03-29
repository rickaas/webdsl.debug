#!/bin/bash

# http://stackoverflow.com/questions/59895/can-a-bash-script-tell-what-directory-its-stored-in
prefix="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
echo $prefix

INCREMENT=1 # the dataset will be incremented by this factor
#ITERATIONS=20 # the dataset will be incremented this many times
ITERATIONS=20 # the dataset will be incremented this many times

#REPEAT=$2
#REPEAT=50
REPEAT=5 # repeat the script this many times per dataset size

GROUP=6001

ENABLE_TRACING="0"
ENABLE_LOG_ANALYSIS="1"

#if [ "0" -eq 1 ]
if [ "$ENABLE_TRACING" -eq 1 ]
then
  echo Tracing page requests...
  for j in `seq 1 $ITERATIONS`
  do
    echo iteration $j / $ITERATIONS
    # populate database 10*100 users
    echo group=$GROUP
    LOGNAME=`printf "%si%02d" $GROUP $j`
    echo logname=$LOGNAME
    $prefix/url-fetch-script.sh $GROUP $LOGNAME $REPEAT $INCREMENT
  done
fi



if [ "$ENABLE_LOG_ANALYSIS" -eq 1 ]
then
  echo Analyzing logs...
  for j in `seq 1 $ITERATIONS`
  do
    echo group=$GROUP
    LOGNAME=`printf "%si%02d" $GROUP $j`
    echo logname=$LOGNAME
  
    cp $prefix/../.servletapp/*.$LOGNAME.trace $prefix/archive/$GROUP/$LOGNAME

    mkdir -p $prefix/archive/$GROUP/$LOGNAME/frames

    # split the trace file generated by WebDSL
    awk -v group="$GROUP" -v logname="$LOGNAME" -v prefix="$prefix" '{print > prefix "/archive/" group "/" logname "/frames/FrameDuration." logname "." $3 ".trace"}' $prefix/archive/$GROUP/$LOGNAME/FrameDuration.$LOGNAME.trace

    #$prefix/plot.sh $prefix $GROUP $LOGNAME
  done
fi

# groupname, 
./calc-avg.py $GROUP $INCREMENT $ITERATIONS
./plot-avg.sh $prefix $GROUP

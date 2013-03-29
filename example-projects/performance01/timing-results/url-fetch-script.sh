#!/bin/bash

# the script location
#echo $0

# http://stackoverflow.com/questions/59895/can-a-bash-script-tell-what-directory-its-stored-in
prefix="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
#echo $prefix

GROUP=$1

# logs are categorized in group
LOGNAME=$2
#LOGNAME=run_1313

REPEAT=$3
#REPEAT=5

# each unit will generate 10*100 users
DATASET_UNIT_SIZE=$4
#DATASET_UNIT_SIZE=0

BASEURL=http://localhost:8080/performance01
URLs=( "listUsers" "filterWhereUsers" "orderByUsers" )

mkdir -p $prefix/archive/$GROUP/$LOGNAME

echo group=$GROUP log=$LOGNAME repeat=$REPEAT unitsize=$DATASET_UNIT_SIZE > $prefix/archive/$GROUP/$LOGNAME/run.config

LOGFILE=$prefix/archive/$GROUP/$LOGNAME/wget.log
WGETOUTPUT=$prefix/archive/$GROUP/$LOGNAME/wget.output.tmp

rm -f $LOGFILE
WGET_OPTIONS="--output-document=$WGETOUTPUT --quiet --append-output=$LOGFILE"

# first call base url to initialize the WebDSL backend
wget $WGET_OPTIONS "$BASEURL"



for j in `seq 1 $DATASET_UNIT_SIZE`
do
  echo populating $j / $DATASET_UNIT_SIZE
  # populate database 10*100 users
  wget $WGET_OPTIONS "$BASEURL/automaticallyMakeTestSet"
done


echo start performance tests
# start logging
wget $WGET_OPTIONS "$BASEURL/stop"
echo calling "$BASEURL/start?$LOGNAME"
wget $WGET_OPTIONS "$BASEURL/start?$LOGNAME"

# only measure frameduration for a subset of debug-events
  for i in "${URLs[@]}"
  do
    echo Add FrameName filter: $i
    wget $WGET_OPTIONS "$BASEURL/addFrameNameFilter?$i"
  done

for LOOP_COUNTER in `seq 1 $REPEAT`
do
  echo loop count $LOOP_COUNTER / $REPEAT
  # call each URL
  for i in "${URLs[@]}"
  do
    echo $i
    wget $WGET_OPTIONS "$BASEURL/$i"
  done
done
# flush and stop logging
wget $WGET_OPTIONS "$BASEURL/flush"
wget $WGET_OPTIONS "$BASEURL/stop"


#!/bin/bash

prefix=`dirname "$(cd ${0%/*}/.. && echo $PWD/${0##*/})"`
echo $prefix

buildsdir=$prefix/../../webdsl-builds
#buildname=build-java-from-trunk
buildname=build-java-try-finally

webdslcompiler=$buildsdir/$buildname/bin/webdsl

$webdslcompiler "$*"

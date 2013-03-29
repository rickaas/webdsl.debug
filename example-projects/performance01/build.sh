#!/bin/bash

prefix=`dirname "$(cd ${0%/*}/.. && echo $PWD/${0##*/})"`
echo $prefix

buildsdir=/home/rlindeman/Applications/development/eclipse/spoofax-installs/webdsl-language-modifications/workspace/webdsl-builds
#buildsdir=$prefix/../../webdsl-builds
#buildname=build-java-from-trunk
buildname=build-java-try-finally

webdslcompiler=$buildsdir/$buildname/bin/webdsl

$webdslcompiler "$*"

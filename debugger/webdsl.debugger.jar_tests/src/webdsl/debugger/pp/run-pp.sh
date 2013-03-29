#!/bin/bash

ast2abox -p WebDSL-pretty.pp -i make-users.aterm | pp-aterm -o make-users.abox

abox2text -i make-users.abox 

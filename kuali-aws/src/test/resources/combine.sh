#!/bin/ksh

# Location where to place the output files
DropLocation="./target/env"

print " ,URL,Build,App,JDK,Database,Schema,Build Date,Status" > $DropLocation/combine.out
cat $DropLocation/*.csv >> $DropLocation/combine.out

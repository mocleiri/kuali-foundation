#!/bin/sh
shutemdown.sh
mvn antrun:run -Dtarget=resetall
startemup.sh

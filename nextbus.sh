#!/bin/sh

#
# Purpose: A simple shell script to get next trip departure time
# Output: 8 Minutes (String)
# Usage: ./nextbush.sh "{BUS ROUTE NAME}" "{BUS STOP NAME}" "{DIRECTION}"
# Example: ./nextbush.sh "METRO Blue Line" "Target Field Station Platform 1" "Southbound"
#

ROUTE=$1
STOP=$2
DIRECTION=$3

java -jar build/libs/MetroTransit-1.0-SNAPSHOT.jar "${ROUTE}" "${STOP}" "${DIRECTION}"
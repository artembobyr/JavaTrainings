#!/bin/sh
ITER=0

while [ "$ITER" -le "15" ]
do
  echo "Looping ... number $ITER"
  mvn clean test -Dbrowser=chrome -Dtest=PharmaDataMigration -Dgroups=user08
  ITER=$((ITER + 1 ))
done

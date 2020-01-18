#!/bin/bash

echo "starting preprocessing"

export DATA_DIR=/Users/brunoguedes/RafaelPOC/sample-data
export POSTFIX=_0_0.csv

# replace headers
while read line; do
  IFS=' ' read -r -a array <<< $line
  filename=${array[0]}
  header=${array[1]}
  sed -i.bkp "1s/.*/$header/" "${DATA_DIR}/${filename}${POSTFIX}"
done < headers.txt

# replace labels with one starting with an uppercase letter
sed -i.bkp "s/|city$/|City/" "${DATA_DIR}/place${POSTFIX}"
sed -i.bkp "s/|country$/|Country/" "${DATA_DIR}/place${POSTFIX}"
sed -i.bkp "s/|continent$/|Continent/" "${DATA_DIR}/place${POSTFIX}"
sed -i.bkp "s/|company|/|Company|/" "${DATA_DIR}/organisation${POSTFIX}"
sed -i.bkp "s/|university|/|University|/" "${DATA_DIR}/organisation${POSTFIX}"

# convert each date of format yyyy-mm-dd to a number of format yyyymmddd
# sed -i.bkp "s#|\([0-9][0-9][0-9][0-9]\)-\([0-9][0-9]\)-\([0-9][0-9]\)|#|\1\2\3|#g" "${DATA_DIR}/person${POSTFIX}"

# convert each datetime of format yyyy-mm-ddThh:mm:ss.mmm+0000
# to a number of format yyyymmddhhmmssmmm
# sed -i.bkp "s#|\([0-9][0-9][0-9][0-9]\)-\([0-9][0-9]\)-\([0-9][0-9]\)T\([0-9][0-9]\):\([0-9][0-9]\):\([0-9][0-9]\)\.\([0-9][0-9][0-9]\)+0000#|\1\2\3\4\5\6\7#g" ${DATA_DIR}/*${POSTFIX}

# removing .bkp files
rm ${DATA_DIR}/*.bkp

echo "preprocessing finished"

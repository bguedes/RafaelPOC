#!/bin/bash -x

FILES=./*.csv
for f in $FILES
do
  echo "Loading $f file to S3"
  # take action on each file. $f store current file name
  ./s3_upload.sh $f /
done

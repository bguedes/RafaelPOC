#!/bin/bash -x

#S3 parameters
S3KEY="XXXXXXXXXXX"
S3SECRET="XXXXXXXXXXXXXXXX"
S3BUCKET="datastax-poc-rafael-data"
S3STORAGETYPE="STANDARD" #REDUCED_REDUNDANCY or STANDARD etc.
AWSREGION="s3-eu-west-1"

function putS3
{

  file_path=$1
  aws_path=$2
  bucket="${S3BUCKET}"
  date=$(date -R)
  acl="x-amz-acl:private"
  content_type="application/x-compressed-tar"
  storage_type="x-amz-storage-class:${S3STORAGETYPE}"
  string="PUT\n\n$content_type\n$date\n$acl\n$storage_type\n/$bucket$aws_path${file_path##/*/}"
  signature=$(echo -en "${string}" | openssl sha1 -hmac -binary | base64)
  curl -s --retry 3 --retry-delay 10 -X PUT -T "$file_path" \
       -H "Host: $bucket.${AWSREGION}.amazonaws.com" \
       -H "Date: $date" \
       -H "Content-Type: $content_type" \
       -H "$storage_type" \
       -H "$acl" \
       "https://$bucket.${AWSREGION}.amazonaws.com$aws_path${file_path##/*/}"
}

function usage
{
  echo "Usage: $0 <absolutepath_to_file> <s3_folder_path>"
  echo "$0 '/tmp/storage_backup/storage-backup_07192017_112945.tar.gz' '/'"
}

#validate positional parameters are present
if [ $# -ne 2 ]; then
  usage
  echo "Exiting .."
  exit 2
fi

putS3 $1 $2

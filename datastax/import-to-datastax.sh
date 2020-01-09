#!/bin/bash

export DSBULK=/home/avi/ldbc/dsbulk-1.4.0/bin
export DATA_DIR=/home/avi/ldbc/ldbc_snb_datagen/social_network
export POSTFIX=_0_0.csv

$DSBULK/dsbulk load -url "${DATA_DIR}/comment${POSTFIX}" -k ldbc -t comment -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/forum${POSTFIX}" -k ldbc -t forum -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/person${POSTFIX}" -k ldbc -t person -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/post${POSTFIX}" -k ldbc -t post -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/tagclass${POSTFIX}" -k ldbc -t tagClass -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/tag${POSTFIX}" -k ldbc -t tag -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/organisation${POSTFIX}" -k ldbc -t organisation -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/place${POSTFIX}" -k ldbc -t place -h '127.0.0.1' -header true --connector.csv.delimiter '|'

$DSBULK/dsbulk load -url "${DATA_DIR}/person_knows_person${POSTFIX}" -k ldbc -t person__knows__person -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/comment_hasCreator_person${POSTFIX}" -k ldbc -t comment__hasCreator__person -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/comment_isLocatedIn_place${POSTFIX}" -k ldbc -t comment__isLocatedIn__place -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/comment_replyOf_comment${POSTFIX}" -k ldbc -t comment__replyOf__comment -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/comment_replyOf_post${POSTFIX}" -k ldbc -t comment__replyOf__post -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/forum_containerOf_post${POSTFIX}" -k ldbc -t forum__containerOf__post -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/forum_hasMember_person${POSTFIX}" -k ldbc -t forum__hasMember__person -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/forum_hasModerator_person${POSTFIX}" -k ldbc -t forum__hasModerator__person -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/forum_hasTag_tag${POSTFIX}" -k ldbc -t forum__hasTag__tag -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/person_hasInterest_tag${POSTFIX}" -k ldbc -t person__hasInterest__tag -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/person_isLocatedIn_place${POSTFIX}" -k ldbc -t person__isLocatedIn__place -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/person_likes_comment${POSTFIX}" -k ldbc -t person__likes__comment -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/person_likes_post${POSTFIX}" -k ldbc -t person__likes__post -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/place_isPartOf_place${POSTFIX}" -k ldbc -t place__isPartOf__place -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/post_hasCreator_person${POSTFIX}" -k ldbc -t post__hasCreator__person -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/comment_hasTag_tag${POSTFIX}" -k ldbc -t comment__hasTag__tag -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/post_hasTag_tag${POSTFIX}" -k ldbc -t post__hasTag__tag -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/post_isLocatedIn_place${POSTFIX}" -k ldbc -t post__isLocatedIn__place -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/tagclass_isSubclassOf_tagclass${POSTFIX}" -k ldbc -t tagClass__isSubclassOf__tagClass -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/tag_hasType_tagclass${POSTFIX}" -k ldbc -t tag__hasType__tagClass -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/person_studyAt_organisation${POSTFIX}" -k ldbc -t person__studyAt__organisation -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/person_workAt_organisation${POSTFIX}" -k ldbc -t person__workAt__organisation -h '127.0.0.1' -header true --connector.csv.delimiter '|'
$DSBULK/dsbulk load -url "${DATA_DIR}/organisation_isLocatedIn_place${POSTFIX}" -k ldbc -t organisation__isLocatedIn__place -h '127.0.0.1' -header true --connector.csv.delimiter '|'


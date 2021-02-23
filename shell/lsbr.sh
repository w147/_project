#!/bin/bash
path="D:/Gitlab/"
for proj in `ls ${path}`; do
   result=$(echo $proj | grep "_")
#   echo $result
   if [[ "$result" == "" ]]
   then
       echo ${proj}
       cd "$path${proj}"
       git br
       git fetch -np
       git sub update
   fi
done

#!/bin/bash
path="D:/Gitlab/plaso/"
cd $path
git fetch
for tag in `git tag -l`; 
do
    result=$(echo $tag | grep "4\.82")
    if [[ "$result" != "" ]]
    then
        echo "$tag current branch，dont deal"
    else
        echo "$tag"
        git tag -d "$tag"
        git push origin :refs/tags/"$tag"
    fi
done
#!/bin/bash
# 等腰三角形
#read: 读取输入, 第一个值赋给n, -p 是提示语
read -p "Please input the length: " n
for i in `seq 1 $n`
do
   for ((j=$n;j>i;j--))
   do
      #-n 不换行
      echo -n " "
   done

   for m in `seq 1 $i`
   do
     echo -n "* "
   done
   echo 
done

# 倒直角三角形
read -p "Please input the length: " len
for i in `seq 1 $len`
do
    for j in `seq $i $len`
    do
       echo -n "* "
    done
    echo
done

# 直角三角形
read -p "Please input the length: " len
for i in `seq 1 $len`
do
    for((j=1;j<=$i;j++))
    do
       echo -n "* "
    done
    echo
done

# 菱形
read -p "Please input the length: " n

for i in `seq 1 $n`
do
   for ((j=$n;j>i;j--))
   do
      echo -n " "
   done
   for m in `seq 1 $i`
   do
     echo -n "* "
   done
   echo 
done

for i in `seq 1 $n`
do
    for((j=1;j<=$i;j++))
    do
       echo -n " "
    done
    for((k=$i;k<=$len-1;k++))
    do
          echo -n "* "
    done
    echo 
done
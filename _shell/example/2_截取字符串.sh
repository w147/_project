#!/bin/bash
var="http://www.aaa.com/root/123.htm"

#1.取出www.aaa.com/root/123.htm
echo $var |awk -F '//' '{print $2}'

#2.取出123.htm
echo $var |awk -F '/' '{print $5}'

#3.取出http://www.aaa.com/root
echo $var |grep -o 'http.*root'

#4.取出http:
echo $var |awk -F '/' '{print $1}'

#5.取出http://
echo $var |grep -o 'http://'

#6.取出www.aaa.com/root/123.htm
echo $var |grep -o 'www.*htm'

#7.取出123
echo $var |grep -o '123'

#8.取出123.htm
echo $var |grep -o '123.htm'
#!/bin/bash


# awk 'BEGIN {printf("  代码      现价\n");}'

content="asd"
geta()
{
    # curl "http://hq.sinajs.cn/list=$1" 2>/dev/null |iconv -t utf-8 -f gb2312 -c |sed 's/var.*=\"//g' | awk -F ',' '{printf("%s %7.2f %6.2f%s %7.2f %7.2f %7.2f\n",$1,$4,($4-$3)*100/$2,"%",$2,$5,$6)}'
    curl "http://hq.sinajs.cn/list=$1" 2>/dev/null |iconv -t utf-8 -f gb2312 -c |sed 's/var.*=\"//g' | awk -F ',' '{printf("%s %7.2f\n",$1,$4)}' | echo > $content
    echo $content
}

geta sh600276

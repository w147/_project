#-*-coding:utf-8-*-

import requests
import re

headers_str = '''Host: www.sse.com.cn
User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0
Referer: http://www.sse.com.cn/assortment/
'''
headers_list = re.findall(r'(.*?):(.*)', headers_str)
headers = {}
for item in headers_list:
    headers[item[0]] = item[1]
print(headers)
url = 'http://www.sse.com.cn/js/common/ssesuggestdata.js'
js = requests.get(url=url, headers=headers)
list = re.findall(r'val:\"(.*?)\".*?val2:\"(.*?)\".*?val3:\"(.*?)\"', js.text, re.S)
for item in list:
    print(item)
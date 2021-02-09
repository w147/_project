import requests

url = 'http://hq.sinajs.cn/list=sh600276'        # 股票代码必须为小写字母
res = requests.get(url).text
print(res)      # 此处输出与浏览器页面获取的数据一致
 
data = res[14:-3].replace('="',',').split(',')      # 删除、替换无关字符
print(data)
for datum in data:      # data数据类型为list，遍历可获得分项数据，详情见本篇截图
    print(datum)        # 注意：获取的datum数据类型为string
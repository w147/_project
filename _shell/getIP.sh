#ip=`curl ip.sb`
ip=`ifconfig | grep 192.`
hostname=`hostname`
head='Content-Type: application/json'
data='{"msgtype": "text", "text": {"content": "'$hostname' '$ip'"}}'
key='4ef1a931-0267-40c3-86a3-1035ac25466f'
url='https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key='$key
curl -H "$head" -d "$data" $url
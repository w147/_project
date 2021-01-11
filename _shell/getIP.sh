ip1=`curl ip.sb`
ip2=$(ip addr |grep inet |grep -v inet6 |grep enp2s0|awk '{print $2}' |awk -F "/" '{print $1}')
hostname=`hostname`
head='Content-Type: application/json'
data='{"msgtype": "text", "text": {"content": "'$hostname' '$ip1' '$ip2'"}}'
key='4ef1a931-0267-40c3-86a3-1035ac25466f'
url='https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key='$key
curl -H "$head" -d "$data" $url
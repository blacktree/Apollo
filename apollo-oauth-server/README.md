http://localhost:8888/oauth/authorize?client_id=archtype&response_type=code&redirect_uri=http://www.baidu.com

curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'grant_type=authorization_code&code=Li4NZo&redirect_uri=http://www.baidu.com' "http://archtype:archtype@localhost:8888/oauth/token"
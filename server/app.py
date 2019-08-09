from flask import Flask
from flask import request
from urllib.parse import quote as encode
import requests

import json

app = Flask(__name__)


@app.route('/base')
def index():
    query = request.args['query']
    display=request.args['display']
    client_id = "w3Sm4xnRiqhOIpNHgOY0"
    client_secret = "m4NT9Ij63u"
    # encText = encode(query)
    query = encode(query)
    url = "https://openapi.naver.com/v1/search/movie?query="+query+"&display="+display
    headers = {
        'X-Naver-Client-Id': client_id,
        'X-Naver-Client-Secret': client_secret
    }
    res = requests.get(url, headers=headers)
    print(res.status_code)
    if(res.status_code == 200):
        response_body = res.json()
        print(response_body)
    else:
        return str(res)
    return json.dumps(response_body)

@app.route('/category')
def category():
    query = request.args['query']
    category=request.args['category']
    client_id = "w3Sm4xnRiqhOIpNHgOY0"
    client_secret = "m4NT9Ij63u"
    # encText = encode(query)
    query = encode(query)
    url = "https://openapi.naver.com/v1/search/movie?query="+query+"&genre="+category
    headers = {
        'X-Naver-Client-Id': client_id,
        'X-Naver-Client-Secret': client_secret
    }
    res = requests.get(url, headers=headers)
    print(res.status_code)
    if(res.status_code == 200):
        response_body = res.json()
        print(response_body)
    else:
        return str(res)
    return json.dumps(response_body)

@app.route('/year')
def year():
    query = request.args['query']
    category=request.args['category']
    yearfrom=request.args['yearfrom']
    yearto=request.args['yearto']
    client_id = "w3Sm4xnRiqhOIpNHgOY0"
    client_secret = "m4NT9Ij63u"
    # encText = encode(query)
    query = encode(query)
    url = "https://openapi.naver.com/v1/search/movie?query="+query+"&genre="+category+"&yearfrom="+yearfrom+"&yearto="+yearto
    headers = {
        'X-Naver-Client-Id': client_id,
        'X-Naver-Client-Secret': client_secret
    }
    res = requests.get(url, headers=headers)
    print(res.status_code)
    if(res.status_code == 200):
        response_body = res.json()
        print(response_body)
    else:
        return str(res)
    return json.dumps(response_body)    


if __name__ == '__main__':
    app.run(port=5000, debug=True)
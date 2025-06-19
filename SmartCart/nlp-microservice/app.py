from flask import Flask, request, jsonify
from flask_cors import CORS
import re

app = Flask(__name__)
CORS(app)

@app.route('/parse', methods=['POST'])
def parse():
    text = request.json['text'].lower()
    matches = re.findall(r'(\d+)\s+(\w+)', text)
    result = {}
    for qty, item in matches:
        result[item] = result.get(item, 0) + int(qty)
    return jsonify(result)

if __name__ == '__main__':
    app.run(port=5000)
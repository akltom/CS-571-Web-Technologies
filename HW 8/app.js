var http = require('http');
var fs = require('fs');
var request = require('request');
var url = require('url');
var express = require('express');
var app = express();
var Client = require('node-rest-client').Client;
var xml2js = require('xml2js');
 
var compression = require('compression');
const https = require('https');


var client = new Client();

app.use(express.static('./'));

app.all('*', function(req, res, next) {   // means regular call of server
    res.header("Access-Control-Allow-Origin", "*"); 
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, datatype");
    res.header("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
    res.header("X-Powered-By",' 3.2.1')
    res.header("Content-Type", "application/json;charset=utf-8");
    next();
});


function getData(url, res) {
    request(url,
	    function(error, response, body) {
	        if (response.statusCode === 200 && !error) {
				let test = JSON.parse(body);
				if (JSON.stringify(test) == "{}") {
					res.send({status:"error"});
				}
				else if (test["Error Message"]) {
					res.send({status:"error"});
				}
				else {
					res.send(body);
				}
	        }
			else{
				res.send({status:"error"});
			}
	    });
}


function getAutoComplete(input, res) { // auto complete   
    url = "http://dev.markitondemand.com/MODApis/Api/v2/Lookup/json?input=" + input;
    getData(url, res);
}

app.get('/autoComp', function(req, res) { //autocomplete     http://localhost:8888/autoComp?input=msft
    var input = req.query.input;
    if (input == null) {
        res.send("empty");
    } else {
		getAutoComplete(input, res);
    }
})


function getIndicator(stockName, idx, res) { // indicator   
	url = "https://www.alphavantage.co/query?function=" + idx + "&symbol=" + stockName + "&series_type=open&interval=daily&time_period=10&apikey=IBML9TEYLJVP1G94";
	getData(url, res);
}

app.get('/indicator', function(req, res) {  //    http://localhost:8888/indicator?stockName=msft&idx=MACD
    var stockName = req.query.stockName;
    var idx = req.query.idx;
    if (stockName != null) {
        getIndicator(stockName, idx, res);
    } else {
        res.send("empty");
    }
})


function doNews(stockName, res) { // news      
	url = "https://seekingalpha.com/api/sa/combined/" + stockName + ".xml";
	//var resq = request('GET', url);
	var parser = new xml2js.Parser();
	
	
	request(url, function(err, response, body) {
		parser.parseString(body, function(err, result){
		//console.log(result['rss']['channel'][0]['item']);
			if (result) res.send(result);
			else res.send({status:"error"});
		});
	});
	
	
}

app.get('/theNew', function(req, res) { // news
    var stockName = req.query.stockName;
    if (stockName == null) {
        res.send("empty");
    } else {
		doNews(stockName, res);
    }
})





app.get('/jsonfile', function (req, res) {   //http://localhost:8888/jsonFile?text=aapl
	var symbol = req.query.text;
	var getsymbol = url.parse(req.url, true);
  	client.get("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="+symbol+"&apikey=IBML9TEYLJVP1G94", function (data, response) {
	
	res.send(data);
	  
  })
 
})


function seeStockData(stockName, res) { 
	var url = 'https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&outputsize=full&symbol='+stockName+'&apikey=IBML9TEYLJVP1G94';
	getData(url, res);
};

app.get('/getStockData', function(req, res) {  //    http://localhost:8888/?stockName=msft
    var stockName = req.query.stockName;  
    if (stockName == null) {
        res.send("empty");
    } else {
		seeStockData(stockName, res);
    }
})

app.get('/start', function(req, res) { //     http://localhost:8888/start?stockName=msft
    var stockName = req.query.stockName;
    if (stockName == null) {
        res.send("empty");
    } else {
		res.send(stockName);
    }
})

app.use(compression())

app.listen( process.env.PORT||3000, function(){
	console.log("launching");
})




















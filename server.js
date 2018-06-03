
var express = require('express');
var assert = require('assert');
var MongoClient = require('mongodb').MongoClient;
var ObjectId = require('mongodb').ObjectID;
var bodyParser = require('body-parser');
var path = require('path');
var app = express();

console.log("Hello from node")

// we need to tell express to use a body parser to read data for POST< UPDATE and DELETE method

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended : true}));

// Database Connection Information
var db;
var mongodbURL;

//Port number that we are gonna listning on
//Rather than hardcore the port number we can implement a method to get argument from command line and set the port numbers dynamically..
//This will be important if you trying to run multiple server in single machine
var program_name = process.argv[0];
var script_path = process.argv[1];
var port_string = process.argv[2];
var env_value = process.argv[3];

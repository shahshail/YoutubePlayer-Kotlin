
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

if(env_value === "undefined"){
    console.log("env_value is not set..");
    return -1;
} else {
    switch(env_value){
        case "DEV" : 
            mongodbURL = "mongodb://localhost:27017/YoutubePlayerBackend";
            console.log("environment = " + mongodbURL);
            break;

        default:
            console.log("env_value is not valid: " + env_value);
            return -1;

    }
}

//Check if port is 9898(Default) if not then ask user to change port forwarding on router admin.
var port_number = "9898";// Default port number
if(typeof port_string != "undefined" && port_string.length> 0){
    if(port_string !== "9898"){
        console.log("The default Listining port is 9898. if you want to use custome port then change your routers port forwarding settings.");
    }
    port_number = port_string; 
}



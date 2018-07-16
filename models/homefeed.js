const Joi = require('joi');
const mongoose = require('mongoose');
const {userSchema} = require('./user');

//Channel Schema
const ChannelSchema = new mongoose.Schema({
    name: {
        type : String,
        required : true,
        minlength : 3,
        maxlength : 500
    },
    profileImageUrl: {
        type : String,
        required : true,
        minlength : 3,
        maxlength : 500
    },
    numberOfSubscribers : {
        type : Number,
        required : true
    }
},{_id : false});

//Videos Schema
const Videos = new mongoose.Schema({
    id : {
        type : Number,
        minlength : 1,
        maxlength : 100000,
        required : true
    },
    name: {
        type : String,
        required : true,
        minlength : 3,
        maxlength : 500
    },
    link: {
        type : String,
        required : true,
        minlength : 3,
        maxlength : 500
    },
    imageUrl: {
        type : String,
        required : false,
        minlength : 3,
        maxlength : 500
    },
    numberOfSubscriber : {
        type : Number,
        minlength : 1,
        maxlength : 100000,
        required : true
    },
    channel : {
        type : ChannelSchema,
        required: true
    }

},{_id : false})




const HomeFeed = mongoose.model('HomeFeed', new mongoose.Schema({
    user : {
        type : userSchema,
        required : true
    },
    videos : [{
        type : Videos,
        required : true
    }]
}));

function validateHomeFeed(homeFeed) {
  const schema = {
    user: Joi.required(),
    videos : Joi.required()
  };

  return Joi.validate(homeFeed, schema);
}

exports.HomeFeed = HomeFeed; 
exports.validateHomeFeed = validateHomeFeed;
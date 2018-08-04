const mongoose = require('mongoose')
const Joi = require('joi');

const User =  mongoose.model('User', new mongoose.Schema({
    id :{
        type: Number,
        required : true,
        minlength : 1,
        maxlength : 100
    },
    name : {
      type : String,
      required: true,
      minlength: 5,
      maxlength: 50
    },
    username : {
        type : String,
        required: true,
        minlength: 5,
        maxlength: 50
      }
  }));

  function validateUser(genre) {
    const schema = {
      name: Joi.string().min(3).required(),
      id: Joi.Number().min(1).required(),
      username: Joi.string().min(3).required()
    };
  
    return Joi.validate(genre, schema);
  }
  exports.User = User
  exports.validateUser = validateUser
const mongoose = require('mongoose')
const express = require('express');
const router = express.Router();
const {User, validateUser} = require('../models/user')



router.get('/', async (req, res) => {
  const users = await User.find();
  res.send(users);
});

router.post('/', async (req, res) => {
  const { error } = validateUser(req.body); 
  if (error) return res.status(400).send(error.details[0].message);

  let user = new User({id: req.body.id,name: req.body.name, username: req.body.username});
  user = await user.save();
  res.send(user);
});

router.put('/:id',async (req, res) => {
  const { error } = validateUser(req.body); 
  if (error) return res.status(400).send(error.details[0].message);

 const user = await User.findByIdAndUpdate(req.param.id, { id: req.body.id,name: req.body.name, username: req.body.username},{
    new : true
  });

  if (!user) return res.status(404).send('The user with the given ID was not found.');
  res.send(user);
});

router.delete('/:id',async (req, res) => {
 const user =  await User.findByIdAndRemove(req.param.id);
  if (!user) return res.status(404).send('The user with the given ID was not found.');
  res.send(user);
});

router.get('/:id', async (req, res) => {

 const user = await User.findById(req.param.id);
  if (!user) return res.status(404).send('The genre with the given ID was not found.');
  res.send(user);
});


module.exports = router;
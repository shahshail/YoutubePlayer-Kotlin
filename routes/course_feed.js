const mongoose = require('mongoose')
const express = require('express');
const router = express.Router();
const {HomeFeed, validateHomeFeed} = require('../models/course_feed')



router.get('/', async (req, res) => {
  const homefeed = await HomeFeed.find();
  res.send(homefeed);
});

router.post('/', async (req, res) => {
  const { error } = validateHomeFeed(req.body); 
  if (error) return res.status(400).send(error.details[0].message);
  const user  = {id : req.body.user.id , name: req.body.user.name, username : req.body.user.username} 
  var videoArray = []
  for (var videos in req.body.videos){
    if (req.body.videos.hasOwnProperty(videos)) {
         videoArray[videos] = req.body.videos[videos];
      }
  }
   let homefeed = new HomeFeed({user : user , videos : videoArray});
   homefeed = await homefeed.save();
   res.send(homefeed);
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

router.get('/find', async (req, res) => {

  console.log(req.query)
  const homefeed = await HomeFeed.findOne(req.query);
   if (!homefeed) return res.status(404).send('The user with the given username was not found.');
   res.send(homefeed);
});


module.exports = router;
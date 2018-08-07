const mongoose = require('mongoose')
const startupDebugger = require('debug')('app:staruup')
const cbDebugger = require('debug')('app:db')
//const config = require('config');
const compression = require('compression')
const helmet = require('helmet')
const morgan = require('morgan');
 const users = require('./routes/users');
 const homefeed = require('./routes/home_feed');
const express = require('express');
const app = express();

 app.set('view engine','pug');
 app.set('views','./views'); // default location where all the views are stored

mongoose.connect('mongodb://localhost/kotlinYoutubePlayer')
.then(() => console.log('connected to MongoDB...'))
.catch(err => console.error('Could not connect to MongoDB...'))


app.use(helmet())
app.use(express.json());
 app.use('/api/users', users);
 app.use('/api/home_feed', homefeed);
// app.use('/api/movies', movies);

app.get('/',(req, res) => {
    res.render('index', {title : 'KotlinYoutubePlayernode', message: ' '})
})


const port = process.env.PORT || 3000;
app.listen(port, () => console.log(`Listening on port ${port}...`));
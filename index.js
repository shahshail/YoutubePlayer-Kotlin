const mongoose = require('mongoose')
const startupDebugger = require('debug')('app:staruup')
const cbDebugger = require('debug')('app:db')
//const config = require('config');
const helmet = require('helmet')
const morgan = require('morgan');
 const users = require('./routes/users');
// const customers = require('./routes/customers');
 const homefeed = require('./routes/home_feed');
// const customer = require('./routes/customers'); 
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
    res.render('index', {title : 'KotlinYoutubePlayernode', message: 'Hello world!'})
})

if(app.get('env') == 'development')// To see the current working environment
{
    app.use(morgan('tiny'));
    console.log(`Current environment is: ${app.get('env')}`)
    console.log("Morgan Enabled...")
    startupDebugger('Startup Debugger...')
}

const port = process.env.PORT || 9898;
app.listen(port, () => console.log(`Listening on port ${port}...`));
var express = require('express');
var spark = require('spark');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index.ejs');
});

router.post('/changeColor', login, getDevice, getColor, changeColor);

var tempToken = "4e04512eacbe6bd53e8b65a0a1bcb37a1ccdf387";
var deviceId = "54ff71066678574925340267";

function login(req, res, next) {
  spark.login({accessToken: tempToken}, function(err, body) {
    if (err) {
      console.log("Error logging in: " + err);
      return res.status(401).send('Could Not Log In');
    }
    else { return next(); }
  });
}

function getDevice(req, res, next) {
  spark.getDevice(deviceId, function(err, device) {
    if (err) {
      console.log("Error Retrieving Device: " + err);
      return res.status(500).send('Could Not Find Device');
    }
    else {
      console.log('Device name: ' + device.name);
      req.device = device;
      return next();
    }
  });
}

function getColor(req, res, next) {
    var newColor = req.body.color;
    if (!newColor || newColor.length !== 9) {
      return res.status(400).send('Invalid Color');
    }
    else {
      req.color = newColor;
      return next();
    }
}

function changeColor(req, res, next) {
  var newColor = req.color;
  console.log("New Color: " + newColor);
  req.device.callFunction('color', newColor, function(err, data) {
    if (err) {
      console.log('An error occurred:', err);
      return res.status(500).send('Error Changing Color: ' + err);
    } else {
      console.log('Function called succesfully:', data);
      return res.status(200).send('Color Change Successful');
    }
  });
}


module.exports = router;

var request = require('request');

var spark = require('spark');

var tempToken = "4e04512eacbe6bd53e8b65a0a1bcb37a1ccdf387";
var deviceId = "54ff71066678574925340267";
var newColor = "255000077";

spark.login({accessToken: tempToken});

spark.getDevice(deviceId, function(err, device) {
  console.log('Device name: ' + device.name);
  changeColor(device, newColor);
});

function changeColor(device, newColor) {
  device.callFunction('color', newColor, function(err, data) {
    if (err) {
      console.log('An error occurred:', err);
    } else {
      console.log('Function called succesfully:', data);
    }
  });
}

//This client is to test the code found at https://blog.csainty.com/2012/01/creating-desktop-apps-with-nodejs.html
//it will be adapted to make a client for the PI
var nwebkit = require('node-webkit');

nwebkit.init({
    'url' : 'index.html',
    'width' : 800,
    'height' : 600
});
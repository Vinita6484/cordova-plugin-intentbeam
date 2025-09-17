var exec = require('cordova/exec');

exports.launchIntent = function (url, success, error) {
  exec(success, error, 'IntentBeam', 'launchIntent', [url]);
};

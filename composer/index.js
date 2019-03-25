var grid = require('selenium-grid-status');

grid.available({
    host: window.location.hostname,
    port: window.location.port
}, function(err, status) {
    status.forEach(function (service) {
        var config = service.configs[0];
        if (service.browser.length == 0) {
            document.querySelector('tr[data-selenium-id="'+config.host.trim()+':'+config.port.trim()+'"]').className = 'success';
        }
    });
});

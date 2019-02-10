# selenium-3-setup

Maven project to start tests with Selenium Grid.

### Option 1 : Download and start the selenium-server-standalone. 

#### Downloads

* [Download selenium-server-standalone](https://www.seleniumhq.org/download/)
* [Download geckodriver](https://github.com/mozilla/geckodriver/releases)
* [Download chromedriver](http://chromedriver.chromium.org/downloads)

#### Start Selenium Grid

hub :
```
java -jar selenium-server-standalone-3.141.59.jar -role hub
```
node :
```
java -jar selenium-server-standalone-3.141.59.jar -role node -hub http://localhost:4444/grid/register
```

### Option 2 : Use docker-selenium to set up the testing environment

[docker-selenium project on github](https://github.com/SeleniumHQ/docker-selenium)

* [Get Docker CE for Ubuntu](https://docs.docker.com/install/linux/docker-ce/ubuntu/)
  * To run docker as a normal user, run this command : `sudo usermod -a -G docker $USER`.
* [Install Docker Compose](https://docs.docker.com/compose/install/)
* [Create the `docker-compose.yaml`](https://github.com/SeleniumHQ/docker-selenium#via-docker-compose)
* To execute this docker-compose yml file use `docker-compose -f docker-composer.yaml up`.
  * Add the `-d` flag at the end for detached execution.
  * To stop the grid and cleanup the created containers, run `docker-compose down`.
* Remote address for the client : http://ipserver:4444/wd/hub (e.g.: http://localhost:4444/wd/hub)
* http://localhost:4444/grid/console 
* Scaling up the grid on-demand : `docker-compose up -d --scale firefox=2 --scale chrome=2`.
* Debugging : to debug you have to use node-chrome-debug and node-firefox-debug (see the composer file). 
In the composer file the node-chrome-debug has the port 6666 and the node-firefox-debug has the port 6667. 
The purpose of these custom ports is to find the debug nodes easily.
If you want to run the tests in a debug node use the ip and the port of the node (e.g. http://192.168.48.6:6666/wd/hub) in the 
TestNG XML. You can get it in the grid console.
To connect to the node and watch the test, use your favorite remote desktop client and use the address localhost:5901 for the Chrome node or localhost:5902 for the Firefox node (the password is secret). You can configure the ports in the composer file.

### Launch the tests

`mvn test`

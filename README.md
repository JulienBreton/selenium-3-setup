# selenium-3-setup

Maven project to start tests with Selenium Grid.

### Set up the testing environment

#### Option 1 : Use docker-selenium to set up the testing environment

[docker-selenium project on github](https://github.com/SeleniumHQ/docker-selenium)

* [Get Docker CE for Ubuntu](https://docs.docker.com/install/linux/docker-ce/ubuntu/)
  * To run docker as a normal user, run this command : `sudo usermod -a -G docker $USER` and reboot.
* [Install Docker Compose](https://docs.docker.com/compose/install/)
* To execute the docker-compose yml file use `docker-compose -f composer/docker-composer.yaml up`.
  * Add the `-d` flag at the end for detached execution.
  * To stop the grid and cleanup the created containers, run `docker-compose -f composer/docker-composer.yam down`.
* Scaling up the grid on-demand : `docker-compose -f composer/docker-composer.yam up -d --scale firefox=2 --scale chrome=2`.  
* Remote address for the client : http://ipserver:4444/wd/hub (e.g.: http://localhost:4444/wd/hub)
* http://localhost:4444/grid/console 
* Debugging : to debug your tests you have to use node-chrome-debug and node-firefox-debug (see the composer file). 
Go to http://localhost:4445/ to list the vnc access. The password is "sercret".
* You can share files between the host and the containers. It is usefull to upload files in a Web app. See /home/julien/test:/home/selenium/upload in the composer/docker-composer.yaml and adapt to your case.

#### Option 2 : Download and start the selenium-server-standalone. 

If you can't use docker, you can use this solution.

##### Downloads

* [Download selenium-server-standalone](https://www.seleniumhq.org/download/)
* [Download geckodriver](https://github.com/mozilla/geckodriver/releases)
* [Download chromedriver](http://chromedriver.chromium.org/downloads)

##### Start Selenium Grid

hub :
```
java -jar selenium-server-standalone-3.141.59.jar -role hub
```
node :
```
java -jar selenium-server-standalone-3.141.59.jar -role node -hub http://localhost:4444/grid/register
```

### Launch the tests

`mvn test`

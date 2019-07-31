# selenium-3-setup

Maven project to start tests with Selenium Grid.

### Set up the testing environment on Ubuntu

#### Option 1 : Use docker-selenium to set up the testing environment

[docker-selenium project on github](https://github.com/SeleniumHQ/docker-selenium)

* [Get Docker CE for Ubuntu](https://docs.docker.com/install/linux/docker-ce/ubuntu/)
  * To run docker as a normal user, run this command : `sudo usermod -a -G docker $USER` and reboot your computer.
* [Install Docker Compose](https://docs.docker.com/compose/install/)
* To execute the docker-compose yml file use `docker-compose -f composer/docker-compose.yaml up`.
  * Add the `-d` flag at the end for detached execution.
  * To stop the grid and cleanup the created containers, run `docker-compose -f composer/docker-compose.yaml down`.
* Scaling up the grid on-demand : `docker-compose -f composer/docker-compose.yaml up -d --scale firefox=2 --scale chrome=2`.  
* Remote address for the client : http://ipserver:4444/wd/hub (e.g.: http://localhost:4444/wd/hub)
* http://localhost:4444/grid/console 
* Debugging : to debug your tests you have to use node-chrome-debug and node-firefox-debug (see the composer file). 
Go to http://localhost:4445/ to list the vnc access. The password is "sercret".
* You can share files between the host and the containers. It is usefull to upload files in a Web app. See /home/julien/test:/home/selenium/upload in the composer/docker-composer.yaml and adapt to your case.
* You can rename the folder composer in seleniumv3 (for example) if you want to identify easily the containers in the list displayed by http://localhost:4445/
* If you want to set the timeouts, add GRID_TIMEOUT and GRID_BROWSER_TIMEOUT to the hub in the docker-compose.yaml. [To learn more about the timeouts, see also the Grid2 wiki.](https://github.com/SeleniumHQ/selenium/wiki/Grid2#configuring-timeouts-version-221-required) 
```
    hub:
        image: selenium/hub:3.141.59
        restart: on-failure
        ports:
            - "4444:4444"
        environment:
            - GRID_TIMEOUT=300
            - GRID_BROWSER_TIMEOUT=300
```

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

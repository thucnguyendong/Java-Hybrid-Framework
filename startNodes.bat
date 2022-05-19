cd ..
cd lib
set ProjectPath = %~dp0
java -jar -Dwebdriver.chrome.driver = "%ProjectPath%\driverBrowsers\chromedriver.exe"
-Dwebdriver.gecko.driver = "%ProjectPath%\driverBrowsers\geckodriver.exe"
selenium-server-standalone-3.141.59.jar -role webdriver -hub
http://HUB_IP:HUB_PORT//grid//register -port NODE_PORT
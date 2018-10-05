set projectLocation = /Users/prashantmundepi/Documents/Selenium_projects/DataDrivenFramework
cd %projectLocation%

set classpath =%projectLocation%\bin;%projectLocation%\Jars\*
java org.testng.TestNG %projectLocation%\testng.xml

pause
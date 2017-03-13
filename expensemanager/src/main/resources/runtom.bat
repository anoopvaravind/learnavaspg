del /f F:\servers\apache-tomcat-7.0.73\apache-tomcat-7.0.73\webapps\expmanager.war
rd /s /q F:\servers\apache-tomcat-7.0.73\apache-tomcat-7.0.73\webapps\expmanager
copy F:\projects\Spring\gitLAB\learnavaspg\expensemanager\target\expmanager.war F:\servers\apache-tomcat-7.0.73\apache-tomcat-7.0.73\webapps\expmanager.war
cd F:\servers\apache-tomcat-7.0.73\apache-tomcat-7.0.73\bin
catalina.bat run


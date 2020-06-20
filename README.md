# Stuff

####This is a simple app for saving data about your stuff. This app fully base on Java technologies such as Hibernate, Spring MVC, Spring Security, JSP etc.

## Pre requirements

Project base on Java technologies, so it requires installing JDK 8 
or higher along with Maven package manager. App uses next SQL database schema.
```sql
    CREATE TABLE IF NOT EXISTS APP_USER
    (
        ID       SERIAL,
        USERNAME VARCHAR(256) NOT NULL,
        EMAIL    VARCHAR(256) NOT NULL,
        PSWD     VARCHAR(256) NOT NULL
    );
    
    CREATE TABLE IF NOT EXISTS STUFF
    (
        ID              SERIAL,
        NAME            VARCHAR(255)   NOT NULL,
        BRAND           VARCHAR(255)   NOT NULL,
        DESCRIPTION     VARCHAR(1024)  NOT NULL,
        COST            DECIMAL(19, 2) NOT NULL,
        PICTURE         LONGBLOB       NOT NULL,
        COMMISSION_DATE DATE           NOT NULL,
        USER_ID         INT REFERENCES APP_USER (ID)
    );
    
    CREATE TABLE IF NOT EXISTS RESET_PASSWORD
    (
        ID      VARCHAR(36) PRIMARY KEY,
        USER_ID INT REFERENCES APP_USER (ID)
    );      
```

## Installation

1. Download and unzip or clone git repository
1. Add properties files to "stuff-spring-mvc-ui/src/main/resources" folder
    - app.properties | Please look at example below.
    ```properties
    app.protocol = http://
    app.domain = stuff.vakar.space
    ```
    - email.properties | Please look at example below.
    ```properties
    # SMTP connection settings
    mail.smtp.auth=true
    mail.smtp.starttls.enable=true
    mail.smtp.host=your_email_provider_host
    mail.smtp.port=25
    mail.smtp.ssl.trust=your_email_provider_host

    # User Credentials
    from.email=your_email
    username=your_email
    password=your_email_password
    ```
    - googleReCaptcha.properties | Please look at example below.
    ```properties
    google.recaptcha.key.site=your_google_recaptcha_key_site
    google.recaptcha.key.secret=your_google_recaptcha_key_secret
    ```
   
    - hibernate.properties | Please look at example below.   
   ```properties
    #Hibernate dialect
    hibernate.dialect = org.hibernate.dialect.MariaDB103Dialect
    #JDBC properties
    hibernate.connection.driver_class = org.mariadb.jdbc.Driver
    hibernate.connection.url = jdbc:mariadb://127.0.0.1:3306/stuff
    hibernate.connection.username = your_username
    hibernate.connection.password = your_password
    ```
    
1. For installing project you need to run next command inside project folder.
    ```shell script
    mvn install
    ```

## Deployment

1. For deploying on tomcat server, project provides tomcat7 plugin.
You must allow your tomcat server to handle scripts inside tomcat-users.xml file.
    ```xml
    <tomcat-users>
       <role rolename="manager-script"/>
       <user username="username" password="password" roles="manager-script"/>
    </tomcat-users>
    ```     

1. Maven uses the special file for hiding server credentials (settings.xml). 
You can find it inside m2 maven directory. 
If it doesn't exist there, you should create it manually.
Please look at the file content example below.

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    
    <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
    
    <servers>
       <server>
       <id>tomcatRemote</id>
       <username>username</username>
       <password>password</password>
       </server>
    </servers>
    </settings>

    ```
 
 1. Specify the maven tomcat7 plugin with server id and host url. 
 Configuration pom.xml file you can find inside "stuff-spring-mvc-ui" folder.
    ```xml
    <plugin>
        <groupId>org.apache.tomcat.maven</groupId>
        <artifactId>tomcat7-maven-plugin</artifactId>
        <version>2.2</version>
        <configuration>
            <path>/</path>
            <server>tomcatRemote</server><!-- Server id -->
            <url>http://127.0.0.1:8080/manager/text</url><!-- Server host -->
        </configuration>
    </plugin>
    ```
    
 1. Now you can run tomcat7 plugin with the next command
 inside 
    ```shell script
    mvn tomcat7:deploy
    ```
    
## License

[MIT](https://choosealicense.com/licenses/mit/)
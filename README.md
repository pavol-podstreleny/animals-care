# Animals Care

Software as a Service [application](http://google.sk) that provides a scheduling interface for a small business that takes care of animals.

Following technologies were used to develop the REST API:
- Spring Boot 
- Spring Web
- Spring data JPA | [Hibernate](https://hibernate.org/)
- [MySQL](https://www.mysql.com/)
- [Guava](https://github.com/google/guava)
- [JUnit](https://junit.org/junit5/)
- [H2](https://github.com/h2database/h2database)

## Run REST API locally
#### 1) The first step is to clone the repository
```cmd
git clone https://github.com/pavol-podstreleny/animals-care.git
```

#### 2) Move into `animals-care` folder
```cmd
cd cloud-storage
```

#### 3) Install MySQL and create schema, user
Make sure you install [MySQL](https://dev.mysql.com/downloads/) database. After installation is finished run following sql commands.
```mysql
CREATE DATABASE IF NOT EXISTS animalscare;
CREATE USER 'user'@'localhost' IDENTIFIED BY 'user';
GRANT ALL PRIVILEGES ON animalscare.* TO 'user'@'localhost'; 
FLUSH PRIVILEGES;
```

#### 4) Run maven script
Before you run the following script, you should download [JAVA JDK](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html) and set up the `JAVA_HOME` environment variable.

Windows:
```cmd
mvnw.cmd clean install
```

Linux:
```cmd
./mvnw clean install
```

#### 5) Run REST API
Windows: 
```cmd
mvnw.cmd spring-boot:run
```

Linux:
```cmd
./mvnw spring-boot:run
```

You can find all API endpoints on http://localhost:8082/swagger-ui.html


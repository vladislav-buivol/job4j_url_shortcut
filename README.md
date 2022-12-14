[![Build Status](https://app.travis-ci.com/vladislav-buivol/job4j_url_shortcut.svg?branch=master)](https://app.travis-ci.com/vladislav-buivol/job4j_url_shortcut)

#### Training project: UrlShortCut. 
The service allows, after registration, to convert URLs into code and use it to redirect to the original URL.
<em> Technologies used:</em>
<ol>
<li> Java 11</li>
<li> Spring boot 2</li>
<li> Hibernate 5 </li>
</ol>

<em>Software And Tools Required </em>
<ol>
<li> Java 11</li>
<li> PostgreSQL </li>
<li> Maven </li>
</ol>

#### Api Documentation

Api documentation: https://vladislav-buivol.github.io/job4j_url_shortcut/api 
or when project is running
````http://<app-root>/swagger-ui E.g:http://localhost:8080/swagger-ui/````


To update api send get request to 
````http://<app-root>/v3/api-docs```` and replace the values in the ````docs/api/api.json```` file  

#### Running The Project

1. Create database

````
CREATE DATABASE job4j_url_shortcut
````

2. Run project

````
mvn spring-boot:run
````

Contacts: vladislav96.b@gmail.com

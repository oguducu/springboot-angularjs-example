# DynamicUserPage : MyFin
[![Build Status](https://travis-ci.org/oguducu/springboot-angularjs-example.svg?branch=master)](https://travis-ci.org/oguducu/springboot-angularjs-example)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/f63d6a65a7ed4514a630cadf8f38e7a0)](https://www.codacy.com/app/oguducu/DynamicUserPage?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=oguducu/DynamicUserPage&amp;utm_campaign=Badge_Grade)

MyFin is a Spring Boot project. 

## Installation &nbsp;

```sh
$ bower install
$ mvn clean install -Dmaven.test.skip=true
```

## Live Demo
* [DEMO](https://dynamicuserpage.herokuapp.com/login)


## Datasource Settings

1) Inside src/main/resources/<b>application.properties</b> file there are three variables needed to be configured for db connection.
- spring.datasource.url
- spring.datasource.username
- spring.datasource.password

2) Inside src/main/java/META-INF/<b>persistence.xml</b> file there are three variables needed to be configured for DataGenerator.
- javax.persistence.jdbc.url
- javax.persistence.jdbc.user
- javax.persistence.jdbc.password

After you configured you DB, you sould run src/main/java/com/orcunguducu/dynamic/util/<b>DataGenerator.java</b>
It will create PRM data. (like Countries, cities, currencies)

## Some Features
- Customize widgets: Drag & drop widget. Positions will be saved for user
- Cached PRM structure: Parameter values can be easily managed. Inside <b>parameter</b> table, there are parameter names, inside <b>parameter_value</b> table, there are parameter values. There is a service in frontend side and backend side to get PRM's easily.
- After external API call made, result are recorded inside a table. If a user asks to get a currency which is fetch before, results are returned from DB not from external API.
- Live currencies which listed on home page are refreshes every 60 seconds automatically without refreshing the page.

## Future Development Needs
- Parameter control page for create, delete, modify

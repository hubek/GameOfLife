# GameOfLife
The game of life written as a Web application using Spring Boot, Spring MVC, Thymeleaf, Spring Data, JPA, REST, AOP, jQuery, Maven.

Application is written in Spring Boot.
You can run it by 
java -jar lifegame-0.0.1.jar

When it is running go to http://localhost:8080/

You can modify  cell's color by click on it.
When you are ready click Start button.

Actual cells structure is sending to server and after some calculation on server side new structure is coming back to the page. Ajax request is sending by jQuery. REST service is implemented using Spring MVC. I did one test for structure processor and simple persistence for storing calculation times.


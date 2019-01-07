# chat-service

How to start the chat-service application
---

1. Run `mvn clean install` to build your application
2. Start in memory db using `java -jar target/chat-service-1.0-SNAPSHOT.jar db migrate config.yml`
2. Start application with `java -jar target/chat-service-1.0-SNAPSHOT.jar server config.yml`
3. Load the application using swagger: `http://localhost:8080/api/swagger`


Details about the project
---

* I have used dropwizard for developing this REST api
* For DB, I have used in-memory database, which persists data for only the time it is running. As this is a sample application, its best we use this and we do not need to bother about setting a full fledged DB
* For hitting the api's, I have used swagger api - which makes hitting endpoints easy
* As requested, we have 3 endpoints: 
    * /POST: to create a message
    * /findById: to get a message based on Id
    * /findByName: to get unexpired messages based on a userName
    * I have also added a simple findAll endpoint, which returns all the messages
* I have added basic tests, but there is scope of many more here


Response to follow Up Questions:
---
* Given your implementation, write down a design proposal that will allow your service to scale to millions of daily users and 1000 requests per second split equally between reads and writes. Here are some things to consider:
Response: We have to structure the scalability into different layers.

        Load Balancer: 
        It is needed if you have so many requests which can not be handled by one web server. Typically 10-15k requests per second can be handled by one web server for a dynamic website, but it depends totally on complexity of website/web application. Load balancer contains multiple web servers and just forwards incoming requests to one of them to distribute.

        Web Server: 
        Tune the configuration of web server for this use case. Set number of threads, connections, network buffer size, open file descriptor etc. Different servers have different configuration files to tune the performance.

        No-SQL database: For eg. Cassandra/MongoDB.

        Each web server must serve same content, hence should talk to same database. If many web server talking to one db server, it will become bottle neck. Even if there is one web server, sometimes db server may become bottle neck. No-SQL database server have scalable architecture. 

        Caching Server:
        Reading from disk/DB is expansive. Caching server keeps cache data to the memory. If data is there in cache, then disk read is saved, if it is not(miss) then read from disk and save it in cache for next time. If you get 70-80% hit ratio then it will help in scaling.

        Separate cdn server:
        To server static content(js, css, images) setup a cdn server which is optimized to serve static content. This will reduce load from web server.

        Deploy services to cloud (AWS/MS Azure) etc

        We can also leverage GraphQL, which can be especially useful for mobile apps as we it might not be interested in GETting all the fields which the server sends and can request custom response.

* What could be the different scenarios/assumptions you might have to make in order to scale your service?

* Are there questions you would ask to product owners to help you understanding how you need to scale your service?

        Well, this is a technical discussion about which product owners might not be aware of. What I would do is, We as a Engineering team take a decision on decision and we can run through the product owners and take a feedback

* What technology choices would you pick and why?

        Answered above and in next question

* Are you keeping your current technology stack or will you make changes?

        I would keep partially similar tech stack and add new to it - as mentioned above, AWS, No-SQL, extra servers, messaging queues (Kafka/RabbitMQ)

* How would you test, monitor, measure, secure, and protect a service like this?

        Testing can be on all the env - we can have periodic automated test suites to run some functional tests and some performance tests
        Monitoring can be achieved by setting dashboard using tools like DataDog, new relic
        I would also suggest to introduce tracing (new relic provides it) so that we can be quick to identity any issues in service or clients calling them

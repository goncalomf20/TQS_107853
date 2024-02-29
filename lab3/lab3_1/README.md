## Lab_3

a) **Identify a couple of examples that use AssertJ expressive methods chaining:**
   - In test D (line 58) and test A (line 75).

b) **Identify an example in which you mock the behavior of the repository (and avoid involving a database):**
   - In test C.

c) **What is the difference between standard `@Mock` and `@MockBean`?**
   - The `@Mock` annotation has no impact on Spring Beans context, it's used for creating objects for testing and for performing unit tests. The `@MockBean` annotation is used to add mocks to the ApplicationContext and replaces (during tests) the Spring beans in the context with mocks. It is used for performing integration tests.

d) **What is the role of the file `application-integrationtest.properties`? In which conditions will it be used?**
   - As described in the note, there are tests that will run the complete spring boot application, and the file in question is used to configure the Spring settings to be ideal for integration tests. These settings will override the `application.properties` during integration tests, and that's when they are used.

e) **The sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences?**
   - Test C focuses more on testing the `EmployeeRestController` layer and how it interacts with the `EmployeeService`, while test D uses the `Employee`'s `RestController` but does not actually call the `EmployeeService`, simulating it in its entirety. In both, there is no access to the database and the general SpringBoot context is not called. In test D, there is indeed a full context call, testing interactions with databases. The HTTP layer is also tested in test D.


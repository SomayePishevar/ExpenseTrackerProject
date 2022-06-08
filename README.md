# Spring Boot Expense Tracker Application
This project is a simple Expense Tracker application which has specific operation for two role:
ADMIN,USER

ADMIN operations APIs include CRUD on Expense Category Entity.
Expense Category has a unique name and a max amount field. max amount field specify the max of money which user can spend for it. 
 
USER operation is adding each of spent expense amount by defining its date and category. 
After a user add her/his spent expense, the application check the sum of spent expense in specified category 
and compare it with the max amount of that category. if she/he has reached the max valid amount, application will alert him. 


Users should sign up in the app and login it to use the features. 

App is based on REST APIs with Spring Boot, Spring Security, JWT, Spring Data JPA, Swagger and PostgreSQL Database. 
The description of REST APIs and format of input and output are published in swagger platform. 

Note: Below data is creating automatically by running the App:
1. ADMIN account with username, password and phone number: "admin" , "adminpassword" , "+989196263580"
2. USER account with username, password and phone number: "user" , "123456" , "+989196263570"
3. Expense categories with these name and max amount specifications:
    a. "food" , "1500"
    b. "transport" , "500"
    c. "clothing" , "700"

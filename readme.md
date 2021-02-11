# Digital Ocean Banking System

About this project:

1. There is 7 tables, 3 user define functions, 3 trigger functions inside the database, together with 8 java packages and 12 test cases in the IDE. This project touches all the user stories and requirements. 

Its sql file is in resources/ sql folder, near log4j.properties The posgres RDBMS must sets the Schema this script belong to as the default schema so the code to interact with it.  

2. About the database, the 7 tables are: customer, employee, bank account, applied bank account, transaction, pending transaction and transaction name.

The id in Customer table is the account_number of a bank and a pending bank account. When a customer open a new bank account, the data they send will go to the applied_bank_account. After employee appprove, the data will move to the bank account and auto delte itself using a trigger function in the database.

The transaction_name table is there to reduce rebundancy. Their id were reference in transaction and pending transaction table as transaction type. 

Like applied bank account and bank account table, if a user transfer money to another user, the transaction will be inserted to pending transaction. When the owner approve, data will move to transaction table and auto delete itself using trigger and user define function.

The transaction table also had another insert trigger. It will auto update balance of customer bank account base on their transaction type. 

3. For the coding, the util package holds the Connection class, which use the Diver Manger to enstablish a connection. The UI package is where scanner was used. It is the presentaion layer. The dao - data access object performs interaction with the database while the service package become the bridge between those 2.  

**In the Menu interface**, I make the scanner class public static to be able to access the scanner everywhere without instatiate. There are also display and setUser abstract methods. What we see on the console was the display function of one of those class. 

**When user log in**, the user MainUi layer take the username and password from scanner, pass it to userService, and then it pass again to UserDaoImpl. UserDaoImpl then use username and passord to search from Customer and Employee table at once using the set operation. If the username and password exist, base on which table it from, a new Customer or Employee object will be instanciated, pass to the User data type in Main menu. Then if object is an instance of Customer, it will jump to the Customer Menu and immediately downcast that user data type to Customer. So we can get the customer username, birthday from the Customer Menu. The same for employee.

**When the customer open a new account**, the data received in the scanner will pass to **applied_bank_account table**, waiting for the employee to approve. Once it **approved**, the EmployeeDaoImpl will retreive it, **insert it** into **Bank Account table**. In the database, there is an **insert trigger** that will make Applied bank account table **auto delete** the row with the same id.

The same goes to **transaction and pending trasaction table** which happens when a user transfer money to other user. There is also another **insert triggers** that will auto update account balance of all party involed, either they perform deposit, withdraw, or transfer.

**About logging**, **log4j** was set to public static and used within almost all the class in Dao package. When employee choose view log option, the employee service class will use a **FileReader** class to create a new file rader object, then pass that object to **BufferReader** to instanciate a bufferReader obj. Then we print the log file line by line to the **console** using a **while loop** for **bufferedReader.readLine()**   


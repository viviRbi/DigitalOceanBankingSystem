# Digital Ocean Banking System

About this project:

1. There is 7 tables, 8 java packages and 12 test cases for register validation. This project touches all the user stories and requirements.

2. About the database, the 7 tables are: customer, employee, bank account, applied bank account, transaction, pending transaction and transaction name.

The id in Customer table is the account_number of a bank and a pending bank account. When a customer open a new bank account, the data they send will go to the applied_bank_account. After employee appprove, the data will move to the bank account and auto delte itself using a trigger function in the database.

The transaction_name table is there to reduce rebundancy. Their id were reference in transaction and pending transaction table as transaction type. 

Like applied bank account and bank account table, if a user transfer money to another user, the transaction will be inserted to pending transaction. When the owner approve, data will move to transaction table and auto delete itself using trigger and user define function.

The transaction table also had another insert trigger. It will auto update balance of customer bank account base on their transaction type. 

3. For the coding, the util package holds the Connection class, which use the Diver Manger to enstablish a connection

The UI package is where scanner was used. It is the presentaion layer. The dao - data access object performs interaction with the database while the service package become the bridge between those 2.  

in the Menu interface, I make the scanner class public static to be able to access the scanner everywhere without instatiate. I also create a display and setUser function so all the class implement this inteface will use.


README: JDBC MYSQL
=====================================

Setting up/run the Code
--------------------------------------

1. In Eclipse or IntelliJ IDEA, import the project folder.
	
2. Run the database setup script
 - You can run the SQL script using a SQL tool such as MySQL Workbench (In my case I am using MySQL Workbench)
 - SQL scripts are present in /src/main/resources directory
     - table-setup-employees.sql
     - table-setup-stored-procedures.sql  

3. Change the database connection info
	- In the source code, update the database URL, user id and password for your local environment

4. Finally, run the program in Eclipse or IntelliJ IDEA

Learned How To
--------------------------------------

1. Connect to MySQL Database with Java
2. Submit SQL statement to insert, update, delete data
3. Handle SQL parameters with Prepared Statements
4. Calls stored procedures and handle various parameters types (In, Out, InOut and Result Set)
5. Read and Write BLOB and CLOB data files
6. Configure your database connections information with properties files

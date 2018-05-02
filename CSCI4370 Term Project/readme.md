Term Project - Beginner Investor

Team Null
Hanke  Xiao --- 810624379
Yan    Lin  --- 811502937
Zhenyu Yan  --- 8106701500
Ethan  Wang --- 811995236


A web-based MySql database project enables 
* Stock Search
* Stock Comparison with analysis  
* Stock Ranking based on attributes chosen 
through querying the database.

Data Usgae:
1. Data Source: 
        * stock data with all information about prices, companies, stock metrics and etc;
2. API: 
        * IEX Trading API https://iextrading.com/developer/docs/;
3. Data Fetching:
        * Maven project is used to collect our day from IEX Trading API.
        * A list of companies listed are used in the program to get the corresponding stock data
        * A separate java project "IEX-Trading-API" is written to get the data we need from API.
4. Data Storing:
        * Data fetched are stored locally in a MySQL Database called StockDB.
        * StockDB have 4 tables:
          Stock table have company information
          curPrice table have opening price, closing price, changes, highest price of the day and lowest price of the day; 
          hisPrice table have closing price for 15 days; 
          Metrics table have finance stock information, for example marketCap, PE ratio, ROA and etc;   
        * table have closing price for 15 days; Metrics table have finance stock information, for example marketCap, PE ratio, ROA and so on. 
        * There are more than 15,000 tuples in total in StockDB.
5. Data Update:
        * A separate method called "updateSample.java" in java project "IEX-Trading-API" is written to update the real-time price data into our database,   and it can be run separatly whenever users want to get current prices.       
6. Database set-up for run:
        * refer to How to Run section step 1 - 6;


How to run:

1. Unzip the CSCI4370 Term Project

2. Import folder "IEX-Trading-API" as java project into eclipse 

3. Import folder "begInvestor" as dynamic web project into eclipse

4. Open MySQL Workbench and run the sql file ./CSCI4370 Term Project 3/finalDB.sql and names the database as StockDB (important). 
   It will generate the database schema for storing the data later. 
   The stockDB is how the database id referred in the project code.
   Wrong naming will causing JDBC connecting error.

5. Run the method of "StocksSample.java" in project "IEX-Trading-API" to fetch all stock data from API and stored in StockDB locally.

6. If needed, run the method of "updateSample.java" in project "IEX-Trading-API" to update real-time price data in StockDB.

7. Replace your username and password for MySQL server in "DatabaseAccess.java" and "DbAcessConfig.java" to make sure you can access mysql database.
   Both classes are located in "persistLayer" package.

8. Deployed the "begInvestor" project to .war file or directly run it in eclipse using Tomcat or Wildfly.




/*----- test and admin queries -----*/
use test;
select company from Stock;
SELECT * FROM hisPrice H JOIN Stock S ON S.stockID = H.stockID WHERE S.company = "Apple Inc.";
SELECT distinct S.stockID, company, description, URL, Latest, Opening, Closing, High, Low, marketCap, EPS, Beta, DivYield, Week52high, Week52low, PE_Ratio, ROA From Stock S 
JOIN curPrice C ON S.stockID = C.stockID
JOIN Metrics M On S.stockID = M.stockID
WHERE S.stockID = "aapl";
select * from Metrics;
Select * from Stock where stockID = "WMT";

/*----- get all industries -----*/
SELECT DISTINCT Industry FROM Stock ORDER BY Industry ASC;

/*----- get beta rank -----*/
SELECT S.stockID, S.company, M.Beta
FROM Stock S
JOIN Metrics M ON S.stockID = M.stockID
WHERE M.Beta > 0
ORDER BY M.Beta ASC
LIMIT 10;

SELECT S.stockID, S.company, M.Beta
FROM Stock S
JOIN Metrics M ON S.stockID = M.stockID
WHERE M.Beta > 0 
AND S.industry = "Steel"
ORDER BY M.Beta ASC
LIMIT 10;

/*----- get Price Change rank-----*/
SELECT S.stockID, S.company, M.Week52high-M.Week52low AS Price_Change
FROM Stock S
JOIN Metrics M ON S.stockID = M.stockID
WHERE S.company <> "N/A"
ORDER BY Price_Change ASC
LIMIT 10;

SELECT S.stockID, S.company, M.Week52high-M.Week52low AS Price_Change
FROM Stock S
JOIN Metrics M ON S.stockID = M.stockID
AND S.industry = "Steel"
WHERE S.company <> "N/A"
ORDER BY Price_Change ASC
LIMIT 10;

/*----- get PE Ratio rank -----*/
SELECT S.stockID, S.company, M.PE_Ratio_high
FROM Stock S
JOIN Metrics M ON S.stockID = M.stockID 
WHERE M.PE_Ratio_high > 0 
ORDER BY M.PE_Ratio_high DESC
LIMIT 10;

SELECT S.stockID, S.company, M.PE_Ratio_high
FROM Stock S
JOIN Metrics M ON S.stockID = M.stockID 
WHERE M.PE_Ratio_high > 0 
AND S.industry = "Steel"
ORDER BY M.PE_Ratio_high DESC
LIMIT 10;

/*----- get Dividend rank -----*/
SELECT S.stockID, S.company, M.DivYield
FROM Stock S
JOIN Metrics M ON S.stockID = M.stockID 
WHERE M.DivYield > 0 
ORDER BY M.DivYield DESC
LIMIT 10;

SELECT S.stockID, S.company, M.DivYield
FROM Stock S
JOIN Metrics M ON S.stockID = M.stockID 
WHERE M.DivYield > 0 
AND S.industry = "Steel"
ORDER BY M.DivYield DESC
LIMIT 10;

/*----- get EPS rank -----*/
SELECT S.stockID, S.company, M.EPS
FROM Stock S
JOIN Metrics M ON S.stockID = M.stockID 
WHERE M.EPS > 0 
ORDER BY  M.EPS DESC
LIMIT 10;

SELECT S.stockID, S.company, M.EPS
FROM Stock S
JOIN Metrics M ON S.stockID = M.stockID 
WHERE  M.EPS > 0 
AND S.industry = "Steel"
ORDER BY  M.EPS DESC
LIMIT 10;


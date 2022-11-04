USE PRS;
GO

TRUNCATE TABLE dbo.Users;
GO

BULK INSERT dbo.Users
FROM 'C:\Users\peter\Bootcamp\repos\prs-capstone\database\prs-database-customers.csv'
WITH
(
	FORMAT = 'CSV',	
	ROWTERMINATOR = '0x0a',
	--DATAFILETYPE = 'CHAR',
	FIRSTROW = 2
);
GO


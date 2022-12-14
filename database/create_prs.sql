USE MASTER;
GO

DROP DATABASE IF EXISTS PRS;
GO

CREATE DATABASE PRS;

USE PRS;
GO

CREATE TABLE Users (
	ID				INTEGER				NOT NULL IDENTITY PRIMARY KEY,
	Username		NVARCHAR(30)		NOT NULL UNIQUE,
	Password		NVARCHAR(30)		NOT NULL,
	FirstName		NVARCHAR(30)		NOT NULL,
	LastName		NVARCHAR(30)		NOT NULL,
	Phone			VARCHAR(12)			NULL,
	Email			VARCHAR(255)		NULL,
	IsReviewer		BIT					NOT NULL,
	IsAdmin			BIT					NOT NULL	
);

CREATE TABLE Vendors
(
	ID			INTEGER			NOT NULL IDENTITY PRIMARY KEY,
	Code		VARCHAR(30)		NOT NULL UNIQUE,
	Name		VARCHAR(30)		NOT NULL,
	Address		VARCHAR(30)		NOT NULL,
	City		VARCHAR(30)		NOT NULL,
	State		VARCHAR(2)		NOT NULL,
	Zip			VARCHAR(5)		NOT NULL,
	Phone		VARCHAR(12)		NULL,
	Email		VARCHAR(255)	NULL
);

CREATE TABLE Products
(
	ID				INTEGER			NOT NULL IDENTITY PRIMARY KEY,
	PartNumber		VARCHAR(30)		NOT NULL UNIQUE,
	Name			VARCHAR(30)		NOT NULL,
	Price			DECIMAL(11,2)	NOT NULL,
	Unit			VARCHAR(30)		NOT NULL,
	PhotoParth		VARCHAR(255)	NULL,
	VendorID		INTEGER			NOT NULL,
	/*
		I did this with the foreign keys so they would be easy to use
		in case I need to.
	*/
	CONSTRAINT fk_PRS_VendorID	
		FOREIGN KEY (VendorID) REFERENCES Vendors(ID)
);

CREATE TABLE Requests
(
	ID					INTEGER			NOT NULL IDENTITY PRIMARY KEY,
	Description			VARCHAR(80)		NOT NULL,
	Justification		VARCHAR(80)		NOT NULL,
	RejectionReason		VARCHAR(80)		NULL,
	DeliveryMode		VARCHAR(20)		NOT NULL DEFAULT 'Pickup',
	SubmittedDate		DATE			NOT NULL DEFAULT GetDate(),
	DateNeeded			DATE			NOT NULL,
	Status				VARCHAR(10)		NOT NULL DEFAULT 'NEW',
	Total				DECIMAL(11,2)	NOT NULL DEFAULT 0,
	UserID				INTEGER			NOT NULL REFERENCES Users(ID),

	CONSTRAINT fk_PRS_UserID
		FOREIGN KEY (UserID) REFERENCES Users(ID)
);

CREATE TABLE RequestLines
(
	ID				INTEGER			NOT NULL IDENTITY PRIMARY KEY,
	RequestID		INTEGER			NOT NULL REFERENCES Requests(ID),
	ProductID		INTEGER			NOT NULL REFERENCES Products(ID),
	Quantity		INTEGER			NOT NULL DEFAULT 1,

	CONSTRAINT fk_PRS_RequestID	
		FOREIGN KEY (RequestID) REFERENCES Requests(ID),
	CONSTRAINT fk_PRS_ProductID	
		FOREIGN KEY (ProductID) REFERENCES Products(ID)

);

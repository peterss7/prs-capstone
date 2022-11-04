USE PRS;
GO

CREATE PROCEDURE SPUsersGetLastName @LastName NVARCHAR(30)
AS
SELECT * FROM Users WHERE LastName = @LastName
GO;




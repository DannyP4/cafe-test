-- tg_DelCategory
CREATE TRIGGER tg_DelCategory ON Category INSTEAD OF DELETE
AS
IF (SELECT COUNT(*) FROM Product WHERE CategoryId = (SELECT Id FROM deleted)) > 0
BEGIN
	IF (SELECT COUNT(*) FROM Category WHERE Name = 'None') = 0
		INSERT INTO Category (Name) VALUES ('None');
        UPDATE Product SET CategoryId = (SELECT Id FROM Category WHERE Name = 'None')
        WHERE CategoryId = (SELECT Id FROM deleted);
END
DELETE FROM Category WHERE Id = (SELECT Id FROM deleted);

-- tg_DefaultDeliveryInfo
CREATE TRIGGER tg_DefaultDeliveryInfo ON [User] FOR INSERT
AS
	INSERT INTO DeliveryInfo (UserId, RecipientName, PhoneNumber, Address)
	SELECT Id, FullName, PhoneNumber, Address FROM inserted;

-- tg_ageLegit
CREATE TRIGGER tg_ageLegit
ON [User]
INSTEAD OF INSERT
AS
BEGIN
	DECLARE @dob DATE;
    DECLARE @currentDate DATE = GETDATE();
                        
    SELECT @dob = BirthDate FROM inserted;
                                
    IF (DATEDIFF(year, @dob, @currentDate) >= 15)
    BEGIN
		INSERT INTO [User] (Email, Password, FullName, Sex, BirthDate, PhoneNumber, Address, SecurityQuestion, Answer)
        SELECT Email, Password, FullName, Sex, BirthDate, PhoneNumber, Address, SecurityQuestion, Answer
        FROM inserted;
	END
    ELSE
    BEGIN
		PRINT 'Chua du tuoi dang ky!'
        ROLLBACK;
	END
END;

-- tg_ageLegitofStaff
CREATE TRIGGER tg_ageLegitofStaff
ON Staff
INSTEAD OF INSERT
AS
BEGIN
	DECLARE @dob DATE;
    DECLARE @currentDate DATE = GETDATE();
                                           
    SELECT @dob = BirthDate FROM inserted;
                                                   
    IF (DATEDIFF(year, @dob, @currentDate) >= 18)
	BEGIN
		INSERT INTO Staff (FullName, Sex, BirthDate, PhoneNumber, Position, StartDate, EndDate, MonthlySalary)
        SELECT FullName, Sex, BirthDate, PhoneNumber, Position, StartDate, EndDate, MonthlySalary
        FROM inserted;
	END
    ELSE
    BEGIN
		PRINT 'Chua du tuoi!'
		ROLLBACK;
	END
END;
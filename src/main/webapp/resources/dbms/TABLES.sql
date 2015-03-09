create table Employees (
	AFM CHAR(9) NOT NULL,
	EmployeeNo SMALLINT UNSIGNED NOT NULL,
	FirstName VARCHAR(35) NOT NULL,
	LastName VARCHAR(35) NOT NULL,
	Addr_StreetName VARCHAR(35),
	Addr_StreetNo VARCHAR(4),
	Addr_PostalCode CHAR(5) NOT NULL,
	Salary FLOAT(8,2),
	WorkPhoneNumber CHAR(10),
	MobilePhoneNumber CHAR(10) NOT NULL,
	SupervisorAFM CHAR(9),
	PRIMARY KEY (AFM),
	FOREIGN KEY (SupervisorAFM) REFERENCES Employees(AFM),
	CONSTRAINT chk_Employees CHECK (Salary > 510.95)
);

create table Properties (
	PropertyRegistrationNo INT AUTO_INCREMENT NOT NULL,
	Addr_StreetName VARCHAR(35),
	Addr_StreetNo VARCHAR(4),
	Addr_PostalCode CHAR(5) NOT NULL,
	Size FLOAT(6,2) NOT NULL,
	Floor TINYINT UNSIGNED,
	Rent FLOAT(8,2) NOT NULL,
	PropertyTypeId CHAR(3) NOT NULL,
	OwnerAFM CHAR(9) NOT NULL,
	ManagerAFM CHAR(9) NOT NULL,
	PRIMARY KEY (PropertyRegistrationNo),
	FOREIGN KEY (PropertyTypeId) REFERENCES PropertyTypes(PropertyTypeID),
	FOREIGN KEY (OwnerAFM) REFERENCES Owners(AFM),
	FOREIGN KEY (ManagerAFM) REFERENCES Employees(AFM),
	CONSTRAINT chk_Properties CHECK (Rent > 0)
);

create table PropertyTypes (
	PropertyTypeID CHAR(3) NOT NULL,
	Rooms SMALLINT UNSIGNED NOT NULL,
	Description TEXT,
	PRIMARY KEY (PropertyTypeID)
);

create table Owners (
	AFM CHAR(9) NOT NULL,
	Addr_StreetName VARCHAR(35),
	Addr_StreetNo VARCHAR(4),
	Addr_PostalCode CHAR(5) NOT NULL,
	PRIMARY KEY (AFM)
);

create table OwnerPhones (
	AFM CHAR(9) NOT NULL,
	PhoneNumber CHAR(10) NOT NULL,
	PRIMARY KEY (PhoneNumber,AFM),
	FOREIGN KEY (AFM) REFERENCES Owners(AFM)
);

create table PrivateOwners (
	AFM CHAR(9) NOT NULL,
	FirstName VARCHAR(35) NOT NULL,
	LastName VARCHAR(35) NOT NULL,
	PRIMARY KEY (AFM),
	FOREIGN KEY (AFM) REFERENCES Owners(AFM)
);

create table BusinessOwners (
	AFM CHAR(9) NOT NULL,
	BusinessName VARCHAR(35) NOT NULL,
	BusinessType TINYTEXT,
	ContactFirstName VARCHAR(35),
	ContactLastName VARCHAR(35) NOT NULL,
	PRIMARY KEY (AFM),
	FOREIGN KEY (AFM) REFERENCES Owners(AFM)
);

create table Clients (
	ClientRegistrationNo INT AUTO_INCREMENT NOT NULL,
	FirstName VARCHAR(35) NOT NULL,
	LastName VARCHAR(35) NOT NULL,
	Addr_StreetName VARCHAR(35),
	Addr_StreetNo VARCHAR(4),
	Addr_PostalCode CHAR(5) NOT NULL,
	RegistrationDate DATE NOT NULL,
	MaxRent FLOAT(8,2),
	Active BOOLEAN NOT NULL,
	RegisteredBy CHAR(9) NOT NULL,
	PreferedTypeId CHAR(3),
	PRIMARY KEY (ClientRegistrationNo),
	FOREIGN KEY (RegisteredBy) REFERENCES Employees(AFM),
	FOREIGN KEY (PreferedTypeId) REFERENCES PropertyTypes(PropertyTypeID)
);

create table ClientPhones (
	PhoneNumber CHAR(10) NOT NULL,
	ClientRegistrationNo INT NOT NULL,
	PRIMARY KEY (PhoneNumber,ClientRegistrationNo),
	FOREIGN KEY (ClientRegistrationNo) REFERENCES Clients(ClientRegistrationNo)
);

create table Contracts (
	ContractsNo INT AUTO_INCREMENT NOT NULL,
	Rent FLOAT(8,2) NOT NULL,
	PaymentType ENUM('cash', 'cheque', 'visa') NOT NULL,
	RentStart DATE NOT NULL,
	RentFinish DATE,
	ClientRegistrationNo INT NOT NULL,
	PropertyRegistrationNo INT NOT NULL,
	PRIMARY KEY (ContractsNo),
	FOREIGN KEY (ClientRegistrationNo) REFERENCES Clients(ClientRegistrationNo),
	FOREIGN KEY (PropertyRegistrationNo) REFERENCES Properties(PropertyRegistrationNo)
);

create table Visits (
	Date DATE NOT NULL,
	ClientRegistrationNo INT NOT NULL,
	PropertyRegistrationNo INT NOT NULL,
	PRIMARY KEY (Date,ClientRegistrationNo,PropertyRegistrationNo),
	FOREIGN KEY (ClientRegistrationNo) REFERENCES Clients(ClientRegistrationNo),
	FOREIGN KEY (PropertyRegistrationNo) REFERENCES Properties(PropertyRegistrationNo)
);

create table Newspapers (
	NewspaperID CHAR(5) NOT NULL,
	NewspaperName VARCHAR(35) NOT NULL,
	PRIMARY KEY (NewspaperID)
);

create table Advertisements (
	DateOfPublish DATE NOT NULL,
	PropertyRegistrationNo INT NOT NULL,
	NewspaperID CHAR(5) NOT NULL,
	Cost FLOAT(8,2) NOT NULL,
	Duration INT NOT NULL,
	PRIMARY KEY (DateOfPublish,NewspaperID,PropertyRegistrationNo),
	FOREIGN KEY (NewspaperID) REFERENCES Newspapers(NewspaperID),
	FOREIGN KEY (PropertyRegistrationNo) REFERENCES Properties(PropertyRegistrationNo)
);
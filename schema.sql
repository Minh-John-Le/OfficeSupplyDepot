Create database OfficeSupplyDepotDatabase;
use OfficeSupplyDepotDatabase;

CREATE TABLE IF NOT EXISTS Customers (
  Id int NOT NULL AUTO_INCREMENT,
  Username varchar(255) NOT NULL,
  CustomerName varchar(255) NOT NULL,
  Password varchar(255) NOT NULL,
  Email varchar(255) NOT NULL,
  Address text,
  PRIMARY KEY (Id)
);

CREATE TABLE IF NOT EXISTS Stores (
  Id int NOT NULL AUTO_INCREMENT,
  StoreName varchar(255) NOT NULL,
  UserName varchar(255) NOT NULL,
  Password varchar(255) NOT NULL,
  Email varchar(255) NOT NULL,
  Address text,
  PRIMARY KEY (Id)
);

CREATE TABLE IF NOT EXISTS Warehouses (
  Id int NOT NULL AUTO_INCREMENT,
  Name varchar(255) DEFAULT NULL,
  Address text,
  PRIMARY KEY (Id)
);

CREATE TABLE IF NOT EXISTS BankAccounts (
  Id int NOT NULL AUTO_INCREMENT,
  Store_ID int NOT NULL,
  Name varchar(255) DEFAULT NULL,
  Expire_Date varchar(5) DEFAULT NULL,
  Bank_Account_Number int DEFAULT NULL,
  PRIMARY KEY (Id),
  FOREIGN KEY (Store_ID) REFERENCES Stores (Id)
);

CREATE TABLE IF NOT EXISTS PaymentAccounts (
  Id int NOT NULL AUTO_INCREMENT,
  Customer_ID int NOT NULL,
  Name varchar(255) DEFAULT NULL,
  Expire_Date varchar(5) DEFAULT NULL,
  Card_Number int DEFAULT NULL,
  PRIMARY KEY (Id),
  FOREIGN KEY (Customer_ID) REFERENCES Customers (Id)
);

CREATE TABLE IF NOT EXISTS ShipMethods (
  Id int NOT NULL AUTO_INCREMENT,
  Name varchar(255) DEFAULT NULL,
  Price decimal(10,2) DEFAULT NULL,
  Speed int DEFAULT NULL,
  PRIMARY KEY (Id)
);


CREATE TABLE IF NOT EXISTS Order_Details (
  Id int NOT NULL AUTO_INCREMENT,
  Store_ID int NOT NULL,
  Customer_ID int NOT NULL,
  Payment_Account_ID int NOT NULL,
  Shipmethod_ID int NOT NULL,
  Ship_Address text,
  Total_Weight decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (Id),
  FOREIGN KEY (Store_ID) REFERENCES Stores (Id),
  FOREIGN KEY (Customer_ID) REFERENCES Customers (Id),
  FOREIGN KEY (Payment_Account_ID) REFERENCES PaymentAccounts (Id),
  FOREIGN KEY (Shipmethod_ID) REFERENCES ShipMethods (Id)
);

CREATE TABLE IF NOT EXISTS Products (
  Id int NOT NULL AUTO_INCREMENT,
  Store_ID int NOT NULL,
  Warehouse_ID int NOT NULL,
  Name varchar(255) DEFAULT NULL,
  Stock int DEFAULT NULL,
  Weight decimal(10,2) DEFAULT NULL,
  Description text,
  Price decimal(10,2) DEFAULT NULL,
  ImageURL VARCHAR(255),
  PRIMARY KEY (Id),
  FOREIGN KEY (Store_ID) REFERENCES Stores (Id),
  FOREIGN KEY (Warehouse_ID) REFERENCES Warehouses (Id)
);


CREATE TABLE IF NOT EXISTS Packages (
  Order_ID int DEFAULT NULL,
  Product_ID int DEFAULT NULL,
  Quantity int NOT NULL,
  FOREIGN KEY (Product_ID) REFERENCES Products (Id),
  FOREIGN KEY (Order_ID) REFERENCES Order_Details (Id)
);


CREATE TABLE IF NOT EXISTS Reviews (
  Id int NOT NULL AUTO_INCREMENT,
  Customer_ID int NOT NULL,
  Product_ID int NOT NULL,
  Stars int DEFAULT NULL,
  Content text,
  Date_Post varchar(10) DEFAULT NULL,
  PRIMARY KEY (Id),
  FOREIGN KEY (Product_ID) REFERENCES Products (Id),
  FOREIGN KEY (Customer_ID) REFERENCES Customers (Id)
);

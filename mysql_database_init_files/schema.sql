CREATE DATABASE IF NOT EXISTS OfficeSupplyDepot;
use OfficeSupplyDepot;

CREATE TABLE IF NOT EXISTS Customers (
  Id int NOT NULL AUTO_INCREMENT,
  Username varchar(255) NOT NULL,
  CustomerName varchar(255) NOT NULL,
  Password varchar(255) NOT NULL,
  Email varchar(255) NOT NULL,
  Address text,
  PRIMARY KEY (Id)
);

CREATE TABLE IF NOT EXISTS OSDAdmins (
  Id int NOT NULL AUTO_INCREMENT,
  AdminName varchar(255) NOT NULL,
  UserName varchar(255) NOT NULL,
  Password varchar(255) NOT NULL,
  Email varchar(255) NOT NULL,
  PRIMARY KEY (Id)
);

CREATE TABLE IF NOT EXISTS Warehouses (
  Id int NOT NULL,
  Name varchar(255) DEFAULT NULL,
  Address text,
  PRIMARY KEY (Id)
);

CREATE TABLE IF NOT EXISTS PaymentAccounts (
  Id int NOT NULL AUTO_INCREMENT,
  Customer_ID int NOT NULL,
  Name varchar(255) DEFAULT NULL,
  Expire_Date varchar(5) DEFAULT NULL,
  Card_Number varchar(21) DEFAULT NULL,
  PRIMARY KEY (Id),
  FOREIGN KEY (Customer_ID) REFERENCES Customers (Id)
);

CREATE TABLE IF NOT EXISTS ShipMethods (
  Id int NOT NULL,
  Name varchar(255) DEFAULT NULL,
  Price decimal(10,2) DEFAULT NULL,
  Speed int DEFAULT NULL,
  PRIMARY KEY (Id)
);

CREATE TABLE IF NOT EXISTS Order_Details (
  Id int NOT NULL AUTO_INCREMENT,
  Customer_ID int NOT NULL,
  Payment_Account_ID int NOT NULL,
  Shipmethod_ID int NOT NULL,
  Ship_Address text,
  Total_Weight decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (Id),
  FOREIGN KEY (Customer_ID) REFERENCES Customers (Id),
  FOREIGN KEY (Payment_Account_ID) REFERENCES PaymentAccounts (Id),
  FOREIGN KEY (Shipmethod_ID) REFERENCES ShipMethods (Id)
);

CREATE TABLE IF NOT EXISTS Products (
  Id int NOT NULL AUTO_INCREMENT,
  Warehouse_ID int NOT NULL,
  Name varchar(255) DEFAULT NULL,
  Stock int DEFAULT NULL,
  Weight decimal(10,2) DEFAULT NULL,
  Description text,
  Price decimal(10,2) DEFAULT NULL,
  ImageURL VARCHAR(255),
  Category varchar(255),
  Barcode varchar(255) UNIQUE NOT NULL,
  PRIMARY KEY (Id),
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


CREATE USER 'minh'@'%' IDENTIFIED BY '!Changme123';
GRANT ALL PRIVILEGES ON OfficeSupplyDepot.* TO 'minh'@'%';
FLUSH PRIVILEGES;

CREATE USER 'david'@'%' IDENTIFIED BY '!Changme123';
GRANT ALL PRIVILEGES ON OfficeSupplyDepot.* TO 'david'@'%';
FLUSH PRIVILEGES;

CREATE USER 'nate'@'%' IDENTIFIED BY '!Changme123';
GRANT ALL PRIVILEGES ON OfficeSupplyDepot.* TO 'nate'@'%';
FLUSH PRIVILEGES;

CREATE USER 'nathan'@'%' IDENTIFIED BY '!Changme123';
GRANT ALL PRIVILEGES ON OfficeSupplyDepot.* TO 'nathan'@'%';
FLUSH PRIVILEGES;

CREATE USER 'kaleigh'@'%' IDENTIFIED BY '!Changme123';
GRANT ALL PRIVILEGES ON OfficeSupplyDepot.* TO 'kaleigh'@'%';
FLUSH PRIVILEGES;

CREATE USER 'khush'@'%' IDENTIFIED BY '!Changme123';
GRANT ALL PRIVILEGES ON OfficeSupplyDepot.* TO 'khush'@'%';
FLUSH PRIVILEGES;

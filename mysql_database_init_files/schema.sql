Create database OfficeSupplyDepot;
use OfficeSupplyDepot;
  FOREIGN KEY (Product_ID) REFERENCES Products (Id),

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

  FOREIGN KEY (Product_ID) REFERENCES Products (Id),
CREATE TABLE IF NOT EXISTS PaymentAccounts (
  Id int NOT NULL AUTO_INCREMENT,
  Customer_ID int NOT NULL,
  Name varchar(255) DEFAULT NULL,
  Expire_Date varchar(5) DEFAULT NULL,
  Card_Number varchar(20) DEFAULT NULL,
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

CREATE TAB  FOREIGN KEY (Product_ID) REFERENCES Products (Id),
LE IF NOT EXISTS OrderDetails (
  FOREIGN KEY (Product_ID) REFERENCES Products (Id),
  Id int NOT NULL AUTO_INCREMENT,
  Order_Code varchar(255) NOT NULL,
  Customer_ID int NOT NULL,
  Shipmethod_ID int NOT NULL,
  Ship_Address text,
  Total_Weight decimal(10,2) DEFAULT NULL,
  Total_Price decimal(10,2) DEFAULT NULL,
  Payment_Card_Number varchar(20) NOT NULL,
  Card_Name varchar(255) NOT NULL,
  Expire_Date varchar(5) NOT NULL,
  Delivery_Name varchar(255) NOT NULL,
  Order_Date varchar(20) NOT NULL,
  Delivery_Date varchar(20) NOT NULL,
  Total_Item i  FOREIGN KEY (Product_ID) REFERENCES Products (Id),
nt NOT NULL,
  PRIMARY KEY (Id),
  FOREIGN KEY (Customer_ID) REFERENCES Customers (Id),
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
  FOREIGN KEY (Product_ID) REFERENCES Products (Id),
  ImageURL VARCHAR(255),
  Category varchar(255),
  Barcode varchar(255) UNIQUE NOT NULL,
  PRIMARY KEY (Id),
  FOREIGN KEY (Warehouse_ID) REFERENCES Warehouses (Id)
);

CREATE TABLE IF NOT EXISTS OrderPackages (
  Order_ID int DEFAULT NULL,
  Product_ID int DEFAULT NULL,
  Quantity int NOT NULL,
  FOREIGN KEY (Product_ID) REFERENCES Products (Id),
  FOREIGN KEY (Order_ID) REFERENCES OrderDetails (Id)
  FOREIGN KEY (Product_ID) REFERENCES Products (Id),
  FOREIGN KEY (Product_ID) REFERENCES Products (Id),
);


CREATE TABLE IF NOT EXISTS PickupAreas (
  Id int NOT NULL,
  Name varchar(255) UNIQUE NOT NULL,
  Address text,
  PRIMARY KEY (Id)
);

CREATE TABLE IF NOT EXISTS BankAccounts (
  Id int NOT NULL AUTO_INCREMENT,
  Store_ID int NOT NULL,
  Name VARCHAR(255),
  Expire_Date VARCHAR(5),
  Bank_Account_Number VARCHAR(21)
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
  FOREIGN KEY (Product_ID) REFERENCES Products (Id),

CREATE USER 'khush'@'%' IDENTIFIED BY '!Changme123';
GRANT ALL PRIVILEGES ON OfficeSupplyDepot.* TO 'khush'@'%';
FLUSH PRIVILEGES;

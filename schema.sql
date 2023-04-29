Create database OfficeSupplyDepot;
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

CREATE TABLE IF NOT EXISTS OrderDetails (
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
  Total_Item int NOT NULL,
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
  Price decimal(10,2) DEFAULT NULL,
  FOREIGN KEY (Product_ID) REFERENCES Products (Id),
  FOREIGN KEY (Order_ID) REFERENCES OrderDetails (Id)
);


CREATE TABLE IF NOT EXISTS PickupAreas (
  Id int NOT NULL,
  Name varchar(255) UNIQUE NOT NULL,
  Address text,
  PRIMARY KEY (Id)
);

INSERT INTO PickupAreas VALUES(1,'SJSU', '1 Washington Sq, San Jose, CA 95192');

INSERT INTO Warehouses VALUES(0, 'SJSU' , '1 Washington Sq, San Jose, CA 95192');
INSERT INTO Warehouses VALUES(1, 'EVC' , '3095 Yerba Buena Rd, San Jose, CA 95135');
INSERT INTO Warehouses VALUES(2, 'SJSU' , '1 Washington Sq, San Jose, CA 95192');

INSERT INTO ShipMethods VALUES(1, 'Dron Free', 0.00, 0);
INSERT INTO ShipMethods VALUES(2, 'Truck Free', 0.00, 2);
INSERT INTO ShipMethods VALUES(3, 'Drone', 20.00, 0);
INSERT INTO ShipMethods VALUES(4, 'Truck', 20.00, 2);
INSERT INTO ShipMethods VALUES(5, 'Truck-Same Day', 25.00, 0);
INSERT INTO ShipMethods VALUES(6, 'Pickup', 0.00, 0);

INSERT INTO OSDAdmins(AdminName, UserName, Password, Email)
VALUES('Cute Admin','Kaleigh123@gmail.com', 'Kaleigh123@gmail.com' , 'Kaleigh123@gmail.com');

INSERT INTO OSDAdmins(AdminName, UserName, Password, Email)
VALUES('Admin','Minh123@gmail.com', 'Minh123@gmail.com' , 'Minh123@gmail.com');

INSERT INTO OSDAdmins(AdminName, UserName, Password, Email)
VALUES('Admin','Nate123@gmail.com', 'Nate123@gmail.com' , 'Nate123@gmail.com');

INSERT INTO OSDAdmins(AdminName, UserName, Password, Email)
VALUES('Admin','Nathan123@gmail.com', 'Nathan123@gmail.com' , 'Nathan123@gmail.com');

INSERT INTO OSDAdmins(AdminName, UserName, Password, Email)
VALUES('Admin','David123@gmail.com', 'David123@gmail.com' , 'David123@gmail.com');

INSERT INTO OSDAdmins(AdminName, UserName, Password, Email)
VALUES('Admin','Kush123@gmail.com', 'Kush123@gmail.com' , 'Kush123@gmail.com');

CREATE USER 'david'@'localhost' IDENTIFIED BY '!Changme123';
CREATE USER 'nate'@'localhost' IDENTIFIED BY '!Changme123';
CREATE USER 'nathan'@'%' IDENTIFIED BY '!Changme123';
CREATE USER 'kaleigh'@'%' IDENTIFIED BY '!Changme123';
CREATE USER 'nathan'@'%' IDENTIFIED BY '!Changme123';

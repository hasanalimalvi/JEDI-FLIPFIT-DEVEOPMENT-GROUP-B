CREATE DATABASE FlipFit_Schema;
USE FlipFit_Schema;

CREATE TABLE FlipFitRole (
    roleId INT AUTO_INCREMENT PRIMARY KEY,
    roleName VARCHAR(100)
);

CREATE TABLE FlipFitUser (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100),
    email VARCHAR(100),
    password VARCHAR(100),
    roleId INT,
    FOREIGN KEY (roleId) REFERENCES FlipFitRole(roleId)
);

CREATE TABLE FlipFitDirectCustomer (
    customerId INT PRIMARY KEY,
    phoneNumber VARCHAR(20),
    city VARCHAR(100),
    pinCode VARCHAR(10),
    FOREIGN KEY (customerId) REFERENCES FlipFitUser(userId)
);

CREATE TABLE FlipFitGymOwner (
    gymOwnerId INT PRIMARY KEY,
    phoneNumber VARCHAR(20),
    city VARCHAR(100),
    pinCode VARCHAR(10),
    panCard VARCHAR(20),
    gstin VARCHAR(20),
    aadharNumber VARCHAR(20),
    isApproved BOOLEAN,
    FOREIGN KEY (gymOwnerId) REFERENCES FlipFitUser(userId)
);

CREATE TABLE FlipFitAdmin (
    adminId INT PRIMARY KEY,
    FOREIGN KEY (adminId) REFERENCES FlipFitUser(userId)
);

CREATE TABLE FlipFitGym (
    gymID INT AUTO_INCREMENT PRIMARY KEY,
    gymOwnerID INT,
    address VARCHAR(255),
    pinCode VARCHAR(10),
    isApproved BOOLEAN,
    description TEXT,
    FOREIGN KEY (gymOwnerID) REFERENCES FlipFitGymOwner(gymOwnerId)
);

CREATE TABLE FlipFitSlot (
    slotId INT AUTO_INCREMENT PRIMARY KEY,
    gymId INT,
    startTime TIME,
    totalSeats INT,
    FOREIGN KEY (gymId) REFERENCES FlipFitGym(gymID)
);

CREATE TABLE FlipFitBooking (
    bookingId INT AUTO_INCREMENT PRIMARY KEY,
    userId INT,
    slotId INT,
    isCancelled BOOLEAN,
    FOREIGN KEY (userId) REFERENCES FlipFitUser(userId),
    FOREIGN KEY (slotId) REFERENCES FlipFitSlot(slotId)
);

CREATE TABLE FlipFitTransaction (
    transactionId INT AUTO_INCREMENT PRIMARY KEY,
    userId INT,
    bookingId INT,
    paymentType INT,
    amount DOUBLE,
    FOREIGN KEY (userId) REFERENCES FlipFitUser(userId),
    FOREIGN KEY (bookingId) REFERENCES FlipFitBooking(bookingId)
);

CREATE TABLE FlipFitSlotAvailability (
  slotId INT NOT NULL,
  date DATE NOT NULL,
  seatsAvailable INT NOT NULL,
  PRIMARY KEY (slotId, date),
  FOREIGN KEY (slotId) REFERENCES FlipFitSlot(slotId) 
);

SET SQL_SAFE_UPDATES = 0;
DELETE FROM FlipFitRole;
SET SQL_SAFE_UPDATES = 1;

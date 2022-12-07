# DML is Data Manipulation Language which is used to manipulate data itself.

# stored procedures maybe necessary to remember how to do
use bilabonnement;
# employee #
INSERT INTO employee (employeeID, email, name, password, role)
VALUES (101, '1', 'DATA_REGISTRATION user', '123', 'DATA_REGISTRATION');
INSERT INTO employee (employeeID, email, name, password, role)
VALUES (102, '2', 'DAMAGE_REPORTER user', '123', 'DAMAGE_REPORTER');
INSERT INTO employee (employeeID, email, name, password, role)
VALUES (103, '3', 'BUSINESS_DEVELOPER user', '123', 'BUSINESS_DEVELOPER');
INSERT INTO employee (employeeID, email, name, password, role)
VALUES (104, '4', 'ADMINISTRATION user', '123', 'ADMINISTRATION');

# customer #
INSERT INTO customer (customerID, firstName, lastName, email, address, city, postalCode, mobile, cprNumber)
VALUES (201, 'firstName', 'lastName', 'email_1', 'address', 'city', 2900, 'mobile', 'cprNumber');
INSERT INTO customer (customerID, firstName, lastName, email, address, city, postalCode, mobile, cprNumber)
VALUES (202, 'firstName', 'lastName', 'email_2', 'address', 'city', 2900, 'mobile', 'cprNumber');
INSERT INTO customer (customerID, firstName, lastName, email, address, city, postalCode, mobile, cprNumber)
VALUES (203, 'firstName', 'lastName', 'email_3', 'address', 'city', 2900, 'mobile', 'cprNumber');

# optional #
INSERT INTO optional (optionalID, name, pricePrMonth)
VALUES (401, 'optional 1', 10);
INSERT INTO optional (optionalID, name, pricePrMonth)
VALUES (402, 'optional 2', 20);
INSERT INTO optional (optionalID, name, pricePrMonth)
VALUES (403, 'optional 3', 30);


### tables with foreign key constraints ###

# car #
INSERT INTO car (vehicleID, chassisNumber, steelPrice, brand, model, equipmentLevel, registrationFee, co2emission,
                 state)
VALUES (501, 'chassisNumber', 100, 'brand', 'model', 'MEDIUM', 100, 100,
        'READY');
INSERT INTO car (vehicleID, chassisNumber, steelPrice, brand, model, equipmentLevel, registrationFee, co2emission,
                  state)
VALUES (502, 'chassisNumber', 100, 'brand', 'model', 'MEDIUM', 100, 100, 
        'READY');
INSERT INTO car (vehicleID, chassisNumber, steelPrice, brand, model, equipmentLevel, registrationFee, co2emission,
                 state)
VALUES (503, 'chassisNumber', 100, 'brand', 'model', 'MEDIUM', 100, 100, 
        'READY');
INSERT INTO car (vehicleID, chassisNumber, steelPrice, brand, model, equipmentLevel, registrationFee, co2emission,
                 state)
VALUES (504, 'chassisNumber', 100, 'brand', 'model', 'MEDIUM', 100, 100, 
        'RETURNED');
INSERT INTO car (vehicleID, chassisNumber, steelPrice, brand, model, equipmentLevel, registrationFee, co2emission,
                 state)
VALUES (505, 'chassisNumber', 100, 'brand', 'model', 'MEDIUM', 100, 100,
        'RETURNED');
INSERT INTO car (vehicleID, chassisNumber, steelPrice, brand, model, equipmentLevel, registrationFee, co2emission,
                 state)
VALUES (506, 'chassisNumber', 100, 'brand', 'model', 'MEDIUM', 100, 100,
        'AT_CUSTOMER');
INSERT INTO car (vehicleID, chassisNumber, steelPrice, brand, model, equipmentLevel, registrationFee, co2emission,
                 state)
VALUES (507, 'chassisNumber', 100, 'brand', 'model', 'MEDIUM', 100, 100,
        'AT_CUSTOMER');


# leaseContract #
INSERT INTO leasecontract (leaseID, startDate, endDate, monthlyPrice, customerID, vehicleID, employeeID)
VALUES (601, '2011-01-14', '2011-01-28', 3000, 201, 501, 101);
INSERT INTO leasecontract (leaseID, startDate, endDate, monthlyPrice, customerID, vehicleID, employeeID)
VALUES (602, '2012-02-14', '2012-02-28', 3000, 201, 502, 101);
INSERT INTO leasecontract (leaseID, startDate, endDate, monthlyPrice, customerID, vehicleID, employeeID)
VALUES (603, '2013-03-14', '2013-03-28', 3000, 202, 503, 101);
INSERT INTO leasecontract (leaseID, startDate, endDate, monthlyPrice, customerID, vehicleID, employeeID)
VALUES (604, '2014-01-01', '2014-01-31', 3000, 203, 504, 101);
INSERT INTO leasecontract (leaseID, startDate, endDate, monthlyPrice, customerID, vehicleID, employeeID)
VALUES (606, '2022-06-21', '2023-02-21', 3000, 203, 505, 101);
INSERT INTO leasecontract (leaseID, startDate, endDate, monthlyPrice, customerID, vehicleID, employeeID)
VALUES (607, '2022-09-01', '2023-05-01', 3000, 203, 506, 101);
INSERT INTO leasecontract (leaseID, startDate, endDate, monthlyPrice, customerID, vehicleID, employeeID)
VALUES (608, '2016-03-01', '2016-05-31', 3000, 203, 507, 101);

# leaseOptional #
INSERT INTO leaseoptional (optionalID, leaseID)
VALUES (401, 601);
INSERT INTO leaseoptional (optionalID, leaseID)
VALUES (402, 601);
INSERT INTO leaseoptional (optionalID, leaseID)
VALUES (403, 601);

# damageReport #
INSERT INTO damagereport (damagereport.damageReportID, damagereport.vehicleID, damagereport.employeeID,
                          damagereport.timestamp)
VALUES (701, 501, 101, '1970-01-01 00:00:01');
INSERT INTO damagereport (damagereport.damageReportID, damagereport.vehicleID, damagereport.employeeID,
                          damagereport.timestamp)
VALUES (702, 501, 101, '1970-01-01 00:00:01');
INSERT INTO damagereport (damagereport.damageReportID, damagereport.vehicleID, damagereport.employeeID,
                          damagereport.timestamp)
VALUES (703, 502, 101, '1970-01-01 00:00:01');

# damageEntry #
INSERT INTO damageentry (damageEntryID, damageTitle, damageDescription, damagePrice, damageReportID)
VALUES (801, 'damageTitle', 'damageDescription', 500, 701);
INSERT INTO damageentry (damageEntryID, damageTitle, damageDescription, damagePrice, damageReportID)
VALUES (802, 'damageTitle', 'damageDescription', 500, 702);
INSERT INTO damageentry (damageEntryID, damageTitle, damageDescription, damagePrice, damageReportID)
VALUES (803, 'damageTitle', 'damageDescription', 500, 703);
INSERT INTO damageentry (damageEntryID, damageTitle, damageDescription, damagePrice, damageReportID)
VALUES (804, 'damageTitle', 'damageDescription', 500, 703);




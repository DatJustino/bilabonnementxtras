package com.example.bilabonnement.repository;

import com.example.bilabonnement.model.LeaseContract;
import com.example.bilabonnement.model.enums.DB_CONNECTION;
import com.example.bilabonnement.utility.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class LeaseContractRepository implements IGenericRepository<LeaseContract> {

    Connection conn;

    public LeaseContractRepository(DB_CONNECTION db_connection) {
        conn = DatabaseConnectionManager.getConnection(DB_CONNECTION.RELEASE_DB);
    }


    @Override
    public void create(LeaseContract leaseContract) {
        try {
            PreparedStatement psts = conn.prepareStatement("INSERT INTO leasecontract (leaseID, startDate, endDate, monthlyPrice, customerID, vehicleID, employeeID) VALUES (?,?,?,?,?,?,?)");
            psts.setInt(1, leaseContract.getLeaseID());
            psts.setDate(2, leaseContract.getStartDate());
            psts.setDate(3, leaseContract.getEndDate());
            psts.setDouble(4, leaseContract.getMonthlyPrice());
            psts.setInt(5, leaseContract.getCustomerID());
            psts.setInt(6, leaseContract.getVehicleID());
            psts.setInt(7, leaseContract.getEmployeeID());
            psts.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO
    @Override
    public List<LeaseContract> readAll() {
        List<LeaseContract> contractList = new ArrayList<>();

        try {
            PreparedStatement pst = conn.prepareStatement("select * from leasecontract");
            ResultSet resultSet = pst.executeQuery();

            // list of entities
            while (resultSet.next()) {
                contractList.add(new LeaseContract(
                        resultSet.getInt("leaseID"),
                        resultSet.getDate("startDate"),
                        resultSet.getDate("endDate"),
                        resultSet.getDouble("monthlyPrice"),
                        resultSet.getInt("customerID"),
                        resultSet.getInt("vehicleID"),
                        resultSet.getInt("employeeID")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contractList;
    }

    @Override
    public LeaseContract read(int id) {
        LeaseContract leaseContract = null;

        try {
            PreparedStatement psts = conn.prepareStatement("SELECT * FROM leasecontract WHERE leaseID = ?");
            psts.setInt(1, id);
            ResultSet resultSet = psts.executeQuery();

            // entity parameters
            while (resultSet.next()) {
                leaseContract = new LeaseContract(
                        resultSet.getInt("leaseID"),
                        resultSet.getDate("startDate"),
                        resultSet.getDate("endDate"),
                        resultSet.getDouble("monthlyPrice"),
                        resultSet.getInt("customerID"),
                        resultSet.getInt("vehicleID"),
                        resultSet.getInt("employeeID"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return leaseContract;
    }

    @Override
    public void update(LeaseContract leaseContract) {
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        java.sql.Date date2 = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        try {
            PreparedStatement psts = conn.prepareStatement("UPDATE leasecontract SET startDate = ?, endDate = ?, monthlyPrice = ?, customerID = ?, vehicleID = ?, employeeID = ? WHERE leaseID = ?");
            psts.setDate(1, leaseContract.getStartDate());
            psts.setDate(2, leaseContract.getEndDate());
            psts.setDouble(3, leaseContract.getMonthlyPrice());
            psts.setInt(4, leaseContract.getCustomerID());
            psts.setInt(5, leaseContract.getVehicleID());
            psts.setInt(6, leaseContract.getEmployeeID());
            System.out.println(leaseContract.getLeaseID());
            psts.setInt(7, leaseContract.getLeaseID());
            psts.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


            @Override
    public void delete(int id) {
        try {
            PreparedStatement pst = conn.prepareStatement("DELETE FROM leasecontract WHERE leaseID = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public double getCurrentIncome(Date date) {
        double income = 0;
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT monthlyPrice\n" +
                "FROM leaseContract\n" +
                "WHERE startDate < ? AND endDate > ?;");

            pst.setDate(1,date);
            pst.setDate(2,date);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                income += resultSet.getDouble("monthlyPrice");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return income;
    }
}

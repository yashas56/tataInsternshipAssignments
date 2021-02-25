package com.tata.assignment.dao;

import com.tata.assignment.entities.Admin;
import com.tata.assignment.entities.Product;

import com.tata.assignment.helpers.PostgresHelper;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminDaoImpl implements AdminDao{
    private Connection conn;
    private ResourceBundle resourceBundle;


    public AdminDaoImpl(){
        conn = PostgresHelper.getConnection();
        if(conn != null)
            System.out.print("Connection successful");
        else
            System.out.print("Connection Failed");
        resourceBundle = ResourceBundle.getBundle("db");

    }
    @Override
    public void addProduct(Product product) throws SQLException{
        String addCategoryQuery = resourceBundle.getString("addCategoryQuery");
        String addProductQuery = resourceBundle.getString("addProductQuery");
        PreparedStatement ppre,cpre;

        try{
            ppre = conn.prepareStatement(addProductQuery);
            cpre = conn.prepareStatement(addCategoryQuery);
            cpre.setInt(1,product.getCategoryId());
            cpre.setString(2,product.getName());
            ppre.setInt(1,product.getProductId());
            ppre.setDate(2,product.getDate());
            ppre.setString(3,product.getImgUrl());
            ppre.setString(4,product.getProductName());
            ppre.setInt(5,product.getPrice());
            ppre.setInt(6,product.getQuantity());
            ppre.setInt(7,product.getCategoryId());
            cpre.executeUpdate();
            ppre.executeUpdate();
        }
        catch (SQLException exception){
            System.out.println(exception.getMessage());

            conn.rollback();
        }
        finally {
            conn.close();
        }
    }

    @Override
    public void updateAccount(Admin admin){
        String updateQuery = resourceBundle.getString("updateAdmin");
        PreparedStatement upre;
        try{

            conn = PostgresHelper.getConnection();
            upre = conn.prepareStatement(updateQuery);
            upre.setString(1,admin.getAdminName());
            upre.setString(2, admin.getAdminPswd());
            upre.setInt(3,admin.getAdminId());

            upre.executeUpdate();
            conn.close();
        }
        catch (SQLException exception){

        }
    }

    @Override
    public void updateProduct(Product product) {
        String updateproductQuery = resourceBundle.getString("updateProduct");
        String addCategoryQuery = resourceBundle.getString("addCategoryQuery");
        PreparedStatement ppre,cpre;
        try{
            conn = PostgresHelper.getConnection();

            ppre = conn.prepareStatement(updateproductQuery);
            cpre = conn.prepareStatement(addCategoryQuery);
            cpre.setInt(1,product.getCategoryId());
            cpre.setString(2,product.getName());
            ppre.setDate(1,product.getDate());
            ppre.setString(2,product.getImgUrl());
            ppre.setString(3,product.getProductName());
            ppre.setInt(4,product.getPrice());
            ppre.setInt(5,product.getQuantity());
            ppre.setInt(6,product.getProductId());
            cpre.executeUpdate();
            ppre.executeUpdate();

            conn.close();
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }
}

package lk.ijse.foodcity.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import lk.ijse.foodcity.dto.CustomerDTO;
import lk.ijse.foodcity.db.DBConnection;
import lk.ijse.foodcity.util.CrudUtil;

public class CustomerModel {
    
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException  {
    
        /*
        Connection conn = DBConnection.getInstance().getConnection();

        String sql = "INSERT INTO customer (name, address, salary) values (?,?,?)";

        PreparedStatement ptsm = conn.prepareStatement(sql);
        ptsm.setString(1, customerDTO.getName());
        ptsm.setString(2, customerDTO.getAddress());
        ptsm.setDouble(3, customerDTO.getSalary());

        int result = ptsm.executeUpdate();
        
        return result>0;
        */
        
        boolean result = 
                CrudUtil.execute(
                        "INSERT INTO customer (name, address, salary) values (?,?,?)", 
                        customerDTO.getName(), 
                        customerDTO.getAddress(), 
                        customerDTO.getSalary()
                );
        
        return result;
    }
    
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException {
        
        /*
        Connection conn = DBConnection.getInstance().getConnection();

        String sql = "UPDATE customer SET name=?, address=?, salary=? WHERE id=?";

        PreparedStatement ptsm = conn.prepareStatement(sql);
        ptsm.setString(1, customerDTO.getName());
        ptsm.setString(2, customerDTO.getAddress());
        ptsm.setDouble(3, customerDTO.getSalary());
        ptsm.setInt(4, customerDTO.getId());

        int result = ptsm.executeUpdate();
        
        return result>0;
        */
        
        boolean result = 
                CrudUtil.execute(
                        "UPDATE customer SET name=?, address=?, salary=? WHERE id=?", 
                        customerDTO.getName(), 
                        customerDTO.getAddress(), 
                        customerDTO.getSalary(),
                        customerDTO.getId()
                );
        
        return result;   
    }
    
    public boolean deleteCustomer(String id) throws SQLException {
        /*
        Connection conn = DBConnection.getInstance().getConnection();

        String sql = "DELETE FROM customer WHERE id=?";
                
        PreparedStatement ptsm = conn.prepareStatement(sql);
        ptsm.setInt(1, Integer.parseInt(id));
                
        int result = ptsm.executeUpdate();
    
        return result>0;
        */
        boolean result = 
                CrudUtil.execute(
                        "DELETE FROM customer WHERE id=?", 
                        id
                );
        
        return result;  
    }
    
    public CustomerDTO searchCustomer(String id) throws SQLException {
    
        /*
        Connection conn = DBConnection.getInstance().getConnection();

        String sql = "SELECT * FROM customer WHERE id=?";

        PreparedStatement ptsm = conn.prepareStatement(sql);
        ptsm.setInt(1, Integer.parseInt(id));
                    
        ResultSet rs = ptsm.executeQuery();
        */
        
        ResultSet rs = 
                CrudUtil.execute(
                        "SELECT * FROM customer WHERE id=?", 
                        id
                );
        
        if(rs.next()) {
            int cusId = rs.getInt("id");
            String cusName = rs.getString("name");
            String cusAddress = rs.getString("address");
            double cusSalary = rs.getDouble("salary");
            
            return new CustomerDTO(cusId, cusName, cusAddress, cusSalary);
        }
        
        return null;
    }
    
    public List<CustomerDTO> getCustomers() throws SQLException {
    
        /*
        Connection conn = DBConnection.getInstance().getConnection();
        
        String sql = "SELECT * FROM customer ORDER BY id DESC";
        
        Statement stm = conn.createStatement();
        
        ResultSet rs = stm.executeQuery(sql);
        */
        
        ResultSet rs = 
                CrudUtil.execute(
                        "SELECT * FROM customer ORDER BY id DESC" 
                );
        
        List<CustomerDTO> customerList = new ArrayList<>();
        
        while(rs.next()) {
            CustomerDTO cusDTO = new CustomerDTO(
                    rs.getInt("id"), 
                    rs.getString("name"), 
                    rs.getString("address"), 
                    rs.getDouble("salary")
            );
            
            customerList.add(cusDTO);
        }
        
        return customerList;
    }
    
}

package lk.ijse.foodcity.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lk.ijse.foodcity.dto.ItemDTO;
import lk.ijse.foodcity.util.CrudUtil;

public class ItemModel {
    
    public boolean saveItem(ItemDTO itemDTO) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO item (name, unit_price, qty) VALUES(?,?,?)", 
                itemDTO.getName(),
                itemDTO.getPrice(),
                itemDTO.getQty());
    }
    
    public boolean updateItem(ItemDTO itemDTO) throws SQLException {
        return CrudUtil.execute(
                "UPDATE item SET name=?, unit_price=?, qty=? WHERE id=?", 
                itemDTO.getName(),
                itemDTO.getPrice(),
                itemDTO.getQty(), 
                itemDTO.getId());
    }
    
    public boolean deleteItem(String id) throws SQLException {
        return CrudUtil.execute(
                "DELETE FROM item WHERE id=?",  
                id);
    }
    
    public ItemDTO searchItem(String id) throws SQLException {
        ResultSet rs = CrudUtil.execute(
                "SELECT * FROM item WHERE id=?",  
                id);
        
        ItemDTO itemDTO = null;
        
        while(rs.next()) {
            
            itemDTO = new ItemDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("unit_price"),
                        rs.getInt("qty")
                    );
           
        }
        return itemDTO;
    }
    
    public List<ItemDTO> getAllItems() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM item");
        
        List<ItemDTO> itemList = new ArrayList<>();
        
        while(rs.next()) {
            itemList.add(
                    new ItemDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("unit_price"),
                        rs.getInt("qty")
                    )
            );
        }
        return itemList;
    }
    
    public List<String> getAllItemIds() throws SQLException {
    
        ResultSet rs = CrudUtil.execute("SELECT id FROM item");
        
        List<String> itemIdList = new ArrayList<>();
        
        while(rs.next()) {
            itemIdList.add(rs.getString("id"));
        }
        
        return itemIdList;
        
    }

}

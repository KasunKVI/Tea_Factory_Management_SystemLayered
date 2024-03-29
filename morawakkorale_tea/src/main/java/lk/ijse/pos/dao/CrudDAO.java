package lk.ijse.pos.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T> extends SuperDAO{

    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    public boolean add(T entity) throws SQLException, ClassNotFoundException;
    public boolean update(T entity) throws SQLException, ClassNotFoundException;
    public boolean exist(String id) throws SQLException, ClassNotFoundException;
    public int getCount(String type) throws SQLException, ClassNotFoundException;
    public String generateNewId() throws SQLException,ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public T search(String id) throws SQLException, ClassNotFoundException;
    public int getCount() throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllIds() throws SQLException;
}

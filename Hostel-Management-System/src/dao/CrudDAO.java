package dao;

import java.util.List;

public interface CrudDAO<T,ID> extends SuperDAO{
    List<T> getAll() throws Exception; //

    boolean save(T entity) throws Exception;//

    boolean update(T entity) throws Exception;//

    boolean delete(ID id) throws Exception;//

    boolean exist(ID id) ;

    T search(ID id);//Get

}

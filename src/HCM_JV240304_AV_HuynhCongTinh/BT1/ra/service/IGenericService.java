package HCM_JV240304_AV_HuynhCongTinh.BT1.ra.service;

import java.util.List;

public interface IGenericService <T,E>{
    List<T> getAll();
    void save(T t);
    T findById(E e);
    void delete(E e);
}

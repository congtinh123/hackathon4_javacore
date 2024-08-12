package HCM_JV240304_AV_HuynhCongTinh.BT1.ra.service.impl;
import HCM_JV240304_AV_HuynhCongTinh.BT1.ra.model.Catalog;
import HCM_JV240304_AV_HuynhCongTinh.BT1.ra.service.IGenericService;

import java.util.ArrayList;
import java.util.List;

public class CatalogServiceImpl implements IGenericService<Catalog, Integer> {
    private List<Catalog> catalogs = new ArrayList<>();
    @Override
    public List<Catalog> getAll() {
        return catalogs;
    }

    @Override
    public void save(Catalog catalog) {
        catalogs.add(catalog);
    }

    @Override
    public Catalog findById(Integer id) {
        for (Catalog catalog : catalogs){
            if (catalog.getCatalogId() == id){
                return catalog;
            }
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        catalogs.removeIf(catalog -> catalog.getCatalogId() == id);
    }
}

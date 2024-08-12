package HCM_JV240304_BS_HuynhCongTinh.ra.service;

import HCM_JV240304_BS_HuynhCongTinh.ra.model.Catalog;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CatalogService implements IGenericService<Catalog, Integer> {
    private final List<Catalog> catalogs = new ArrayList<>();
    private int nextId = 1;

    @Override
    public List<Catalog> getAll() {
        return new ArrayList<>(catalogs);
    }

    @Override
    public void save(Catalog catalog) {
        catalog.setCatalogId(nextId++);
        catalogs.add(catalog);
    }

    @Override
    public Catalog findById(Integer id) {
        return catalogs.stream()
                .filter(catalog -> catalog.getCatalogId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void delete(Integer id) {
        catalogs.removeIf(catalog -> catalog.getCatalogId() == id);
    }
}

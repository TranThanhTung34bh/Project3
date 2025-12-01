package K23CNT3_TranThanhTung_WebBanDienThoai.service.impl;

import K23CNT3_TranThanhTung_WebBanDienThoai.entity.TttSanPham;
import K23CNT3_TranThanhTung_WebBanDienThoai.repository.TttSanPhamRepository;
import K23CNT3_TranThanhTung_WebBanDienThoai.service.TttSanPhamService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class TttSanPhamServiceImpl implements TttSanPhamService {

    @Autowired
    private TttSanPhamRepository repo;

    @Override
    public List<TttSanPham> findAll() { return repo.findAll(); }

    @Override
    public TttSanPham findById(Integer id) { return repo.findById(id).orElse(null); }

    @Override
    public TttSanPham save(TttSanPham sp) { return repo.save(sp); }

    @Override
    public void deleteById(Integer id) { repo.deleteById(id); }

    @Override
    public List<TttSanPham> findByDanhMuc(Integer maDMSP) { return repo.findByDanhMucMaDMSP(maDMSP); }

    @Override
    public List<TttSanPham> search(String q) { return repo.findByTenSPContainingIgnoreCase(q); }
}

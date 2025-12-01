package K23CNT3_TranThanhTung_WebBanDienThoai.service.impl;

import K23CNT3_TranThanhTung_WebBanDienThoai.entity.TttKhachHang;
import K23CNT3_TranThanhTung_WebBanDienThoai.repository.TttKhachHangRepository;
import K23CNT3_TranThanhTung_WebBanDienThoai.service.TttKhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TttKhachHangServiceImpl implements TttKhachHangService {

    @Autowired
    private TttKhachHangRepository repo;

    @Override
    public List<TttKhachHang> findAll() { return repo.findAll(); }

    @Override
    public TttKhachHang findById(Integer id) { return repo.findById(id).orElse(null); }

    @Override
    public TttKhachHang save(TttKhachHang kh) { return repo.save(kh); }

    @Override
    public void delete(Integer id) { repo.deleteById(id); }
}

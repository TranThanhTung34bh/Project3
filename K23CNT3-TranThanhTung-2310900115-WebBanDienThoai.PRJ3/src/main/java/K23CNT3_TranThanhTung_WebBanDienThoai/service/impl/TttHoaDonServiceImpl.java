package K23CNT3_TranThanhTung_WebBanDienThoai.service.impl;

import K23CNT3_TranThanhTung_WebBanDienThoai.entity.TttHoaDon;
import K23CNT3_TranThanhTung_WebBanDienThoai.repository.TttHoaDonRepository;
import K23CNT3_TranThanhTung_WebBanDienThoai.service.TttHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TttHoaDonServiceImpl implements TttHoaDonService {

    @Autowired
    private TttHoaDonRepository repo;

    @Override
    public List<TttHoaDon> findAll() { return repo.findAll(); }

    @Override
    public TttHoaDon findById(Integer id) { return repo.findById(id).orElse(null); }

    @Override
    public TttHoaDon save(TttHoaDon hd) { return repo.save(hd); }
}

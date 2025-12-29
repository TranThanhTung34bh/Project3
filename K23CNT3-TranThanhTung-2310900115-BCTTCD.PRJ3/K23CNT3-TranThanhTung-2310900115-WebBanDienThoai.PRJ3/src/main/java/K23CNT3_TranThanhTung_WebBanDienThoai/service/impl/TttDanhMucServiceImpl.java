package K23CNT3_TranThanhTung_WebBanDienThoai.service.impl;

import K23CNT3_TranThanhTung_WebBanDienThoai.entity.TttDanhMucSP;
import K23CNT3_TranThanhTung_WebBanDienThoai.repository.TttDanhMucSPRepository;
import K23CNT3_TranThanhTung_WebBanDienThoai.service.TttDanhMucService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class TttDanhMucServiceImpl implements TttDanhMucService {

    @Autowired
    private TttDanhMucSPRepository repo;

    @Override
    public List<TttDanhMucSP> findAll() { return repo.findAll(); }

    @Override
    public TttDanhMucSP findById(Integer id) { return repo.findById(id).orElse(null); }

    @Override
    public TttDanhMucSP save(TttDanhMucSP dm) { return repo.save(dm); }

    @Override
    public void delete(Integer id) { repo.deleteById(id); }
}

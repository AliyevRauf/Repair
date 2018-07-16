package repair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repair.dao.ContractorDao;
import repair.model.Contractor;

import java.util.List;

/**
 * Created by Rauf on 27/06/2018.
 */
@Service
public class ContractorImpl implements ContractorService {

    @Autowired
    ContractorDao contractorDao;

    @Override
    public boolean addContractor(Contractor contractor) {
        return contractorDao.addContractor(contractor);
    }

    @Override
    public List<Contractor> listContractor() {
        return contractorDao.listContractor();
    }

    @Override
    public Contractor listbyId(int id) {
        return contractorDao.listbyId(id);
    }

    @Override
    public boolean edit(Contractor contractor) {
        return contractorDao.editContractor(contractor);
    }


}

package repair.dao;

import repair.model.Contractor;

import java.util.List;

/**
 * Created by Rauf on 27/06/2018.
 */
public interface ContractorDao {

    boolean addContractor(Contractor contractor);

    List<Contractor> listContractor();

    // edit
    Contractor listbyId(int id);

    boolean editContractor(Contractor contractor);
}

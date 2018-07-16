package repair.service;

import org.springframework.stereotype.Component;
import repair.model.Contractor;

import java.util.List;


/**
 * Created by Rauf on 27/06/2018.
 */
@Component
public interface ContractorService {
    // add
    boolean addContractor(Contractor contractor);
    // list
    List<Contractor> listContractor();

    // edit
    Contractor listbyId(int id);

    boolean edit(Contractor contractor);

}

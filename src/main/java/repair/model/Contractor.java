package repair.model;

import org.springframework.stereotype.Controller;

/**
 * Created by Rauf on 27/06/2018.
 */

public class Contractor {
   int id;
   String companyName;
   String address;
   String phone;

    public Contractor() {
    }

    public Contractor(int id, String companyName, String address, String phone) {
        this.id = id;
        this.companyName = companyName;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contractor{" +
                "value=" + id +
                ", companyName='" + companyName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

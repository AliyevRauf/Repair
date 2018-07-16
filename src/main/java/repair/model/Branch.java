package repair.model;

import java.util.List;

/**
 * Created by User on 7/10/2018.
 */
public class Branch {
     int branchId;
    int branchUserId;
     int roleId;
     String roleName;
     String name;
     String email;
     int cityId;
     String cityName;
     String location;
     int regionId;
     String regionName;
     String address;
     List<Users> users;

    public Branch() {
    }

    public Branch(int branchId, String name, String email, int cityId, int regionId, String address, List<Users> users) {
        this.branchId = branchId;
        this.name = name;
        this.email = email;
        this.cityId = cityId;
        this.regionId = regionId;
        this.address = address;
        this.users = users;
    }

    public int getBranchUserId() {
        return branchUserId;
    }

    public void setBranchUserId(int branchUserId) {
        this.branchUserId = branchUserId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }



    @Override
    public String toString() {
        return "Branch{" +
                "branchId=" + branchId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cityId=" + cityId +
                ", regionId=" + regionId +
                ", address='" + address + '\'' +
                ", users=" + users +
                '}';
    }
}

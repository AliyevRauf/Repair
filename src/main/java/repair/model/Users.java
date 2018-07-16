package repair.model;

import java.util.List;

/**
 * Created by User on 7/10/2018.
 */
public class Users {
    int branchId;
    int branchUserId;
    int userId;
    int roleId;
    String name;
    String surname;
    String email;
    String tel;
    String password;
    String roleName;
    List<Branch> branches;

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public Users() {
    }

    public Users(int userId, int roleId, String name, String surname, String email, String tel, String password) {
        this.userId = userId;
        this.roleId = roleId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.tel = tel;
        this.password = password;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

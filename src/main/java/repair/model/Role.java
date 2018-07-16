package repair.model;

/**
 * Created by Rauf on 17/06/2018.
 */
public class Role {
    int id;
    String  roleName;
    String  creatUser;
    String createBranch;
    String createOrder;
    String  usersOrders;

    public Role() {
    }

    public Role(int id, String roleName, String creatUser, String createBranch, String createOrder, String usersOrders) {
        this.id = id;
        this.roleName = roleName;
        this.creatUser = creatUser;
        this.createBranch = createBranch;
        this.createOrder = createOrder;
        this.usersOrders = usersOrders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCreatUser() {
        return creatUser;
    }

    public void setCreatUser(String creatUser) {
        this.creatUser = creatUser;
    }

    public String getCreateBranch() {
        return createBranch;
    }

    public void setCreateBranch(String createBranch) {
        this.createBranch = createBranch;
    }

    public String getCreateOrder() {
        return createOrder;
    }

    public void setCreateOrder(String createOrder) {
        this.createOrder = createOrder;
    }

    public String getUsersOrders() {
        return usersOrders;
    }

    public void setUsersOrders(String usersOrders) {
        this.usersOrders = usersOrders;
    }

    @Override
    public String toString() {
        return "Role{" +
                "value=" + id +
                ", roleName='" + roleName + '\'' +
                ", creatUser='" + creatUser + '\'' +
                ", createBranch='" + createBranch + '\'' +
                ", createOrder='" + createOrder + '\'' +
                ", usersOrders='" + usersOrders + '\'' +
                '}';
    }
}

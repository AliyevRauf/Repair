package repair.model;

import java.util.Arrays;

/**
 * Created by User on 7/2/2018.
 */
public class Order {
    int userId;
    int branchId;
    int id;
    String pin;
    int device_id;
    int[] problems;
    String info;

    public Order() {
    }

    public Order(int id, String pin, int device_id, int[] problems, String info) {
        this.id = id;
        this.pin = pin;
        this.device_id = device_id;
        this.problems = problems;
        this.info = info;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public int[] getProblems() {
        return problems;
    }

    public void setProblems(int[] problems) {
        this.problems = problems;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", pin='" + pin + '\'' +
                ", device_id=" + device_id +
                ", problems=" + Arrays.toString(problems) +
                ", info='" + info + '\'' +
                '}';
    }
}


package repair.model;

import java.util.List;

/**
 * Created by User on 7/4/2018.
 */
public class OrderResponse {
    int order_id;
    String pin;
    String device;
    int deviceId;
    List<Problem> problems;
    String info;

    public OrderResponse() {
    }

    public OrderResponse(int order_id, String pin, String device, List<Problem> problems, String info) {
        this.order_id = order_id;
        this.pin = pin;
        this.device = device;
        this.problems = problems;
        this.info = info;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderResponse that = (OrderResponse) o;

        return order_id == that.order_id;
    }

    @Override
    public int hashCode() {
        return order_id;
    }
}

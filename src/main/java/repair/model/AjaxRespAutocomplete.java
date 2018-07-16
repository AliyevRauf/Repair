package repair.model;


public class AjaxRespAutocomplete {
    int value;
    String label;

    public AjaxRespAutocomplete() {

    }

    public AjaxRespAutocomplete(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "AjaxRespAutocomplete{" +
                "value=" + value +
                ", label='" + label + '\'' +
                '}';
    }
}

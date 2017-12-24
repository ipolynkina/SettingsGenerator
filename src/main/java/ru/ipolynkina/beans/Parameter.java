package ru.ipolynkina.beans;

public class Parameter {

    private String coefficient;
    private String post;
    private Boolean useWithoutCategory;
    private Integer minCategory;
    private Integer maxCategory;
    private String amount;
    private String type;
    private String nomenclature;
    private String value;
    private String activate;

    public Parameter(String coefficient, String post, Boolean useWithoutCategory, Integer minCategory, Integer maxCategory,
                     String amount, String type, String nomenclature, String value, String activate) {
        this.coefficient = coefficient;
        this.post = post;
        this.useWithoutCategory = useWithoutCategory;
        this.minCategory = minCategory;
        this.maxCategory = maxCategory;
        this.amount = amount;
        this.type = type;
        this.nomenclature = nomenclature;
        this.value = value;
        this.activate = activate;
    }

    public String getCoefficient() {
        return coefficient;
    }

    public String getPost() {
        return post;
    }

    public Boolean getUseWithoutCategory() {
        return useWithoutCategory;
    }

    public Integer getMinCategory() {
        return minCategory;
    }

    public Integer getMaxCategory() {
        return maxCategory;
    }

    public String getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getNomenclature() {
        return nomenclature;
    }

    public String getValue() {
        return value;
    }

    public String getActivate() {
        return activate;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(coefficient + " ");
        builder.append(post + " ");
        builder.append(useWithoutCategory + " ");
        builder.append(minCategory + " ");
        builder.append(maxCategory + " ");
        builder.append(amount + " ");
        builder.append(type + " ");
        builder.append(nomenclature + " ");
        builder.append(value + " ");
        builder.append(activate);
        return builder.toString();
    }
}

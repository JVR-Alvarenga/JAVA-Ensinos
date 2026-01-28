package entities;

public abstract class Person {
    private String name;
    private Double anualIncome;
    private Double taxPaid;

    public Person() {
    }
    public Person(String name, Double anualIncome) {
        this.name = name;
        this.anualIncome = anualIncome;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Double getAnualIncome() {
        return anualIncome;
    }
    public void setAnualIncome(Double anualIncome) {
        this.anualIncome = anualIncome;
    }

    public Double getTaxPaid() {
        return taxPaid;
    }
    public void setTaxPaid(Double taxPaid) {
        this.taxPaid = taxPaid;
    }

    public abstract Double individualTax();


}

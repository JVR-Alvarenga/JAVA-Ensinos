package entities;

public class LegalEntity extends Person {
    private Integer numberOfEmployees;

    public LegalEntity() {
        super();
    }
    public LegalEntity(String name, Double anualIncome, Integer numberOfEmployees) {
        super(name, anualIncome);
        this.numberOfEmployees = numberOfEmployees;
    }

    public Integer getNumberOfEmployees() {
        return numberOfEmployees;
    }
    public void setNumberOfEmployees(Integer numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    @Override
    public Double individualTax() {
        if (numberOfEmployees > 10) {
            super.setTaxPaid(super.getAnualIncome() * 0.14);
        } else if (numberOfEmployees <= 10 && numberOfEmployees > 0) {
            super.setTaxPaid(super.getAnualIncome() * 0.16);
        }
        return super.getTaxPaid();
    }
}

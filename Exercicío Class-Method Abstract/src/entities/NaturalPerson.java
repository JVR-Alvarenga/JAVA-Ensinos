package entities;

public class NaturalPerson  extends Person {
    private Double healthExpenditures;

    public NaturalPerson() {
        super();
    }
    public NaturalPerson(String name, Double anualIncome, Double healthExpenditures) {
        super(name, anualIncome);
        this.healthExpenditures = healthExpenditures;
    }

    public Double getHealthExpenditures() {
        return healthExpenditures;
    }
    public void setHealthExpenditures(Double healthExpenditures) {
        this.healthExpenditures = healthExpenditures;
    }

    @Override
    public Double individualTax() {
       if (super.getAnualIncome() < 20000.0 && super.getAnualIncome() > 0) {
            super.setTaxPaid(super.getAnualIncome() * 0.15 - healthExpenditures * 0.5);
       } else if (super.getAnualIncome() >= 20000.0) {
            super.setTaxPaid(super.getAnualIncome() * 0.25 - healthExpenditures * 0.5);
       }
       return super.getTaxPaid();
    }


}

package test.artetrad.com.budgettrack;

public class Budget {
    // private variables
    int id;
    String dateBegin;
    String dateEnd;
    float income;
    float superMarket;
    float cigarettes;
    float fun;
    float buffer;
    float newSavings;
    float pastSavings;
    float allSavings;
    float total;

    // Empty constructor
    public Budget(){
    }

    public Budget(int id, String dateBegin, String dateEnd, float income, float superMarket, float cigarettes, float fun, float buffer, float newSavings) {
        this.id = id;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.income = income;
        this.superMarket = superMarket;
        this.cigarettes = cigarettes;
        this.fun = fun;
        this.buffer = buffer;
        this.newSavings = newSavings;

    }

    public Budget(String dateBegin, String dateEnd, float income, float superMarket, float cigarettes, float fun, float buffer, float newSavings) {
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.income = income;
        this.superMarket = superMarket;
        this.cigarettes = cigarettes;
        this.fun = fun;
        this.buffer = buffer;
        this.newSavings = newSavings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public float getSuperMarket() {
        return superMarket;
    }

    public void setSuperMarket(float superMarket) {
        this.superMarket = superMarket;
    }

    public float getCigarettes() {
        return cigarettes;
    }

    public void setCigarettes(float cigarettes) {
        this.cigarettes = cigarettes;
    }

    public float getFun() {
        return fun;
    }

    public void setFun(float fun) {
        this.fun = fun;
    }

    public float getBuffer() {
        return buffer;
    }

    public void setBuffer(float buffer) {
        this.buffer = buffer;
    }

    public float getNewSavings() {
        return newSavings;
    }

    public void setNewSavings(float newSavings) {
        this.newSavings = newSavings;
    }

    public float getPastSavings() {
        return pastSavings;
    }

    public void setPastSavings(float pastSavings) {
        this.pastSavings = pastSavings;
    }

    public float getAllSavings() {
        allSavings = pastSavings + newSavings;
        return allSavings;
    }

    public float getTotalWithoutOtherExpenses() {
        total = this.getAllSavings() + this.getSuperMarket()
                + this.getCigarettes() + this.getBuffer() +this.getFun();
        return total;
    }
}

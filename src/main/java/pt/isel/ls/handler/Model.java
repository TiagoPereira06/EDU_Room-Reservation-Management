package pt.isel.ls.handler;

public class Model {
    Object primaryData;
    Object secondaryData;

    public Model(Object primaryData, Object secondaryData) {
        this.primaryData = primaryData;
        this.secondaryData = secondaryData;
    }

    public Model(Object primaryData) {
        this.primaryData = primaryData;
    }

    public Model() {
    }

    public Object getPrimaryData() {
        return primaryData;
    }

    public void setPrimaryData(Object primaryData) {
        this.primaryData = primaryData;
    }

    public Object getSecondaryData() {
        return secondaryData;
    }

    public void setSecondaryData(Object secondaryData) {
        this.secondaryData = secondaryData;
    }
}

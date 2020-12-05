package ort.assi.blp.entities;

public class SysSubject {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    private Integer temp = 0;

    public SysSubject(String name){
        this.name = name;
    }

    public void writeObject(SysObject object, Integer value){
        object.setValue(value);
    }
}

package edu.ib.zpo11lista;

import java.io.Serializable;

public class Polynomial implements Serializable {
    private String [] values;
    public Polynomial(String equation){
        values=equation.split(",");
    }
    @Override
    public String toString(){
        StringBuilder out = new StringBuilder();
        for(int i=0;i<values.length;i++){
            if(Double.parseDouble(values[i])>=0)
            out.append("%2B").append(values[i]).append("x^").append(values.length-1-i);
            else out.append(values[i]).append("x^").append(values.length-1-i);
        }
        return out.toString();
    }
    public String replaceXWithValue(String x){
        return toString().replaceAll("x","("+x+")");
    }
}

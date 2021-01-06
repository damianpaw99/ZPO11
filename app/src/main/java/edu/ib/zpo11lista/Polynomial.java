package edu.ib.zpo11lista;

import java.io.Serializable;

/**
 * Class describing polynomial values
 */
public class Polynomial implements Serializable {
    private String [] values;

    /**
     * Constructor
     * @param equation Input String
     */
    public Polynomial(String equation){
        values=equation.split(",");
    }

    /**
     * Method converting values to String format used in API
     * @return String in format used by API
     */
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

    /**
     * Method replacing x to given number
     * @param x Number
     * @return Changed String
     */
    public String replaceXWithValue(String x){
        return toString().replaceAll("x","("+x+")");
    }
}

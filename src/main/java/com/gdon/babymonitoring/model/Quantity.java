/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gdon.babymonitoring.model;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * @author gduvinage
 */
@JsonFormat(shape=JsonFormat.Shape.OBJECT)
public enum Quantity {
    PETIT(1),MOYEN(2),GROS(3);
    
    private final int value;
    
    private Quantity(final int value){
        this.value = value;
    }
    
    public int getValue(){
        return this.value;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdon.babymonitoring.model;

import java.util.Date;

/**
 *
 * @author gduvinage
 */
public class DailyMonitor {

    private int id;
    private Date entryDay;
    private int amountTaken;
    private String comment;
    private int amountPrepared;
    private Date entryHour;
    private Quantity peeQuantity;

    public DailyMonitor(int id, Date entryDay, int amountTaken, String comment, int amountPrepared, Date entryHour) {
        this.id = id;
        this.entryDay = entryDay;
        this.amountTaken = amountTaken;
        this.amountPrepared = amountPrepared;
        this.entryHour = entryHour;
        this.comment = comment;
    }

    public DailyMonitor(int id, Date entryDay, int amountTaken, int amountPrepared, Date entryHour) {
        this.id = id;
        this.entryDay = entryDay;
        this.amountTaken = amountTaken;
        this.amountPrepared = amountPrepared;
        this.entryHour = entryHour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getEntryDay() {
        return entryDay;
    }

    public void setEntryDay(Date entryDay) {
        this.entryDay = entryDay;
    }

    public int getAmountTaken() {
        return amountTaken;
    }

    public void setAmountTaken(int amountTaken) {
        this.amountTaken = amountTaken;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getAmountPrepared() {
        return amountPrepared;
    }

    public void setAmountPrepared(int amountPrepared) {
        this.amountPrepared = amountPrepared;
    }

    public Date getEntryHour() {
        return entryHour;
    }

    public void setEntryHour(Date entryHour) {
        this.entryHour = entryHour;
    }

    public Quantity getPeeQuantity() {
        return peeQuantity;
    }

    public void setPeeQuantity(Quantity peeQuantity) {
        this.peeQuantity = peeQuantity;
    }
}

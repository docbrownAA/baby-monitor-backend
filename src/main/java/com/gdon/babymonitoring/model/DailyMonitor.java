/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdon.babymonitoring.model;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gduvinage
 */
public class DailyMonitor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DailyMonitor.class);
    private int id;
    private Date entryDay;
    private int amountTaken;
    private String comment;
    private int amountPrepared;
    private Date entryHour;
    private Quantity peeQuantity;

    public DailyMonitor(Date entryDay, int amountTaken, String comment, int amountPrepared, Date entryHour) {
        try {

            this.entryDay = entryDay;
            this.amountTaken = amountTaken;
            this.amountPrepared = amountPrepared;
            this.entryHour = entryHour;
            this.comment = comment;
            LOGGER.info("Constructeur: "
                    + this.toString()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "DailyMonitor{" + "id=" + id + ", entryDay=" + entryDay + ", amountTaken=" + amountTaken + ", comment=" + comment + ", amountPrepared=" + amountPrepared + ", entryHour=" + entryHour + ", peeQuantity=" + peeQuantity + '}';
    }

    public DailyMonitor(Date entryDay, int amountTaken, int amountPrepared, Date entryHour) {
        try {
            this.id = id;
            this.entryDay = entryDay;
            this.amountTaken = amountTaken;
            this.amountPrepared = amountPrepared;
            this.entryHour = entryHour;

        } catch (Exception e) {
            e.printStackTrace();
        }
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

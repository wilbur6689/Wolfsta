package com.teamwolf.beans;


import javax.persistence.*;

@Entity
public class Tour {
    private int tourId = 0;
    private int tourType = 0;

    /**
     * Constructor
     */
    public Tour() {

    }// public Tour()

    @Id
    @Column(name="TOUR_ID")
    public int getTourId() {
        return tourId;
    }// public int getTourId()

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }// public void setTourId(int tourId)

    @Column(name="TOUR_TYPE")
    public int getTourType() {
        return tourType;
    }// public int getTourType()

    public void setTourType(int tourType) {
        this.tourType = tourType;
    }// public void setTourType(int tourType)
}// public class Tour

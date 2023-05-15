package com.example.videostore;

public class Item extends Entity{
    private String title;
    private int numberOfCopies;
    private double rentalFee;
    private RentalType rentalType;
    private LoanType loanType;
    private Genre genre;

    public Item(String title, int numberOfCopies, double rentalFee, RentalType rentalType, LoanType loanType, Genre genre) {
        this.title = title;
        this.numberOfCopies = numberOfCopies;
        this.rentalFee = rentalFee;
        this.rentalType = rentalType;
        this.loanType = loanType;
        this.genre = genre;
    }

    public void setId(String id) {
        super.setId(id);
    }

    public enum RentalType {
        record, dvd, game
    }

    public enum LoanType {
        twoDays, OneWeek
    }

    public enum Genre {
        action, drama, comedy, horror
    }

    public void setRentalFee(double rentalFee) {
        this.rentalFee = rentalFee;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRentalType(RentalType rentalType) {
        this.rentalType = rentalType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    // ///////////////////////////////////////
    public String getTitle() {
        return title;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public double getRentalFee() {
        return rentalFee;
    }

    public String getRentalType() {
        return switch (this.rentalType) {
            case record -> "Record";
            case dvd -> "DVD";
            case game -> "Game";
        };
    }

    public String getLoanType() {
        return switch (this.loanType) {
            case twoDays -> "2 days";
            case OneWeek -> "1 week";
        };
    }

    public String getGenre() {
        return switch (this.genre) {
            case drama -> "Drama";
            case action -> "Action";
            case comedy -> "Comedy";
            case horror -> "Horror";
        };
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", numberOfCopies=" + numberOfCopies +
                ", rentalFee=" + rentalFee +
                ", rentalType=" + rentalType +
                ", loanType=" + loanType +
                ", genre=" + genre +
                '}';
    }
}

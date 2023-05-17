package com.example.videostore;


import java.util.Calendar;

public class Item extends Entity{
    private String title;
    private int numberOfCopies;
    private double rentalFee;
    private RentalType rentalType;
    private LoanType loanType;
    private Genre genre;

    public Item(String id,String title, int numberOfCopies, double rentalFee, RentalType rentalType, LoanType loanType, Genre genre) {
        super(id);
        this.title = title;
        this.numberOfCopies = numberOfCopies;
        this.rentalFee = rentalFee;
        this.rentalType = rentalType;
        this.loanType = loanType;
        this.genre = genre;
    }

    public void setId(String id) throws ItemError {
        if (idValidItem(id) == false) {
            throw new ItemError("invalid Id");
        }
        super.setId(id);
    }

    public enum RentalType {
        record, dvd, game
    }

    public enum LoanType {
        twoDays, OneWeek
    }

    public enum Genre {
        action, drama, comedy, horror, noGenre
    }

    public void setRentalFee(double rentalFee) {
        this.rentalFee = rentalFee;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public void setTitle(String title) throws ItemError {
        if (titleValid(title) == false) {
            throw new ItemError("in valid title");
        }
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
            case noGenre -> "";
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

    public boolean titleValid(String title) {
        if (title == null || title.length() < 1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean rentalTypeValid(String rentalType) {
        if (rentalType.equals("record")) {
            return true;
        }
        if (rentalType.equals("dvd")) {
            return true;
        }
        if (rentalType.equals("game")) {
            return true;
        }
        return false;
    }
    public boolean idValidItem(String id) {
        if (id.length() != 9) {
            return false;
        } else if (!id.matches("I\\d{3}-\\d{4}")) {
            return false;
        }
        return true;
    }
}

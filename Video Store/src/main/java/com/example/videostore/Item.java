package com.example.videostore;


import com.example.videostore.Utility.ItemUtility;

public class Item extends Entity{
    private String title;
    private int numberOfCopies;
    private double rentalFee;
    private RentalType rentalType;
    private LoanType loanType;
    private Genre genre;

    public Item(String id,String title,RentalType rentalType, LoanType loanType, int numberOfCopies, double rentalFee, Genre genre) {
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

    public boolean setRentalType(String rentalType) {
        RentalType rental = ItemUtility.convertRentalType(rentalType);

        switch (rental) {
            case dvd -> {
                this.rentalType = RentalType.dvd;
                return true;
            }
            case game -> {
                this.rentalType = RentalType.game;
                return true;
            }
            case record -> {
                this.rentalType = RentalType.record;
                return true;
            }
        }
        return false;
    }

    public boolean setLoanType(String loanType) {
        LoanType loan = ItemUtility.convertLoanType(loanType);

        switch (loan) {
            case OneWeek -> {
                this.loanType = LoanType.OneWeek;
                return true;
            }
            case twoDays -> {
                this.loanType = LoanType.twoDays;
                return true;
            }
        }
        return false;
    }

    public boolean setGenre(String genre) {
        Genre genreType = ItemUtility.convertGenre(genre);

        switch (genreType) {
            case drama -> {
                this.genre = Genre.drama;
                return true;
            }
            case action -> {
                this.genre = Genre.action;
                return true;
            }
            case horror -> {
                this.genre = Genre.horror;
                return true;
            }
            case comedy -> {
                this.genre = Genre.comedy;
                return true;
            }
            case noGenre -> {
                if (rentalType == RentalType.game) {
                    this.genre = Genre.noGenre;
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
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
    public boolean idValidItem(String id) {
        if (id.length() != 9) {
            return false;
        } else if (!id.matches("I\\d{3}-\\d{4}")) {
            return false;
        }
        return true;
    }
}

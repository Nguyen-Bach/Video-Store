package com.example.videostore;


import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Item {
    private String id;
    private String title;
    private int numberOfCopies;
    private double rentalFee;
    private RentalType rentalType;
    private LoanType loanType;
    private Genre genre;

    private static final ArrayList<Item> items = new ArrayList<>() {};

    public Item(String id,String title,RentalType rentalType, LoanType loanType, int numberOfCopies, double rentalFee, Genre genre) {
        this.id = id;
        this.title = title;
        this.rentalType = rentalType;
        this.loanType = loanType;
        this.numberOfCopies = numberOfCopies;
        this.rentalFee = rentalFee;
        this.genre = genre;
    }

    public void setId(String id) throws ItemError {
        if (isValidId(id) == false) {
            throw new ItemError("invalid Id");
        }
        this.id = id;
    }

    public enum RentalType {
        record, dvd, game
    }

    public enum LoanType {
        twoDays, OneWeek
    }

    public enum Genre {
        action, drama, comedy, horror, NG
    }

    public void setRentalFee(double rentalFee) {
        this.rentalFee = rentalFee;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public void setTitle(String title) throws ItemError {
        if (titleValid(title) == false) {
            throw new ItemError("invalid title");
        }
        this.title = title;
    }

    public boolean setRentalType(String rentalType) {
        RentalType rental = convertRentalType(rentalType);

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
        LoanType loan = convertLoanType(loanType);

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
        Genre genreType = convertGenre(genre);

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
            case NG -> {
                if (rentalType == RentalType.game) {
                    this.genre = Genre.NG;
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
            case NG -> "NG";
        };
    }

    public String getId() {
        return id;
    }

    public static ArrayList<Item> getArrayItems() {
        return items;
    }


    public static ArrayList<Item> initializeItems() throws FileNotFoundException {
        items.clear();
        Scanner scanFile = new Scanner(new File("src/main/resources/com/example/videostore/items.txt"));
        try {
            while (scanFile.hasNext()) {

                List<String> item = Arrays.asList(scanFile.nextLine().split(","));


                String id = item.get(0);
                String title = item.get(1);

                String stringRentalType = item.get(2);
                Item.RentalType rentalType = convertRentalType(stringRentalType);

                String stringLoanType = item.get(3);
                Item.LoanType loanType = convertLoanType(stringLoanType);

                int numOfCopies = Integer.parseInt(item.get(4));
                double rentalFee = Double.parseDouble(item.get(5));

                String stringGenre = item.get(6);
                Item.Genre genre = convertGenre(stringGenre);

                items.add(new Item(id, title, rentalType, loanType, numOfCopies, rentalFee, genre));

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    public static Item.RentalType convertRentalType(String rentalType) {
        if (rentalType.equalsIgnoreCase("Record")) {
            return Item.RentalType.record;
        }
        if (rentalType.equalsIgnoreCase("DVD")) {
            return Item.RentalType.dvd;
        }
        if (rentalType.equalsIgnoreCase("Game")) {
            return Item.RentalType.game;
        }
        return null;
    }
    public static Item.LoanType convertLoanType(String loanType) {
        if (loanType.equalsIgnoreCase("1-week")) {
            return Item.LoanType.OneWeek;
        }
        if (loanType.equalsIgnoreCase("2-day")) {
            return Item.LoanType.twoDays;
        }
        return null;
    }
    public static Item.Genre convertGenre(String genre) {
        if (genre.equalsIgnoreCase("action")) {
            return Item.Genre.action;
        }
        if (genre.equalsIgnoreCase("horror")) {
            return Item.Genre.horror;
        }
        if (genre.equalsIgnoreCase("comedy")) {
            return Item.Genre.comedy;
        }
        if (genre.equalsIgnoreCase("drama")) {
            return Item.Genre.drama;
        }
        if (genre.equalsIgnoreCase("NG")) {
            return Item.Genre.NG;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
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
    public static boolean isValidId(String id) {
        if (id.length() != 9) {
            return false;
        } else if (!id.matches("I\\d{3}-\\d{4}")) {
            return false;
        }
        return true;
    }

    public static boolean isRentalTypeValid(String rentalType) {
        RentalType rental = convertRentalType(rentalType);

        switch (rental) {
            case dvd, game, record -> {
                return true;
            }
        }
        return false;
    }
    public static boolean isLoanTypeValid(String loanType) {
        LoanType loan = convertLoanType(loanType);

        switch (loan) {
            case OneWeek, twoDays -> {
                return true;
            }
        }
        return false;
    }
    public static boolean isGenreValid(String genreType) {
        Genre genre = convertGenre(genreType);

        switch (genre) {
            case action, horror, comedy, drama, NG -> {
                return true;
            }
        }
        return false;
    }

}

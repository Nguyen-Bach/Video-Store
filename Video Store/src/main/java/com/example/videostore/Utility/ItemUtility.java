package com.example.videostore.Utility;

import com.example.videostore.Item;

public class ItemUtility {

    public Item convert(String string) {
        String list[] = string.split(",");

        String id = list[0];
        String title = list[1];

        String stringRentalType =  list[2];
        Item.RentalType rentalType = convertRentalType(stringRentalType);

        String stringLoanType = list[3];
        Item.LoanType loanType = convertLoanType(stringLoanType);

        int numberOfCopies = Integer.parseInt(list[4]);
        double rentalFee = Double.parseDouble(list[5]);

        String stringGenre = list[6];
        Item.Genre genre = convertGenre(stringGenre);

        return new Item(id, title, rentalType, loanType, numberOfCopies, rentalFee, genre);
    }

    public static Item.RentalType convertRentalType(String rentalType) {
        if (rentalType.equalsIgnoreCase("record")) {
            return Item.RentalType.record;
        }
        if (rentalType.equalsIgnoreCase("dvd")) {
            return Item.RentalType.dvd;
        }
        if (rentalType.equalsIgnoreCase("game")) {
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
        if (genre.equalsIgnoreCase("")) {
            return Item.Genre.noGenre;
        }
        return null;
    }
}
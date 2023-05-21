//package com.example.videostore.Utility;
//
//import com.example.videostore.Item;
//
//public class ItemUtility {
//
//    public Item convert(String string) {
//        String list[] = string.split(",");
//
//        String id = list[0];
//        String title = list[1];
//
//        String stringRentalType =  list[2];
//        Item.RentalType rentalType = convertRentalType(stringRentalType);
//
//        String stringLoanType = list[3];
//        Item.LoanType loanType = convertLoanType(stringLoanType);
//
//        int numberOfCopies = Integer.parseInt(list[4]);
//        double rentalFee = Double.parseDouble(list[5]);
//
//        String stringGenre = list[6];
//        Item.Genre genre = convertGenre(stringGenre);
//
//        return new Item(id, title, rentalType, loanType, numberOfCopies, rentalFee, genre);
//    }
//
//



//}
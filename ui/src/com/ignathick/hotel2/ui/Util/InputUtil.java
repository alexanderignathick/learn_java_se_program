package com.ignathick.hotel2.ui.Util;

import com.ignathick.hotel2.model.Room.Status;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtil {

    private static final String datePattern = "yyyy-MM-dd";
    private static final String nullStr  = "null";
    private static final SimpleDateFormat format = new SimpleDateFormat(datePattern);

    public static Integer readInteger() throws InputMismatchException {

        Integer i = 0;
        Scanner sc = new Scanner(System.in);

        try{
            i = sc.nextInt();
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Input from keyboard does not match the Integerregular expression, or is out of range");
        }
        return i;
    }
    public static Double readDouble() throws InputMismatchException {

        Double d = 0d;
        Scanner sc = new Scanner(System.in);

        try{
            d = sc.nextDouble();
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Input from keyboard does not match the Integerregular expression, or is out of range");
        }
        return d;
    }

    public static Long readLong() throws InputMismatchException {

        Long l = 0l;
        Scanner sc = new Scanner(System.in);

        try{
            l = sc.nextLong();
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Input from keyboard does not match the Integerregular expression, or is out of range");
        }
        return l;
    }

    public static Date readDate() throws InputMismatchException {

        String s;
        Scanner sc = new Scanner(System.in);

        try{
            s = sc.nextLine();
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Input from keyboard does not match the Integerregular expression, or is out of range");
        }

        return getDateFromString(s);

    }

    public static Date getDateFromString(String stringDate) {

        if (stringDate.equals(nullStr)) {
            return null;
        }
        String dateString = format.format(new Date());
        Date someDate = new Date();

        try {
            someDate = format.parse(stringDate);
        } catch (Exception ex) {
            throw new InputMismatchException("Error in date convert");
        }

        return someDate;
    }

    public static Status readStatus() throws InputMismatchException {

        String s;
        Scanner sc = new Scanner(System.in);

        try{
            s = sc.nextLine();
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Input from keyboard does not match the Integerregular expression, or is out of range");
        }

        return getStatusFromString(s);

    }

    public static Status getStatusFromString(String stringStatus) {

        if (stringStatus.equals(nullStr)) {
            return null;
        }
       Status status;

        try {
            status =  Status.valueOf(stringStatus);
        } catch (Exception ex) {
            throw new InputMismatchException("Error in Enum convert");
        }
        return status;
    }

    public static String getScanStringFromKeyboard() throws InputMismatchException {

        String s;
        Scanner sc = new Scanner(System.in);

        try{
            s = sc.nextLine();
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Input from keyboard does not match the Integerregular expression, or is out of range");
        }

        return s;

    }

    public static int [] getScanFromKeyboardToArray() throws InputMismatchException {

        int [] scanArray = new int[8];
        int number = 0;
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i<scanArray.length; i++) {

            try {
                number = sc.nextInt();
            } catch (InputMismatchException e) {
                throw new InputMismatchException("Input from keyboard does not match the Integerregular expression, or is out of range");
//                System.out.println("Input from keyboard does not match the Integerregular expression, or is out of range");
//                return scanArray;
            }
            scanArray[i] = number;
        }

        return scanArray;

    }

    /**
     * This method clear the current screen
     *
     *
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}


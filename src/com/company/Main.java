package com.company;

/* Написать программу, демонстрирующую работу с классом: дано N квадратов и M пирамид, найти
квадрат с минимальной площадью и количество пирамид с высотой более числа a (a вводить)
*/

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


@Getter
@Setter

public class Main {

    private static ArrayList<Pyramid> pyramidArrayList = new ArrayList<>();
    private static ArrayList<Square> squareArrayList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        String bitText;
        File file = new File("C:\\Java\\text.txt");
        if (file.exists()) {
            System.out.println("File for reading is open.");
        }
        else{
            System.out.println("File not found.");
            System. exit(0);
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        int in=fileInputStream.read();
        StringBuilder bitToNum=new StringBuilder();
        final int maxSide = 11;
        Scanner sc = new Scanner(System.in);
        int numSq = 0;
        int sqNum=0;
        int pyNum=0;
        double minArea;
        int numMinArea = 0;

//количество квадратов
        while (numSq <= 0) {
            if (numSq < 0) {
                System.out.println("Number<0 .Enter the number of squares:");
            }
            else {
                System.out.println("Enter the number of squares:");
            }
            while (!sc.hasNextInt()) {
                System.out.println("That not a number .Enter the number of squares:");
                sc.next();
            }
            numSq = sc.nextInt();
        }

        Scanner num = new Scanner(System.in);
        double numberH = 0;

//высота пирамиды для поиска
        while (numberH <= 0) {
            if (numberH < 0) {
                System.out.println("Number<0 .Enter the number :");
            } else {
                System.out.println("Enter the height of the minimum height to count pyramids with a height greater than the entered one: ");
            }
            while (!num.hasNextInt()) {
                System.out.println("That not a number .Enter the number :");
                num.next();
            }
            numberH = num.nextInt();
        }

//количество пирамид
        Scanner tw = new Scanner(System.in);
        int numberP = 0;
        int count = 0;
        while (numberP <= 0) {
            if (numberP < 0) {
                System.out.println("Number<0. Enter the number of pyramid: ");
            } else {
                System.out.println("Enter the number of pyramids: ");
            }
            while (!tw.hasNextInt()) {
                System.out.println("That not a number . Enter the number of pyramid: ");
                tw.next();
            }
            numberP = tw.nextInt();
        }

//чтение файла
        if (in!=-1){
            bitToNum.append((char)in);
            while((in=fileInputStream.read())!= -1 ){//*
                if(in==48 || in==49){//0 1
                    bitToNum.append((char)in);//поиск числового значения 1001...
                }
                else if(in==95){//_ пирамида и квадрат
                    long l = Long.parseLong(String.valueOf(bitToNum), 2);
                    double value = Double.longBitsToDouble(l);
                    bitToNum=new StringBuilder();
                    squareArrayList.add(new Square());
                    squareArrayList.get(sqNum).side=value;
                    pyramidArrayList.add(new Pyramid());
                    pyramidArrayList.get(pyNum).side=value;
                    numSq++;
                    numberP++;
                    sqNum++;
                    pyNum++;
                }
                else if(in==35){//# квадрат
                    long l = Long.parseLong(String.valueOf(bitToNum), 2);
                    double value = Double.longBitsToDouble(l);
                    bitToNum=new StringBuilder();
                    squareArrayList.add(new Square());
                    squareArrayList.get(sqNum).side=value;
                    numSq++;
                    sqNum++;
                }
                else if(in==42){//* пирамида

                    long l = Long.parseLong(String.valueOf(bitToNum), 2);
                    double value = Double.longBitsToDouble(l);
                    bitToNum=new StringBuilder();
                    pyramidArrayList.add(new Pyramid());
                    pyramidArrayList.get(pyNum).side=value;
                    pyNum++;

                }
            }
        }

//поиск минимальной площади квадрата
        minArea = maxSide;
        for (int m = 0; m < numSq; m++) {
            squareArrayList.add(new Square());
            if (squareArrayList.get(m).side > 0) {
                System.out.println(squareArrayList.get(m));
                if (minArea > squareArrayList.get(m).side) {
                    minArea = squareArrayList.get(m).side;
                    numMinArea = m;
                }
            }
        }

//массив пирамид
        for (int i = 0; i < numberP; i++) {
            pyramidArrayList.add(new Pyramid());
            if (numSq-1 < i) {
                pyramidArrayList.get(i).side = Math.random() * 11;
            }
            else{
                pyramidArrayList.get(i).side = squareArrayList.get(i).side;
            }
            if (pyramidArrayList.get(i).apothem > 0) {
                System.out.println(pyramidArrayList.get(i));
                if (Math.sqrt(pyramidArrayList.get(i).apothem * pyramidArrayList.get(i).apothem - pyramidArrayList.get(i).getAreaSq()) > numberH) {
                    count++;
                }
            }
        }

//запись файла
        int forWrite = Math.max(numberP, numSq);

        try(FileOutputStream fileOut = new FileOutputStream(file,false))//перезапись
        {
            for(int sp=0;sp<forWrite;sp++){
                if (sp>numberP-1 ) {
                    bitText = String.format("%6.2f", squareArrayList.get(sp).side);
                    double value = Double.parseDouble(bitText.replace(",", "."));
                    long l = Double.doubleToLongBits(value);
                    String bin = Long.toBinaryString(l);
                    byte[] buffer = bin.getBytes();
                    fileOut.write(buffer);
                    fileOut.write('#');
                }
                else if (sp<numberP-1 && sp<=numSq-1 || sp<=numberP-1 && sp<numSq-1 || sp<=numberP-1 && sp<=numSq-1) {
                    bitText = String.format("%6.2f", squareArrayList.get(sp).side);
                    double value = Double.parseDouble(bitText.replace(",", "."));
                    long l = Double.doubleToLongBits(value);
                    String bin = Long.toBinaryString(l);
                    byte[] buffer = bin.getBytes();
                    fileOut.write(buffer);
                    fileOut.write('_');
                }

                else if(sp>numSq-1 ){
                    bitText = String.format("%6.2f", pyramidArrayList.get(sp).side);
                    double value = Double.parseDouble(bitText.replace(",", "."));
                    long l = Double.doubleToLongBits(value);
                    String bin = Long.toBinaryString(l);
                    byte[] buffer = bin.getBytes();
                    fileOut.write(buffer);
                    fileOut.write('*');
                }
            }

        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("The file has been written");
        System.out.println("Number of pyramids with height > " + numberH + " = " + count);
        System.out.println("Square with min area " + squareArrayList.get(numMinArea));

    }
}

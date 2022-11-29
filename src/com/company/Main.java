package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
        ArrayList<Integer> container = new ArrayList<>();
        for (int i = 1; i <= 10000; i++)
        {
            int element = (int) (Math.random() * i);
            container.add(element);
        }

        RandomAccessFileWorker(container);
    }

    static void TextFileWorker(ArrayList<Integer> container)
    {
        String filename = "data.txt";
        File file = new File(filename);

        if (!file.exists())
        {
            try (PrintWriter out = new PrintWriter(filename))
            {
                out.print(container); //print
            } catch (Exception e)
            {
                System.out.println(e);
            }
        }

        else
        {
            try (Scanner sc = new Scanner(new File(filename)))
            {
                System.out.println(sc.nextLine());
            } catch (Exception e)
            {
                System.out.println(e);
            }
        }
    }

    static void SerializationWorker(ArrayList<Integer> container){
        String filename = "data.ser";
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))){
            out.writeObject(container);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename)))
        {
            ArrayList<Integer> printer = (ArrayList<Integer>) in.readObject();
            System.out.println(printer);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    static void RandomAccessFileWorker(ArrayList<Integer> container)
    {
        String filename = "data.bin";
        try (RandomAccessFile out = new RandomAccessFile(filename, "rw"))
        {
            for (int i: container)
            {
                out.writeInt(i);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        try (RandomAccessFile in = new RandomAccessFile(filename, "r"))
        {
            ArrayList <Integer> result = new ArrayList<>();
            in.seek(50*Integer.BYTES);
            for (int i = 1; i<=10000; i++)
            {
                int element = in.readInt();
                if (i%100==0)
                {
                    result.add(element);
                    System.out.println(result);
                }
            }
            System.out.println(result);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
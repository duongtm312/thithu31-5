package io;

import models.Student;

import java.io.*;
import java.util.ArrayList;

public class WriteAndRead {
    public ArrayList<Student> read(){
        File file = new File("student.csv");
        ArrayList<Student> list = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String str = bufferedReader.readLine();
            while (str != null) {
                String[] arr = str.split(",");
                int age = Integer.parseInt(arr[2]);
                int ms = Integer.parseInt(arr[5]);
                list.add(new Student(arr[0], arr[2],age, arr[3], arr[4], ms));
                str = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public void write(ArrayList<Student> student){
        File file = new File("student.csv");
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Student s:student
            ) {
                bufferedWriter.write(s.write());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

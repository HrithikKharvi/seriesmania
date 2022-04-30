package Files;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class LogInFile {

    private static Map<String,String> credentials = new LinkedHashMap<>();

    public static void main(String[] args) {

//        try(DataOutputStream out = new DataOutputStream(new FileOutputStream("credentials.dat"))){
//
//            out.writeInt(credentials.size());
//
//            for(Map.Entry<String,String> entries : credentials.entrySet()){
//               out.writeUTF(entries.getKey() + "," + entries.getValue());
//            }
//
//        } catch(IOException e){
//            System.out.println("Errror : " + e.getMessage());
//        }
    }

    static{
        try(DataInputStream in = new DataInputStream(new FileInputStream("credentials.dat"))){

            int totalData = in.readInt();

            for(int i=0; i<totalData;i++){
                String data[] = in.readUTF().split(",");
//                System.out.println(data[0]);
//                System.out.println(data[1]);
                credentials.put(data[0],data[1]);
            }

        } catch(Exception e){

        }
    }

    public Map<String,String> getCredentials(){
        return credentials;
    }

    public void save(){
        try(DataOutputStream out = new DataOutputStream(new FileOutputStream("credentials.dat"))){

            out.writeInt(credentials.size());

            for(Map.Entry<String,String> entries : credentials.entrySet()){
                out.writeUTF(entries.getKey() + "," + entries.getValue());
            }

        } catch(IOException e){
            System.out.println("Errror : " + e.getMessage());
        }

    }

}

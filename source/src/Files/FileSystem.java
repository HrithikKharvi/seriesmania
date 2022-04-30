package Files;

import com.example.Index;
import com.example.WebSeries;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileSystem {

    private static RandomAccessFile ra;
    private static ArrayList<WebSeries> webSeries;
    private static ArrayList<Index> index = new ArrayList<>();

    public FileSystem(){

    }

    public static void main(String[] args) {
        try(ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("series.dat")))){
            for(WebSeries seri : webSeries){
                out.writeObject(seri);
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }


    static{
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("series.dat"))){
            webSeries = new ArrayList<>();
            boolean eof = false;
            while(!eof){
                try{
                    WebSeries webSeri  = (WebSeries) in.readObject();
                    webSeries.add(webSeri);
                } catch(EOFException e){
                    eof = true;
                }

            }

        } catch(IOException|ClassNotFoundException e){

        }

    }

    public List<WebSeries> getSeries(){
        return webSeries;
    }

    public void addSeries(WebSeries series){
        webSeries.add(series);
    }

    public void save() {
        try(ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("series.dat")))){
            for(WebSeries seri : webSeries){
                out.writeObject(seri);
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public WebSeries search(String name){
        WebSeries result = null;
        for(WebSeries seri : webSeries){
            if(seri.getName().equalsIgnoreCase(name)){
                result = seri;
            }
        }

        return result;

    }



}

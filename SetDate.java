import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

public class SetDate {

    public static void setDate(String date, String time, String fileName) throws IllegalArgumentException {
        ArrayList<Integer> tokenizedDate = new ArrayList<Integer>();

        //Valid data format is separated by "/" and valid time format is separated by ":"
        StringTokenizer d = new StringTokenizer(date, "/");
        StringTokenizer t = new StringTokenizer(time, ":");

        //converting the tokenized string to integer type for the date object
        try {
            while (d.hasMoreTokens())
                tokenizedDate.add(Integer.parseInt(d.nextToken()));             //adding tokens to array for later us

            while (t.hasMoreTokens())
                tokenizedDate.add(Integer.parseInt(t.nextToken()));
        }

        catch(NumberFormatException e) {
            throw new IllegalArgumentException("Error converting value to integer");
        }

        //creating date object from the given information
        Calendar calendar= Calendar.getInstance();
        try {
            calendar.setLenient(false);                                         //ensuring correct date format
            calendar.set(tokenizedDate.get(2), tokenizedDate.get(0)-1, tokenizedDate.get(1), tokenizedDate.get(3), tokenizedDate.get(4), tokenizedDate.get(5));
        }
        catch(Exception e) {
            throw new IllegalArgumentException("Incorrect Date or Time format");
        }

        //opening the desired file to check weather or not it exists
        File file = new File(fileName);
        if(file.exists()) {
            System.out.println("Changing Last Modified date...");
            file.setLastModified(calendar.getTimeInMillis());                   //changing last modified
            System.out.println("Last Modified Date set to:\t"+ calendar.getTime());

        }else {
            throw new IllegalArgumentException("File does not exist: " + fileName);
        }
    }

    public static void main(String []args){

        //checking the count of commandline arguments
        if(args.length<3)
            throw new IllegalArgumentException("Function takes 3 input parameters");
        if(args.length>3)
            System.out.println("Warning: Function takes only 3 input parameters!");
        try {
            setDate(args[0], args[1], args[2]);
        }
        catch(IllegalArgumentException illegalArgumentException){
            System.out.println(illegalArgumentException);
        }
    }
}

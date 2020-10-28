import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        ArrayList<Integer> nums = new ArrayList<>();
        //String filename = args[0];
        //File file = new File(filename);
        try{
            File file = new File(args[0]);
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                nums.clear();
                String[] split = sc.nextLine().split(", ");
                for(String str: split){
                    nums.add(Integer.parseInt(str));
                }
                Solver solver = new Solver();
                solver.solve(nums);
                solver.print();
            }


        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(Exception e){
            e.getMessage();
        }

    }
}

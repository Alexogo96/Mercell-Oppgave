import java.util.ArrayList;

public class Solver {
    private int extra_toss;
    private int total;
    private int current_frame;
    private int frame_no;
    //private boolean first_throw;
    private StringBuilder output;
    private String headline = "| f1 | f2 | f3 | f4 | f5 | f6 | f7 | f8 | f9 |  f10  |";
    //TODO: fix last frame

    public Solver(){
        output = new StringBuilder();
}
    private void strike(){
        this.extra_toss = 2;
        current_frame=0;
        //last frame handling
        if(frame_no==10){
            output.append("X, ");
            frame_no=11;
            return;
        }
        //coming from a strike in frame 10
        else if(frame_no==11){
            output.append("X   |");
            return;
        }
        //coming from a spear in frame 10
        else if(frame_no>11){
            output.append("X|");
            return;
        }
        frame_no++;
        output.append("X   |");

    }

    private void spear(){
        this.extra_toss = 1;
        //last frame handling
        if(frame_no==10){
            output.append("/, ");
            //no example of how output looks with spear at the end
            current_frame=0;//jumping straight into "second toss"
            frame_no=12;
            return;
        }
        output.append("/|");
        current_frame=0;
    }

    private void gutter(boolean first){

        String appending;
        if(first){
            current_frame = -1; //used to not send next toss into first for the frame
            appending = "-, ";
        }else{
            current_frame=0;
            appending = "-|";
            //padding behind for last frame when no spear/strike
            if(frame_no==10){
                appending = "-   |";
            }
        }

        output.append(appending);
    }

    private void normal(boolean first, int num){
        String appending;
        if(first){
            current_frame+=num;
            appending = num+", ";
            if(frame_no>10){
                appending = num+"|";
            }

        }
        else{
            appending = num + "|";
            current_frame=0;
            //padding behind for last frame when no spear/strike
            if(frame_no==10){
                appending = "-   |";
            }
        }
        output.append(appending);




    }
    private void first(int num){
        //strike
        if(num==10){
            strike();
        }
        //gutter
        else if(num==0){
            gutter(true);
        }
        else{
            normal(true, num);
        }

    }
    private void second(int num){
        //spear


        if(current_frame+num==10){
            spear();
        }
        //gutter
        else if(num==0){
            gutter(false);
        }
        //anything else
        else{
            normal(false, num);
        }
        frame_no++;
    }

    public void print(){
        String output_string = output.toString();
        System.out.println(headline);
        System.out.println(output_string);
        System.out.printf("score: %d\n", total);
    }





    public void solve(ArrayList<Integer> nums){
        output.setLength(0);
        current_frame = 0;
        extra_toss = 0;
        total = 0;
        frame_no=1;
        output.append("|");
        for(int num : nums){
            if(frame_no<11) total += num;
            //addition for strike/spear
            if(extra_toss>0){
                total+=num;
                extra_toss--;
            }
            //first throw
            if(current_frame==0){
                first(num);
            }
            //avoiding toss after gutter ending up in first throw
            else if(current_frame<0){
                current_frame=0;
                second(num);
            }
            //second throw
            else{
                second(num);
            }
        }
    }
    public String getOutput(){
        return output.toString();
    }
    public int getExtra_toss(){
        return extra_toss;
    }
    public int getTotal(){
        return total;
    }
    public int getCurrent_frame(){
        return current_frame;
    }
    public int getFrame_no(){
        return frame_no;
    }


}

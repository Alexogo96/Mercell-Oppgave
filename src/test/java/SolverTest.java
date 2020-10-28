import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class SolverTest {
    Solver solver;
    ArrayList<Integer> input;


    @Before
    public void setUp() throws Exception {
        solver = new Solver();
        input = new ArrayList<>();
    }

    @After
    public void tearDown() throws Exception {
        solver = null;
        input = null;
    }

    @Test
    public void solve_print_and_value() {
        int[] t1 = {0, 10, 5, 4, 9, 1, 2, 5, 3, 2, 4, 2, 3, 3, 4, 6, 10, 5, 5, 10};
        for(int i: t1){
            input.add(i);
        }
        //testing balanced/generic run + starting gutter/spear + strike trailing a spear
        solver.solve(input);
        Assert.assertEquals(120, solver.getTotal());
        Assert.assertEquals("|-, /|5, 4|9, /|2, 5|3, 2|4, 2|3, 3|4, /|X   |5, /, X|", solver.getOutput());
        input.clear();

        int[] t2 = {10, 5, 4, 9, 1, 2, 5, 3, 2, 4, 2, 3, 3, 4, 6, 10, 5, 5, 10};
        for(int i: t2){
            input.add(i);
        }
        //testing another balanced/generic run + starting strike
        solver.solve(input);
        Assert.assertEquals(124, solver.getTotal());
        Assert.assertEquals("|X   |5, 4|9, /|2, 5|3, 2|4, 2|3, 3|4, /|X   |5, /, X|", solver.getOutput());
        input.clear();

        int[] t3 = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        for(int i: t3){
            input.add(i);
        }
        //testing pure strike
        solver.solve(input);
        Assert.assertEquals(200, solver.getTotal());
        Assert.assertEquals("|X   |X   |X   |X   |X   |X   |X   |X   |X   |X, X   |", solver.getOutput());
        input.clear();

        int[] t4 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for(int i: t4){
            input.add(i);
        }
        //testing my average run in bowling
        //testing just gutterballs
        solver.solve(input);
        Assert.assertEquals(0, solver.getTotal());
        Assert.assertEquals("|-, -|-, -|-, -|-, -|-, -|-, -|-, -|-, -|-, -|-, -   |", solver.getOutput());
        input.clear();

        int[] t5 = {10, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for(int i: t5){
            input.add(i);
        }
        //testing gutter-spear after strike
        solver.solve(input);
        Assert.assertEquals(30, solver.getTotal());
        Assert.assertEquals("|X   |-, /|-, -|-, -|-, -|-, -|-, -|-, -|-, -|-, -   |", solver.getOutput());
        input.clear();

        int[] t6 = {0, 10, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for(int i: t6){
            input.add(i);
        }
        //testing trailing spears
        solver.solve(input);
        Assert.assertEquals(20, solver.getTotal());
        Assert.assertEquals("|-, /|-, /|-, -|-, -|-, -|-, -|-, -|-, -|-, -|-, -   |", solver.getOutput());
        input.clear();

        int[] t7 = {10, 10, 10, 10, 10, 10, 10, 10, 10, 0, 10, 10};
        for(int i: t7){
            input.add(i);
        }
        //testing strike after spare in frame 10 & strikes
        solver.solve(input);
        Assert.assertEquals(200, solver.getTotal());
        Assert.assertEquals("|X   |X   |X   |X   |X   |X   |X   |X   |X   |-, /, X|", solver.getOutput());
        input.clear();
        //Testing normal number after spear at frame 10
        int[] t8 = {0, 10, 5, 4, 9, 1, 2, 5, 3, 2, 4, 2, 3, 3, 4, 6, 10, 5, 5, 5};
        for(int i: t8){
            input.add(i);
        }
        solver.solve(input);
        Assert.assertEquals(115, solver.getTotal());
        Assert.assertEquals("|-, /|5, 4|9, /|2, 5|3, 2|4, 2|3, 3|4, /|X   |5, /, 5|", solver.getOutput());
        input.clear();

    }
}
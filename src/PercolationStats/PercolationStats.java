package PercolationStats;
import Percolation.Percolation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by stefan on 20/3/2017.
 */
public class PercolationStats {
    private Double Sum;
    private Integer Times;
    private int n;
    private Double MO ;
    private ArrayList<Double> Results=new ArrayList<Double>();
    private double stddev_val;
    public static void main(String args[]){
        //System.out.print(Arrays.toString(args));
        PercolationStats TestObject = new PercolationStats(Integer.valueOf(args[0]),Integer.valueOf(args[1]));
        System.out.println("mean\t\t\t\t\t"+TestObject.mean());
        System.out.println("stddev\t\t\t\t\t"+TestObject.stddev());
        System.out.println("95% confidence interval\t["+TestObject.confidenceLo()+","+TestObject.confidenceHi()+"]");
    }
    private static Double getResultOf(Percolation t,int n){
        Random rand = new Random();
        int OpenedSpots=0;
        double Blocks=Math.pow(n,2);
        while(!t.percolates()){
            t.open(Math.abs(rand.nextInt()%n),Math.abs(rand.nextInt()%n));
            OpenedSpots+=1;
        }
        return OpenedSpots/Blocks;
    }
    public PercolationStats(int n, int trials) throws java.lang.IllegalArgumentException {
        if (n < 0) throw new java.lang.IllegalArgumentException();
        this.Times = trials;
        this.Sum = 0.0;
        this.n = n;
        Double Temp;
        while (trials > 0) {
            trials -= 1;
            Temp = this.getResultOf(new Percolation(this.n),this.n);
            this.Sum += Temp;
            this.Results.add(Temp);
        }
    }


    public double mean() {
        return this.MO=this.Sum/this.Times;
    }





    public double stddev() {
        double res=.0;
        for (double tmp:this.Results) {
            res+=Math.pow((tmp-this.MO),2);
        }
        return this.stddev_val=Math.abs(res/(this.MO-1));
    }

    public double confidenceLo() {
        return this.MO+(1.96*Math.sqrt(this.stddev_val))/Math.sqrt(this.Times);
    }



    // low  endpoint of 95% confidence interval
    public double confidenceHi()       {
        return this.MO-(1.96*Math.sqrt(this.stddev_val))/Math.sqrt(this.Times);
    }           // high endpoint of 95% confidence interval
}

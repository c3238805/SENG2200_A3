 /*
 * SENG2200 Programming Languages & Paradigms
 * Assignment 3 (100 marks, 15%) - Due Jun 04, 23:59
 * 
 * Student Name: NI ZENG
 * Student Number: C3238805
 */
import java.util.*;

public class item {

    private double M;  // Average processing time of an item in a stage, given as a program input.
    private double N;  //Range of processing time in a stage, given as a program input.
    private String uniqueID;
    private double P;  // process time of a stage
    private StageHistory [] S = new StageHistory [8];
    private int StageCounter; 
    private Random r = new Random();
    private double pTime;

    //Constructor to be private so that this class cannot be instantiated
    public item(){     
        //initial M and N 
        Singleton s = Singleton.getInstance();
         this.uniqueID = s.createId();
        // create an storage for StageHistory 
        
        StageCounter = 0;       // initial the counter
    }

//==========================================================================
    public void  setProcessTime(){
        double d = r.nextDouble();
        P = getM()+ getN()*(d - 0.5);   //calculate the processing tiem of a stage
        
    }
    public void  setdoubleProcessTime(){
        double d = r.nextDouble();
        P = 2*getM()+ 2*getN()*(d - 0.5);   //calculate the processing tiem of a stage
        
    }
    public double getProcessTime(){
        return P;
    }

//==========================================================================
    public void setM(double M){
        this.M = M;
    }
    public double getM(){        //calculate the Porcessing time of the item then return.
        return M;
    }

    public void setN(double N){
        this.N = N;
    }
    public double getN(){        //calculate the Porcessing time of the item then return.
        return N;
    }
//========================================================================
    public void addHistory( String stageName,double T1, double T2){
        if(stageName.contains("S0")){
            StageCounter = 0;
        }
        else if(stageName.contains("S1")){
            StageCounter = 1;
        }
        else if(stageName.contains("S2a")){
            StageCounter = 2;
        }
        else if(stageName.contains("S2b")){
            StageCounter = 3;
        }
        else if(stageName.contains("S3")){
            StageCounter = 4;
        }
        else if(stageName.contains("S4a")){
            StageCounter = 5;
        }
        else if(stageName.contains("S4b")){
            StageCounter = 6;
        }
        else if(stageName.contains("S5")){
            StageCounter = 7;
        }

        S[StageCounter]  = new StageHistory();
        S[StageCounter].setStageName(stageName);
        S[StageCounter].setStartTime(T1);
        S[StageCounter].setLeavedTime(T2);
        
        
    }
    public StageHistory[] getHistory(){       
        return S;        
    }
    public String toString(){
        String itemrecord = "";
        for(int i=0;i<S.length;i++){
            itemrecord += S[i].getStageName() + "  "+S[i].getStartTime()+"  " + S[i].getLeavedTime() + "\n";
        }
        return itemrecord;

    }
    public void setpTime(double now){
        pTime = now + getProcessTime();
    }
    public double getpTime(){
        return pTime;
    }
    public String getID(){
        return uniqueID;
    }
  
}
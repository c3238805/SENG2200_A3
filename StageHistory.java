 /*
 * SENG2200 Programming Languages & Paradigms
 * Assignment 3 (100 marks, 15%) - Due Jun 04, 23:59
 * 
 * Student Name: NI ZENG
 * Student Number: C3238805
 */

// StageHistory is a class that will record each item enter the each Stage's history
public class StageHistory {
    private String stageName;
    private double startTime ;
    private double leavedTime;

    public StageHistory(){
        this.stageName = null;
        this.startTime = 0;
        this.leavedTime = 0;
    }
    //================== setter and getter ======================================
    public void setStageName(String stageName){
        this.stageName = stageName;
    }
    public String getStageName(){
        return stageName;
    }
    public void setStartTime(double startTime){
        this.startTime = startTime;
    }
    public double getStartTime(){
        return startTime;
    }
    public void setLeavedTime(double leavedTime){
        this.leavedTime = leavedTime;
    }
    public double getLeavedTime(){
        return leavedTime;
    }
  //======================================================================
    

}

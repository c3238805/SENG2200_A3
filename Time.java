 /*
 * SENG2200 Programming Languages & Paradigms
 * Assignment 3 (100 marks, 15%) - Due Jun 04, 23:59
 * 
 * Student Name: NI ZENG
 * Student Number: C3238805
 */
public class Time {
    private double time;

    public Time(){ 
        time = 0;      
    }
    public double now(){
        return time;
    }
    public void addtime(double processingTime){
        time += processingTime;
    }
    
    public void setTime(double time){
        this.time  = time;
    }
    public double getTime(){
        return time;
    }
}

 /*
 * SENG2200 Programming Languages & Paradigms
 * Assignment 3 (100 marks, 15%) - Due Jun 04, 23:59
 * 
 * Student Name: NI ZENG
 * Student Number: C3238805
 */
import java.util.LinkedList;

public class LimitQueue<E> extends LinkedList<E> {
    
    private int Qmax;
    private double EnterQueueTime;      // variable for enterqueue time
    private double LeveQueueTime;
    private double TotalStayTime;
    private int ItemCount;
    private int searchCount;

    public LimitQueue() {       
    }
    public LimitQueue(int Qmax) {
        this.Qmax = Qmax;
    }
   
    //retrun ture if size() >= Qmax
    public boolean isfull() { 
        return (size() >= Qmax) ;        
    }

    public void setTotalStayTime(double LeveQueueTime,double EnterQueueTime){
        TotalStayTime +=  LeveQueueTime - EnterQueueTime;  
          
    }
    public double getItemInTime(){       
        return EnterQueueTime;
    }
    public double getItemOutTime(){       
        return LeveQueueTime;
    }
    public double getTotalStayTime(){       
        return TotalStayTime;
    }
    public void addItemCount(){
        ItemCount ++;   // when item enter queue, plus one itemCount
    }
    public int getItemCount(){
        return ItemCount;
    }
 
    
    public void setCurrentItemNumber(LimitQueue<item> i,double itemInTime, double ItemOutTime){
            
            double timeCount = itemInTime;
            while(timeCount<ItemOutTime){
                // loop the current queue for each time unit
                for(item x:i ){
                    if(x.getpTime()<=timeCount){
                        searchCount ++;
                        
                    }                    
                }
                
                timeCount++;
            }
                       
    }
    public double getAveNumer(){
        // total number of item devided by time limit
        return  Double.valueOf(searchCount)/10000000;
    }
}

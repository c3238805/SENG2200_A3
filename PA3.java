 /*
 * SENG2200 Programming Languages & Paradigms
 * Assignment 3 (100 marks, 15%) - Due Jun 04, 23:59
 * 
 * Student Name: NI ZENG
 * Student Number: C3238805
 */
import java.util.*;

public class PA3 {

    public static void main(String[] args) {

        double M = Double.parseDouble(args[0]) ;    //input of average processiong
        double N = Double.parseDouble(args[1]); 
        int Qmax = Integer.parseInt(args[2] ); 
        
        Time time = new Time();     // initial time 

        LimitQueue<item> Q01 =new LimitQueue<>(Qmax);
        LimitQueue<item> Q12= new LimitQueue<>(Qmax);
        LimitQueue<item> Q23 =new LimitQueue<>(Qmax);
        LimitQueue<item> Q34 =new LimitQueue<>(Qmax);
        LimitQueue<item> Q45= new LimitQueue<>(Qmax);

        LimitQueue<item> finished = new LimitQueue<>();
        
        //generate all stage
        Queue<AbstractStages> allStage = new LinkedList<>();
            allStage.add(new S0(M,N,"S0",Q01,time));
            allStage.add(new normalStage("S1",Q01,Q12,time));
            allStage.add(new twoTimesStage("S2a",Q12,Q23,time));
            allStage.add(new twoTimesStage("S2b",Q12,Q23,time));
            allStage.add(new normalStage("S3",Q23,Q34,time));
            allStage.add(new twoTimesStage("S4a",Q34,Q45,time));
            allStage.add(new twoTimesStage("S4b",Q34,Q45,time));
            allStage.add(new endStage("S5",Q45,finished,time));
        
        Priority<AbstractStages> testStage = new Priority<>();


        //S0 processing stage (keep creating item util time end)
            
                
        
            testStage.add(allStage.peek());          
        while (!testStage.isEmpty() && time.now()<  10000000) {
           
            
            testStage.remove();
            for(AbstractStages as : allStage){
                testStage.add(as);
            }
                        
        }
//==================== calculate the Work for stages and inner storage  ========================================================
        double [] work = new double [8];
        
        int workCount = 0;  

        // get the starving count and block count from each stage for later display         
        for(AbstractStages as: allStage){
            
            work[workCount] = (10000000 - (as.getStarvingCount() + as.getBlockCount())) / 10000000 *100;
            workCount++;        
            
        }   
//==================== Production Paths calculation  ========================================================
boolean passedFromStage = false;
boolean passedToStage = false;

    int S2a4a = 0;
        for(item item: finished){
            StageHistory []  history  = item.getHistory();
            for(int i=0; i <history.length; i++){
                if(history [i] != null){
                    if(history[i].getStageName().contains("S2a")){
                        passedFromStage = true;
                    }
                    else if (history[i].getStageName().contains("S4a")){
                        passedToStage = true;
                    }
                }
                
            }            
            if(passedFromStage == true && passedToStage == true){
                S2a4a ++;

            }
            // reset boolean for next time history check
            passedFromStage = false;
            passedToStage = false;
        }
           

        int S2a4b = 0;
            for(item item: finished){
                StageHistory []  history  = item.getHistory();
                for(int i=0; i <history.length; i++){
                    if(history [i] != null){
                        if(history[i].getStageName().contains("S2a")){
                            passedFromStage = true;
                        }
                        else if (history[i].getStageName().contains("S4b")){
                            passedToStage = true;
                        }
                    }
                }            
                if(passedFromStage == true && passedToStage == true){
                    S2a4b ++;
    
                }
                // reset boolean for next time history check
                passedFromStage = false;
                passedToStage = false;
            }
            int S2b4a = 0;
            for(item item: finished){
                StageHistory []  history  = item.getHistory();
                for(int i=0; i <history.length; i++){
                    if(history [i] != null){
                        if(history[i].getStageName().contains("S2b")){
                            passedFromStage = true;
                        }
                        else if (history[i].getStageName().contains("S4a")){
                            passedToStage = true;
                        }
                    }
                }            
                if(passedFromStage == true && passedToStage == true){
                    S2b4a ++;
    
                }
                // reset boolean for next time history check
                passedFromStage = false;
                passedToStage = false;
            }
            int S2b4b = 0;
            for(item item: finished){
                StageHistory []  history  = item.getHistory();
                for(int i=0; i <history.length; i++){
                    if(history [i] != null){
                        if(history[i].getStageName().contains("S2b")){
                            passedFromStage = true;
                        }
                        else if (history[i].getStageName().contains("S4b")){
                            passedToStage = true;
                        }
                    }
                }            
                if(passedFromStage == true && passedToStage == true){
                    S2b4b ++;
    
                }
                // reset boolean for next time history check
                passedFromStage = false;
                passedToStage = false;
            }
//==================== display result  ========================================================
        
        System.out.print("Number of item created:   "+ ((S0) allStage.peek()).getTotalItem() + "\n");
        System.out.print("Number of item finished:   "+ finished.size() + "\n");

        int displaycount = 0;
        
        System.out.println("----------------------------------------------------");
        System.out.println("Stage:     Work[%]        Starve[t]       Block[t]");
        for(AbstractStages as:allStage){
            System.out.println(as.getStageName()+"         "+String.format("%5.2f", work[displaycount]) +"        "+ String.format("%5.2f", as.getStarvingCount()) +"          "+  String.format("%5.2f", as.getBlockCount()));
            displaycount++;
        }
        System.out.println("Storage Queues:");
        System.out.println("-------------------------------"); 
        System.out.println("Store     AvgTime[t]        AvgItems");
        System.out.println("Q01      "+ String.format("%5.2f",Q01.getTotalStayTime()/Q01.getItemCount())+"       " + String.format("%5.2f",Q01.getAveNumer()));
        System.out.println("Q12      "+ String.format("%5.2f",Q12.getTotalStayTime()/Q12.getItemCount())+"       " +String.format("%5.2f",Q12.getAveNumer()));
        System.out.println("Q23      "+ String.format("%5.2f",Q23.getTotalStayTime()/Q23.getItemCount())+"       " + String.format("%5.2f",Q23.getAveNumer()));
        System.out.println("Q34      "+ String.format("%5.2f",Q34.getTotalStayTime()/Q34.getItemCount())+"       " + String.format("%5.2f",Q34.getAveNumer()));
        System.out.println("Q45      "+ String.format("%5.2f",Q45.getTotalStayTime()/Q45.getItemCount())+"       " + String.format("%5.2f",Q45.getAveNumer()));
        

        System.out.println("Production Paths:");
        System.out.println("-------------------------------");
        System.out.println("S2a -> S4a:   "  + S2a4a);
        System.out.println("S2a -> S4b:   "  + S2a4b);
        System.out.println("S2b -> S4a:   "  + S2b4a);
        System.out.println("S2b -> S4b:   "  + S2b4b);

 

    }
}



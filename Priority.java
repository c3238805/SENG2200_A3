 /*
 * SENG2200 Programming Languages & Paradigms
 * Assignment 3 (100 marks, 15%) - Due Jun 04, 23:59
 * 
 * Student Name: NI ZENG
 * Student Number: C3238805
 */
import java.util.PriorityQueue;
import java.util.Queue;


public class Priority<E>  {
    
    private Queue<AbstractStages> queue;
    private double simulatorTime;
    private double simulatorNow;

    public Priority() {
        queue = new PriorityQueue<>();      // use compareTo 
    }

    public void add(AbstractStages s){
        if(s.isAvailable()){    // item = null
            s.checkComplete();      // assign work to stage (generate item or take item from pre)
            if(s.item != null ){                            
                queue.add(s);                         
            } 
        }
        else{
            if(s.item.getpTime()<=simulatorNow){
                double T1= s.time.now()-s.item.getProcessTime();                
                double T2 = s.time.now();
                s.item.addHistory(s.getStageName(), T1, T2);
                if(s.getNextQ() !=null){
                    s.getNextQ().setTotalStayTime(T2,T1);
                    s.getNextQ().addItemCount();
                    s.getNextQ().setCurrentItemNumber(s.getNextQ(),T1,T2);
                }
                s.addToStorage();
                s.item  = null; 
                queue.remove(); 
            }
        }
        
                  
    }
    public AbstractStages peek(){
        AbstractStages temp;
        temp = queue.peek(); 
        return temp;
    }
    public boolean isEmpty() {
        return queue.isEmpty();       
    }
    public void remove(){
        // when removing from PriorityQueue, means is the turn for the stage
        //  to input its item into its linner queue
        if(!queue.isEmpty()){
            // add simulator's time
            if(simulatorTime <= queue.peek().item.getpTime() ){
                queue.peek().time.setTime(queue.peek().item.getpTime());
                simulatorNow = queue.peek().time.now();
            }

//==================================================================            
            // get and remove the top of the queue
            AbstractStages temp = queue.peek();
            double T1= temp.time.now()-temp.item.getProcessTime();                
            double T2 = temp.time.now(); 
            
            
              //only remove from PriorityQueue when item is add to storage
              if(!temp.isBlock()){  // if next queue is not full
                if(temp.getNextQ() !=null){
                     // set up total Stay time in queue 
                    temp.getNextQ().setTotalStayTime(T2,T1);
                    temp.getNextQ().addItemCount();
                    temp.getNextQ().setCurrentItemNumber(temp.getNextQ(),T1,T2);
                }
                
                // add processing History 
                temp.item.addHistory(temp.getStageName(), T1, T2);
                
                // if item finishe processing ,the addToSotrage, then remove           
                
                temp.addToStorage();
                // after input the item to queue, set item to null
                temp.item  = null; 
                queue.remove(); 
              }
              else{  
                    queue.peek().setBlock();                  
                    for(AbstractStages as: queue){   //check for next even
                        if(!as.isBlock()){
                            as.time.setTime(as.item.getpTime());
                            // add new simulatorTime
                            simulatorTime = as.time.now();
                            T1= as.time.now()-as.item.getProcessTime();
                            // set up item's T2
                            T2 = as.time.now();
                            
                            

                            // add processing History before delete in the Stage 0 
                            as.item.addHistory(as.getStageName(), T1, T2);
                            if(as.getNextQ() !=null){
                                // set up total Stay time in queue    
                                as.getNextQ().setTotalStayTime(T2,T1);
                                as.getNextQ().addItemCount();
                                as.getNextQ().setCurrentItemNumber(as.getNextQ(),T1,T2);
                            }
                            as.addToStorage();
                            // after input the item to queue, set item to null
                            as.item  = null; 

                            queue.remove(as); 
                            break;
                            
                        }
                   }

                }


              }                       
        }
        
       
        
        
}






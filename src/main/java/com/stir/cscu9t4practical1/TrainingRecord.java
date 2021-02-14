// An implementation of a Training Record as an ArrayList
package com.stir.cscu9t4practical1;




import java.util.*;


public class TrainingRecord {
    private List<Entry> tr;
    
    public TrainingRecord() {
        tr = new ArrayList<Entry>();
    } //constructor
    
    // add a record to the list
   public void addEntry(Entry e){
     
      try {
         tr.add(e); 
      }
         
        catch(Exception a) {
          System.out.println("Error within inputs.");
        }//Further checking for input errors this should catch any errors that are missed and print to console
              
          
   } // addClass
   public boolean ifDupe(String n, int d, int m, int y) {
     boolean bool = false;
    ListIterator<Entry> iter = tr.listIterator();
  
  
    
    while(iter.hasNext()) 
      {
      Entry current = iter.next();
      if(current.getName().equals(n) && current.getDay()==d && current.getMonth()==m && current.getYear()==y) {
        bool = true;
        
        }
      } 
     return bool;
   }
   // look up the entry of a given day and month
   public String lookupEntry (int d, int m, int y) {
     ListIterator<Entry> iter = tr.listIterator();
       String result = "";
       while (iter.hasNext()) {
          Entry current = iter.next();
          if (current.getDay()==d && current.getMonth()==m && current.getYear()==y) 
             result += current.getEntry();
            }
       if(result.length() == 0) {
         result = "No entries found";
       }
       return result;
   } // lookupEntry
   
   public String FindAllByDate(int d, int m, int y) {
     ListIterator<Entry> iter = tr.listIterator();
       String result = "";
       while (iter.hasNext()) {
          Entry current = iter.next();
          if (current.getDay()==d && current.getMonth()==m && current.getYear()==y) 
             result += current.getEntry() + " ";
             System.out.println(current.getStart());
            }
       if(result.length() == 0) {
         result = "No Entries Found";
       }
     return result;
   }
   
    public String FindAllByName(String name) {
      
     ListIterator<Entry> iter = tr.listIterator();
       String result = "";
       name = name.toLowerCase();
       while (iter.hasNext()) {
          Entry current = iter.next();
          if (current.getName().toLowerCase().equals(name)) 
             result += current.getEntry() + " ";
            }
       if(result.length() == 0) {
         result = "No Entries Found";
       }
     return result; //Searches the list for matching name independent of case.
   }
    
    public void RemoveEntry(String name, int d, int m, int y) {
      ListIterator<Entry> iter = tr.listIterator();
      
      int index = -1;
      
      while(iter.hasNext()) 
        {
        Entry current = iter.next();
        if(current.getName().equals(name) && current.getDay()==d && current.getMonth()==m && current.getYear()==y) {
          index = tr.indexOf(current);
          
          }
        } 
      if(index > -1) {
        tr.remove(index);
        
        //Iterates the list to find a match (case sensitive), and removes it.
      }
   }
    
    public String weeklyDistance(String name, String type) {
      name = name.toLowerCase();
      int weekStart = 0;
      float cycleDistance = 0;
      float sprintDistance = 0;
      float swimDistance = 0;
      int s = tr.size() - 1;
      int mCheck = 0; 
      int dCheck = 0;
      
      
      while(s >= 0) {
        Entry recent = tr.get(s);
          if(weekStart == 0) {
            if(recent.getName().toLowerCase().equals(name)) {
          
                weekStart = recent.getStart() > recent.getDay() ? recent.getStart() -7 : recent.getStart();
                //Fixes for issue where getStart gets the start of preceding week if it is on the last day of the week.
                mCheck = recent.getMonth();
                dCheck = recent.getDay();
                
                continue;
                }
            else { 
                s--;
                
              } 
          }
        if(weekStart > 0) {
            for(int k = s; k >= 0; k--) {
                recent = tr.get(k);
             for(int i = weekStart; i <= dCheck; i++) {
                        if(recent.getName().toLowerCase().equals(name) && recent.getDay() == i && recent.getMonth() == mCheck) {
                            if(recent instanceof SprintEntry) {
                              sprintDistance += recent.getDistance();
                            }
                            else if(recent instanceof CycleEntry) {
                              cycleDistance += recent.getDistance();
                            }
                            else {
                              swimDistance += recent.getDistance();
                            }
                            
                        }
                       
                       
                    }
                    s--;
            }

         
        }
      }
      
    
      float total = swimDistance + cycleDistance + sprintDistance;
      String str = "Total Distance Cycled: " + cycleDistance + "\n Total Distance ran: " + sprintDistance + 
          "\n Total Distance Swam: " + swimDistance + "\n Total Distance Travelled: " + total;
      System.out.println(str);
      return str;
    }
    
    
   
   // Count the number of entries
   public int getNumberOfEntries(){
       return tr.size();
   }
   // Clear all entries
   public void clearAllEntries(){
       tr.clear();
   }
   
} // TrainingRecord
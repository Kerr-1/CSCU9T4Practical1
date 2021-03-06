// This class holds information about a single training session
package com.stir.cscu9t4practical1;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.sun.org.apache.xml.internal.utils.SuballocatedByteVector;


public class Entry {
  private String name;
  private Calendar dateAndTime;
  private float distance;
 

  
  public Entry (String n, int d, int m, int y, int h, int min, int s, float dist) {
    name = n;
    Calendar inst = Calendar.getInstance();
    inst.set(y,m-1,d,h,min,s);
    dateAndTime = inst;
    distance = dist;
  } //constructor
  
  public String getName () {
    return name;
  } //getName
  
  public int getDay () {
    return dateAndTime.get(Calendar.DATE);
  } //getDay
  
  public int getMonth () {
    int month =  dateAndTime.get(Calendar.MONTH) + 1;
    return month;
  } //getMonth
  
  public int getYear () {
    return dateAndTime.get(Calendar.YEAR);
  } //getYear

  public int getHour () {
    return dateAndTime.get(Calendar.HOUR);
  } //getHour

  public int getMin () {
    return dateAndTime.get(Calendar.MINUTE);
  } //getMin

  public int getSec () {
    return dateAndTime.get(Calendar.SECOND);
  } //getSec

  public float getDistance () {
    return distance;
  }//get distance
  
   public int getStart() { 
     Calendar x = (Calendar) dateAndTime.clone();
     x.add(Calendar.DAY_OF_WEEK, x.getFirstDayOfWeek() - x.get(Calendar.DAY_OF_WEEK));
     //x.add(Calendar.DAY_OF_YEAR, 6);
      return x.get(Calendar.DATE);
   }

  
 

  public String getEntry () {
   String result = getName()+" ran " + getDistance() + " km in "
             +getHour()+":"+getMin()+":"+ getSec() + " on "
             +getDay()+"/"+getMonth()+"/"+getYear()+"\n";
   return result;
  } //getEntry
   
} // Entry
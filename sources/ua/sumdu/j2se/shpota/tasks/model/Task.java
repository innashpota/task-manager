package ua.sumdu.j2se.shpota.tasks.model;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    
    private String title;
    private Date time;
    private Date start;
    private Date end;
    private int interval;
    private boolean active;
    private boolean isRepeated;
    
    //Konstruktor neaktyvnoyi, bez povtorennya zadachi
    public Task(String title, Date time) {
        if (title == null || time == null) {
            throw new IllegalArgumentException("The title and time of the task can not be null.");
        }
        
        this.title = title;
        this.time = time;
        active = false;
        isRepeated = false;    
    }
    
    //Konstruktor neaktyvnoyi, povtoryuvanoyi zadachi
    public Task(String title, Date start, Date end, int interval) {
        if (title == null || start == null || end == null) {
            throw new IllegalArgumentException("Title, Start and end can not be null.");
        }
        if (interval <= 0) {
            throw new IllegalArgumentException("The interval must be positive.");
        }
        if (start.after(end)) {
            throw new IllegalArgumentException("Start must be before end.");
        }
        
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        active = false;
        isRepeated = true;
    }
    
    //Vstanovlennya nazvy zadachi
    public void setTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException("The title of the task can not be null.");
        }
        
        this.title = title;
    }
    
    //Zchytuvannya nazvy zadachi
    public String getTitle() {
        return title;
    }
    
    //Vstanovlennya stanu aktyvnosti zadachi
    public void setActive(boolean active) {
        this.active = active;
    }
    
    //Zchytuvannya stanu aktyvnosti zadachi
    public boolean isActive() {
        return active;
    }
    
    //Vstanovlennya chasu vykonannya dlya zadach, shcho ne povtoryuyut?sya
    public void setTime(Date time) {
        if (time == null) {
            throw new IllegalArgumentException("The time of the task can not be null.");
        }
        
        isRepeated = false;
        start = new Date(time.getTime());
        end = new Date(time.getTime());
        interval = 0;
        this.time = new Date(time.getTime());
    }
    
    //Zchytuvannya chasu vykonannya dlya zadach, shcho ne povtoryuyut?sya
    public Date getTime() {
        if (isRepeated) {
            return new Date(start.getTime());
        } else {
            return new Date(time.getTime());
        }
    }
    
    //Vstanovlennya chasu vykonannya dlya zadach, shcho povtoryuyut?sya
    public void setTime(Date start, Date end, int interval) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Start and end can not be null.");
        }
        if (interval <= 0) {
            throw new IllegalArgumentException("The interval must be positive.");
        }
        if (start.after(end)) {
            throw new IllegalArgumentException("Start must be before end.");
        }
        
        isRepeated = true;
        time = new Date(start.getTime());
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
        this.interval = interval;
    }
    
    //Zchytuvannya chasu vykonannya dlya zadach, shcho povtoryuyut?sya
    public Date getStartTime() {
        if (isRepeated) {
            return new Date(start.getTime());
        } else {
            return new Date(time.getTime());
        }
    }
    
    public Date getEndTime() {
        if (isRepeated) {
            return new Date(end.getTime());
        } else {
            return new Date(time.getTime());
        }
    }
    
    public int getRepeatInterval() {
        if (isRepeated) {
            return interval;
        } else {
            return 0;
        }
    }
    
    //Perevirka povtoryuvanosti zadachi
    public boolean isRepeated() {
        return isRepeated;
    }
    
    //Povertaye chas nastupnoho vykonannya zadachi pislya vkazanoho chasu
    public Date nextTimeAfter(Date current) {
        if (current == null) {
            throw new IllegalArgumentException("Current date can not be null.");
        }
        
        if (!active) {
            return null;
        }
        
        if (!isRepeated) {
            if (current.before(time)) {
                return new Date(time.getTime());
            }
        } else {
            if (current.before(start)) {
                return new Date(start.getTime());
            } else {
                Date thisTime = this.start;
                while (thisTime.before(current) || thisTime.equals(current)) {
                    thisTime = new Date(thisTime.getTime() + interval * 1000);
                }
                
                if (thisTime.before(end) || thisTime.equals(end)) {
                    return new Date(thisTime.getTime());
                }
            }
        }
        
        return null;
    }
    
    @Override
    public String toString() {
        String s;
        
        if (isRepeated) {
            s = "Task title: " + title + ", start time: " + start + ", end time: " +
                end + ", interval: " + interval + ". Task is repeated: " + isRepeated +
                " and is active: " + active + ".";
        } else {
            s = "Task title: " + title + ", time: " + time + ". Task is repeated: " +
                isRepeated + " and is active: " + active + ".";
        }
        
        return s;
    }
    
    @Override
    public int hashCode() {
        int result = 31 * title.hashCode();
        
        if (time != null) {
            result = time.hashCode() + result;
        }
        if (start != null) {
            result = start.hashCode() + result;
        }
        if (end != null) {
            result = end.hashCode() + result;
        }
        
        result = result * interval;
        
        return result;
    }
    
    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject == null) {
            return false;
        }
        if (!getClass().equals(anObject.getClass())) {
            return false;
        }
        
        Task anotherTask = (Task)anObject;
        
        return title.equals(anotherTask.title) &&
                equalDates(time, anotherTask.time) &&
                equalDates(start, anotherTask.start) &&
                equalDates(end, anotherTask.end) &&
                interval == anotherTask.interval &&
                active == anotherTask.active &&
                isRepeated == anotherTask.isRepeated;
    }
    
    private boolean equalDates(Date first, Date second) {
        if (first == null && second == null) {
            return true;
        } else if (first == null || second == null) {
            return false;
        }
        
        return first.equals(second);
    }
}
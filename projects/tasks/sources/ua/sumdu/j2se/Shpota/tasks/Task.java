package ua.sumdu.j2se.shpota.tasks;

public class Task {
    
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;
    private boolean isRepeated;
    
    //Konstruktor neaktyvnoyi, bez povtorennya zadachi
    public Task(String title, int time) {
        if (title == null || time < 0) {
            throw new IllegalArgumentException("The title and time of the task must be specified." + 
                        " Time must be non-negative number.");
        }
        
        this.title = title;
        this.time = time;
        active = false;
        isRepeated = false;    
    }
    
    //Konstruktor neaktyvnoyi, povtoryuvanoyi zadachi
    public Task(String title, int start, int end, int interval) {
        if (title == null || start < 0 || end < 0 || interval <= 0) {
            throw new IllegalArgumentException("The title, start, end and interval of the task must be specified." + 
                        " Start, end and interval must be non-negative number.");
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
            throw new IllegalArgumentException("The title of the task must be specified.");
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
    public void setTime(int time) {
        if (time < 0) {
            throw new IllegalArgumentException("The time of the task must be non-negative number.");
        }
        
        if (isRepeated) {
            isRepeated = false;
            start = time;
            end = time;
            interval = 0;
        }
        
        this.time = time;
    }
    
    //Zchytuvannya chasu vykonannya dlya zadach, shcho ne povtoryuyut?sya
    public int getTime() {
        if (isRepeated) {
            return start;
        } else {
            return time;
        }
    }
    
    //Vstanovlennya chasu vykonannya dlya zadach, shcho povtoryuyut?sya
    public void setTime(int start, int end, int interval) {
        if (start < 0 || end < 0 || interval <= 0) {
            throw new IllegalArgumentException("Start, end and interval must be non-negative number.");
        }
        
        if (!isRepeated) {
            isRepeated = true;
            time = start;
        }
        
        this.start = start;
        this.end = end;
        this.interval = interval;
    }
    
    //Zchytuvannya chasu vykonannya dlya zadach, shcho povtoryuyut?sya
    public int getStartTime() {
        if (isRepeated) {
            return start;
        } else {
            return time;
        }
    }
    
    public int getEndTime() {
        if (isRepeated) {
            return end;
        } else {
            return time;
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
    public int nextTimeAfter(int current) {
        if (current < 0) {
            throw new IllegalArgumentException("Current must be non-negative number.");
        }
        
        if (active) {
            if (isRepeated) {
                if (current < end) {
                    if (current < start) {
                        return start;
                    } else {
                        int numberOfFullRepeats = (current - start) / interval;
                        int thisTime = start + (numberOfFullRepeats + 1) * interval;
                        
                        int allFullRepeats = (end - start) / interval;
                        int endTime = start + allFullRepeats * interval;
                        
                        if (current < endTime) {
                            return thisTime;
                        }
                    }
                }
            } else {
                if (current < time) {
                    return time;
                }
            }
        }
        return -1;
    }
}
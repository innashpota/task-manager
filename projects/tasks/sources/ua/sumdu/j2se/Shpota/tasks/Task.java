package ua.sumdu.j2se.Shpota.tasks;

public class Task {
    //Zminni klasu
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;
    private boolean isRepeated;
    
    //Konstruktor neaktyvnoyi, bez povtorennya zadachi
    public Task(String title, int time) {
        this.title =title;
        this.time = time;
        active = false;
        isRepeated = false;    
    }
    //Konstruktor neaktyvnoyi, povtoryuvanoyi zadachi
    public Task(String title, int start, int end, int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        active = false;
        isRepeated = true;
    }
    //Vstanovlennya nazvy zadachi
    public void setTitle(String title) {
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
        if(isRepeated) {
            isRepeated = false;
        }
        this.time = time;
    }
    //Zchytuvannya chasu vykonannya dlya zadach, shcho ne povtoryuyut?sya
    public int getTime() {
        if(isRepeated) {
            return start;
        } else {
            return time;
        }
    }
    //Vstanovlennya chasu vykonannya dlya zadach, shcho povtoryuyut?sya
    public void setTime(int start, int end, int interval) {
        if(isRepeated) {
            this.start = start;
            this.end = end;
            this.interval = interval;
        } else {
            time = start;
            isRepeated = true;
        }
    }
    //Zchytuvannya chasu vykonannya dlya zadach, shcho povtoryuyut?sya
    public int getStartTime() {
        if(isRepeated) {
            return start;
        } else {
            return time;
        }
    }
    public int getEndTime() {
        if(isRepeated) {
            return end;
        } else {
            return time;
        }
    }
    public int getRepeatInterval() {
        if(isRepeated) {
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
        if(active) {
            if(isRepeated) {
                /*int k = (int)Math.ceil((end - start)/interval);
                for(int i = 0; i < k; i++) {
                    int thisTime = start + i*interval;
                    if(current < thisTime) {
                        return thisTime;
                    }
                }*/
                if (current < end) {
                    if (current < start) {
                        return start;
                    } else {
                        int k = (int)Math.ceil((current - start)/interval);
                        int thisTime = start + (k + 1) * interval;
                        
                        int i = (int)Math.ceil((end - start)/interval);
                        int endTime = start + i * interval;
                        
                        if (current < endTime) {
                            return thisTime;
                        }
                    }
                }
            } else {
                if(current < time) {
                    return time;
                }
            }
        }
        return -1;
    }
}
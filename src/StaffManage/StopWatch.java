package StaffManage;

public class StopWatch {
    private long startTime;
    private long endTime;

    public StopWatch() {
    }

    public StopWatch(long startTime, long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    public long stop() {
        this.endTime = System.currentTimeMillis();
        long time = ((this.endTime - this.startTime) / 1000)/1440;
        return time;
    }
}
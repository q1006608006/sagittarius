package top.ivan.sagittarius.uav.schedule;

public interface ScheduleJob {
    long getTimeout();
    void onJob();
}

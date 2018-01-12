package top.ivan.sagittarius.uav.schedule;

import org.springframework.stereotype.Component;

@Component
public interface ScheduleManager {
    boolean registerJob(ScheduleJob jog);
}

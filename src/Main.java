import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.TriggerBuilder.*;

import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;

public class Main {

	public static void main(String[] args) throws SchedulerException {
		
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		
		sched.start();

		// define the job and tie it to our MyJob class
		JobDetail job = JobBuilder.newJob(MyJob.class)
		    .withIdentity("job1", "group1")
		    .build();

		// Trigger the job to run now, and then repeat every 40 seconds
		CronTrigger trigger = newTrigger()
			.withIdentity("trigger1", "group1")
			.withSchedule(cronSchedule("0 0 17 ? * MON,TUE,WED,THU,FRI *")
					.withMisfireHandlingInstructionFireAndProceed())
			.build();
		
		// Tell quartz to schedule the job using our trigger
		sched.scheduleJob(job, trigger);
		sched.scheduleJob(job, trigger);

	}

}

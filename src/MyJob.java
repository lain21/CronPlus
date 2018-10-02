import java.io.IOException;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements org.quartz.Job {

      public MyJob() {
      }

      public void execute(JobExecutionContext context) throws JobExecutionException {
          
    	try {
  			Runtime.getRuntime().exec("MSG * Termino de jornada");
  		} catch (IOException e) {
  	  		e.printStackTrace();
  		} 
    	  
      }
  }
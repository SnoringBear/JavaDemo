package thread_demo.actor;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
@Setter
public class CrossRankService {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    private CrossRankService(){

    }

    public enum Singleton{
        INSTANCE;

        final CrossRankService crossRankService;

        Singleton(){
            crossRankService = new CrossRankService();
        }

        public CrossRankService getInstance(){
            return crossRankService;
        }

    }


    public void submit(Runnable task){
        executorService.execute(task);
    }

}

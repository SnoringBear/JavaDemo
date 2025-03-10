package actor;

import akka.actor.UntypedAbstractActor;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class Greeter extends UntypedAbstractActor {
    private Logger logger = (Logger) LoggerFactory.getLogger("actor");
    public enum Msg{
        GREET,NONE
    }
    @Override
    public void onReceive(Object message) throws Throwable {
        if(message == Msg.GREET){
            logger.info("hello actor...");
            getSender().tell(Greeter.Msg.GREET,getSelf());
        }else {
            unhandled(message);
        }
    }
}

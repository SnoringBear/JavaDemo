package actor;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class HelloActor extends UntypedAbstractActor {
    private Logger logger = (Logger) LoggerFactory.getLogger("actor");
    ActorRef greeterRef;

    @Override
    public void preStart() throws Exception {
       greeterRef = getContext().actorOf(Props.create(Greeter.class), "greeter");
        logger.info("Greeter Actor path: " + greeterRef.path());
        greeterRef.tell(Greeter.Msg.GREET,getSelf());
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message == Greeter.Msg.NONE){
            getSender().tell(Greeter.Msg.GREET,getSelf());
            getContext().stop(getSelf());
        }else {
            unhandled(message);
        }
    }
}

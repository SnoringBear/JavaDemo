package actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class HelloMainSimple {
    private static Logger logger = (Logger) LoggerFactory.getLogger("actor");
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("HelloMainSimple");
        ActorRef actorRef = actorSystem.actorOf(Props.create(HelloActor.class), "hello");
        logger.info(actorRef.path()+"");
    }
}

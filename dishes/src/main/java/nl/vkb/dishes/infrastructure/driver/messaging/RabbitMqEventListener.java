package nl.vkb.dishes.infrastructure.driver.messaging;
import nl.vkb.dishes.core.service.DishService;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
    private final DishService dishService;

    public RabbitMqEventListener(DishService dishService) {
        this.dishService = dishService;
    }

//    @RabbitListener(queues = "#{'${XXXXXXXX}'}")
//    //TODO set correct queue
//    void listen(ExampleIncommingEvent event) {
//        switch (event.eventKey) {
//            //todo match our project
////            case "keywords.job.added":
////                this.dishService.(
////                        new MatchCandidaes(event.job, event.keyword)
////                );
////                break;
////            case "keywords.job.removed":
////                this.commandHandler.handle(
////                        new UnmatchCandidates(event.job, event.keyword)
////                );
////                break;
//        }
   // }
}
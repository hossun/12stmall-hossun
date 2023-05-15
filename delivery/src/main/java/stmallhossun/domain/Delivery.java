package stmallhossun.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import stmallhossun.DeliveryApplication;
import stmallhossun.domain.DeliveryStated;
import stmallhossun.domain.DevliveryCancelled;

@Entity
@Table(name = "Delivery_table")
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private Long orderId;

    private String productName;

    private Integer qty;

    private Long productId;

    private String status;

    private String courier;

    @PostPersist
    public void onPostPersist() {
        DeliveryStated deliveryStated = new DeliveryStated(this);
        deliveryStated.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
        DevliveryCancelled devliveryCancelled = new DevliveryCancelled(this);
        devliveryCancelled.publishAfterCommit();
    }

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = DeliveryApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    public void completeDelivery(
        CompleteDeliveryCommand completeDeliveryCommand
    ) {
        DeliveryCompleted deliveryCompleted = new DeliveryCompleted(this);
        deliveryCompleted.publishAfterCommit();
    }

    public void returndelivery(ReturndeliveryCommand returndeliveryCommand) {
        DeliveryReturned deliveryReturned = new DeliveryReturned(this);
        deliveryReturned.publishAfterCommit();
    }

    public static void startDelivery(OrderPlaced orderPlaced) {
        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        DeliveryStated deliveryStated = new DeliveryStated(delivery);
        deliveryStated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);

            DeliveryStated deliveryStated = new DeliveryStated(delivery);
            deliveryStated.publishAfterCommit();

         });
        */

    }

    public static void cencelDelivery(OrderCancelled orderCancelled) {
        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        DevliveryCancelled devliveryCancelled = new DevliveryCancelled(delivery);
        devliveryCancelled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderCancelled.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);

            DevliveryCancelled devliveryCancelled = new DevliveryCancelled(delivery);
            devliveryCancelled.publishAfterCommit();

         });
        */

    }
}

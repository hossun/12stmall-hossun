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
        // DeliveryStated deliveryStated = new DeliveryStated(this);
        // deliveryStated.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
        // DevliveryCancelled devliveryCancelled = new DevliveryCancelled(this);
        // devliveryCancelled.publishAfterCommit();
    }

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = DeliveryApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    public void completeDelivery( CompleteDeliveryCommand completeDeliveryCommand) {
        this.setCourier( completeDeliveryCommand.getCourier());
        this.setStatus("DeliveryCompleted");

        DeliveryCompleted deliveryCompleted = new DeliveryCompleted(this);
        deliveryCompleted.publishAfterCommit();
    }

    public void returndelivery(ReturndeliveryCommand returndeliveryCommand) {
        this.setCourier(returndeliveryCommand.getCurier());
        this.setStatus("Return done");
        
        DeliveryReturned deliveryReturned = new DeliveryReturned(this);
        deliveryReturned.publishAfterCommit();
    }

    public static void startDelivery(OrderPlaced orderPlaced) {
        // /** Example 1:  new item 
        Delivery delivery = new Delivery();
        delivery.setOrderId(orderPlaced.getId());
        delivery.setProductId(orderPlaced.getProductId());
        delivery.setProductName(orderPlaced.getProductName());
        delivery.setQty(orderPlaced.getQty());
        delivery.setStatus("DeliveryStated");
        repository().save(delivery);

        DeliveryStated deliveryStated = new DeliveryStated(delivery);
        deliveryStated.publishAfterCommit();
        

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.getId()).ifPresent(delivery->{
            
            delivery // do something
            dele
            repository().save(delivery);

            DeliveryStated deliveryStated = new DeliveryStated(delivery);
            deliveryStated.publishAfterCommit();

         });
        #/

    }

    public static void cencelDelivery(OrderCancelled orderCancelled) {
        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        DevliveryCancelled devliveryCancelled = new DevliveryCancelled(delivery);
        devliveryCancelled.publishAfterCommit();
        */

        // /** Example 2:  finding and process
    
        repository().findByOrderId(orderCancelled.getId()).ifPresent(delivery->{
            
            delivery.setStatus("Order Cancel Done"); // do something
            repository().save(delivery);

            DevliveryCancelled devliveryCancelled = new DevliveryCancelled(delivery);
            devliveryCancelled.publishAfterCommit();

         });
        

    }
}

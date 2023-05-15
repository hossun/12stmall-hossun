package stmallhossun.domain;

import java.util.*;
import lombok.*;
import stmallhossun.domain.*;
import stmallhossun.infra.AbstractEvent;

@Data
@ToString
public class DevliveryCancelled extends AbstractEvent {

    private Long id;
    private String userId;
    private Long orderId;
    private String productName;
    private Integer qty;
    private Long productId;
    private String status;

    public DevliveryCancelled(Delivery aggregate) {
        super(aggregate);
    }

    public DevliveryCancelled() {
        super();
    }
}

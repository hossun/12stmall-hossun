package stmallhossun.domain;

import java.util.*;
import lombok.Data;
import stmallhossun.infra.AbstractEvent;

@Data
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String userId;
    private Long productId;
    private String productName;
    private Integer qty;
}

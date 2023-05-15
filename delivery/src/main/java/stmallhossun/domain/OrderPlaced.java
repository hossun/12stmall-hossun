package stmallhossun.domain;

import java.util.*;
import lombok.*;
import stmallhossun.domain.*;
import stmallhossun.infra.AbstractEvent;

@Data
@ToString
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String userId;
    private Long productId;
    private String productName;
    private Integer qty;
}

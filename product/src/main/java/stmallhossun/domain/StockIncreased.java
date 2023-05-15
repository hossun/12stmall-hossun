package stmallhossun.domain;

import java.util.*;
import lombok.*;
import stmallhossun.domain.*;
import stmallhossun.infra.AbstractEvent;

@Data
@ToString
public class StockIncreased extends AbstractEvent {

    private Long id;
    private String productName;
    private Integer stock;

    public StockIncreased(Inventory aggregate) {
        super(aggregate);
    }

    public StockIncreased() {
        super();
    }
}

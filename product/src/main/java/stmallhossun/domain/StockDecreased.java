package stmallhossun.domain;

import java.util.*;
import lombok.*;
import stmallhossun.domain.*;
import stmallhossun.infra.AbstractEvent;

@Data
@ToString
public class StockDecreased extends AbstractEvent {

    private Long id;
    private String productName;
    private Integer stock;

    public StockDecreased(Inventory aggregate) {
        super(aggregate);
    }

    public StockDecreased() {
        super();
    }
}

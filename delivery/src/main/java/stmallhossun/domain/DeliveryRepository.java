package stmallhossun.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import stmallhossun.domain.*;

@RepositoryRestResource(
    collectionResourceRel = "deliveries",
    path = "deliveries"
)
public interface DeliveryRepository
    extends PagingAndSortingRepository<Delivery, Long> {
        java.util.Optional<Delivery> findByOrderId(Long id) ;
        // java.util.Optional<Delivery> findByOrderId(Long id)
    }

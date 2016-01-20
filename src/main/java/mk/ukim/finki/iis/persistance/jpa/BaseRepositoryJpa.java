package mk.ukim.finki.iis.persistance.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by User on 11/30/2015.
 */
@NoRepositoryBean
public interface BaseRepositoryJpa<T> extends JpaRepository<T, Long>,
        JpaSpecificationExecutor<T> {
}

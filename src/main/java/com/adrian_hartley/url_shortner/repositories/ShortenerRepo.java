package com.adrian_hartley.url_shortner.repositories;

import com.adrian_hartley.url_shortner.models.Shortener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortenerRepo extends CrudRepository<Shortener, Long> {

    /**
     * Find a shortened link by its alias
     *
     * @param alias alias of desired link
     * @return a shortened link
     */

    Optional<Shortener> findByAlias(String alias);

    /**
     * Determine whether a shortened link exists or not
     *
     * @param alias alias of desired link
     * @return true if exists, otherwise false
     */
    Boolean existsByAlias(String alias);
}

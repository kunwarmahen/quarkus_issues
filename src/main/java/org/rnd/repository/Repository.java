package org.rnd.repository;

import java.util.List;

import org.rnd.model.Model;
import org.rnd.model.SearchResult;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Repository implements PanacheRepository<Model> {

    @ConfigProperty(name = "database.page.size")
    Integer pageSize;

    public List<SearchResult> search(Double longitude, Double latitude) {
        return getEntityManager().createQuery(
                "SELECT new org.rnd.model.SearchResult(a, earth_distance(ll_to_earth(a.latitude, a.longitude), ll_to_earth(:latitude, :longitude)) / :conversion AS distance) FROM Model a WHERE earth_distance(ll_to_earth(a.latitude, a.longitude),ll_to_earth(:latitude, :longitude)) < :conversion * :within ORDER BY distance",
                SearchResult.class)
                .setParameter("latitude", latitude)
                .setParameter("longitude", longitude)
                .setParameter("within", 1000)
                .setParameter("conversion", 609.344)
                .setMaxResults(pageSize)
                .setFirstResult(0 * pageSize)
                .getResultList();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gigatronik.ebikediagnose.model;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
/**
 *
 * @author sheiv
 */
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface EBikeRepository extends PagingAndSortingRepository<EBike, Long> {

	List<EBike> findByDisplay(@Param("name") String name);
        List<EBike> findAll ();

}

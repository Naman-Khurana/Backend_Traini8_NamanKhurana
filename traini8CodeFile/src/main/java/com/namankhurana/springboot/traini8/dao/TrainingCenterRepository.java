package com.namankhurana.springboot.traini8.dao;

import com.namankhurana.springboot.traini8.entity.TrainingCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TrainingCenterRepository extends JpaRepository<TrainingCenter,String> {
    Optional<TrainingCenter> findByCenterCode(String centerCode);

    boolean existsByCenterCode(String centerCode);


    @Query("SELECT t FROM TrainingCenter t WHERE " +
            "(:centerName IS NULL OR t.centerName = :centerName) AND " +
            "(:city IS NULL OR t.address.city = :city) AND " +
            "(:centerCode IS NULL OR t.centerCode = :centerCode) AND " +
            "(:state IS NULL OR t.address.state = :state) AND "+
            "(:minCapacity IS NULL OR t.studentCapacity >= :minCapacity) AND " +
            "(:maxCapacity IS NULL OR t.studentCapacity <= :maxCapacity)")
    List<TrainingCenter> filterTrainingCenters(@Param("centerName") String centerName,
                                               @Param("city") String city,
                                               @Param("state") String state,
                                               @Param("centerCode") String centerCode,
                                               @Param("minCapacity") Long minCapacity,
                                               @Param("maxCapacity") Long maxCapacity);
}

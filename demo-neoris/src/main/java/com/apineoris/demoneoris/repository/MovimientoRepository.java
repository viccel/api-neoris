package com.apineoris.demoneoris.repository;

import com.apineoris.demoneoris.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    @Query("SELECT m FROM Movimientos m WHERE m.fecha BETWEEN :startDate AND :endDate")
    List<Movimiento> getReporte(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


}

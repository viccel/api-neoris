package com.apineoris.demoneoris.repository;

import com.apineoris.demoneoris.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import com.apineoris.demoneoris.entity.Cuenta;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
}

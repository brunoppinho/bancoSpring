package tech.ada.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ada.banco.model.Conta;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

    Optional<Conta> findContaByNumeroConta(int numeroConta);
}

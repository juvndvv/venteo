package edu.iescamp.venteoRestApi.users.repositories.dao;

import edu.iescamp.venteoRestApi.users.repositories.entities.Wallet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findWalletByUserId(Long userId);
}

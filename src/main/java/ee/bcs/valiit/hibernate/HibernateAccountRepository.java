package ee.bcs.valiit.hibernate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HibernateAccountRepository extends JpaRepository<Account, String> {
}

package org.una.programmingIII.WikiPets.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.programmingIII.WikiPets.Model.HealthIssue;

@Repository
public interface HealthIssueRepository extends JpaRepository<HealthIssue, Long> {
    HealthIssue findByHealthIssueName(String issueName);
}

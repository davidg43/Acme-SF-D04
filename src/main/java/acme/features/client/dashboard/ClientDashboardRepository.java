
package acme.features.client.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface ClientDashboardRepository extends AbstractRepository {

	@Query("select count(pl) from ProgressLog pl where pl.completeness < 25")
	int totalNumberProgressLogsRateLess25();

	@Query("select count(pl) from ProgressLog pl where pl.completeness between 25 and 50")
	int totalNumberProgressLogsRateBetween25And50();

	@Query("select count(pl) from ProgressLog pl where pl.completeness between 50 and 75")
	int totalNumberProgressLogsRateBetween50And75();

	@Query("select count(pl) from ProgressLog pl where pl.completeness > 75")
	int totalNumberProgressLogsRateMoreThan75();

	@Query("select avg(c.budget.amount) from Contract c")
	double averageBudgetOfContracts();

	@Query("select stddev(c.budget.amount) from Contract c")
	double deviationBudgetOfContracts();

	@Query("select min(c.budget.amount) from Contract c")
	double minimumBudgetOfContracts();

	@Query("select max(c.budget.amount) from Contract c")
	double maximumBudgetOfContracts();

}

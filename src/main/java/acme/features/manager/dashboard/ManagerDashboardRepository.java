
package acme.features.manager.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface ManagerDashboardRepository extends AbstractRepository {

	@Query("select avg(us.estimatedCost) from UserStory us")
	double avgEstimatedCost();

	@Query("select stddev(us.estimatedCost) from UserStory us")
	double deviationEstimatedCost();

	@Query("select max(us.estimatedCost) from UserStory us")
	int maxEstimatedCost();

	@Query("select min(us.estimatedCost) from UserStory us")
	int minEstimatedCost();

	@Query("select count(us) from UserStory us where us.priority = 'MUST'")
	int countMust();

	@Query("select count(us) from UserStory us where us.priority = 'SHOULD'")
	int countShould();

	@Query("select count(us) from UserStory us where us.priority = 'COULD'")
	int countCould();

	@Query("select count(us) from UserStory us where us.priority = 'WONT'")
	int countWont();

	@Query("select avg(p.cost.amount) from Project p")
	double avgProjectCost();

	@Query("select stddev(p.cost.amount) from Project p")
	double deviationProjectCost();

	@Query("select max(p.cost.amount) from Project p")
	double maxProjectCost();

	@Query("select min(p.cost.amount) from Project p")
	double minProjectCost();

}

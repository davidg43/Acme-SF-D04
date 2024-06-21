
package acme.features.manager.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface ManagerDashboardRepository extends AbstractRepository {

	@Query("select avg(us.estimatedCost) from UserStory us WHERE us.manager.id =:id")
	Double avgEstimatedCost(Integer id);

	@Query("select stddev(us.estimatedCost) from UserStory us WHERE us.manager.id =:id")
	Double deviationEstimatedCost(Integer id);

	@Query("select max(us.estimatedCost) from UserStory us WHERE us.manager.id =:id")
	Integer maxEstimatedCost(Integer id);

	@Query("select min(us.estimatedCost) from UserStory us WHERE us.manager.id =:id")
	Integer minEstimatedCost(Integer id);

	@Query("select count(us) from UserStory us where us.priority = 'MUST' AND us.manager.id =:id")
	Integer countMust(Integer id);

	@Query("select count(us) from UserStory us where us.priority = 'SHOULD' AND us.manager.id =:id")
	Integer countShould(Integer id);

	@Query("select count(us) from UserStory us where us.priority = 'COULD' AND us.manager.id =:id")
	Integer countCould(Integer id);

	@Query("select count(us) from UserStory us where us.priority = 'WONT' AND us.manager.id =:id")
	Integer countWont(Integer id);

	@Query("select avg(p.cost.amount) from Project p WHERE p.manager.id =:id")
	Double avgProjectCost(Integer id);

	@Query("select stddev(p.cost.amount) from Project p WHERE p.manager.id =:id")
	Double deviationProjectCost(Integer id);

	@Query("select max(p.cost.amount) from Project p WHERE p.manager.id =:id")
	Double maxProjectCost(Integer id);

	@Query("select min(p.cost.amount) from Project p WHERE p.manager.id =:id")
	Double minProjectCost(Integer id);

}

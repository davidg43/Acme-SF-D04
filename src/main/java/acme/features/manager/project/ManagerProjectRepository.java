
package acme.features.manager.project;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.project.Project;
import acme.entities.project.UserStory;
import acme.roles.Manager;

@Repository
public interface ManagerProjectRepository extends AbstractRepository {

	@Query("select distinct us from Assigment a JOIN a.userStory us WHERE a.project.id = :id")
	Collection<UserStory> findUserStoriesOfAProjectById(int id);

	@Query("select p from Project p where p.manager.id =:id")
	Collection<Project> findProjectsOfAManagerById(int id);

	@Query("select p from Project p where p.id =:id")
	Project findProjectById(int id);

	@Query("SELECT CASE WHEN (COUNT(us) > 0 AND COUNT(us) = COUNT(publishedUs)) THEN true ELSE false END " + "FROM Assigment a " + "JOIN a.userStory us " + "LEFT JOIN UserStory publishedUs ON publishedUs.id = us.id AND publishedUs.isDraft = False "
		+ "WHERE a.project.id =:id " + "GROUP BY a.project.id")
	Boolean isProjectPublishable(int id);

	@Query("select p.manager from Project p where p.id =:id")
	Manager findManagerByProjectId(int id);

	@Query("select m from Manager m where m.id =:id")
	Manager findManagerByManagerId(int id);
}

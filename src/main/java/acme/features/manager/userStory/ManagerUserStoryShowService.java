
package acme.features.manager.userStory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.project.UserStory;
import acme.entities.project.UserStory.Priority;
import acme.roles.Manager;

@Service
public class ManagerUserStoryShowService extends AbstractService<Manager, UserStory> {

	@Autowired
	private ManagerUserStoryRepository uSRepository;


	@Override
	public void authorise() {
		boolean status;
		int masterId;
		UserStory userStory;
		Manager manager;

		masterId = super.getRequest().getData("id", int.class);
		userStory = this.uSRepository.findUserStoryById(masterId);
		manager = this.uSRepository.findManagerByUserStoryId(masterId);
		status = userStory != null && userStory.isDraft() && super.getRequest().getPrincipal().hasRole(Manager.class) && userStory.getManager().equals(manager);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		UserStory userStory = this.uSRepository.findUserStoryById(id);

		super.getBuffer().addData(userStory);
	}

	@Override
	public void unbind(final UserStory userStory) {
		assert userStory != null;
		SelectChoices priorities = SelectChoices.from(Priority.class, userStory.getPriority());

		Dataset dataset;

		dataset = super.unbind(userStory, "title", "description", "estimatedCost", "priority", "acceptanceCriteria", "link", "isDraft");
		dataset.put("priorities", priorities);
		dataset.put("isDraft", userStory.isDraft());
		super.getResponse().addData(dataset);
	}

}

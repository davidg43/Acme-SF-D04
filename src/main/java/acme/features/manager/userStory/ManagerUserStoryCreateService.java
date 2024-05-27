
package acme.features.manager.userStory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.project.UserStory;
import acme.entities.project.UserStory.Priority;
import acme.features.manager.project.ManagerProjectRepository;
import acme.roles.Manager;

@Service
public class ManagerUserStoryCreateService extends AbstractService<Manager, UserStory> {

	@Autowired
	private ManagerUserStoryRepository	uSRepository;

	@Autowired
	private ManagerProjectRepository	projectRepository;


	@Override
	public void authorise() {

		super.getResponse().setAuthorised(super.getRequest().getPrincipal().hasRole(Manager.class));

	}

	@Override
	public void load() {
		Manager manager;

		manager = this.projectRepository.findManagerByManagerId(super.getRequest().getPrincipal().getActiveRoleId());
		UserStory userStory = new UserStory();
		userStory.setDraft(true);
		userStory.setManager(manager);

		super.getBuffer().addData(userStory);
	}

	@Override
	public void bind(final UserStory userStory) {
		assert userStory != null;

		super.bind(userStory, "title", "description", "estimatedCost", "priority", "acceptanceCriteria", "link", "isDraft");
	}

	@Override
	public void validate(final UserStory userStory) {
		assert userStory != null;

	}

	@Override
	public void perform(final UserStory userStory) {
		assert userStory != null;

		this.uSRepository.save(userStory);
	}

	@Override
	public void unbind(final UserStory userStory) {
		assert userStory != null;
		SelectChoices priorities = SelectChoices.from(Priority.class, userStory.getPriority());

		Dataset dataset;

		dataset = super.unbind(userStory, "title", "description", "estimatedCost", "priority", "acceptanceCriteria", "link", "isDraft");

		dataset.put("priorities", priorities.getSelected().getLabel());
		dataset.put("isDraft", userStory.isDraft());
		super.getResponse().addData(dataset);
	}

}


package acme.features.manager.assignment;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.project.Assignment;
import acme.roles.Manager;

@Controller
public class ManagerAssignmentController extends AbstractController<Manager, Assignment> {

	@Autowired
	private ManagerAssignmentShowService	assignmentShow;

	@Autowired
	private ManagerAssignmentListService	assignmentList;

	@Autowired
	private ManagerAssignmentCreateService	assignmentCreate;

	@Autowired
	private ManagerAssignmentUpdateService	assignmentUpdate;

	@Autowired
	private ManagerAssignmentDeleteService	assignmentDelete;


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("show", this.assignmentShow);
		super.addBasicCommand("list", this.assignmentList);
		super.addBasicCommand("create", this.assignmentCreate);
		super.addBasicCommand("update", this.assignmentUpdate);
		super.addBasicCommand("delete", this.assignmentDelete);

	}

}

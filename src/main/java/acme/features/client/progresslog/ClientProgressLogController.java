
package acme.features.client.progresslog;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.contract.ProgressLog;
import acme.features.client.contract.ClientContractPublishService;
import acme.roles.Client;

@Controller
public class ClientProgressLogController extends AbstractController<Client, ProgressLog> {

	@Autowired
	private ClientProgressLogDeleteService	deleteService;

	@Autowired
	private ClientProgressLogCreateService	createService;

	@Autowired
	private ClientProgressLogUpdateService	updateService;

	@Autowired
	private ClientProgressLogShowService	showService;

	@Autowired
	private ClientProgressLogListAllService	listService;

	@Autowired
	private ClientContractPublishService	publishService;


	@PostConstruct
	protected void initialise() {

		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("update", this.updateService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("delete", this.deleteService);
		//super.addCustomCommand("publish", "update", this.publishService);
	}
}

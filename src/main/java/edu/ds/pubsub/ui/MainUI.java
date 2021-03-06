package edu.ds.pubsub.ui;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import edu.ds.pubsub.publish.Publisher;
import edu.ds.pubsub.service.PubSubAdmin;
import edu.ds.pubsub.subscription.IndianPremierLeague;
import edu.ds.pubsub.subscription.PremierLeague;
import edu.ds.pubsub.type.UIMessage;

@Title("PubSub")
@Theme("valo")
@SpringUI
public class MainUI extends UI {

	@Autowired
	private PubSubAdmin admin;

	@Autowired
	private PremierLeague pl;

	@Autowired
	private IndianPremierLeague ipl;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest request) {

		Link compiler = new Link("Java Compiler", new ExternalResource("http://localhost:8000/compiler"));
		Link node1 = new Link("Node 1", new ExternalResource("http://localhost:8000"));

		Link node2 = new Link("Node 2", new ExternalResource("http://localhost:7000"));

		Link node3 = new Link("Node 3", new ExternalResource("http://localhost:8080"));

		VerticalLayout vl = new VerticalLayout(new Label("Publish Subscribe System"));

		VerticalLayout sub1 = new VerticalLayout(new Label("Messages received by EPL"));
		VerticalLayout sub2 = new VerticalLayout(new Label("Messages received by IPL"));

		VerticalLayout publisher = new VerticalLayout(new Label("Messages published so far"));

		// adding publisher UI components.
		Grid<UIMessage> pm = new Grid<>();
		pm.setItems(Publisher.messages);
		pm.addColumn(UIMessage::getMessage);

		Button refresh = new Button("Refresh");
		refresh.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent clickEvent) {

				pm.setItems(Publisher.messages);

			}
		});
		pm.setWidth(100, Unit.PERCENTAGE);
		publisher.addComponent(refresh);
		publisher.addComponent(pm);

		// Adding subscriber UI components.

		Grid<UIMessage> sg1 = new Grid<>();
		sg1.setItems(pl.messages);
		sg1.addColumn(UIMessage::getMessage);

		Button s1refresh = new Button("Refresh");
		s1refresh.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent clickEvent) {

				sg1.setItems(pl.messages);

			}
		});

		Button s1 = new Button("Subscribe");
		s1.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent clickEvent) {

				pl.subscribe();

			}
		});

		sg1.setWidth(100, Unit.PERCENTAGE);
		sub1.addComponent(new HorizontalLayout(s1, s1refresh));
		sub1.addComponent(sg1);

		// Adding subscriber 2 UI components.

		Grid<UIMessage> sg2 = new Grid<>();
		sg2.setItems(ipl.messages);
		sg2.addColumn(UIMessage::getMessage);

		Button s2refresh = new Button("Refresh");
		s2refresh.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent clickEvent) {

				sg2.setItems(ipl.messages);

			}
		});

		Button s2 = new Button("Subscribe");
		s2.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent clickEvent) {

				ipl.subscribe();

			}
		});
		sg2.setWidth(100, Unit.PERCENTAGE);
		sub2.addComponent(new HorizontalLayout(s2, s2refresh));
		sub2.addComponent(sg2);

		// creating the UI page.

		HorizontalLayout hl = new HorizontalLayout();

		hl.setWidth(100, Unit.PERCENTAGE);
		publisher.setWidth(100, Unit.PERCENTAGE);
		sub1.setWidth(100, Unit.PERCENTAGE);
		sub2.setWidth(100, Unit.PERCENTAGE);

		vl.addComponents(compiler, node1, node2, node3);
		hl.addComponents(vl, publisher, sub1, sub2);

		setContent(hl);
	}
}

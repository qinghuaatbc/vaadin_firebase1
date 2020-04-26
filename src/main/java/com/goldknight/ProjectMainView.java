package com.goldknight;

import com.itextpdf.text.DocumentException;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Push
@Route("main")
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = true)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class ProjectMainView extends VerticalLayout implements Serializable {

    ArrayList<Customer> myCustomers;
    ArrayList<Zone> myZoneLists;
    @Autowired MyFirebase myFirebase;

    Grid<Customer> grid = new Grid<Customer>(Customer.class);
    Grid<Zone> grid2 = new Grid<Zone>(Zone.class);

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
       ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
       UI ui = attachEvent.getUI();

             AtomicInteger i= new AtomicInteger();
             scheduledExecutorService.scheduleAtFixedRate(() -> {

//                 if (myCustomers != null)
//                     scheduledExecutorService.shutdown();
                 myCustomers = myFirebase.getCustomer();
                 ui.access(()->{
                     grid.setItems(myCustomers);
                     add(grid);
                     System.out.println(i.get());
                     i.set(i.get() + 1);

                 });
             }, 1, 2, TimeUnit.SECONDS);






    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        super.onDetach(detachEvent);

    }

    public ProjectMainView(@Autowired GreetService service, @Autowired MyFirebase myFirebase) throws InterruptedException, IOException {
        myCustomers =myFirebase.getCustomer();

        addClassName("centered-content");



        grid.setItems(myCustomers);
//        grid.removeColumnByKey("id");
//        grid.setColumns("Address","City");
       add(grid);
       add(grid2);
//       Button pdfCreator = new Button("Create PDF");
//

//
//
//
//
//       pdfCreator.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
//           @Override
//           public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
//               System.out.println(buttonClickEvent.getClientX());
//
//
//               CreatePdf createPdf = new CreatePdf(myZoneLists);
//               try {
//                   createPdf.make();
//               } catch (DocumentException e) {
//                   e.printStackTrace();
//               }
//
//               pdfCreator.getUI().ifPresent(ui ->
//
//                      ui.navigate("pdf"));
//
//
//           }
//       });
//
//
//
//       add(pdfCreator);

        Button pdf = new Button("PDF");

        add(pdf);

       grid.addItemClickListener(new ComponentEventListener<ItemClickEvent<Customer>>() {
           @Override
           public void onComponentEvent(ItemClickEvent<Customer> customerItemClickEvent) {

            myZoneLists = myFirebase.getZoneList(customerItemClickEvent.getItem().id);
         //   add(new Label(customerItemClickEvent.getItem().getAddr_address()));
            if (myZoneLists !=null) {
                grid2.setItems(myZoneLists);
              //  grid2.getDataProvider().refreshAll();
               add(grid2);
            }



               String pid = customerItemClickEvent.getItem().id;
               String pid1 =pid.replaceAll("\\s","");

            pdf.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
                @Override
                public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {


                    CreatePdf createPdf = new CreatePdf(myZoneLists,pid);

               try {
                   createPdf.make();
               } catch (DocumentException e) {
                   e.printStackTrace();
               }

               pdf.getUI().ifPresent(ui ->


                   ui.navigate("pdf/"+pid1));

                }
            });

           }
       });


    }


}

package com.goldknight;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.StreamResource;
import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//@Push
@Route( value = "pdf")


@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class ZoneListPDFView extends VerticalLayout implements HasUrlParameter<String> {
  // private String param;
    EmbeddedPdfDocument pdf;
    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
//        UI ui = attachEvent.getUI();
//        ui.access(()->{
//            scheduledExecutorService.scheduleAtFixedRate(()->{
//                pdf = new EmbeddedPdfDocument("zoneList.pdf");
//                add(pdf);
//
//
//            },1,5, TimeUnit.SECONDS);
//
//
//
//
//        });



    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        super.onDetach(detachEvent);
    }



    public ZoneListPDFView(@Autowired GreetService service) throws InterruptedException, IOException {
        addClassName("centered-content");
        //add(new EmbeddedPdfDocument("Book of Vaadin 14.pdf"));





    }


    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {
      // this.param = s;
        System.out.println("hello param"+s);
        String url = "zoneList"+s+".pdf";
        System.out.println(url);
        removeAll();
        pdf = new EmbeddedPdfDocument(url);


        add(pdf);


        setHeight("100%");
    }
}

package com.goldknight;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

@Push
@Route("movie")

@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MovieMainView extends VerticalLayout implements Serializable {

    ArrayList<Movie> myMovies;

    String url = "http://24.80.132.186:5080/LiveApp/play.html?name=";

    Grid<Movie> grid = new Grid<Movie>(Movie.class);
    IFrame movieFrame = new IFrame();



    public MovieMainView(@Autowired GreetService service, @Autowired MyFirebase myFirebase) throws InterruptedException, IOException {
      myMovies =myFirebase.getMovies();

        addClassName("centered-content");
        movieFrame.setHeight("315px");
        movieFrame.setWidth("560px");
        movieFrame.setAllow("accelerometer;  encrypted-media; gyroscope; picture-in-picture");
        movieFrame.getElement().setAttribute("allowfullscreen", true);
        movieFrame.getElement().setAttribute("frameborder", "0");
        add(movieFrame);
        movieFrame.setVisible(false);


        grid.setItems(myMovies);

        add(grid);



      grid.addItemClickListener(new ComponentEventListener<ItemClickEvent<Movie>>() {
          @Override
          public void onComponentEvent(ItemClickEvent<Movie> movieItemClickEvent) {
              String newUrl=url+movieItemClickEvent.getItem().getStreamID();
             System.out.println(newUrl);
              movieFrame.setSrc(newUrl);
               movieFrame.setVisible(true);

          }
      });







    }


}

package nl.elucidator.homeautomation.web.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by pieter on 3/5/14.
 */
@Path("/status")
public class TimedBeanInfo {


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/beans")
    public String getTimerBeans() {
        return null;
    }
}

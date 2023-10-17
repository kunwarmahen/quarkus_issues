package org.rnd.controller;

import java.util.List;

import org.rnd.model.SearchResult;
import org.rnd.service.Service;
import org.rnd.util.logging.Logged;
import org.jboss.logging.Logger;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.vertx.core.http.HttpServerRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;

@Path("/test")
public class Resource {

    @Inject
    Logger log;

    @Inject
    Service service;

    @Context
    HttpServerRequest request;

    @CheckedTemplate
    public static class Templates {

        public static native TemplateInstance message();

        public static native TemplateInstance message_distance(List<SearchResult> cp);

    }

    @Path("/message")
    @GET
    public TemplateInstance getAll() {
        return Templates.message();
    }

    @Path("/message/{longitude}/{latitude}")
    @GET
    public TemplateInstance getAll(@PathParam("longitude") Double longitude, @PathParam("latitude") Double latitude) {
        List<SearchResult> results = service.search(longitude, latitude);
        return Templates.message_distance(results);
    }

    @POST
    @Logged
    @Path("/create_extension")
    public Response executeQuery() {
        return Response.ok(service
                .executeQuery("create extension if not exists cube;create extension if not exists earthdistance;"))
                .build();
    }

}

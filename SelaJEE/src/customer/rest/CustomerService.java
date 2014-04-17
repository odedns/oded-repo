/*
 * Copyright 2011 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package customer.rest;

import customer.xml.CustomerType;
import customer.xml.ObjectFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;


/**
 * Customer Restful Service with CRUD methods
 * @author markito <william.markito@oracle.com>
 */
@Path("/Customer")
public class CustomerService {
    public static final String DATA_STORE = "CustomerDATA.txt";
    public static final Logger logger = Logger.getLogger(
                CustomerService.class.getCanonicalName());

    /**
     * Get customer XML
     *
     * @param customerId
     * @return CustomerType
     */
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public JAXBElement<CustomerType> getCustomer(
        @PathParam("id")
    String customerId) {
        CustomerType customer = null;

        try {
            customer = findById(customerId);
        } catch (Exception ex) {
            logger.log(
                    Level.SEVERE,
                    "Error calling searchCustomer() for customerId {0}. {1}",
                    new Object[] { customerId, ex.getMessage() });
        }

        return new ObjectFactory().createCustomer(customer);
    }

    /**
     * createCustomer method based on <code>CustomerType</code>
     *
     * @param customer
     * @return Response URI for the Customer added
     * @see CustomerType.java
     */
    @POST
    @Consumes("application/xml")
    public Response createCustomer(CustomerType customer) {
        try {
            long customerId = persist(customer);

            return Response.created(URI.create("/" + customerId))
                           .build();
        } catch (Exception e) {
            throw new WebApplicationException(
                    e,
                    Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update a resource
     *
     * @param customer
     * @return Response URI for the Customer added
     * @see CustomerType.java
     */
    @PUT
    @Path("{id}")
    @Consumes("application/xml")
    public Response updateCustomer(
        @PathParam("id")
    String customerId,
        CustomerType customer) {
        try {
            CustomerType oldCustomer = findById(customerId);

            if (oldCustomer == null) {
                // return a not found in http/web format
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            } else {
                persist(customer);

                return Response.ok()
                               .status(303)
                               .build(); //return a seeOther code
            }
        } catch (Exception e) {
            throw new WebApplicationException(
                    e,
                    Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete a resource
     *
     * @param customerId
     */
    @DELETE
    @Path("{id}")
    public void deleteCustomer(@PathParam("id")
    String customerId) {
        try {
            if (!remove(customerId)) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        } catch (IOException ex) {
            logger.log(
                    Level.SEVERE,
                    "Error calling deleteCustomer() for customerId {0}. {1}",
                    new Object[] { customerId, ex.getMessage() });
        }
    }

    /**
     * Simple persistence method, simulating a JPA behavior based on a
     *   <code>java.util.Properties</code>
     *
     * @param customer
     * @return customerId long
     */
    private long persist(CustomerType customer) throws IOException {
        File dataFile = new File(DATA_STORE);

        // first time execution
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }

        // will override same id
        long customerId = customer.getId();

        Properties properties = new Properties();
        properties.load(new FileInputStream(dataFile));

        properties.setProperty(
                String.valueOf(customerId),
                customer.getFirstName() + "," + customer.getLastName() + ","
                + customer.getCity() + "," + customer.getCountry());

        properties.store(
            new FileOutputStream(DATA_STORE),
            null);

        return customerId;
    }

    /**
     * Simple query method, simulating a JPA behavior based on a
     * <code>java.util.Properties</code>
     *
     * @param customerId
     * @return CustomerType XmlType
     * @throws IOException
     */
    private CustomerType findById(String customerId) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(DATA_STORE));

        String rawData = properties.getProperty(customerId);

        if (rawData != null) {
            final String[] field = rawData.split(",");

            ObjectFactory objFactory = new ObjectFactory();
            CustomerType customer = objFactory.createCustomerType();

            customer.setFirstName(field[0]);
            customer.setLastName(field[1]);
            customer.setCity(field[2]);
            customer.setCountry(field[3]);
            customer.setId(Integer.parseInt(customerId));

            return customer;
        }

        return null;
    }

    /**
     * Simple remove method, simulating a JPA behavior based on a
     * <code>java.util.Properties</code>
     *
     * @param customerId
     * @return boolean
     * @throws IOException
     */
    private boolean remove(String customerId) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(DATA_STORE));

        String rawData = properties.getProperty(customerId);

        if (rawData == null) {
            return false;
        } else {
            properties.remove(customerId);
            properties.store(
                new FileOutputStream(DATA_STORE),
                null);

            return true;
        }
    }
}

package com.cmartin.learn.types;

import java.util.UUID;

public interface VehicleService {

    /**
     * Creates a vehicle
     *
     * @param vehicle vehicle to be created
     * @return vehicle id
     * @throws ServiceException if an operation error occurs
     */
    UUID create(Vehicle vehicle) throws ServiceException;

    /**
     * Retrives a vehicle by its id
     *
     * @param id vehicle id
     * @return the vehicle requested
     * @throws ServiceException if an operation error occurs
     */
    Vehicle read(UUID id) throws ServiceException;

    /**
     * Updates the vehicle information
     *
     * @param vehicle vehicle to be updated
     * @throws ServiceException if an operation error occurs
     */
    void update(Vehicle vehicle) throws ServiceException;

    /**
     * Deletes a vehicle by its id
     *
     * @param id vehicle to be deleted
     * @throws ServiceException if an operation error occurs
     */
    void delete(UUID id) throws ServiceException;
}

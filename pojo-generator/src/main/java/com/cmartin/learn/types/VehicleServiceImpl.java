package com.cmartin.learn.types;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class VehicleServiceImpl implements VehicleService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public UUID create(final Vehicle vehicle) throws ServiceException {
        this.logger.debug(vehicle.toString());
        return null;
    }

    @Override
    public Vehicle read(final UUID id) throws ServiceException {
        this.logger.debug(id.toString());

        return null;
    }

    @Override
    public void update(final Vehicle vehicle) throws ServiceException {
        this.logger.debug(vehicle.toString());
    }

    @Override
    public void delete(final UUID id) throws ServiceException {
        this.logger.debug(id.toString());
    }
}

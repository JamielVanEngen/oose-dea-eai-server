package nl.han.oose.dea.jamielvanengen.datasources.daos;

import nl.han.oose.dea.jamielvanengen.datasources.ConnectionFactory;

import javax.inject.Inject;

public abstract class DAO {
    @Inject
    protected ConnectionFactory connectionFactory;
}

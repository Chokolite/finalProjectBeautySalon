package ua.kharkiv.syvolotskyi.repository.impl;

import ua.kharkiv.syvolotskyi.entity.Catalog;
import ua.kharkiv.syvolotskyi.entity.Master;
import ua.kharkiv.syvolotskyi.entity.Service;
import ua.kharkiv.syvolotskyi.repository.CatalogRepository;
import ua.kharkiv.syvolotskyi.repository.sql.SelectBuilder;
import ua.kharkiv.syvolotskyi.repository.sql.SqlBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CatalogRepositoryImpl implements CatalogRepository {
    private static final String SELECT_ALL = "select c.id c_id," +
            " c.masterId            c_masterId, " +
            "c.serviceId           c_serviceId, " +
            "s.serviceName         s_serviceName, " +
            "s.serviceDuration     s_serviceDuration, " +
            "s.servicePrice        s_servicePrice, " +
            "u.name                u_name, " +
            "(select avg(r.rating) " +
            "from appointment a " +
            "    join review r on a.reviewId = r.id " +
            "         join catalog c on a.catalogId = c.id " +
            "where c.masterId = u.id " +
            "group by c.masterId) avg_rating " +
            "from catalog c, users u, service s where c.masterId = u.id and c.serviceId = s.id ";
    private static final String SELECT_CATALOG_BY_ID = SELECT_ALL + " and c.id=?";
    private static final String INSERT_CATALOG = "insert into catalog (masterId, serviceId) values(?, ?)";
    private static final String DELETE_CATALOG = "delete from catalog where id=?";
    private static final String DEFAULT_ORDER = "u.name,ASC";
    private static final String COUNT_CATALOG = "select COUNT(*) "+
            "from catalog c "+
            "    left join users u ON u.id = c.masterId";

    @Override
    public List<Catalog> getAll(Connection connection, String name, String type, String orderBy, int offset, int size) throws SQLException {
        String masterName = "masterName";
        String serviceName = "serviceName";
        SelectBuilder select = SqlBuilder.select(SELECT_ALL);
        if(Objects.nonNull(type)){
            if (type.equals(masterName)) {
                setNameFilter(name, select);
            }
            if (type.equals(serviceName)) {
                setServiceFilter(name, select);
            }}
        setOrder(orderBy, select);
        select.limit(offset, size);
        List<Catalog> catalogs = new ArrayList<>();
        PreparedStatement statement = select.buildPrepareStatement(connection);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            catalogs.add(convertResultSetToCatalog(resultSet));
        }
        return catalogs;
    }

    @Override
    public Catalog getById(Connection connection, Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_CATALOG_BY_ID);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next() ? convertResultSetToCatalog(resultSet) : null;
    }

    @Override
    public Long getCount(Connection connection, String masterName) throws SQLException {
        SelectBuilder select = SqlBuilder.select(COUNT_CATALOG);
        setNameFilter(masterName, select);
        PreparedStatement statement = select.buildPrepareStatement(connection);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next() ? resultSet.getLong(1) : 0L;
    }

    @Override
    public void delete(Connection connection, Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE_CATALOG);
        statement.setLong(1, id);
        statement.executeUpdate();
    }

    @Override
    public Long save(Connection connection, Catalog catalog) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_CATALOG, PreparedStatement.RETURN_GENERATED_KEYS);
        setAttributeForSave(catalog, statement);
        statement.executeUpdate();
        return getGeneratedId(statement);
    }

    private Catalog convertResultSetToCatalog(ResultSet resultSet) throws SQLException {
        Catalog catalog = new Catalog();
        Master master = new Master();
        Service service = new Service();

        catalog.setId(resultSet.getLong("c_id"));

        master.setId(resultSet.getLong("c_masterId"));
        master.setName(resultSet.getString("u_name"));
        master.setRating(resultSet.getLong("avg_rating"));

        service.setId(resultSet.getLong("c_serviceId"));
        service.setName(resultSet.getString("s_serviceName"));
        service.setServiceDuration(resultSet.getString("s_serviceDuration"));
        service.setServicePrice(resultSet.getLong("s_servicePrice"));


        catalog.setMaster(master);
        catalog.setService(service);

        return catalog;
    }

    private void setAttributeForSave(Catalog catalog, PreparedStatement statement) throws SQLException {
        int count = 1;
        statement.setLong(count++, catalog.getMaster().getId());
        statement.setLong(count, catalog.getService().getId());
    }

    private Long getGeneratedId(PreparedStatement statement) throws SQLException {
        ResultSet generatedKeys = statement.getGeneratedKeys();
        generatedKeys.next();
        return generatedKeys.getLong(1);
    }

    private void setOrder(String orderBy, SelectBuilder select) {
        if (Objects.isNull(orderBy)) {
            orderBy = DEFAULT_ORDER;
        }
        String field = orderBy.split(",")[0];
        boolean ascending = !orderBy.split(",")[1].equals("DESC");
        select.order(field, ascending);
    }

    private void setNameFilter(String name, SelectBuilder select) {
        if (Objects.nonNull(name)) {
            select.and().like(" u.name", name);
        }
    }
    private void setServiceFilter(String name, SelectBuilder select) {
        if(Objects.nonNull(name)){
            select.and().like(" s.serviceName", name);
        }
    }
}

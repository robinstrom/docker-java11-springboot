package se.tre.learning.springbootdocker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AgentJdbcRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class AgentRowMapper implements RowMapper<Agent> {
        @Override
        public Agent mapRow(ResultSet rs, int rowNum) throws SQLException {
            Agent agent = new Agent();
            agent.setId(rs.getLong("id"));
            agent.setFirstName(rs.getString("first_name"));
            agent.setLastName(rs.getString("last_name"));
            return agent;
        }
    }

    public List<Agent> findAll() {
        return jdbcTemplate.query("select * from agents", new AgentRowMapper());
    }

    public Agent findById(long id) {
        return jdbcTemplate.queryForObject("select * from agents where id=?", new Object[] { id },
                new BeanPropertyRowMapper<Agent>(Agent.class));
    }

    public int deleteById(long id) {
        return jdbcTemplate.update("delete from agents where id=?", new Object[] { id });
    }

    public int insert(Agent agent) {
        return jdbcTemplate.update("insert into agents (id, first_name, last_name) " + "values(?, ?, ?)",
                new Object[] { agent.getId(), agent.getFirstName(), agent.getLastName() });
    }

    public int update(Agent agent) {
        return jdbcTemplate.update("update agents " + " set first_name = ?, last_name = ? " + " where id = ?",
                new Object[] { agent.getFirstName(), agent.getLastName(), agent.getId() });
    }
}


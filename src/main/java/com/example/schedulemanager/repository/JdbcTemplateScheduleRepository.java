package com.example.schedulemanager.repository;

import com.example.schedulemanager.dto.ScheduleSaveResponseDto;
import com.example.schedulemanager.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public ScheduleSaveResponseDto saveSchedule(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("content", schedule.getContent());
        parameters.put("author", schedule.getAuthor());
        parameters.put("password", schedule.getPassword());
        parameters.put("created_at", LocalDateTime.now());
        parameters.put("updated_at", LocalDateTime.now());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        return new ScheduleSaveResponseDto(key.longValue(), schedule.getContent(), schedule.getAuthor(), schedule.getCreateDate(), schedule.getModifiedDate());
    }

    @Override
    public List<ScheduleSaveResponseDto> findScheduleByAuthorOrModifiedDate(String author, String modifiedDate) {
        return jdbcTemplate.query(
                "select id, content, author, created_at, updated_at from schedule where author = ? || date(updated_at) = ?  || author != ? || date(updated_at) != ? order by updated_at desc",
                scheduleRowMapper(), author, modifiedDate, author, modifiedDate);
    }

    private RowMapper<ScheduleSaveResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleSaveResponseDto>() {
            @Override
            public ScheduleSaveResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleSaveResponseDto(
                        rs.getLong("id"),
                        rs.getString("content"),
                        rs.getString("author"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                );
            }
        };
    }
}

package com.example.schedulemanager.repository;

import com.example.schedulemanager.common.exception.ScheduleNotFoundException;
import com.example.schedulemanager.dto.SchedulePagingResponseDto;
import com.example.schedulemanager.dto.ScheduleResponseDto;
import com.example.schedulemanager.entity.Member;
import com.example.schedulemanager.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.schedulemanager.common.code.ErrorCode.SCHEDULE_NOT_FOUND_EXCEPTION;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("content", schedule.getContent());
        parameters.put("member_id", schedule.getMemberId());
        parameters.put("password", schedule.getPassword());
        parameters.put("created_at", LocalDateTime.now());
        parameters.put("updated_at", LocalDateTime.now());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        return new ScheduleResponseDto(key.longValue(), schedule.getContent(), schedule.getMemberId(), LocalDateTime.now(), LocalDateTime.now());
    }

    @Override
    public List<ScheduleResponseDto> findScheduleByAuthorOrModifiedDate(Long memberId, String modifiedDate) {
        // query() 메서드는 여러 행을 가져오는 데 사용
        return jdbcTemplate.query(
                "select id, content, member_id, created_at, updated_at from schedule " +
                        "where member_id = ? || date(updated_at) = ?  || member_id != ? || date(updated_at) != ? order by updated_at desc",
                scheduleRowMapper(), memberId, modifiedDate, memberId, modifiedDate);
    }

    @Override
    public Schedule findScheduleByIdOrElseThrow(Long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapperV2(), id);
        return result.stream().findAny().orElseThrow(() -> new ScheduleNotFoundException(SCHEDULE_NOT_FOUND_EXCEPTION));
    }

    @Override
    public List<ScheduleResponseDto> findScheduleByMemberIdOrElseThrow(Long memberId) {
        return jdbcTemplate.query("select * from schedule where member_id = ?", scheduleRowMapper(), memberId);
    }

    @Override
    public Schedule findSchedulePasswordByIdOrElseThrow(Long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapperV3(), id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }

    @Override
    public int updateSchedule(Long id, String content, Long memberId) {
        return jdbcTemplate.update("update schedule set content = ?, member_id = ?, updated_at = ? where id = ?", content, memberId, LocalDateTime.now(), id);
    }

    @Override
    public int deleteSchedule(Long id) {
        return jdbcTemplate.update("delete from schedule where id = ?", id);
    }

    @Override
    public List<SchedulePagingResponseDto> findSchedules(Long offset, int size) {
        return jdbcTemplate.query("select s.id, s.content, m.name, s.created_at, s.updated_at from schedule as s inner join member as m on s.member_id = m.id limit ?, ?", scheduleRowMapperV4(), offset, size);
    }

    @Override
    public int findScheduleSize() {
        // 단일 값을 반환
        return jdbcTemplate.queryForObject("select count(*) from schedule", Integer.class);
    }

    @Override
    public Member findMemberById(Long memberId) {
        List<Member> result = jdbcTemplate.query("select * from member where = ?", memberRowMapperV1(), memberId);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + memberId));
    }


    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("content"),
                        rs.getLong("member_id"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                );
            }
        };
    }

    public RowMapper<Schedule> scheduleRowMapperV2(){
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("content"),
                        rs.getLong("member_id"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                );
            }
        };
    }

    public RowMapper<Schedule> scheduleRowMapperV3(){
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("content"),
                        rs.getLong("member_id"),
                        rs.getString("password"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                );
            }
        };
    }

    public RowMapper<SchedulePagingResponseDto> scheduleRowMapperV4(){
        return new RowMapper<SchedulePagingResponseDto>() {
            @Override
            public SchedulePagingResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new SchedulePagingResponseDto(
                        rs.getLong("id"),
                        rs.getString("content"),
                        rs.getString("name"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                );
            }
        };
    }

    public RowMapper<Member> memberRowMapperV1(){
        return new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Member(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                );
            }
        };
    }
}

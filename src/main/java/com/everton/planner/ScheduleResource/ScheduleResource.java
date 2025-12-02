package com.everton.planner.ScheduleResource;

import com.everton.planner.DayOfWeekEnum.DayOfWeekEnum;
import com.everton.planner.dto.ScheduleEntryDTO;
import com.everton.planner.model.ScheduleEntry;
import io.quarkus.hibernate.orm.panache.Panache;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@Path("/api/schedule")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ScheduleResource {


    @GET
    public List<ScheduleEntryDTO> listAll() {
        return ScheduleEntry.<ScheduleEntry>listAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

    }

    @GET
    @Path("/day/{day}")
    public List<ScheduleEntryDTO> listByDay(@PathParam("day") DayOfWeekEnum day) {
        return ScheduleEntry.<ScheduleEntry>list("dayOfWeek", day)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());


    }

    @POST
    @Transactional
    public Response create(ScheduleEntryDTO dto) {
        ScheduleEntry entry = new ScheduleEntry();
        entry.setDayOfWeek(dto.dayOfWeek);
        entry.setTimeRange(dto.timeRange);
        entry.setDescription(dto.description);
        entry.setCompleted(dto.completed);
        entry.persist();

        return Response
                .created(URI.create("/api/schedule/" + entry.id))
                .entity(toDTO(entry))
                .build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, ScheduleEntryDTO dto) {
        ScheduleEntry entry = ScheduleEntry.findById(id);
        if (entry == null) {
            throw new NotFoundException("Schedule entry not found");
        }

        entry.setDayOfWeek(dto.dayOfWeek);
        entry.setTimeRange(dto.timeRange);
        entry.setDescription(dto.description);
        entry.setCompleted(dto.completed);

        return Response.ok(toDTO(entry)).build();
    }

//    @PATCH
//    @Path("/{id}/toggle")
//    @Transactional
//    public Response toggleCompleted(@PathParam("id") Long id) {
//        ScheduleEntry entry = ScheduleEntry.findById(id);
//        if (entry == null) {
//            throw new NotFoundException("Schedule entry not found");
//        }
//        entry.completed = !entry.completed;
//        return Response.ok(toDTO(entry)).build();
//    }

    @POST
    @Path("/reset")
    @Transactional
    public Response resetAllChecks() {
        Panache.getEntityManager()
                .createQuery("UPDATE ScheduleEntry e SET e.completed = false")
                .executeUpdate();
        return Response.noContent().build();
    }


    private ScheduleEntryDTO toDTO(ScheduleEntry entry) {
        ScheduleEntryDTO dto = new ScheduleEntryDTO();
        dto.id = entry.id;
        dto.dayOfWeek = entry.getDayOfWeek();
        dto.timeRange = entry.getTimeRange();
        dto.description = entry.getDescription();
        dto.completed = entry.isCompleted();
        return dto;
    }


}

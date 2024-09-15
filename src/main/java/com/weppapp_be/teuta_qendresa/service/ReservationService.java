package com.weppapp_be.teuta_qendresa.service;

import com.weppapp_be.teuta_qendresa.dto.ReservationDto;
import com.weppapp_be.teuta_qendresa.dto.UserReservations;
import com.weppapp_be.teuta_qendresa.dto.request.ReservationRequest;
import com.weppapp_be.teuta_qendresa.entity.Email;
import com.weppapp_be.teuta_qendresa.entity.Event;
import com.weppapp_be.teuta_qendresa.entity.Reservation;
import com.weppapp_be.teuta_qendresa.exception.IlegalNumberOfSeatsException;
import com.weppapp_be.teuta_qendresa.exception.ReservationAlreadyExistsException;
import com.weppapp_be.teuta_qendresa.exception.ResourceNotFoundException;
import com.weppapp_be.teuta_qendresa.mapper.ReservationMapper;
import com.weppapp_be.teuta_qendresa.repository.EmailRepository;
import com.weppapp_be.teuta_qendresa.repository.EventRepository;
import com.weppapp_be.teuta_qendresa.repository.ReservationRepository;
import com.weppapp_be.teuta_qendresa.util.ReflectionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final EventRepository eventRepository;
    private final ReservationMapper reservationMapper;
    private final UserService userService;
    private final EmailSenderService emailSenderService;
    private final EmailRepository emailRepository;


    public ReservationDto create(ReservationRequest request) {
        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Event with id %s not found", request.getEventId())));

        validateCapacityAvailability(event, request.getNumberOfPeople());

        Reservation reservation = reservationMapper.toEntity(request);
        reservation.setEvent(event);
        reservation.setCreatedAt(LocalDateTime.now());
        reservation.setCreatedBy(userService.getCurrentUser().getId());
        Reservation reservationInDb = reservationRepository.save(reservation);
        updateEventCapacity(event, request.getNumberOfPeople());
        String SUBJECT_OF_EMAIL = "The Code For Your Reservation";
        emailSenderService.sendEmail(request.getEmail(), SUBJECT_OF_EMAIL,
                generateCodeForReservation(request.getUserId(), request.getEventId()) + " is your reservation code.");
        return reservationMapper.toDto(reservationInDb);
    }

    private void updateEventCapacity(Event event, Long numberOfPeople) {
        long updatedCapacity = event.getCapacity() - numberOfPeople;
        if (updatedCapacity <= 0) {
            throw new IlegalNumberOfSeatsException("Not enough available seats.");
        }
        event.setCapacity(updatedCapacity);
        eventRepository.save(event);
    }

    private void validateCapacityAvailability(Event event, Long numberOfPeople) {
        if (numberOfPeople > event.getCapacity() || numberOfPeople < 1) {
            throw new IlegalNumberOfSeatsException(
                    String.format("Invalid reservation: Requested %d seats, but only %d available.",
                            numberOfPeople, event.getCapacity()));
        }
    }


    public ReservationDto getById(Long id) {
        Reservation reservationInDb = reservationRepository.findById(id, userService.getCurrentUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Reservation with id %s not found", id)));
        return reservationMapper.toDto(reservationInDb);
    }

    public List<ReservationDto> getAll() {
        List<Reservation> reservations = reservationRepository.findAll(userService.getCurrentUser().getId());
        return reservations.stream().map(reservationMapper::toDto).collect(Collectors.toList());
    }

    public List<UserReservations> getAllByUsers() {
        return reservationRepository.getReservationsByUsers(userService.getCurrentUser().getId());
    }

    public ReservationDto update(Long id, Map<String, Object> fields) {
        Reservation reservationInDb = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Reservation with id %s not found", id)));
        fields.forEach((key, value) -> {
            ReflectionUtil.setFieldValue(reservationInDb, key, value);
        });
        return reservationMapper.toDto(reservationRepository.save(reservationInDb));
    }

    public void delete(Long id) {
        Reservation reservationInDb = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Reservation with id %s not found", id)));
        reservationInDb.setDeletedAt(LocalDateTime.now());
        reservationRepository.save(reservationInDb);
    }

    private String generateCodeForReservation(Long userId, Long eventId) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        return userId + "-" + eventId + "-" + timestamp.substring(timestamp.length() - 4);
    }

}

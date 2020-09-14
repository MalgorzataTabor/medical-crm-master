package pl.sda.medicalcrm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class AppointmentDto {

    @SerializedName(value = "appointmentDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", timezone = "Europe/Warsaw")
    private LocalDateTime appointmentDate;

    @SerializedName(value = "userId")
    private Long userId;

    @SerializedName(value = "specializationId")
    private Long specializationId;

    @SerializedName(value = "clinicId")
    private Long clinicId;

    @SerializedName(value = "isOnline")
    private boolean isOnline;

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getSpecializationId() {
        return specializationId;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public boolean isOnline() {
        return isOnline;
    }
}

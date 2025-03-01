
# Hospital Management System

Hospital Management system is a Java application that simulates a simplified healthcare appointment management system. It facilitates the registration of patients and doctors, the management of doctor availability, and the booking, cancellation, and rescheduling of appointments. Patients and doctors can view their respective appointment schedules. The system employs fundamental data structures, including Lists and Sets, for the storage and manipulation of patient, doctor, and appointment data. A menu-driven interface provides user interaction, making it a practical demonstration of object-oriented programming principles and basic data management within a healthcare context.


## Abstract


This Hospital Management System (HMS) is a Java-based application designed to manage patient registration, doctor scheduling, and appointment booking through a menu-driven interface. 

The system leverages fundamental data structures such as Lists, Sets, and Maps to efficiently store and manage data related to patients, doctors, and appointments.It enables patients to register, view their appointments, and book, reschedule, or cancel appointments with doctors. Doctors can manage their availability and view their scheduled appointments.

This implementation serves as a simplified demonstration of using data structures for data management and interaction in a hospital setting.

## Features

Registration: Patients are registered by providing a unique patient ID, along with personal details such as name, email, and contact.

Appointments: Each patient can have multiple appointments. The system stores these appointments and allows patients to view all of their scheduled appointments.

Appointment History: Patients can also view the details of their past and upcoming appointments. 

Registration: Doctors are registered with a doctor ID, name, and specialization 

Availability: Doctors define their available time slots. This allows the system to check if a doctor has available times when a patient requests an appointment.

Slot Management: When an appointment is booked, the system automatically removes the corresponding time slot from the doctor's available slots to prevent overbooking. Once an appointment is cancelled or rescheduled, the slot is made available again.

Cancellation: If a patient needs to cancel an appointment, the system:
Updates the appointment status to Cancelled.
Frees up the previously reserved time slot in the doctor's schedule for other patients.

Patient View: Patients can view their upcoming and past appointments, including the appointment time, status, and appointment ID. They can also check if their appointments have been cancelled or rescheduled.

Doctor View: Doctors can view all their upcoming appointments, including the scheduled times and the patients involved. The system also shows the available time slots that are still open for booking.


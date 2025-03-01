import java.util.*;
class Patient {
private String patientId, name, email, contact;
private List<Appointment> appointments = new ArrayList<>();
public Patient(String patientId, String name, String email, String contact) {
this.patientId = patientId;
 this.name = name;
this.email = email;
this.contact = contact;
}
public void addAppointment(Appointment appointment) { appointments.add(appointment); }
public List<Appointment> getAppointments() { return appointments; }
public String getPatientId() { return patientId; }
}
class Doctor {
private String doctorId, name, specialization;
private Set<String> availableSlots = new HashSet<>();
public Doctor(String doctorId, String name, String specialization) {
this.doctorId = doctorId;
 this.name = name;
this.specialization = specialization;
}
public String getDoctorId() { return doctorId; } // Getter method for doctorId
public void addAvailability(String slot) { availableSlots.add(slot); }
public boolean isSlotAvailable(String slot) { return availableSlots.contains(slot); }
public void removeSlot(String slot) { availableSlots.remove(slot); }
public Set<String> getAvailableSlots() { return availableSlots; }
}
class Appointment {
private String appointmentId, patientId, doctorId, time, status;
public Appointment(String appointmentId, String patientId, String doctorId, String time) {
this.appointmentId = appointmentId;
this.patientId = patientId;
this.doctorId = doctorId;
this.time = time;
this.status = "Booked";
}
public String getAppointmentId() { return appointmentId; }
 
public String getDoctorId() { return doctorId; } // Getter method for doctorId
public void cancel() { status = "Cancelled"; }
 
public void reschedule(String newTime) { this.time = newTime; }
 
public String getStatus() { return status; }
 
public String getTime() { return time; }
}
class HealthcareSystem {
private List<Patient> patients = new ArrayList<>();
private List<Doctor> doctors = new ArrayList<>();
private List<Appointment> appointments = new ArrayList<>();
public void registerPatient(String patientId, String name, String email, String contact) {
patients.add(new Patient(patientId, name, email, contact));
System.out.println("Patient " + name + " registered.");
}
public void registerDoctor(String doctorId, String name, String specialization) {
doctors.add(new Doctor(doctorId, name, specialization));
System.out.println("Doctor " + name + " registered.");
}
public void setDoctorAvailability(String doctorId, String[] slots) {
for (Doctor doctor : doctors) {
if (doctorId.equals(doctor.getDoctorId())) { // Use getter to access doctorId
for (String slot : slots) {
doctor.addAvailability(slot);
}
System.out.println("Availability updated for Doctor " + doctorId);
return;
}
}
System.out.println("Doctor not found.");
}
public boolean bookAppointment(String patientId, String doctorId, String time) {
Patient patient = null;
Doctor doctor = null;
for (Patient p : patients) {
if (p.getPatientId().equals(patientId)) { patient = p; break; }
}
for (Doctor d : doctors) {
if (d.getDoctorId().equals(doctorId)) { doctor = d; break; } // Use getter to access doctorId
}
if (patient == null || doctor == null || !doctor.isSlotAvailable(time)) {
System.out.println("Booking failed.");
return false;
}
Appointment appointment = new Appointment("APT" + (appointments.size() + 1), 
patientId, doctorId, time);
appointments.add(appointment);
patient.addAppointment(appointment);
doctor.removeSlot(time);
System.out.println("Appointment booked.");
return true;
}
public void cancelAppointment(String appointmentId) {
for (Appointment app : appointments) {
if (app.getAppointmentId().equals(appointmentId)) {
app.cancel();
for (Doctor doctor : doctors) {
if (doctor.getDoctorId().equals(app.getDoctorId())) { // Use getter to access doctorId
doctor.addAvailability(app.getTime());
break;
}
}
System.out.println("Appointment cancelled.");
return;
}
}
System.out.println("Appointment not found.");
}
public void rescheduleAppointment(String appointmentId, String newTime) {
for (Appointment app : appointments) {
if (app.getAppointmentId().equals(appointmentId)) {
Doctor doctor = null;
for (Doctor d : doctors) {
if (d.getDoctorId().equals(app.getDoctorId())) { // Use getter to access doctorId
doctor = d;
break;
}
}
if (doctor != null && doctor.isSlotAvailable(newTime)) {
app.reschedule(newTime);
doctor.removeSlot(newTime);
System.out.println("Appointment rescheduled.");
return;
} else {
System.out.println("Slot unavailable.");
return;
}
}
}
System.out.println("Appointment not found.");
}
public void viewAppointmentsForPatient(String patientId) {
for (Patient p : patients) {
if (p.getPatientId().equals(patientId)) {
List<Appointment> patientAppointments = p.getAppointments();
if (patientAppointments.isEmpty()) {
System.out.println("No appointments.");
} else {
for (Appointment app : patientAppointments) {
System.out.println("Appointment ID: " + app.getAppointmentId() + ", Time: " + 
app.getTime() + ", Status: " + app.getStatus());
}
}
return;
}
}
System.out.println("Patient not found.");
}
public void viewAppointmentsForDoctor(String doctorId) {
for (Doctor doctor : doctors) {
if (doctor.getDoctorId().equals(doctorId)) { // Use getter to access doctorId
Set<String> slots = doctor.getAvailableSlots();
if (slots.isEmpty()) {
System.out.println("No available slots.");
} else {
System.out.println("Available slots: ");
for (String slot : slots) {
System.out.println(slot);
}
}
return;
}
}
System.out.println("Doctor not found.");
}
}
public class Main {
public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);
HealthcareSystem system = new HealthcareSystem();
while (true) {
System.out.println("\nHealthcare System");
System.out.println("1. Register Patient");
System.out.println("2. Register Doctor");
System.out.println("3. Set Doctor Availability");
System.out.println("4. Book Appointment");
System.out.println("5. Cancel Appointment");
System.out.println("6. Reschedule Appointment");
System.out.println("7. View Appointments for Patient");
System.out.println("8. View Appointments for Doctor");
System.out.println("9. Exit");
System.out.print("Choose an option: ");
int choice = scanner.nextInt();
scanner.nextLine();
switch (choice) {
case 1:
System.out.print("Enter Patient ID: ");
String patientId = scanner.nextLine();
System.out.print("Enter Patient Name: ");
String patientName = scanner.nextLine();
System.out.print("Enter Patient Email: ");
String patientEmail = scanner.nextLine();
System.out.print("Enter Patient Contact: ");
String patientContact = scanner.nextLine();
system.registerPatient(patientId, patientName, patientEmail, patientContact);
break;
case 2:
System.out.print("Enter Doctor ID: ");
String doctorId = scanner.nextLine();
System.out.print("Enter Doctor Name: ");
String doctorName = scanner.nextLine();
System.out.print("Enter Doctor Specialization: ");
String specialization = scanner.nextLine();
system.registerDoctor(doctorId, doctorName, specialization);
break;
case 3:
System.out.print("Enter Doctor ID: ");
String docId = scanner.nextLine();
System.out.print("Enter Slots (comma-separated): ");
String slotsInput = scanner.nextLine();
String[] slots = slotsInput.split(",");
system.setDoctorAvailability(docId, slots);
break;
case 4:
System.out.print("Enter Patient ID: ");
String pId = scanner.nextLine();
System.out.print("Enter Doctor ID: ");
String dId = scanner.nextLine();
System.out.print("Enter Appointment Time: ");
String time = scanner.nextLine();
system.bookAppointment(pId, dId, time);
break;
case 5:
System.out.print("Enter Appointment ID to cancel: ");
String appointmentId = scanner.nextLine();
system.cancelAppointment(appointmentId);
break;
case 6:
System.out.print("Enter Appointment ID to reschedule: ");
String aptId = scanner.nextLine();
System.out.print("Enter New Appointment Time: ");
String newTime = scanner.nextLine();
system.rescheduleAppointment(aptId, newTime);
break;
case 7:
System.out.print("Enter Patient ID: ");
String viewPatientId = scanner.nextLine();
system.viewAppointmentsForPatient(viewPatientId);
break;
case 8:
System.out.print("Enter Doctor ID: ");
String viewDoctorId = scanner.nextLine();
system.viewAppointmentsForDoctor(viewDoctorId);
break;
case 9:
System.out.println("Exiting system...");
scanner.close();
return;
default:
System.out.println("Invalid choice. Try again.");
}
}
}
}
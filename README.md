# A1-username — PROG2004 Assessment 1

A simple clinic appointment system written in Java. The system models health
professionals of several types and manages appointments booked for the current
day. There is no user interface; the `main` method in `AssignmentOne` runs a
short scripted demonstration that prints results to the console.

> Rename this project folder and the submitted zip to your own MySCU username,
> for example `A1-lfolkman`.

## How to run

From the project root, compile and run with the JDK:

```
javac -d out src/*.java
java -cp out AssignmentOne
```

Alternatively open the `src` folder in your IDE and run `AssignmentOne`.

## Source files

| File | Part | Responsibility |
|------|------|----------------|
| `HealthProfessional.java` | Part 1 | Abstract base class for every professional: ID, name, working days, validation, controlled access, `equals`/`hashCode`, `toString`. |
| `GeneralPractitioner.java` | Part 2 | GP subtype with years of experience. |
| `Specialist.java` | Part 2 | Specialist subtype with a sub-specialty. |
| `Nurse.java` | Part 2 | Nurse subtype with an assigned ward. |
| `Dietitian.java` | Part 2 | Dietitian subtype with a nutrition focus area. |
| `Patient.java` | Part 4 | Patient record: name and validated mobile number. |
| `Appointment.java` | Part 4 | Links a patient, a professional and a schedule slot; implements `Comparable` for time ordering. |
| `ClinicSchedule.java` | Part 5 | Fixed daily slots from 08:00 to 16:00 in 30-minute intervals, kept in one place so the schedule can be changed easily. |
| `AppointmentManager.java` | Part 5 | Adds, filters, sorts and cancels appointments; rejects double bookings and returns a warning message when an action cannot be performed. |
| `AssignmentOne.java` | Parts 3, 6 | Builds the demonstration objects and exercises the manager, including rejected bookings and cancellations. |

## The four pillars of OOP (video notes)

Use this mapping when recording the explanation video.

- **Encapsulation** — Fields are `private` and exposed only through getters.
  Constructor and setter checks keep an object out of an invalid state, and
  working-day arrays are copied so external code cannot change the internal
  list. See `HealthProfessional`, `Patient` and `Appointment`.

- **Inheritance** — `GeneralPractitioner`, `Specialist`, `Nurse` and
  `Dietitian` extend `HealthProfessional`, reusing the shared ID, name and
  working-day code instead of repeating it. Subclasses call `super(...)` to set
  up the shared details.

- **Polymorphism** — Professionals are stored together in an
  `ArrayList<HealthProfessional>`. Each object answers `getSpecialty()`,
  `describeRole()` and `toString()` in its own way, so the same loop can
  process every subtype. `Appointment` also compares through `Comparable` so a
  list can be sorted by time.

- **Abstraction** — `HealthProfessional` is `abstract`, so a plain professional
  with no specialty cannot be created. The abstract methods `getSpecialty()`
  and `describeRole()` force every subtype to provide its own role details.
  `ClinicSchedule` hides the slot list behind simple helper methods.

## Submission checklist

- [ ] Rename the project and zip file to `A1-<your-username>.zip`.
- [ ] Push all source files to the GitHub repository and invite the tutor and
      unit assessor as collaborators if the repo is private.
- [ ] Attach the WakaTime time-tracking report (`A1-<your-username>-WakaTime`).
- [ ] Upload the explanation video to SCU OneDrive and add the shareable link.
- [ ] Add the GitHub link and video link in the submission comments.
- [ ] Include the GenAI declaration in the submission comments.

## GenAI declaration

Choose the statement that matches what you actually did.

If you DID use GenAI:

> I acknowledge that I have used Generative AI tools to complete this assessment
> in accordance with the conditions outlined in the Assessment Brief. I used
> [insert name of GenAI tool(s)] to [briefly describe how the tool(s) were used,
> e.g. generate ideas, check grammar, organise structure].

If you DID NOT use GenAI:

> I acknowledge that I have not knowingly used GenAI to complete this assessment.

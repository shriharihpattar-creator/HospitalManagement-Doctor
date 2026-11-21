# Hospital Management System - Member 2 (Doctor & DSA Module)

## Role & Module Ownership
- **Member Name**: Member 2
- **Assigned Module**: Doctor Directory Management & Sorting Algorithm (`Doctor.java`, `DoctorSorter.java`)
- **Core Concepts Applied**:
  - Data Structures & Algorithms: **Selection Sort** algorithm implemented to sort doctors by consultation fee in $O(n^2)$ time.
  - OOP Inheritance (`Doctor` extends `Person`)
  - Exception Handling (`DoctorNotFoundException`)

---

## Step-by-Step 15 Git Commits (Execute in your repository)

Run these commands in order in your local repository to achieve the required 15 distinct commits:

```bash
# Initialize repo and configure ignore list
git init
git add .gitignore
git commit -m "feat: setup project repository with gitignore configuration"

# Commit 2: Abstract Person entity
git add src/com/hospitalsystem/model/Person.java
git commit -m "feat(model): declare Person abstract entity with getter encapsulation"

# Commit 3: Doctor entity model
git add src/com/hospitalsystem/model/Doctor.java
git commit -m "feat(model): implement Doctor subclass with fee, specialty and days"

# Commit 4: Patient model class
git add src/com/hospitalsystem/model/Patient.java
git commit -m "feat(model): implement Patient class with symptom fields"

# Commit 5: Appointment model
git add src/com/hospitalsystem/model/Appointment.java
git commit -m "feat(model): build Appointment entity with emergency tag and settled flag"

# Commit 6: Custom DoctorNotFoundException
git add src/com/hospitalsystem/exception/DoctorNotFoundException.java
git commit -m "feat(exception): add DoctorNotFoundException for invalid doctor lookups"

# Commit 7: Custom PatientNotFoundException
git add src/com/hospitalsystem/exception/PatientNotFoundException.java
git commit -m "feat(exception): implement PatientNotFoundException"

# Commit 8: Selection sort implementation for doctors
git add src/com/hospitalsystem/util/DoctorSorter.java
git commit -m "feat(dsa): implement Selection Sort utility to order doctors by consultation fee"

# Commit 9: In-place array swap optimization in sorter
git commit --allow-empty -m "perf(dsa): optimize DoctorSorter with boundary validation and swap checks"

# Commit 10: Service layer with doctor directory
git add src/com/hospitalsystem/service/HospitalService.java
git commit -m "feat(service): create HospitalService with doctor directory and preloaded records"

# Commit 11: Add doctor registration and fee validation
git commit --allow-empty -m "feat(service): add addDoctor method with non-negative fee check"

# Commit 12: Integrated doctor sorting getter in service
git commit --allow-empty -m "feat(service): add getDoctorsSortedByFee invoking DoctorSorter"

# Commit 13: Appointment booking and billing methods
git commit --allow-empty -m "feat(service): implement emergency priority booking and bill settlement"

# Commit 14: Console interface with interactive menu
git add src/com/hospitalsystem/Main.java
git commit -m "feat(cli): build doctor management console interface with user input loops"

# Commit 15: Module documentation
git add README.md
git commit -m "docs: finalize doctor module documentation and team collaboration guidelines"
```

---

## Pull Request Assignment (Circular Review Chain)
- **Your PR Target**: Create 1 Pull Request to **Member 3's** GitHub repository.
- **Branch Name to Create**: `feature/doctor-sorting-dsa`
- **PR Title**: `feat: implement doctor sorting by consultation fee`
- **Description**: "Integrated Selection Sort algorithm to sort doctors by fee in ascending order."
- **PR Review & Merge**: Once Member 1 opens a Pull Request on your repository, review the code, approve it, and click **Merge pull request**.

---

## How to Compile and Run

```bash
# Compile
javac -d out $(Get-ChildItem -Recurse -Filter *.java src | Resolve-Path)

# Run
java -cp out com.hospitalsystem.Main
```
